package models;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import remoteobjects.FileMetadata;

public class FileModel {
	String csvFile = "files.csv";
	
	public FileModel() {
		
	}
	
	public List<FileMetadata> filterByOwner(String owner) {
        String csvFile = this.csvFile;
        String line = "";
        String cvsSplitBy = ",";
        List<FileMetadata> files = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                   
                String[] file = line.split(cvsSplitBy);

                if (file[1].equals(owner)){
                	files.add(new FileMetadata(file[0]));
                }
            }
            if(files.isEmpty()) {
            	return null;
            }
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null; 
	}
	
}
