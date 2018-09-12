package remoteobjects;

import java.util.List;
import java.util.ArrayList;

public class ResponseLogin extends RFSCommand{

	private static final long serialVersionUID = 1L;
	public List<FileMetadata> availableFiles;
	
	public void setAvailableFiles(List<FileMetadata> files) {
		if (availableFiles.isEmpty()) {
			availableFiles = new ArrayList<FileMetadata>(files);
		}
	}
		
	
}
