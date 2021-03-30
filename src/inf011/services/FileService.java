package inf011.services;

import inf011.factorys.JavaFactory;

public class FileService {
	
	public boolean isValid(String filePath) throws Exception {				
		
		String[] validExtensions = new JavaFactory().supportedExtensions();
		int count = 0;
			   
		while(count < validExtensions.length) {
			String temp = validExtensions[count];
			if( temp.equals(this.getExtension(filePath))) {
				return true;
			}
				count++;
		}			
		return false;
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
