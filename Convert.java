import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Convert {
    public static void convertFile(String filename) {
        try {
        	
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            FileWriter writer = new FileWriter("triplog.csv");

            writer.write("Time,Latitude,Longitude\n");

            String line;
            int time = 0;

            Pattern latPattern = Pattern.compile("lat=\"(.*?)\"");
            Pattern lonPattern = Pattern.compile("lon=\"(.*?)\"");

            while ((line = reader.readLine()) != null) {
                
                if (line.trim().isEmpty()) {
                    continue;
                }

                Matcher latMatcher = latPattern.matcher(line);
                Matcher lonMatcher = lonPattern.matcher(line);

                if (latMatcher.find() && lonMatcher.find()) {
                    String latitude = latMatcher.group(1).replaceAll("\\s|\\?", "");
                    String longitude = lonMatcher.group(1).replaceAll("\\s|\\?", "");

                    writer.write(time + "," + latitude + "," + longitude + "\n");
                    time += 5;
                }
            }

            reader.close();
            writer.close();

            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


