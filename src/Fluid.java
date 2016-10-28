
public class Fluid {
	private float density;
	private float viscosity;
	private Vector3 flow;
	
	public Fluid(float density, float viscosity, Vector3 flow){
		this.density = density;
		this.viscosity = viscosity;
		this.flow = flow;
	}
	
	// Getters & Setters
	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getViscosity() {
		return viscosity;
	}

	public void setViscosity(float viscosity) {
		this.viscosity = viscosity;
	}

	public Vector3 getFlow() {
		return flow;
	}

	public void setFlow(Vector3 flow) {
		this.flow = flow;
	}
	
	
	
	
}
