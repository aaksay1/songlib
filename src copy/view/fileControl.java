package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import app.Song;


// Author: Akin Aksay
// Author: Manupreeth Vallala
public class fileControl implements Comparator<Song>{

    public int compare(Song song1, Song song2) {

        if(song1.getTitle().equalsIgnoreCase(song2.getTitle())) {

            return song1.getArtist().toLowerCase().compareTo(song2.getArtist().toLowerCase());
        }
		return song1.getTitle().toLowerCase().compareTo(song2.getTitle().toLowerCase());
	}

    public void writeToTheFile(String filePathName, List<Song> songs){

        fileControl comparator = new fileControl();
        songs.sort(comparator);

        PrintWriter file;
        try
        {
            file = new PrintWriter(filePathName);
                          
            for(Song song: songs){
                file.println(song.getTitle()); 
                file.println(song.getArtist());
                file.println(song.getAlbum());
                file.println(song.getYear());
                       
            }
            file.close();
            
        }
        catch (Exception except)

        {
            except.printStackTrace();
        }

    }

    public List<Song> readFromFile(String filePathName) {

        ArrayList<Song> songs = new ArrayList<>();

        BufferedReader reader;
        Path filePath = Paths.get(filePathName);

        File file = new File(filePathName);

        if(!file.exists()) {
            return songs;
        }

		try {

            reader = Files.newBufferedReader(filePath);
            String line = reader.readLine();

            while(line != null) {

                String title = line;
						
                line = reader.readLine();
                String artist = line;
                    
                line = reader.readLine();
                String album = line;
                    
                line = reader.readLine();
                String year = line;
                    
                Song temp = new Song(title, artist, album, year);

                if(!title.isEmpty() && !artist.isEmpty() && !duplicate(temp, songs)) {
                    songs.add(temp);
                }
                    
                line = reader.readLine(); 
            } 
	
				
		}catch (Exception e) {
			e.printStackTrace();
		}

        fileControl comparator = new fileControl();
        songs.sort(comparator);
        return songs;

    }

    public boolean duplicate(Song s, List<Song> obsList) {

        String title = s.getTitle();
        String artist = s.getArtist();

        for(Song song: obsList) {
            if(title.equalsIgnoreCase(song.getTitle().toLowerCase()) &&
            artist.equalsIgnoreCase(song.getArtist().toLowerCase())) {
                return true;
            } 
        }
        return false;
        
    }

}
