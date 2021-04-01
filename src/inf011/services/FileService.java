package inf011.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import inf011.factorys.CppFactory;
import inf011.factorys.JavaFactory;
import inf011.interfaces.ILangFactory;

public class FileService {
	
	private BufferedReader bufferedReader;
	
	public void validateExtension(String filePath) throws Exception {				
		
		String[] validExtensions = new JavaFactory().supportedExtensions();
		String fileExtension = this.getExtension(filePath);
		
			   
		for(String extencion : validExtensions) {
			if( extencion.equals(fileExtension)) {
				return;
			}
		}
		throw new Exception("Não existe plugin que suporte este arquivo");
	}
	
	public String getExtension(String filePath) throws Exception {
		int i = filePath.lastIndexOf('.');
		if (i > 0) {
			return filePath.substring(i+1);
		}else {
			throw new Exception("Invalid Path");
		}	
	}	
}
