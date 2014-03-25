package jboxGlue;

import jgame.JGColor;

import org.jbox2d.collision.CircleDef;

import forces.Gravity;
import forces.WallRepulsion;

/**
 * Added certain features to this class;
 * In the move() method, gravity and wall repulsion
 * are called.
 * 
 * @editor Grace
 */
public class PhysicalObjectCircle extends PhysicalObject
{
    private double myRadius;
    
    private Gravity myGravity = new Gravity();
    private WallRepulsion w1 = new WallRepulsion("1", 2, JGColor.green, 0, 0);
	private WallRepulsion w2 = new WallRepulsion("2", 2, JGColor.green, 0, 0);
	private WallRepulsion w3 = new WallRepulsion("3", 2, JGColor.green, 0, 0);
	private WallRepulsion w4 = new WallRepulsion("4", 2, JGColor.green, 0, 0);

    public PhysicalObjectCircle (String id,
                                 int collisionId,
                                 JGColor color,
                                 double radius)
    {
        this(id, collisionId, color, radius, 0);
    }

    public PhysicalObjectCircle (String id,
                                 int collisionId,
                                 JGColor color,
                                 double radius,
                                 double mass)
    {
        super(id, collisionId, color);
        init(radius, mass);
    }

    public PhysicalObjectCircle (String id,
                                 int collisionId,
                                 String gfxname,
                                 double radius)
    {
        this(id, collisionId, gfxname, radius, 0);
    }

    public PhysicalObjectCircle (String id,
                                 int collisionId,
                                 String gfxname,
                                 double radius,
                                 double mass)
    {
        super(id, collisionId, gfxname);
        init(radius, mass);
    }

    private void init (double radius, double mass)
    {
        // save arguments
        myRadius = radius;
        int intRadius = (int)radius;
        // make it a circle
        CircleDef shape = new CircleDef();
        shape.radius = (float)radius;
        shape.density = (float)mass;
        createBody(shape);
        setBBox(-intRadius, -intRadius, 2 * intRadius, 2 * intRadius);
    }

    @Override
    public void paintShape ()
    {
        myEngine.setColor(myColor);
        myEngine.drawOval(x, y, (float)myRadius * 2, (float)myRadius * 2, true, true);
    }
    
    /**
     * call move() method of PhysicalObject, and implement
     * gravity and wall repulsion in addition.
     */
    @Override
    public void move() {
    	super.move();
    	implementGravity();
    	implementWallRepulsion();
    }
    
    /**
     * apply gravity force on the PhysicalObjectCircle in question
     */
    public void implementGravity() {
//    	Gravity g = new Gravity();
//    	g.applyForce(this);
    	myGravity.applyForce(this);
    }
    
    /**
     * apply wall repulsion forces on the PhysicalObjectCircle in question
     */
    public void implementWallRepulsion() {
//    	WallRepulsion w1 = new WallRepulsion("1", 2, JGColor.green, 0, 0);
//    	WallRepulsion w2 = new WallRepulsion("2", 2, JGColor.green, 0, 0);
//    	WallRepulsion w3 = new WallRepulsion("3", 2, JGColor.green, 0, 0);
//    	WallRepulsion w4 = new WallRepulsion("4", 2, JGColor.green, 0, 0);
		w1.applyTop(this);
		w2.applyRight(this);
		w3.applyBottom(this);
		w4.applyLeft(this);
    }
}
