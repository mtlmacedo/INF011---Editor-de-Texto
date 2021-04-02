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
		return new TextEditorUi(SyntaxConstants.SYNTAX_STYLE_JAVA, filePath, this.createBuilder());
	}

	@Override
	public IBuilder createBuilder() {
		return new JavaBuilder();
	}

}
