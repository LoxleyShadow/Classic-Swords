package loxleyshadow.blockhitting.movement;

public class VecXZ {
	private double x;
	private double z;
	
	public VecXZ(double x, double z) {
		this.x = x;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getMagnitude() {
		return Math.sqrt(x*x+z*z);
	}
	
	public void normalize() {
		double m = this.getMagnitude();
		x /= m;
		z /= m;
	}
}
