package inf011.factorys;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import inf011.builders.JavaBuilder;
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;
import inf011.ui.TextEditorUi;

public class JavaFactory extends ILangFactory {

	@Override
	public JFrame createTextArea(String filePath) {
		RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		return new TextEditorUi(textArea, filePath, this.createBuilder());
	}

	@Override
	public IBuilder createBuilder() {
		return new JavaBuilder();
	}

}
