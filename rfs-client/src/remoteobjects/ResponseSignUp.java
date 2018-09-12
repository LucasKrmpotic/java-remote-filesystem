package remoteobjects;
import java.io.Serializable;


public class ResponseSignUp extends RFSCommand{
	
	private String[] availableFiles;
	
	public ResponseSignUp() {
		
	}
	
	public void setAvailableFiles(String[] availableFiles) {
		
		for (int i = 0; i < availableFiles.length; i++) {
			this.availableFiles[i] = availableFiles[i];
		}
		
	}
	
}
