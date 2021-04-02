package inf011.interfaces;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public abstract class ILangFactory {
	public abstract RSyntaxTextArea createTextArea(String filePath);
	public abstract IBuilder createBuilder();
}
