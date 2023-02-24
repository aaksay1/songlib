## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder/File Structure

- `src`: the folder to maintain sources
- `app`: This is where the main class as well as the Song object is
- `view`: This is where the controller files as well as the .fxml file is
- `songList.txt`:  The file that collects the song details

## How does it work?

You must have a file called songList.txt which is an empty .txt file that collects each songs title, artist, and optionally its album and year.

The UI has 3 important parts, one of them is called Song List which is the list of songs, you can move from song to song using up and down key buttons as well as clicking each song with your mouse. 

Each entry shows the title of the song as well as its artist. If you want more details about the selected song you should look to the right and look under the Selected Song where you will see details such as the album name and the year of the song as well as the artist and title of the song.

There are three buttons:

    The Add button -> the way this button works is, there are 4 TextFields Song Title, Artist, Album and Year. To add a song you should fill those textfields and press the add button which will then ask you to confirm whether or not you want to add this song, press OK and you will have a new song in your library.

    The Delete button -> the way this button works is very simple, when you select a song and press on the delete button, the program would remove that song from the library and will select the next song.
    
    The Edit Song button -> This button works similar to The Add button in the sense that you will have to use TextFields in order to make an edit to the song. To edit a song, first select a song, then fill up those 4 fields with the edits you want to make, then click on Edit Song.

Constraints:

    1) You can't add a song without its title and artist, the year and album are optional.

    2) Add button would not work if you want to add a song with the same title and artist.

    3) Edit button would not work if you want to edit a song and leave the title and artist fields empty.

    4) You can't use the delete button if the library is empty.

    5) You can't add/edit a song with a year that is not made up of numbers or one that is not four digits. You can't add a year such as "12345" or "abcd"# songlib
# songlib
# songlib
