package inf011.interfaces;

import javax.swing.JFrame;

public abstract class ILangFactory {
	
	public ILangFactory() {
		//TODO: Load extencions metadata
	}
	public String [] supportedExtensions() {
		String[] extensions = {"cpp", "java"};
		return extensions;		
	}
	public abstract JFrame createTextArea(String filePath);
	public abstract IBuilder createBuilder();
}
