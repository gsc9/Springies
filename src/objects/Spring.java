package objects;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{
	
    protected double mySpringy = 1;
    protected double myRestLength;
    protected Mass myMass1;
    protected Mass myMass2;
    protected double myF1X = 0;
    protected double myF1Y = 0;
    protected double myF2X = 0;
    protected double myF2Y = 0;
    
    public Spring(Mass m1, Mass m2, double rl){
    	super("new", 1, JGColor.grey);
    	myMass1 = m1;
    	myMass2 = m2;
    	myRestLength = rl;
	}
    
    public Spring(Mass m1, Mass m2, double rl, double K){
    	this(m1, m2, rl);
		mySpringy = K;
	}
    
	@Override
	protected void paintShape() {
		myEngine.setColor(myColor);
		myEngine.drawLine(myMass1.x, myMass1.y, myMass2.x, myMass2.y);
	}
	
	public void move(){
		
		//super.move();
		double l = Math.sqrt(Math.pow(myMass1.x-myMass2.x, 2)+Math.pow(myMass1.y-myMass2.y, 2));
		double F = -mySpringy * (l - myRestLength);	
		double currF1X = (myMass1.x-myMass2.x)/l * F;
		double currF1Y = (myMass1.y-myMass2.y)/l * F;
		double currF2X = (myMass2.x-myMass1.x)/l * F;
		double currF2Y = (myMass2.y-myMass1.y)/l * F;
		
		myMass1.myForceX += currF1X - myF1X;
		myMass1.myForceY += currF1Y - myF1Y;
		myMass2.myForceX += currF2X - myF2X;
		myMass2.myForceY += currF2Y - myF2Y;
		
		myF1X = currF1X;
		myF1Y = currF1Y;
		myF2X = currF2X;
		myF2Y = currF2Y;
	}
	
	public void destroy() {
		
	}
   
}