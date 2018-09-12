package remoteobjects;
import java.io.File;
import java.io.Serializable;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import java.text.SimpleDateFormat;


public class FileMetadata implements Serializable {
	
	public static final int CLOSED = 0;
	public static final int OPENED = 1;
	
	private String fileName;
    private String creationTime; 
    private String lastAccessTime; 
    private String lastModifiedTime; 
    private long size;
    private int status;
    
    public FileMetadata(File f, String file_name) {
        Path path = f.toPath();
        this.fileName = file_name;
        try {            
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            this.creationTime = attr.creationTime().toString();
            this.lastAccessTime = attr.lastAccessTime().toString();
            this.lastModifiedTime = attr.lastModifiedTime().toString();
            this.size = attr.size();
            this.status = CLOSED;
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
    
    public String getFileName() {
    	return this.fileName;
    }

    public int getStatus(){
    	return this.status; 
    }
    
    public void setStatus(int status) {
    	this.status = status;
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
        	long milis = new SimpleDateFormat("YYYY-MM-DD").parse(attr).getTime();
            return FileTime.fromMillis(milis);    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
       
    }
}
