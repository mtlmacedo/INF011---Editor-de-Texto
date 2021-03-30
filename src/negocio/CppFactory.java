package negocio;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import interfaces.IBuilder;
import interfaces.ILangFactory;
import ui.TextEditorUi;

public class CppFactory extends ILangFactory {

	@Override
	public JFrame createTextArea(String filePath) {
		RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		return new TextEditorUi(textArea, filePath, this.createBuilder());
	}

	@Override
	public IBuilder createBuilder() {
		return new CppBuilder();
	}

}