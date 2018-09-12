package remoteobjects;
import java.io.File;
import java.io.Serializable;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import java.text.SimpleDateFormat;


public class FileMetadata implements Serializable {
	
	private String fileName;
    private String creationTime; 
    private String lastAccessTime; 
    private String lastModifiedTime; 
    private long size;
    
    public FileMetadata(File f) {
        Path path = f.toPath();
        try {            
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            this.creationTime = attr.creationTime().toString();
            this.lastAccessTime = attr.lastAccessTime().toString();
            this.lastModifiedTime = attr.lastModifiedTime().toString();
            this.size = attr.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FileMetadata(String filename) {
    	this.fileName = filename;
    	File f = new File(filename);
    	Path path = f.toPath();
    	try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            this.creationTime = attr.creationTime().toString();
            this.lastAccessTime = attr.lastAccessTime().toString();
            this.lastModifiedTime = attr.lastModifiedTime().toString();
            this.size = attr.size();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    

    public long getSize(){
        return this.size;
    }

    public FileTime getCreationDate(){
        return this._getAtrrToFileTime(this.creationTime);
    }

    public FileTime getLastAccessTime(){
        return this._getAtrrToFileTime(this.lastAccessTime);
    }

    public FileTime getLastModifiedTime(){
        return this._getAtrrToFileTime(this.lastModifiedTime);
    }

    private FileTime _getAtrrToFileTime(String attr) {
        try {
            long milis = new SimpleDateFormat("YYYY-MM-DDThh:mm:ss[.s+]Z").parse(attr).getTime();
            return FileTime.fromMillis(milis);    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
       
    }
}
