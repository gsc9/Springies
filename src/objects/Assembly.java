package objects;

import java.io.File;
import java.util.Map;
import parser.ObjectParser;
import springies.Springies;

public class Assembly {
	private Mass[] myMassList;
	private FixedMass[] myFixedList;
	private Spring[] mySpringList;
	private Muscle[] myMuscleList;
    
    public void addAssembly(File f, Map<String, Mass> map, Springies engine){
		
		ObjectParser op = new ObjectParser(f);
		
		myMassList = new Mass[op.getMassNum()];
		for(int i = 0; i < op.getMassNum(); i++) {
			Mass mass = new Mass(op.getMassID()[i], op.getMassX()[i], 
					engine.displayHeight()-30-op.getMassY()[i], 
					op.getMassVx()[i], op.getMassVy()[i], op.getMassM()[i]);
			map.put(op.getMassID()[i], mass);
			Springies.myMassList.add(mass);
			myMassList[i] = mass;
		}
		
		myFixedList = new FixedMass[op.getFixedNum()];
		for(int i = 0; i < op.getFixedNum(); i++) {
			FixedMass fixed = new FixedMass(op.getFixedID()[i], op.getFixedX()[i],
					engine.displayHeight()-30-op.getFixedY()[i]);
			map.put(op.getFixedID()[i], (Mass) fixed);
			myFixedList[i] = fixed;
		}
		
		mySpringList = new Spring[op.getSpringNum()];
		for(int i = 0; i < op.getSpringNum(); i++) {
			Spring spring = new Spring(map.get(op.getSpringID1()[i]), 
					map.get(op.getSpringID2()[i]), op.getSpringRL()[i], op.getSpringK()[i]);
			mySpringList[i] = spring;
		}
		
		myMuscleList = new Muscle[op.getMuscleNum()];
		for(int i = 0; i < op.getMuscleNum(); i++) {
			Muscle muscle = new Muscle(map.get(op.getMuscleID1()[i]), 
					map.get(op.getMuscleID2()[i]), op.getMuscleRL()[i], op.getMuscleA()[i], 
					op.getMuscleK()[i]);
			myMuscleList[i] = muscle;
		}
	}
    
    public Mass[] getMassList() {
    	return myMassList;
    }
    
    public FixedMass[] getFixedList() {
    	return myFixedList;
    }
    
    public Spring[] getSpringList() {
    	return mySpringList;
    }
    
    public Muscle[] getMuscleList() {
    	return myMuscleList;
    }
        
}
