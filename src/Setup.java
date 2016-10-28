// TODO ADD TIME

// Class which takes data from the input fields, converts it to objects and begins euler's
import java.util.ArrayList;

public class Setup {

	public Setup(){
	}
	

	public String start(float mass, float radius, float dragCoefficient, Vector3 position, Vector3 velocity, Vector3 acceleration, Vector3 spin,
			float densityF, float viscosity, Vector3 flow, float grav, int gr, int dr, int ma, int method, float stepSize, int steps, int ground) {

		// Creating fluid
		Fluid water = new Fluid(densityF, viscosity, flow);
		
		// Getting apparent velocity at time 0
		Vector3 apparentVelocity = Vector3.Subtract(velocity, flow);
		
		// Forces
		Gravity g = new Gravity(mass, grav);
		Drag d = new Drag(apparentVelocity, radius, viscosity, densityF, dragCoefficient);
		Magnus m = new Magnus(radius, densityF, spin, apparentVelocity);
		// List of forces
		ArrayList<Force> fList = new ArrayList<Force>();
		
		// Checking which forces were selected
		if(gr == 1){
			fList.add(g);
		}
		if(dr == 1){
			fList.add(d);
		}
		if(ma == 1){
			fList.add(m);
		}
		
		// Projectile
		Object ball = new Object(mass, radius, position, velocity, acceleration, fList, water, spin);
		
		/**
		 *	Test Data
		 **/
		
		// Object
		//mass = 4.08407f;
		//radius = .5f;
		//dragCoefficient = .1f;
		
		//position = new Vector3(2, -3, 6);
		//velocity = new Vector3(-5, 14, 2);
		//acceleration = new Vector3(0, 0, 0);
		//spin = new Vector3((float)10/-3, (float)5/-3, (float)10/3);
		
		// Fluid
		//densityF = 80;
		//viscosity = 1f;
		//flow = new Vector3(2, 3, 0);

		//Vector3 acc = ball.CalculateAcceleration();
		//Vector3 newtons = ball.getNetForce();
		
		//System.out.println(d.calculateForce());
		//System.out.println(newtons);
		
		//System.out.println(acc);
		
		String output = "";
		
		// If using Euler's or RK4
		if(method == 1){
			Euler c = new Euler(stepSize, ball, 0);
			if(ground == 1){
				output = c.InitiateHitsFloor();
			}
			else{
				output = c.InitiateWithSteps(steps);
			}
			
			
		}
		else if(method == 2){
			RungeKutta4 c = new RungeKutta4(stepSize, ball, 0);
			if(ground == 1){
				output = c.InitiateHitsFloor();
			}
			else{
				output = c.InitiateWithSteps(steps);
			}
		}
		else{
			output = "ERROR IN METHOD SELECTION";
		}
		
		
		// Returns data
		return output;
	}

}
