package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.platform.JGEngine;
import objects.Mass;
import forces.CenterofMass;
import forces.Gravity;
import forces.Viscosity;
import forces.WallRepulsion;
import objects.*;
import listener.*;


@SuppressWarnings("serial")
public class Springies extends JGEngine
{	
	//wall dimensions:
	private double myWallIncrement = 0;
	private double myWallMargin = 10;
	private double myWallThickness = 10;
	private double myWallWidth;
	private double myWallHeight;
	
	//walls:
	private PhysicalObjectRect myTopWall;
	private PhysicalObjectRect myRightWall;
	private PhysicalObjectRect myBottomWall;
	private PhysicalObjectRect myLeftWall;
	
	private KeyboardListener myKeyboardListener;
	private MouseListener myMouseListener;
	private static int myClock = 0;
	final static int myFrameNum = 60;
	
	public static Map<String, Mass> myMassMap = new HashMap<String, Mass>();
	public static ArrayList<Mass> myMassList = new ArrayList<Mass>();
	public ArrayList<Assembly> myAssemblyList = new ArrayList<Assembly>();
	
	
	public Springies ()
	{
		// set the window size
		int height = 580;
		double aspect = 16.0 / 9.0;
		initEngineComponent((int) (height * aspect), height);
	}

	public static Map<String, Mass> getMassMap() {
		return myMassMap;
	}
	
	public static void addToMap(String id, Mass mass) {
		myMassMap.put(id, mass);
	}
	
	public static void addToList(Mass mass) {
		myMassList.add(mass);
	}
	
	@Override
	public void initCanvas ()
	{
		// I have no idea what tiles do...
		setCanvasSettings(1, // width of the canvas in tiles
				1, // height of the canvas in tiles
				displayWidth(), // width of one tile
				displayHeight(), // height of one tile
				null,// foreground colour -> use default colour white
				null,// background colour -> use default colour black
				null); // standard font -> use default font
	}

	@Override
	public void initGame ()
	{
		setFrameRate(myFrameNum,2);
		WorldManager.initWorld(this);
		myWallWidth = displayWidth() - myWallMargin * 2 + myWallThickness;
		myWallHeight = displayHeight() - myWallMargin * 2 + myWallThickness;
		addWalls();
		myKeyboardListener = new KeyboardListener(this);
		myMouseListener = new MouseListener(this);
		addWalker();
	}
	
	public void addWalker() {
		File f = new File("example.xml");
		Assembly assembly = new Assembly();
		assembly.addAssembly(f, Springies.myMassMap, this);
		this.myAssemblyList.add(assembly);
	}
	
	/**
	 * add a mass to the simulation.
	 */
	public void addBall() {
		Mass mass = new Mass(displayWidth() / 2, displayHeight() / 2) {
			@Override
			public void move() {
				super.move();
			}
		};
		myMassList.add(mass);
	}

	/**
	 * adds top, right, bottom, and left walls.
	 * (This method calls the four other methods that
	 * adds the four walls myMassListed above.)
	 * 
	 */
	private void addWalls ()
	{
		addTopWall();
		addRightWall();
		addBottomWall();
		addLeftWall();
	}
	
	private void destroyWalls() {
		myTopWall.remove();
		myRightWall.remove();
		myBottomWall.remove();
		myLeftWall.remove();
	}

	public void addTopWall() {
		myTopWall = new PhysicalObjectRect("1", 2, JGColor.green,
				myWallWidth + myWallIncrement, myWallThickness);
		myTopWall.setPos(displayWidth() / 2, myWallMargin - (myWallIncrement / 2));
	}
	
	public void addRightWall() {
		myRightWall = new PhysicalObjectRect("4", 2, JGColor.green,
				myWallThickness, myWallHeight + myWallIncrement);
		myRightWall.setPos(displayWidth() - myWallMargin + (myWallIncrement / 2), displayHeight() / 2);
	}
	
	public void addBottomWall() {
		myBottomWall = new PhysicalObjectRect("2", 2, JGColor.green,
				myWallWidth + myWallIncrement, myWallThickness);
		myBottomWall.setPos(displayWidth() / 2, displayHeight() - myWallMargin + (myWallIncrement / 2));
	}
	
	public void addLeftWall() {
		myLeftWall = new PhysicalObjectRect("3", 2, JGColor.green,
				myWallThickness, myWallHeight+ myWallIncrement);
		myLeftWall.setPos(myWallMargin - (myWallIncrement / 2), displayHeight() / 2);
	}
	
	/**
	 * doFrame() is called during each frame, and handles 
	 * calling the keyboard myMassListener, moving objects, and 
	 * checking for collisions.
	 */
	@Override
	public void doFrame ()
	{ 
		myKeyboardListener.listen();
		myMouseListener.listen();
		myClock = (myClock+1) % myFrameNum;
		paintFrame();
		moveObjects();
		checkCollision(1 + 2, 1);
		WorldManager.getWorld().step(3f, 1);
	}

	/**
	 * paintFrame() displays the status of each force at 
	 * the top left corner of the simulation window.
	 */
	@Override
	public void paintFrame ()
	{
		Gravity g = new Gravity();
		drawString("Gravity: " + g.getApplyForce(), myWallMargin + myWallThickness, 
				1 * myWallMargin + myWallThickness, -1);
		
		Viscosity v = new Viscosity();
		drawString("Viscosity: " + v.getApplyForce(), myWallMargin + myWallThickness, 
				2 * (myWallMargin + myWallThickness + 1), -1);
		
		CenterofMass c = new CenterofMass();
		drawString("Center of Mass: " + c.getApplyForce(), myWallMargin + myWallThickness, 
				3 * (myWallMargin + myWallThickness + 1), -1);
		
		WallRepulsion w = new WallRepulsion("1", 0, JGColor.red, 0, 0);
		drawString("Top Wall Repulsion: " + w.getApplyTop(), myWallMargin + myWallThickness, 
				4 * (myWallMargin + myWallThickness + 1), -1);
		drawString("Right Wall Repulsion: " + w.getApplyRight(), myWallMargin + myWallThickness, 
				5 * (myWallMargin + myWallThickness + 1), -1);
		drawString("Bottom Wall Repulsion: " + w.getApplyBottom(), myWallMargin + myWallThickness, 
				6 * (myWallMargin + myWallThickness + 1), -1);
		drawString("Left Wall Repulsion: " + w.getApplyLeft(), myWallMargin + myWallThickness, 
				7 * (myWallMargin + myWallThickness + 1), -1);
	}
	
	/**
	 * Increments the length and the position of the walls
	 * according to user input. Called by KeyboardListener
	 * when appropriate.
	 * @param input
	 */
	public void incrementWall(double input) {
		myWallIncrement += input;
		rebuildWalls();
	}
	
	/**
	 * Destroys current walls and rebuilds them. This is called
	 * when user changes the dimensions/positions of the walls.
	 */
	public void rebuildWalls() {
		destroyWalls();
		addWalls();
	}
	
	/**
	 * clear the myMassList of masses,
	 * and remove each one from
	 * the simulation.
	 */
	public void clearMassList() {
		for(Mass m : myMassList) {
			m.remove();
		}
		myMassList.clear();
	}

	public static double getClock(){
		return myClock/myFrameNum;
	}
	
}
