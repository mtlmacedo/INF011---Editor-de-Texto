package inf011.builders;

import java.io.File;

import inf011.interfaces.IBuilder;

public class CppBuilder implements IBuilder {

	@Override
	public void build(String filePath) {
		try {	
			String cmd = this.setCommand(filePath);			
		    Process process = Runtime.getRuntime().exec(cmd);
		    process.waitFor();
		}catch (Exception e) {
		    System.out.println("CPP Builder - Execution Error : " + e);
		}		
	}
	//A linha de comando não é montada no FileService para manter a modularidade do builder 
	private String setCommand(String filePath) {
		String name = new File(filePath).getName();
		
		int lastBarIndex = filePath.lastIndexOf(name);			
		String pathWithAnBar = filePath.substring(0, lastBarIndex++);
		
		int dotIndex = name.lastIndexOf('.');			
		String exeName = name.substring(0, dotIndex);
		
		String exePath = pathWithAnBar + exeName;
		
		return "g++"+ ' ' + filePath + ' ' + "-o" + ' ' + exePath;
	}
}
