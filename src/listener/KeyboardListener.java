package listener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.*;
import java.awt.event.KeyEvent;
import java.io.File;

import springies.Springies;
import forces.*;

/**
 * This class serves as the keyboard listener, and is to be called in doFrame in 
 * the class Springies. This class listens for keyboard input and responds with 
 * certain actions, as specified by project guidelines, when certain keys
 * are pressed.
 * 
 * @author Grace, Shenghan
 */
public class KeyboardListener {

	private Springies myEngine;
	private File myFile;
	private int myWallIncrement = 3;//default value for amount to increment
									//wall heights/widths by

	public KeyboardListener(Springies engine) {
		myEngine = engine;
	}

	/**
	 * Listens for input from the keyboard.
	 */
	public void listen() {

		if(getKey(KeyEvent.VK_B)) {
			myEngine.addBall();
		}
		if(getKey(KeyEvent.VK_G)) {
			Gravity.toggleForce();
			myEngine.clearKey(KeyEvent.VK_G);
		} 
		if(getKey(KeyEvent.VK_V)) {
			Viscosity.toggleForce();
			myEngine.clearKey(KeyEvent.VK_V);
		} 
		if(getKey(KeyEvent.VK_M)) {
			CenterofMass.toggleForce();;
			myEngine.clearKey(KeyEvent.VK_M);
		} 
		if(getKey(KeyEvent.VK_1)) {
			WallRepulsion.toggleTop();
			myEngine.clearKey(KeyEvent.VK_1);
		} 
		if(getKey(KeyEvent.VK_2)) {
			WallRepulsion.toggleRight();
			myEngine.clearKey(KeyEvent.VK_2);
		} 
		if(getKey(KeyEvent.VK_3)) {
			WallRepulsion.toggleBottom();
			myEngine.clearKey(KeyEvent.VK_3);
		} 
		if(getKey(KeyEvent.VK_4)) {
			WallRepulsion.toggleLeft();
			myEngine.clearKey(KeyEvent.VK_4);
		} 
		if(getKey(KeyEvent.VK_N)) {
			chooseFile();
			myEngine.clearKey(KeyEvent.VK_N);
		}
		if(getKey(KeyEvent.VK_UP)) {
			myEngine.incrementWall(myWallIncrement);
			myEngine.clearKey(KeyEvent.VK_UP);
		}
		if(getKey(KeyEvent.VK_DOWN)) {
			myEngine.incrementWall(-1 * myWallIncrement);
			myEngine.clearKey(KeyEvent.VK_DOWN);
		}
		if(getKey(KeyEvent.VK_C)) {
			for(Assembly asbl:myEngine.myAssemblyList){	
				for(Mass mass: asbl.getMassList()){
					mass.remove();
				}
				for(FixedMass fixed: asbl.getFixedList()){
					fixed.remove();
				}
				for(Spring spring: asbl.getSpringList()){
					spring.remove();
				}
				for(Muscle muscle: asbl.getMuscleList()){
					muscle.remove();
				}
			}
			myEngine.myAssemblyList.clear();
		}
		if(getKey(KeyEvent.VK_PLUS)) {
			change_muscles(0.01);
		}
		if(getKey(KeyEvent.VK_MINUS)) {
			change_muscles(-0.01);
		}
	}

	/**
	 * Handles whether a key was pushed or not.
	 * @param int key, a given key
	 * @return whether the key was pushed or not
	 */
	private boolean getKey(int key) {
		return myEngine.getKey(key);
	}

	/**
	 * chooseFile will open a dialog box where the user may
	 * navigate directories and choose a .xml file.
	 * 
	 * Runs (or should run) when user presses the 'n' key.
	 */
	public void chooseFile() {
		
		final JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Xml Files", "xml");
		chooser.setFileFilter(filter);

		int loadedFile = chooser.showOpenDialog(null);
        if (loadedFile == JFileChooser.APPROVE_OPTION) {
            myFile = chooser.getSelectedFile();
        }
		Assembly assembly = new Assembly();
		assembly.addAssembly(myFile, Springies.myMassMap, myEngine);
		myEngine.myAssemblyList.add(assembly);
	}
	
	public void change_muscles(double diff) {
		for(Assembly asbl: myEngine.myAssemblyList){
			for(Muscle ms: asbl.getMuscleList()){
				ms.change_amplitude(diff);
			}
		}
	}

}
