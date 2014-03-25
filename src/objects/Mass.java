package objects;

import org.jbox2d.common.Vec2;
import springies.Springies;
import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;
import jgame.JGObject;

public class Mass extends PhysicalObjectCircle{
	
	final double myDampingFactor = 0.8;
	protected static int myRadius = 5;
	protected static JGColor myColor = JGColor.white;
	protected double myMass = 0.5;
	
	protected double myForceX = 0;
	protected double myForceY = 0;
	
	public Mass(){
		super("new", 1, myColor, myRadius, 5);
		this.setBBox((int) this.x, (int) this.y, myRadius+2, myRadius+2);
	}
	
	public Mass(double xCoor, double yCoor) {
		super("new", 1, myColor, myRadius, 5);
		this.setPos(xCoor, yCoor);//
	}
	
	public Mass(String id, double xCoor, double yCoor, double mass){
		super(id, 1, myColor, myRadius, mass);
		this.setPos(xCoor, yCoor);//
		myMass = mass;
		Springies.addToMap(id, this);
		this.setBBox((int) this.x, (int) this.y, myRadius+2, myRadius+2);
	}
	
	public Mass(String id, double xCoor, double yCoor){
		this(id, xCoor, yCoor, 1);
		Springies.addToMap(id, this);
		this.setBBox((int) this.x, (int) this.y, myRadius+2, myRadius+2);
	}
	
	public Mass(String id, double xCoor, double yCoor, double Vx, double Vy, double mass) {
		this(id, xCoor, yCoor, mass);
		xspeed = Vx;
		yspeed = Vy;
		myMass = mass;
		this.setBBox((int) this.x, (int) this.y, myRadius+2, myRadius+2);
	}
	
	
	public double getMass() {
		return myMass;
	}
	
	public Mass(String id, double xCoor, double yCoor, double Vx, double Vy){
		this(id, xCoor, yCoor, Vx, Vy, 1);
		this.setBBox((int) this.x, (int) this.y, myRadius+2, myRadius+2);
	}
	
	@Override
	public void hit (JGObject other) {
		if(other.colid==1) return;
		Vec2 velocity = myBody.getLinearVelocity();
		
		boolean isSide = other.getBBox().height > other.getBBox().width;
		if (isSide) {
			velocity.x *= -myDampingFactor;
		}
		else {
			velocity.y *= -myDampingFactor;
		}
		myBody.setLinearVelocity(velocity);
	}
	
}
