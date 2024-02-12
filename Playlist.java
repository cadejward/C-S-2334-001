import java.util.Arrays;
public class Playlist {
	private static final int MIN_CAPACITY = 3;
    private final Song[] songs;
    private int numSongs;

    public Playlist() {
        this(MIN_CAPACITY);
    }

    public Playlist(int capacity) {
        capacity = Math.max(capacity, MIN_CAPACITY);
        this.songs = new Song[capacity];
        this.numSongs = 0;
    }

    public int getCapacity() {
        return songs.length;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public Song getSong(int index) {
        if (index < 0 || index >= numSongs) {
            return null;
        }
        return songs[index];
    }

    public Song[] getSongs() {
        return Arrays.copyOf(songs, numSongs);
    }

    public boolean addSong(Song song) {
        return addSong(numSongs, song);
    }

    public boolean addSong(int index, Song song) {
        if (index < 0 || index > numSongs || numSongs == getCapacity() || song == null) {
            return false;
        }

        System.arraycopy(songs, index, songs, index + 1, numSongs - index);

        songs[index] = song;
        numSongs++;
        return true;
    }

    public int addSongs(Playlist playlist) {
        if (playlist == null) {
            return 0;
        }

        int addedSongs = 0;
        for (Song song : playlist.getSongs()) {
            if (addSong(song)) {
                addedSongs++;
            }
        }
        return addedSongs;
    }

    public Song removeSong() {
        if (numSongs == 0) {
            return null;
        }
        Song removedSong = songs[numSongs - 1];
        songs[numSongs - 1] = null;
        numSongs--;
        return removedSong;
    }

    public Song removeSong(int index) {
        if (index < 0 || index >= numSongs) {
            return null;
        }

        Song removedSong = songs[index];

        
        System.arraycopy(songs, index + 1, songs, index, numSongs - index - 1);

       
        songs[numSongs - 1] = null;

        numSongs--;
        return removedSong;
    }

}
