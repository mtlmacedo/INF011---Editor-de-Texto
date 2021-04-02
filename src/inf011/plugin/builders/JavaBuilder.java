package inf011.plugin.builders;

import inf011.interfaces.IBuilder;

public class JavaBuilder implements IBuilder {
	@Override
	public void build(String filePath) {
		try {			
		    Process process = Runtime.getRuntime().exec("javac " + filePath);
		    process.waitFor();
		}catch (Exception e) {
		    System.out.println("JavaBuilder - Execution Error : " + e);
		}		
	}
}
