package parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Abstract class for parsing .xml files
 * 
 * Defines the method newDoc, which returns a Document for 
 * the constructor, and declares the abstract method parse().
 * 
 * @author Grace
 *
 */
public abstract class DOMParser {
	
	protected Document myDocument;
	
	protected DOMParser(File f) {
		myDocument = newDoc(f);
	}
	
	/**
	 * creates a document out of a file, for the constructor
	 * 
	 * @param File f
	 * @return Document
	 */
	private Document newDoc(File f) {
		//code from Javarevisited.blogspot.com
		 try{
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(f);
             doc.getDocumentElement().normalize();
             return doc;
		 } catch (Exception e) {
             e.printStackTrace();
		 }
		 return null;
	}
	
	/**
	 * the abstract method parse() will read the document for a certain label,
	 * depending on the type of information being parsed (i.e. type of force),
	 * and will store any info found in an appropriate data structure.
	 */
	public abstract void parse();
}
