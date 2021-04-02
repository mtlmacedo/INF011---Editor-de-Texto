package inf011.plugin.builders;

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
