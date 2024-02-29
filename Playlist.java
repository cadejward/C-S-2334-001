import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Playlist {
	
	private ArrayList<Song> songs;
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	public Playlist() {
		// TODO: Initialize the songs field.
		songs = new ArrayList<>();
	}
	
	public Playlist (String filename) throws IOException
	{
		this();
		addSongs(filename);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		// TODO: Update the Lab 3 method.
		if(song == null || index < 0 || index > getNumSongs())
		{
			return false;
		}
		else
		{
			songs.add(index, song);
			return true;
		}
	}
	
	public int addSongs(Playlist playlist) {
		// TODO: Update the Lab 3 method.
		if (playlist == null)
		{
			return 0;
		}
		
		ArrayList<Song> songToAdd = (playlist == this) ? new ArrayList<>(songs) : playlist.songs;
		int numAddedSongs = 0;
		for(Song song : songToAdd)
		{
			if(addSong(song)) 
			{
				numAddedSongs++;
			}
		}
		return numAddedSongs;
	}
	
	public int addSongs(String filename) throws IOException
	{
		int numAddedSongs = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				Song song = new Song(line);
				if (addSong(song))
				{
					numAddedSongs++;
				}
			}
		}
		return numAddedSongs;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		// TODO: Update the Lab 3 method.
		if(index < 0 || index >= getNumSongs())
		{
			return null;
		}
		return songs.remove(index);
	}
	
	public void saveSongs(String filename)throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(this.toString());
			bw.close();
	}

	public String toString()
	{
		StringJoiner joiner = new StringJoiner(System.lineSeparator());
		for (Song song : songs)
		{
			joiner.add(song.toString());
		}
		return joiner.toString();
	}
	
	public int[] getTotalTime() {
        int totalSeconds = 0;
        for (Song song : songs) {
            int[] time = song.getTime();
            totalSeconds += time[0] * 60 + time[1];
        }

        int totalMinutes = totalSeconds / 60;
        int remainingSeconds = totalSeconds % 60;

        int totalHours = totalMinutes / 60;
        int remainingMinutes = totalMinutes % 60;

        return new int[]{totalHours, remainingMinutes, remainingSeconds};
    }
	
	public static void main(String[] args) {
        
        try {
            Playlist playlist = new Playlist("your_file.txt");
            System.out.println(playlist);
            playlist.saveSongs("output_file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
