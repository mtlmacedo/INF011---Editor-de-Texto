package inf011.services;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import inf011.interfaces.ILangFactory;

public class PluginService {
	
	public ILangFactory loadFactoryByExtension(String extension) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder builder = dbFactory.newDocumentBuilder();
	     Document document = builder.parse("data/Plugins.xml");
	
	     NodeList nodeList = document.getElementsByTagName("plugin");
	     
	 	for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
	 		Node node = nodeList.item(itr);   
			if (node.getNodeType() == Node.ELEMENT_NODE)   
			{  
				Element eElement = (Element) node; 
				if(extension.equals(eElement.getElementsByTagName("extension").item(0).getTextContent())){
					
					 Class<?> factoryClass = Class.forName("inf011.factorys." + eElement.getElementsByTagName("factoryName").item(0).getTextContent());
				     ILangFactory factory = (ILangFactory)factoryClass.newInstance();
				     return factory;
				}
				
			}
		}
		return null;	     
	}
}
