package forces;

import java.io.File;

import jboxGlue.PhysicalObject;

import parser.EnvironmentParser;

/**
 * Abstract superclass for all types of forces; the applyForce(PhysicalObject o) 
 * method is to be implemented by each subclass as per the nature of that subclass.
 * 
 * This class also initiates the environment.xml parser file, which subclasses
 * use to extract information relevant to them, and includes several useful 
 * mathematical functions, and sets a scaling double for the magnitudes of the 
 * forces used (for testing purposes).
 * 
 * I also chose to refrain from making toggleForce() an inherited method for all 
 * subclasses to use, because each subclass has its own private static myApplyForce
 * boolean variable, and creating an inherited toggleForce() method would have
 * made toggleForce toggle all subclass booleans at once.
 * 
 * @author Grace
 *
 */

public abstract class Force {

	private static float scale = 1f;	//can be changed; this scale factor was used
										//to test forces
	
	protected File myFile = new File("environment.xml");
	EnvironmentParser myEnvironmentParser = new EnvironmentParser(myFile);
	
	public double convertDegreesToRadians(double degree) {
		return degree * (Math.PI/180d);
	}
	
	public double calcDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
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
	
	public static double getScale() {
		return scale;
	}
	
	/**
	 * The abstract method applyForce, which takes in a PhysicalObject
	 * as a parameter, will calculate a force vector of the respective
	 * force using distance, speed, direction, and/or magnitude,
	 * and will apply the resulting vector of that respective force
	 * on the PhysicalObject. 
	 * 
	 * @param PhysicalObject o
	 */
	public abstract void applyForce(PhysicalObject o);
	
}
