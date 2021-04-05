package inf011.interfaces;

import java.awt.print.Printable;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public abstract class ILangFactory {
	public abstract Printable createTextArea(String filePath);
	public abstract IBuilder createBuilder();
}
