package inf011.interfaces;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public abstract class ILangFactory {
	
	public ILangFactory() {
		//TODO: Load extencions metadata
	}
	public String [] supportedExtensions() {
		String[] extensions = {"cpp", "java"};
		return extensions;		
	}
	public abstract RSyntaxTextArea createTextArea(String filePath);
	public abstract IBuilder createBuilder();
}
