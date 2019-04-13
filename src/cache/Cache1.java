package cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.Part;

public class Cache1 {

	public String getFilename(Part justification) {
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("/","");
				return filename.substring(filename.lastIndexOf('\\')+1, filename.length() - 1);
			}
		}
		return null;
	}
	
	public String moveFileToUploads(Part justification){
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\\","/");
				filename = filename.substring(1, filename.length()-1);
				File file = new File(filename);

				String extension = file.getName().substring(file.getName().indexOf(".") + 1);
				String fileName = file.getName().substring(0, file.getName().indexOf("."));
				
				String newPath = "C:\\Users\\KDragon\\git\\AbsencesManager-web-app\\WebContent\\uploads\\" + fileName + "_" + (new Date()).getTime() + "." + extension;
				File newFile = new File(newPath);
				
				try {
					
					FileInputStream inStream = new FileInputStream(file);
					FileOutputStream outStream = new FileOutputStream(newFile);
		        	
		    	    byte[] buffer = new byte[1024];
		    		
		    	    int length;
		    	    //copy the file content in bytes 
		    	    while ((length = inStream.read(buffer)) > 0){
		    	  
		    	    	outStream.write(buffer, 0, length);
		    	 
		    	    }
		    	 
		    	    inStream.close();
		    	    outStream.close();
		    	    
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return newPath;
			}
		}
		return null;
	}
	
}
