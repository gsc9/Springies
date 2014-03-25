package parser;

import java.io.File;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * EnvironmentParser extends DOMParser.
 * 
 * This class will parse an environment file for environment-related
 * info, mostly force-related, and store information in appropriate 
 * data structures for reference elsewhere.
 * 
 * @author Grace
 *
 */
public class EnvironmentParser extends DOMParser {
	
	private double[] myGravityInfo;			//store gravity info in a double list of length 2
	private double myViscosityInfo;			//store viscosity info as a double
	private double[] myCenterOfMassInfo;
	private double[][] myWallRepulsionInfo;
	
	private String myGravityTagName = "gravity";
	private String myViscosityTagName = "viscosity";
	private String myCenterMassTagName = "centermass";
	private String myWallTagName = "wall";
	
	private String namedItemMagnitude = "magnitude";
	private String namedItemDirection = "direction";
	private String namedItemExponent = "exponent";
	private String namedItemId = "id";
	
	
	public EnvironmentParser(File f) {
		super(f);
		parse();
	}
	
	@Override
	public void parse() {
		myGravityInfo = parseGravity();
		myViscosityInfo = parseViscosity();
		myCenterOfMassInfo = parseCenterOfMass();
		myWallRepulsionInfo = parseWalls();
	}
	
	/**
	 * takes a tag name and returns a NamedNodeMap of the attributes
	 * regarding that tag, in the file being parsed.
	 * 
	 * @param tagName, the name of the tag being sought for
	 * @return NamedNodeMap of attributes
	 */
	private NamedNodeMap getAttributesByTagName(String tagName) {
		NodeList n = myDocument.getElementsByTagName(tagName);
		return n.item(0).getAttributes();
	}
	
	/**
	 * Takes a NamedNodeMap of attributes and the name of a specific item
	 * being parsed for, and returns the value of the item being parsed for
	 * as a double.
	 * 
	 * @param map, a NamedNodeMap of attributes for a certain tag (i.e. gravity)
	 * @param namedItem, name of item being parsed for (i.e. "magnitude")
	 * @return double value of the item being parsed for (i.e. 2.0)
	 */
	private double getNodeValue(NamedNodeMap map, String namedItem) {
		return Double.parseDouble(map.getNamedItem(namedItem).getNodeValue());
	}
	
	//parse gravity:
	private double[] parseGravity() {
		double[] info = new double[2];
		NamedNodeMap map = getAttributesByTagName(myGravityTagName);
		info[0] = getNodeValue(map, namedItemDirection);
		info[1] = getNodeValue(map, namedItemMagnitude);
		return info;
	}
	
	//parse viscosity:
	private double parseViscosity() {
		NamedNodeMap map = getAttributesByTagName(myViscosityTagName);
		return getNodeValue(map, namedItemMagnitude);
	}
	
	//parse center of mass:
	private double[] parseCenterOfMass() {
		double[] info = new double[2];
		NamedNodeMap map = getAttributesByTagName(myCenterMassTagName);
		info[0] = getNodeValue(map, namedItemMagnitude);
		info[1] = getNodeValue(map, namedItemExponent); 
		return info;
	}
	
	//parse walls! In the returned double[][],
	//column 1 will be IDs, 
	//column 2 will be magnitudes, and
	//column 3 will be exponents
	private double[][] parseWalls() {
		double[][] info = new double[4][3];
		NodeList n = myDocument.getElementsByTagName(myWallTagName);
		for(int i = 0; i < n.getLength(); i++) {
			NamedNodeMap wallMap = n.item(i).getAttributes();
			info[i][0] = getNodeValue(wallMap, namedItemId);
			info[i][1] = getNodeValue(wallMap, namedItemMagnitude);
			info[i][2] = getNodeValue(wallMap, namedItemExponent);
		}
		return info;
		
	}
	
	public double[] getGravityInfo() {
		return myGravityInfo;
	}
	
	public double getViscosityInfo() {
		return myViscosityInfo;
	}
	
	public double[] getCenterofMassInfo() {
		return myCenterOfMassInfo;
	}
	
	public double[][] getWallRepulsionInfo() {
		return myWallRepulsionInfo;
	}
	
}
