package inf011.services;

public class FileService {
		
	public String getExtension(String filePath) throws Exception {
		int i = filePath.lastIndexOf('.');
		if (i > 0) {
			return filePath.substring(i+1);
		}else {
			throw new Exception("Invalid Path");
		}	
	}	
}
