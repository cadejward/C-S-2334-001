import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private final String INFO_DELIMITER = "; ";
	private final String TIME_DELIMITER = ":";
	private final int IDX_TITLE = 0;
	private final int IDX_ARTIST = 1;
	private final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song (String info)
	{
		String[] split = info.split(INFO_DELIMITER);
		this.title = split[IDX_TITLE];
		this.artist = split[IDX_ARTIST];
		String[] timeSplit = split[IDX_TIME].split(TIME_DELIMITER);
		this.time = new int[timeSplit.length];
		for (int i = 0; i < timeSplit.length; i++)
		{
			this.time[i] = Integer.parseInt(timeSplit[timeSplit.length - i - 1]);
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}

	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
	        sb.append(title).append(INFO_DELIMITER).append(artist).append(INFO_DELIMITER);
	        if (time.length == 3) {
	            sb.append(String.format("%d:%02d:%02d", time[2], time[1], time[0]));
	        } else if (time.length == 2) {
	            sb.append(String.format("%d:%02d", time[1], time[0]));
	        } else if (time.length == 1) {
	            sb.append(time[0]);
	        }
	        return sb.toString();
	    }
}