package inf011.builders;

import inf011.interfaces.IBuilder;

public class JavaBuilder implements IBuilder {
	@Override
	public void run(String filePath) {
		try {			
		    Process process = Runtime.getRuntime().exec("javac " + filePath);
		    process.waitFor();
		}catch (Exception e) {
		    System.out.println("JavaBuilder - Execution Error : " + e);
		}		
	}
}
