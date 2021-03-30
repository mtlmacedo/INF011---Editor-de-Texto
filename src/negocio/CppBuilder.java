package negocio;

import java.io.File;

import interfaces.IBuilder;

public class CppBuilder implements IBuilder {

	@Override
	public void run(String file) {
		try {
			int i = file.lastIndexOf('\\');
			String fileName = file.substring(i+1);
			String dir = file.substring(0, i+1);
			//Runtime.getRuntime().exec("gcc " + dir + fileName + " -o " + dir + "Teste");
			System.out.println("cmd /C gcc " + dir + fileName + " -o " + dir + "Teste");
		}catch (Exception e) {
			// TODO: handle exception
		}		
	}
}
