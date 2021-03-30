package inf011.builders;

import java.io.File;

import inf011.interfaces.IBuilder;

public class CppBuilder implements IBuilder {

	@Override
	public void run(String filePath) {
		try {	
			File file = new File(filePath);
			
			String name = file.getName();
			int i = name.lastIndexOf('.');
			String exeName = name.substring(0, i) + ".exe";
			String path = file.getParent();
			
			String cmd = "g++"+ ' ' + filePath + ' ' + "-o" + ' ' + path + '\\' + exeName;
			
		    Process process = Runtime.getRuntime().exec(cmd);
		    process.waitFor();
		}catch (Exception e) {
		    System.out.println("CPP Builder - Execution Error : " + e);
		}		
	}
}
