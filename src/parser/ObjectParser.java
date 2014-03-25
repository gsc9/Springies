package parser;

import java.io.File;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectParser extends DOMParser {
	
	private int myMassNum;
	private String[] myMassID;
	private double[] myMassX;
	private double[] myMassY;
	private double[] myMassVx;
	private double[] myMassVy;
	private double[] myMassM;
	
	private int myFixedNum;
	private String[] myFixedID;
	private double[] myFixedX;
	private double[] myFixedY;
	
	private int mySpringNum;
	private String[] mySpringID1;
	private String[] mySpringID2;
	private double[] mySpringRL;
	private double[] mySpringK;
	
	private int myMuscleNum;
	private String[] myMuscleID1;
	private String[] myMuscleID2;
	private double[] myMuscleRL;
	private double[] myMuscleK;
	private double[] myMuscleA;
	
	public ObjectParser(File f) {
		super(f);
		parse();
	}

	@Override
	public void parse() {
		parseMass();
		parseFixed();
		ParseSpring();
		ParseMuscle();
	}

	private void parseMass() {
		NodeList n = myDocument.getElementsByTagName("mass");
		int num = n.getLength();
		myMassNum = num;
		String[] ID = new String[num];
		double[] xCoor = new double[num];
		double[] yCoor = new double[num];
		double[] xSpd = new double[num];
		double[] ySpd = new double[num];
		double[] m = new double[num];
		
		for(int i = 0; i < num; i++) {
			NamedNodeMap MassMap = n.item(i).getAttributes();
			ID[i] = MassMap.getNamedItem("id").getNodeValue();
			xCoor[i] = Double.parseDouble(MassMap.getNamedItem("x").getNodeValue());
			yCoor[i] = Double.parseDouble(MassMap.getNamedItem("y").getNodeValue());
			Node vxNode = MassMap.getNamedItem("vx");
			Node vyNode = MassMap.getNamedItem("vy");
			if(vxNode == null || vyNode == null){
				xSpd[i] = 0;
				ySpd[i] = 0;
			}
			else{
			    xSpd[i] = Double.parseDouble(vxNode.getNodeValue());
			    ySpd[i] = Double.parseDouble(vyNode.getNodeValue());
			}
			Node massNode = MassMap.getNamedItem("mass");
			if(massNode == null)
				m[i] = 1;
			else
			    m[i] = Double.parseDouble(massNode.getNodeValue());
		}
		myMassID = ID;
		myMassX = xCoor;
		myMassY = yCoor;
		myMassVx = xSpd;
		myMassVy = ySpd;
		myMassM = m;
	}

	public void parseFixed(){
		NodeList n = myDocument.getElementsByTagName("fixed");
		int num = n.getLength();
		myFixedNum = num;
		String[] ID = new String[num];
		double[] xCoor = new double[num];
		double[] yCoor = new double[num];
		
		for(int i = 0; i < num; i++) {
			NamedNodeMap MassMap = n.item(i).getAttributes();
			ID[i] = MassMap.getNamedItem("id").getNodeValue();
			xCoor[i] = Double.parseDouble(MassMap.getNamedItem("x").getNodeValue());
			yCoor[i] = Double.parseDouble(MassMap.getNamedItem("y").getNodeValue());
		}
		myFixedID = ID;
		myFixedX = xCoor;
		myFixedY = yCoor;
	}
	
	public void ParseSpring(){
		NodeList n = myDocument.getElementsByTagName("spring");
		int num = n.getLength();
		mySpringNum = num;
		String[] ID1 = new String[num];
		String[] ID2 = new String[num];
		double[] rl = new double[num];
		double[] k = new double[num];
		
		for(int i = 0; i < num; i++) {
			NamedNodeMap MassMap = n.item(i).getAttributes();
			ID1[i] = MassMap.getNamedItem("a").getNodeValue();
			ID2[i] = MassMap.getNamedItem("b").getNodeValue();
			rl[i] = Double.parseDouble(MassMap.getNamedItem("restlength").getNodeValue());
			Node kNode = MassMap.getNamedItem("constant");
			if(kNode == null)
				k[i] = 1;
			else
			    k[i] = Double.parseDouble(kNode.getNodeValue());
		}
		mySpringID1 = ID1;
		mySpringID2 = ID2;
		mySpringRL = rl;
		mySpringK = k;
	}
	
	public void ParseMuscle(){
		NodeList n = myDocument.getElementsByTagName("muscle");
		int num = n.getLength();
		myMuscleNum = num;
		String[] ID1 = new String[num];
		String[] ID2 = new String[num];
		double[] rl = new double[num];
		double[] k = new double[num];
		double[] a = new double[num];
		
		for(int i = 0; i < num; i++) {
			NamedNodeMap MassMap = n.item(i).getAttributes();
			ID1[i] = MassMap.getNamedItem("a").getNodeValue();
			ID2[i] = MassMap.getNamedItem("b").getNodeValue();
			rl[i] = Double.parseDouble(MassMap.getNamedItem("restlength").getNodeValue());
			a[i] = Double.parseDouble(MassMap.getNamedItem("amplitude").getNodeValue());
			Node kNode = MassMap.getNamedItem("constant");
			if(kNode == null)
				k[i] = 1;
			else
			    k[i] = Double.parseDouble(kNode.getNodeValue());
		}
		myMuscleID1 = ID1;
		myMuscleID2 = ID2;
		myMuscleRL = rl;
		myMuscleK = k;
		myMuscleA = a;
	}
	
	//getters galore.
	
	public int getMassNum() {
		return myMassNum;
	}
	
	public String[] getMassID() {
		return myMassID;
	}
	
	public double[] getMassX() {
		return myMassX;
	}
	
	public double[] getMassY() {
		return myMassY;
	}
	
	public double[] getMassVx() {
		return myMassVx;
	}
	
	public double[] getMassVy() {
		return myMassVy;
	}
	
	public double[] getMassM() {
		return myMassM;
	}
	
	public int getFixedNum() {
		return myFixedNum;
	}
	
	public String[] getFixedID() {
		return myFixedID;
	}
	
	public double[] getFixedX() {
		return myFixedX;
	}
	
	public double[] getFixedY() {
		return myFixedY;
	}
	
	public int getSpringNum() {
		return mySpringNum;
	}
	
	public String[] getSpringID1() {
		return mySpringID1;
	}
	
	public String[] getSpringID2() {
		return mySpringID2;
	}
	
	public double[] getSpringRL() {
		return mySpringRL;
	}
	
	public double[] getSpringK() {
		return mySpringK;
	}
	
	public int getMuscleNum() {
		return myMuscleNum;
	}
	
	public String[] getMuscleID1() {
		return myMuscleID1;
	}

	public String[] getMuscleID2() {
		return myMuscleID2;
	}

	public double[] getMuscleRL() {
		return myMuscleRL;
	}

	public double[] getMuscleK() {
		return myMuscleK;
	}

	public double[] getMuscleA() {
		return myMuscleA;
	}
	
	
}


