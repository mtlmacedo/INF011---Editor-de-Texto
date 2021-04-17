package inf011.plugin.factorys;

import java.awt.print.Printable;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import inf011.plugin.builders.CppBuilder;
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;

public class CppFactory implements ILangFactory {

	private static ILangFactory instance = null;
	private static Object mutex = new Object();
	
	private CppFactory() {
		
	}
	
	public Printable createTextArea(String filePath) {
		RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		return textArea;
	}

	public IBuilder createBuilder() {
		return new CppBuilder();
	}
	
	public static ILangFactory getInstance() {
		ILangFactory result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new CppFactory();
			}
		}
		return result;
	}

}
