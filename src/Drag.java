
public class Drag extends Force{ 
	private Vector3 velocity;
	private float radius, viscosity, density, dragCoefficient;
	
	// Re = ((density * velocity.getMagnitude() * (radius*2)) / viscosity); // -1/2 * 0.5 * Re * density * Math.PI * (radius*radius) * velocity.getMagnitude() // pressure: Vector3.Multiply(velocity,-1/2 * dragCoefficient * ((density * velocity.getMagnitude() * (radius*2)) / viscosity) * density * Math.PI * (radius*radius) * velocity.getMagnitude()) // viscosity: Vector3.Multiply(velocity, Vector3.Multiply(velocity,(-6 * (float)Math.PI * viscosity * radius)))
	public Drag(Vector3 velocity, float radius, float viscosity, float density, float dragCoefficient) {
		super(	
				Vector3.Multiply(velocity, -1 * .5f * density *(float)Math.PI * radius * radius * dragCoefficient * velocity.getMagnitude())
			  );		
		this.velocity = velocity;
		this.radius = radius;
		this.viscosity = viscosity;
		this.density = density;
		this.dragCoefficient = dragCoefficient;
	}
	
	// Calculating force
	public Vector3 calculateForce(){
		Vector3 force = Vector3.Add(Vector3.Multiply(velocity, (float)(-1/2 * dragCoefficient * ((density * velocity.getMagnitude() * (radius*2)) / viscosity) * density * Math.PI * (radius*radius) * velocity.getMagnitude())), Vector3.Multiply(velocity, (-6 * (float)Math.PI * viscosity * radius)));
		this.value = force;
		return force;
	}
	
	// Getters & Setters
	// Each setter recalculates the net force for changing variables
	public Vector3 getValue() {
		return value;
	}
	
	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
		calculateForce();
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		calculateForce();
	}

	public float getViscosity() {
		return viscosity;
	}

	public void setViscosity(float viscosity) {
		this.viscosity = viscosity;
		calculateForce();
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
		calculateForce();
	}
	// End
	
}
