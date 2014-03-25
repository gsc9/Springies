package forces;

import jboxGlue.PhysicalObject;

import org.jbox2d.common.Vec2;

/**
 * Viscosity extends Force, and handles calculating the magnitude 
 * of viscosity on a given object.
 * 
 * @author Grace
 *
 */
public class Viscosity extends Force {
	
	private double myMagnitude = myEnvironmentParser.getViscosityInfo();
	private static boolean myApplyForce = true;
	
	public Vec2 directionOfForce(double xSpeed, double ySpeed) {
		return new Vec2((float) (-1 * xSpeed * 5 * myMagnitude),
						(float) (-1 * ySpeed * 5 * myMagnitude));
	}
	
	public double magnitudeOfForce(double xSpeed, double ySpeed, double magnitude) {
		return magnitude * 
				Math.sqrt(Math.pow(xSpeed,2) + Math.sqrt(Math.pow(ySpeed, 2)));
	}
	
	/**
	 * switch the boolean for applying
	 * the viscosity force on objects
	 */
	public static void toggleForce() {
		myApplyForce = !myApplyForce;
	}
	
	/**
	 * returns whether the boolean for applying
	 * the viscosity force on objects
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
			Vec2 force = directionOfForce(o.xspeed, o.yspeed);
			o.setForce(force.x, force.y);
		}
		return;
	}
}
