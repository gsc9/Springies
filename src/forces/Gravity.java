package forces;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObject;

/**
 * Class Gravity, extends Force.
 * 
 * This class takes information parsed from environment.xml in
 * Force and 
 * @author Grace
 *
 */
public class Gravity extends Force {
	
	private double[] myGravityInfo= myEnvironmentParser.getGravityInfo();
	private double myDirection = myGravityInfo[0] == 0 ? 90 : myGravityInfo[0];
	private double myMagnitude = myGravityInfo[1] == 0 ? 20 : myGravityInfo[0];
	
	private static boolean myApplyForce = true;
	
	public double getDirection() {
		return myDirection;
	}
	
	public double getMagnitude() {
		return myMagnitude;
	}
	
	/**
	 * takes a direction (degree) and returns
	 * a Vec2 gravity vector, with magnitude
	 * calculated into the vector as well.
	 * 
	 * @return Vec2 gravity vector
	 */
	public Vec2 gravityVector() {
		double rad = convertDegreesToRadians(myDirection);
		return new Vec2((float) Math.cos(rad), (float) Math.sin(rad))
			.mul((float) ( myMagnitude));
	}
	
	/**
	 * switch the boolean for applying
	 * the gravity force on objects
	 */
	public static void toggleForce() {
		myApplyForce = !myApplyForce;
	}
	
	/**
	 * returns whether the boolean for applying
	 * the gravity force on objects
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
			Vec2 force = gravityVector();
			o.setForce(force.x, force.y);
		}
		return;
	}

}
