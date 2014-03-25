package forces;

import jboxGlue.PhysicalObject;

/**
 * CenterofMass extends Force.
 * 
 * This class will apply the center of mass force on the
 * PhysicalObject inputed, and will also provide methods 
 * that can be called to find the actual center of mass.
 * 
 * @author Grace
 *
 */
public class CenterofMass extends Force {

	private double myXMassCenter;
	private double myYMassCenter;
	private static boolean myApplyForce = true;
	
	private double[] myCenterOfMassInfo = myEnvironmentParser.getCenterofMassInfo();
	private double myMagnitude = myCenterOfMassInfo[0];
	private double myExponent = myCenterOfMassInfo[1];

	/**
	 * set coordinates of the center of mass
	 * @param x, the x-coordinate of the center
	 * @param y, the y-coordinate of the center
	 */
	public void setCenterCoord(double x, double y) {
		myXMassCenter = x;
		myYMassCenter = y;
	}
	
	/**
	 * returns the stored center coordinates
	 * 
	 * @return double[] of size two, containing the x- and y coordinates
	 * in indices 0 and 1 respectively.
	 */
	public double[] getCenterCoord() {
		return new double[] {myXMassCenter, myYMassCenter};
	}

	public void setMagnitudeAndExponent(double magnitude, double exponent) {
		myMagnitude = magnitude;
		myExponent = exponent;
	}
	
	/**
	 * switch the boolean for applying
	 * the center of mass force on objects
	 */
	public static void toggleForce() {
		myApplyForce = !myApplyForce;
	}
	
	/**
	 * returns whether the boolean for applying
	 * the center of mass force on objects
	 * is true or false. 
	 * 
	 * @return String "on" or "off"
	 */
	public String getApplyForce() {
		if(myApplyForce) 
			return "on";
		return "off";
	}
	
	@Override
	public void applyForce(PhysicalObject o) {
		if(myApplyForce) {
			double distance = calcDistance(myXMassCenter, o.x, myYMassCenter, o.y);
			double constant = myMagnitude * Math.abs(1 / Math.pow(distance, myExponent))
					* Force.getScale();
			o.xspeed += o.xspeed * Math.cos(constant);
			o.yspeed += o.yspeed * Math.sin(constant);
		}
	}

}
