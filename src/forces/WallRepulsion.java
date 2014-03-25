package forces;

import java.io.File;

import org.jbox2d.common.Vec2;

import parser.EnvironmentParser;
import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;

/**
 * Abstract class that handles extracting wall repulsion info 
 * from the environment file (parsed in the Force abstract class).
 * Allows for toggling the wall repulsion for each wall, and
 * holds functions for applying the repulsion force of each wall
 * on a passed-in PhysicalObject.
 * 
 * @author Grace
 *
 */
public class WallRepulsion extends PhysicalObjectRect {

	//parse information from file
	File f = new File("environment.xml");
	EnvironmentParser e = new EnvironmentParser(f);
	private double[][] myWallInfo = e.getWallRepulsionInfo();
	
	private String myId;
	private double myMagnitude = 20;	//default value
	private double myExponent = 2.0;	//default value
	
	private static boolean myApplyTop = true; 
	private static boolean myApplyRight = true; 
	private static boolean myApplyBottom = true; 
	private static boolean myApplyLeft = true; 

	public WallRepulsion (String id, int collisionId, JGColor color, double width, 
			double height) {
		super(id, collisionId, color, width, height);
		if(id.equals("1")) {
			myId = "top";
		} else if(id.equals("2")) {
			myId = "right";
		} else if(id.equals("3")) {
			myId = "bottom";
		} else if(id.equals("4")) {
			myId = "left";
		}
		myMagnitude = myWallInfo[Integer.parseInt(id)-1][1];
		myExponent = myWallInfo[Integer.parseInt(id)-1][2];
	}

	/**
	 * 
	 * @param PhysicalObject o
	 * @return Vec2 vector of force on o
	 */
	public Vec2 wallRepulsionForce(PhysicalObject o) {
		if(myId.equals("top")) {
			return new Vec2(0, (float) (-1 * myMagnitude
					/ Math.pow(calcDistance(o.y, this.y), myExponent)));
		} else if(myId.equals("right")) {
			return new Vec2((float) (-1 * myMagnitude
					/ Math.pow(calcDistance(o.y, this.y), myExponent)), 0);
		} else if(myId.equals("bottom")) {
			return new Vec2(0, (float) (-1 * myMagnitude
					/ Math.pow(calcDistance(o.y, this.y), myExponent)));
		}  else if(myId.equals("left")) {
			return new Vec2((float) (-1 * myMagnitude
					/ Math.pow(calcDistance(o.y, this.y), myExponent)), 0);
		}
		return new Vec2(0, 0);
	}

	/**
	 * apply wall repulsion force of top wall
	 * on object.
	 * 
	 * @param PhysicalObject o
	 */
	public void applyTop(PhysicalObject o) {
		if(myApplyTop) {
			myId = "top";
			Vec2 force = wallRepulsionForce(o);
			o.setForce(force.x, force.y);
		}
	}

	/**
	 * apply wall repulsion force of right wall
	 * on object.
	 * 
	 * @param PhysicalObject o
	 */
	public void applyRight(PhysicalObject o) {
		if(myApplyRight) {
			myId = "right";
			Vec2 force = wallRepulsionForce(o);
			o.setForce(force.x, force.y);
		}
	}

	/**
	 * apply wall repulsion force of bottom wall
	 * on object.
	 * 
	 * @param PhysicalObject o
	 */
	public void applyBottom(PhysicalObject o) {
		if(myApplyBottom) {
			myId = "bottom";
			Vec2 force = wallRepulsionForce(o);
			o.setForce(force.x, force.y);
		}
	}

	/**
	 * apply wall repulsion force of left wall
	 * on object.
	 * 
	 * @param PhysicalObject o
	 */
	public void applyLeft(PhysicalObject o) {
		if(myApplyLeft) {
			myId = "left";
			Vec2 force = wallRepulsionForce(o);
			o.setForce(force.x, force.y);
		}
	}

	/**
	 * switch the boolean for applying
	 * the top wall repulsion force on objects
	 */
	public static void toggleTop() {
		myApplyTop = !myApplyTop;
	}
	
	/**
	 * switch the boolean for applying
	 * the right wall repulsion force on objects
	 */
	public static void toggleRight() {
		myApplyRight = !myApplyRight;
	}
	
	/**
	 * switch the boolean for applying
	 * the bottom wall repulsion force on objects
	 */
	public static void toggleBottom() {
		myApplyBottom = !myApplyBottom;
	}
	
	/**
	 * switch the boolean for applying
	 * the left wall repulsion force on objects
	 */
	public static void toggleLeft() {
		myApplyLeft = !myApplyLeft;
	}
	
	/**
	 * returns whether the boolean for applying
	 * the top wall repulsion force on objects
	 * is true or false. 
	 * 
	 * @return String "on" or "off"
	 */
	public String getApplyTop() {
		if(myApplyTop) 
			return "on";
		return "off";
	}
	
	/**
	 * returns whether the boolean for applying
	 * the right wall repulsion force on objects
	 * is true or false. 
	 * 
	 * @return String "on" or "off"
	 */
	public String getApplyRight() {
		if(myApplyRight) 
			return "on";
		return "off";
	}
	
	/**
	 * returns whether the boolean for applying
	 * the bottom wall repulsion force on objects
	 * is true or false. 
	 * 
	 * @return String "on" or "off"
	 */
	public String getApplyBottom() {
		if(myApplyBottom) 
			return "on";
		return "off";
	}
	
	/**
	 * returns whether the boolean for applying
	 * the left wall repulsion force on objects
	 * is true or false. 
	 * 
	 * @return String "on" or "off"
	 */
	public String getApplyLeft() {
		if(myApplyLeft) 
			return "on";
		return "off";
	}

	/**
	 * calculate the distance between point x and point y.
	 * @param x starting point
	 * @param y ending point
	 * @return distance
	 */
	public double calcDistance(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	@Override
	public void move() {
		//do nothing. walls shouldn't move.
	}
}


