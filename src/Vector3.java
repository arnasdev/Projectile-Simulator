import java.util.Arrays;

// Class for a vector made of 3 components (i, j, k)
// Contains useful methods for calculating vectors

public class Vector3 {
	// Default vector is 0, 0, 0
	public float[] value = new float[]{0, 0, 0};
	
	// Vector with set value
	public Vector3(float i, float j, float k){
		this.value = new float[]{i, j, k};
	}
	
	// 0, 0, 0
	public Vector3(){
	}

	/**
	 *	Calculating methods 
	 **/
	
	// Vector + Vector
	public static Vector3 Add(Vector3 v1, Vector3 v2){
		Vector3 total = new Vector3();
		
		for(int i = 0; i < 3; i++){
			total.value[i] = (v1.value[i] + v2.value[i]);
		}
		
		return total;
	}
	
	// Vector + Vector + Vector + Vector
		public static Vector3 Add(Vector3 v1, Vector3 v2, Vector3 v3, Vector3 v4){
			Vector3 total = new Vector3();
			
			for(int i = 0; i < 3; i++){
				total.value[i] = (v1.value[i] + v2.value[i] + v3.value[i] + v4.value[i]);
			}
			
			return total;
		}
	
	// Vector - Vector
		public static Vector3 Subtract(Vector3 v1, Vector3 v2){
			Vector3 total = new Vector3();
			
			for(int i = 0; i < 3; i++){
				total.value[i] = (v1.value[i] - v2.value[i]);
			}
			
			return total;
		}
	
	// Vector * Vector
	public static Vector3 Multiply(Vector3 v1, Vector3 v2){
		// cx = ay bz - az by
		// cy = az bx - ax bz
		// cz = ax by - ay bx
		// 	x = 0 	y = 1 	z = 2

		Vector3 total = new Vector3();
		
		total.value[0] = (v1.value[1] * v2.value[2]) - (v1.value[2] * v2.value[1]);
		total.value[1] = (v1.value[2] * v2.value[0]) - (v1.value[0] * v2.value[2]);
		total.value[2] = (v1.value[0] * v2.value[1]) - (v1.value[1] * v2.value[0]);
		
		return total;
	}
	
	// Vector * Constant
	public static Vector3 Multiply(Vector3 v1, float f1){
			Vector3 total = new Vector3();
			
			for(int i = 0; i < 3; i++){
				total.value[i] = (v1.value[i] * f1);
			}
			
			return total;
		}
	
	// Vector . Vector
	public float dotProduct(){
		float dotProduct = 0f;
		dotProduct = (this.getValue()[0] * this.getValue()[0]) + (this.getValue()[1] * this.getValue()[1]) + (this.getValue()[2] * this.getValue()[2]);
		return dotProduct;
	}
	
	// Getting magnitude/strength of vector
	public float getMagnitude(){
		float magnitude = (float)Math.sqrt(this.dotProduct());
		return magnitude;
	}
	
	// Getting unit vector
	public Vector3 getDirection(){
		float magnitude = this.getMagnitude();
		Vector3 direction = Vector3.Multiply(this, 1/magnitude);
		return direction;
	}
	
	// Getters & Setters
	public float[] getValue() {
		return value;
	}
	
	public void setValue(float[] value) {
		this.value = value;
	}
	// End

	@Override
	public String toString() {
		return "" + Arrays.toString(value);
	}
	
	
}
