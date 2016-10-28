import java.util.ArrayList;

public class Object {
	// Object
	private float mass;
	private float radius;

	// Initial Conditions
	private Vector3 position;
	private Vector3 velocity;
	private Vector3 acceleration;
	private Vector3 initialAcceleration;
	private Vector3 apparentVelocity;
	private Vector3 spin;
	
	// Fluid
	private Fluid fluid;
	
	// List of <Forces>
	private ArrayList<Force> forces;
	
	// Holds a value to determine 
	
	// Constructor taking in all information to do with an object
	public Object(float mass, float radius, Vector3 position, Vector3 velocity, Vector3 acceleration, ArrayList<Force> forces, Fluid fluid, Vector3 spin) {
		this.mass = mass;
		this.radius = radius;
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.initialAcceleration = acceleration;
		this.forces = forces;
		this.fluid = fluid;
		this.spin = spin;
		this.apparentVelocity = ApparentVelocity();
	}

	// Blank object
	public Object(){
	}
	
	/**
	 *	Below methods are for calculating various attributes of the object	 
	 **/
	
	// Returns acceleration of the object in Vector form by using acceleration formula + initial acceleration
	// A = 1/M * Fnet
	public Vector3 CalculateAcceleration(){
		Vector3 netForce = this.getNetForce();
		Vector3 accelerationFromForce = Vector3.Multiply(netForce, 1/mass); 
		return Vector3.Add(initialAcceleration, accelerationFromForce);
	}

	// This method is called while the simulation is taking place, recalculating the net forces due to changing apparent velocity
	public Vector3 updateForces(){
		// Updates objects apparent velocity
		this.ApparentVelocity();
		
		for(Force f : forces) {
			// Gravity's acceleration is non changing
			if(f instanceof Drag){
				((Drag) f).setVelocity(apparentVelocity);
			}
			else if(f instanceof Magnus){
				((Magnus) f).setVelocity(apparentVelocity);
				((Magnus) f).setSpin(spin);
			}
		}
		Vector3 newAcceleration = this.CalculateAcceleration();
		this.setAcceleration(newAcceleration);
		return newAcceleration;
	}
	
	// Returns fNet, measured in Newtons
	public Vector3 getNetForce() {
		Vector3 netForce = new Vector3(0, 0, 0);
		
		for(Force f : forces) {
			netForce = Vector3.Add(netForce, f.value);	
		}
		
		return netForce;
	}

	// Apparent Velocity
	public Vector3 ApparentVelocity(){
		Vector3 apparentVelocity = Vector3.Subtract(velocity, fluid.getFlow());
		this.apparentVelocity = apparentVelocity;
		return apparentVelocity;
	}
	
	// Called at every iteration of RK4/Eulers
	public Vector3 reduceSpin(){
		Vector3 newSpin = Vector3.Multiply(this.spin, .9999f);
		this.spin = newSpin;
		return newSpin;
	}
	/** 
	 *	Getters & Setters
	 **/
	
	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public Vector3 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector3 acceleration) {
		this.acceleration = acceleration;
	}

	public Fluid getFluid() {
		return fluid;
	}

	public void setFluid(Fluid fluid) {
		this.fluid = fluid;
	}

	public ArrayList<Force> getForces() {
		return forces;
	}

	public void setForces(ArrayList<Force> forces) {
		this.forces = forces;
	}
	
}
