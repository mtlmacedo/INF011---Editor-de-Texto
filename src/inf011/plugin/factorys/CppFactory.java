package inf011.plugin.factorys;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import inf011.plugin.builders.CppBuilder;
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;
import inf011.ui.TextEditorUi;

public class CppFactory extends ILangFactory {

	@Override
	public RSyntaxTextArea createTextArea(String filePath) {
		RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		return textArea;
	}

	@Override
	public IBuilder createBuilder() {
		return new CppBuilder();
	}

}
