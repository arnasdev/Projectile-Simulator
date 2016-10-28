
public class Magnus extends Force {
	private float radius, density;
	private Vector3 spin, velocity;
	
	// Magnus force formula
	public Magnus(float radius, float density, Vector3 spin, Vector3 velocity) {
		super(
			  Vector3.Multiply(Vector3.Multiply(spin, velocity), .5f * density *(float)Math.PI * radius * radius * radius * spin.getMagnitude() * velocity.getMagnitude() * (1/Vector3.Multiply(spin, velocity).getMagnitude()))
			 );
		this.radius = radius;
		this.density = density;
		this.spin = spin;
		this.velocity = velocity;
	}
	
	// Calculating force
	private Vector3 calculateForce(){
		Vector3 force = (Vector3.Multiply(Vector3.Multiply(spin, velocity), (1 / (Vector3.Multiply(spin, velocity)).getMagnitude())));
		this.value = force;
		return force;	
	}
	
	// Getters & Setters
	// Each setter recalculates the net force for changing variables
	public Vector3 getValue() {
		return this.value;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		calculateForce();
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
		calculateForce();
	}

	public Vector3 getSpin() {
		return spin;
	}

	public void setSpin(Vector3 spin) {
		this.spin = spin;
		calculateForce();
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
		calculateForce();
	}
	
	

}
