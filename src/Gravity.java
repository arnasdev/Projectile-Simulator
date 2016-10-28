
public class Gravity extends Force {
	private float mass, acceleration;
	private Vector3 direction = new Vector3(0, 0, -1);
	
	public Gravity(float mass, float acceleration){
		super(
				Vector3.Multiply(new Vector3(0, 0, -1), (mass * acceleration))
			  );		
		this.mass = mass;
		this.acceleration = acceleration;
	}
	
	// Calculates the force
	private Vector3 calculateForce(){
		this.value = Vector3.Multiply(direction, (mass * acceleration));
		return this.value;
	}
	
	// Getters & Setters
	// Gravity is a constant acceleration
	public Vector3 getValue() {
		return this.value;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
		calculateForce();
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
		calculateForce();
	}

	public Vector3 getDirection() {
		return direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
		calculateForce();
	}
	// End
}

