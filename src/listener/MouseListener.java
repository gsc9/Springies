package listener;

import java.util.*;

import objects.FixedMass;
import objects.Mass;
import objects.Spring;

import springies.Springies;


public class MouseListener{
	
	private Springies myEngine;
	private boolean clicked = false;
	private boolean changed = false;
	private ArrayList<FixedMass> myFixedList = new ArrayList<FixedMass>();
	private ArrayList<Spring> mySpringList = new ArrayList<Spring>();
	
	public MouseListener(Springies engine) {
		
		myEngine = engine;
	}

	public void listen() {
		
		
		boolean current = myEngine.getMouseButton(1);
		changed = clicked != current;
		clicked = current;
		
		double Mx = myEngine.getMouseX();
		double My = myEngine.getMouseY();
		
		if (changed && clicked)
			press(Mx, My);
		if (changed && !clicked)
			release();
		if (!changed && clicked)
			update(Mx, My);
	}
	
	public void press(double Mx, double My){
		
		
		FixedMass mouse = new FixedMass("mouse", Mx, My);
		
//		System.out.format("%s %f %f ", mouse.getName(), Mx, My);
		
		myFixedList.add(mouse);
		
		if(Springies.myMassList.isEmpty())
			return;
		double Distance = 0;
		Mass nearest = (Mass) mouse;
		for(Mass m: Springies.myMassList){
			double dis = Math.sqrt(Math.pow(Mx-m.x, 2) + Math.pow(My-m.y, 2));
			if (dis < Distance){
				Distance = dis;
				nearest = m;
			}
		}
		
		
		Spring drag = new Spring((Mass) mouse, nearest, Distance);
		mySpringList.add(drag);
	}
	
	public void release(){
		for(FixedMass fixed: myFixedList){
			fixed.remove();
		}
		for(Spring spring: mySpringList){
			spring.remove();
			System.out.print("pressing\n");
		}

		myFixedList.clear();
//		mySpringList.clear();
	}
	
	public void update(double Mx, double My){
		for(FixedMass fixed: myFixedList)
			fixed.setPos(Mx, My);
	}
}
