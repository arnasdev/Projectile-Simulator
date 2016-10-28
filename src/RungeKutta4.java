public class RungeKutta4 
{
		// Starting time, increments by stepsize
		private float time;
		// Below variables hold data for whatever the current time is
		private Vector3 position;
		private Vector3 velocity;
		private Vector3 acceleration;
		private float stepSize;
		private Object projectile;
		
		public RungeKutta4(float stepSize, Object projectile, float time) {
			this.time = time;
			this.position = projectile.getPosition();
			this.velocity = projectile.getVelocity();
			this.acceleration = projectile.getAcceleration();
			this.stepSize = stepSize;
			this.projectile = projectile;
		}

		// Method for no parameter
		public String InitiateWithSteps(){
			String output = "";
			output += InitiateWithSteps(20);
			return output;
		}
		
		// Method for steps passed as parameter
		public String InitiateWithSteps(int steps){
			byte hasHitFloor = 0;
			String output = "";
			
			projectile.setPosition(position);
			projectile.setVelocity(velocity);
			acceleration = projectile.updateForces();
			
			output += InitialConditions();
			for(int i = 1; i < steps; i++){
				// If position is less than radius
				if(projectile.getPosition().getValue()[2] < projectile.getRadius() && hasHitFloor == 0){
					output += ("Projectile has hit floor!");
					hasHitFloor = 1;
				}
				
				output += CalculateStep(i);
			}
			return output;
		}
		
		// Method that stops when the object hits the floor
		public String InitiateHitsFloor(){
			String output = "";
			output += InitialConditions();
			
			int i = 1;
			float kPos = projectile.getPosition().getValue()[2];
			float groundPos = projectile.getRadius();
			
			// While position is greater than radius
			while(kPos > groundPos){
				// keeps setting the position and velocity of the projectile as it follows trajectory
				projectile.setPosition(position);
				projectile.setVelocity(velocity);
				// acceleration is calculated
				acceleration = projectile.CalculateAcceleration();
				
				
				output += CalculateStep(i);
				
				kPos = position.getValue()[2];
				i++;
			}
			
			return output;
		}
		
		// Calculates one block of the method
		private String CalculateStep(int i) {
			String output = "";
			Vector3 p1 = position;
			Vector3 v1 = velocity;
			Vector3 a1 = acceleration;
			
			Vector3 p2 = Vector3.Add(position, Vector3.Multiply(Vector3.Multiply(v1, stepSize), 0.5f));
			Vector3 v2 = Vector3.Add(velocity, Vector3.Multiply(Vector3.Multiply(a1, stepSize), 0.5f));
			Vector3 a2 = acceleration(p2, v2);
		
			Vector3 p3 = Vector3.Add(position, Vector3.Multiply(Vector3.Multiply(v2, stepSize), 0.5f));
			Vector3 v3 = Vector3.Add(velocity, Vector3.Multiply(Vector3.Multiply(a2, stepSize), 0.5f));
			Vector3 a3 = acceleration(p3, v3);

			Vector3 p4 = Vector3.Add(position, Vector3.Multiply(Vector3.Multiply(v3, stepSize), 1f));
			Vector3 v4 = Vector3.Add(velocity, Vector3.Multiply(Vector3.Multiply(a3, stepSize), 1f));
			Vector3 a4 = acceleration(p4, v4);
			
			Vector3 pNext = Vector3.Add(p1, Vector3.Multiply(Vector3.Add(v1, Vector3.Multiply(v2, 2), Vector3.Multiply(v3, 2), v4), stepSize * 0.1666666f));
			Vector3 vNext = Vector3.Add(v1, Vector3.Multiply(Vector3.Add(a1, Vector3.Multiply(a2, 2), Vector3.Multiply(a3, 2), a4), stepSize * 0.1666666f));
			Vector3 aNext = acceleration(pNext, vNext);
			
			this.position = pNext;
			this.velocity = vNext;
			this.acceleration = aNext;
			
			output += ("\n------------------------ TIME " + (float)(time+stepSize*i) + " ------------------------\n");
			output += ("Position:\t" + projectile.getPosition() + " m\n");
			output += ("Velocity:\t" + projectile.getVelocity() + " ms\n");
			output += ("Acceleration:\t" + a1 + " ms²\n");
		
			return output;
		}

		public Vector3 acceleration(Vector3 p, Vector3 v){
			projectile.setPosition(p);
			projectile.setVelocity(v);
			projectile.reduceSpin();
			projectile.updateForces();
			Vector3 a = projectile.CalculateAcceleration();
			return a;
		}
		
		private String InitialConditions(){
			String output = "";
			output += ("------------------------ INITIAL CONDITIONS ------------------------\n");
			output += ("Position:\t" + position + " m\n");
			output += ("Velocity:\t" + velocity + " ms\n");
			output += ("Acceleration:\t" + acceleration + " ms²\n");
			return output;
		}
		
	}