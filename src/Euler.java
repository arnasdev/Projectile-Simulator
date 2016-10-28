// Euler's Method
// String variable is carried from the beginning to the end of the function, methods add and return this string
public class Euler {
	// Starting time, increments by stepsize
	private float time;
	// Below variables hold data for whatever the current time is
	private Vector3 position;
	private Vector3 velocity;
	private Vector3 acceleration;
	private float stepSize;
	private Object projectile;
	
	public Euler(float stepSize, Object projectile, float time) {
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
		
		output += InitialConditions();
		for(int i = 1; i < steps; i++){
			
			
			projectile.setPosition(position);
			projectile.setVelocity(velocity);
			acceleration = projectile.updateForces();
			
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
		
		int i = 0;
		float kPos = projectile.getPosition().getValue()[2];
		float groundPos = projectile.getRadius();
		
		// While position is greater than radius
		while(kPos > groundPos){
			// keeps setting the position and velocity of the projectile as it follows trajectory
			projectile.setPosition(position);
			projectile.setVelocity(velocity);
			// acceleration is calculated
			acceleration = projectile.CalculateAcceleration();
			
			i++;
			output += CalculateStep(i);
			
			kPos = position.getValue()[2];
		}
		return output;
	}
	
	// Calculates one block of the method
	private String CalculateStep(int i) {
		String output = "";
		
		// pos 1 = pos 0 + (stepsize * vel 0)
		// vel 1 = vel 0 + (stepsize * acc 0)
		output += ("\n------------------------ TIME " + (float)(time+stepSize*i) + " ------------------------\n");
		Vector3 newPos = Vector3.Add(position, Vector3.Multiply(velocity, stepSize));
		Vector3 newVel = Vector3.Add(velocity, Vector3.Multiply(acceleration, stepSize));
		Vector3 newAcc = acceleration;
		
		position = newPos;
		velocity = newVel;
		acceleration = newAcc;
		
		output += ("Position:\t" + position + " m\n");
		output += ("Velocity:\t" + velocity + " ms\n");
		output += ("Acceleration:\t" + acceleration + " ms²\n");
		
		return output;
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
