package zautomate.zadoqa.walkthrough;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML 
{
	
	public static void main(String[] args){
	    try{
	    File file=new File("./OR.xml");
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    Document doc=db.parse(file);
	    doc.getDocumentElement().normalize();
	    System.out.println("The node name is: "+doc.getDocumentElement().getNodeName());   

	    NodeList nList=doc.getElementsByTagName("ObjRep");
	 
	    System.out.println("The length is: "+nList.getLength());
	    for(int i=0; i<nList.getLength(); i++){
	        Node nNode=nList.item(i);
	        if(nNode.getNodeType()==Node.ELEMENT_NODE){
	          Element ele=(Element) nNode;
	          System.out.println(ele);
	          System.out.println(ele.getElementsByTagName("Sample").item(i).getTextContent());
	          }
	    }

	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

}
