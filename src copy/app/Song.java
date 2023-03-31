package app;

// Author: Akin Aksay
public class Song {

    private String title;
	private String artist;
	private String album;
	private String year;

    // Constructor 1
    public Song(String title, String artist, String album, String year) {

        this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;

    }
    // Constructor 2
    public Song(String title, String artist, String album) {

        this.title = title;
		this.artist = artist;
		this.album = album;

    }

    // Constructor 3
    public Song(String title, String artist) {

        this.title = title;
		this.artist = artist;

    }

    // Getter methods 
    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getYear() {
        return this.year;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
}
