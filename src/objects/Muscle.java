package objects;

import springies.Springies;

public class Muscle extends Spring {
	
	protected double myAmplitude;
	protected double myConstantRest;
	
	public Muscle(Mass m1, Mass m2, double rl, double A){
		super(m1, m2, rl);
		myAmplitude = A;
	}
    
    public Muscle(Mass m1, Mass m2, double rl, double A, double K){
    	this(m1, m2, rl, A);
		mySpringy = K;
	}
	
    public void move(){
    	super.move();
    	set_restLength();
    }
    
    public void set_restLength(){
    	double num = Springies.getClock();
    	this.myRestLength = myConstantRest * ( 1 + myAmplitude*Math.sin(2*Math.PI*num) );
    }
    
    public void change_amplitude(double diff){
    	myAmplitude *= 1+diff;
    }
    
}
