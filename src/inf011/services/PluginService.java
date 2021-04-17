package inf011.services;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import inf011.interfaces.ILangFactory;

public class PluginService {
	
	private final String factorysPath = "inf011.plugin.factorys.";
	private final String dataPath = "data/Plugins.xml";
	private DocumentBuilder builder;
	private Document document;
	private Map<String, String> plugins;
	
	public PluginService() {
		try {
			this.plugins = new HashMap<String, String>();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    this.builder = dbFactory.newDocumentBuilder();
		    this.document = builder.parse(dataPath);
		
		    NodeList nodeList = document.getElementsByTagName("plugin");
		     
		 	for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
		 		Node node = nodeList.item(itr);   
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node; 
					String extension = eElement.getElementsByTagName("extension").item(0).getTextContent();
					String factoryName = eElement.getElementsByTagName("factoryName").item(0).getTextContent();			
					this.plugins.put(extension, factoryName);				
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public ILangFactory getFactoryByExtension(String extension) throws Exception {	    	
		String factoryName = this.plugins.get(extension);
	    if(factoryName == null || factoryName.isEmpty()) {  		    
			throw new Exception("Não existe plugin que suporte este arquivo");
	    }else {
	    	
	    	Class<?> factoryClass = Class.forName(this.factorysPath + factoryName);
	    	
	    	List<Method> methods = Arrays.asList(factoryClass.getDeclaredMethods());
	    	Method getInstanceMethod = methods.stream()
	    			  .filter(method -> method.getName().equals("getInstance"))
	    			  .findAny()
	    			  .orElse(null);
	    	
	    	
			 
		     ILangFactory factory =  (ILangFactory) getInstanceMethod.invoke(null); //(ILangFactory)factoryClass.newInstance();
		     return factory;
	    }
	}
	
	public String[] getValidExtension() {
		List<String> str = new ArrayList<String>();
		for(Map.Entry<String,String> map : this.plugins.entrySet()){
		     str.add(map.getKey());
		}
		return str.toArray(new String[0]);
	}
}
