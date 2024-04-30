import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Driver {

   private static Image raccoonImage;
   private static final Color RED = new Color(255, 0, 0);
   private static final Color WHITE = new Color(255, 255, 255);



   public static void main(String[] args) throws FileNotFoundException, IOException {

	// Read file and call stop detection
       TripPoint.readFile("triplog.csv");
       TripPoint.h1StopDetection();
       TripPoint.h2StopDetection();

       raccoonImage = ImageIO.read(new File("raccoon.png"));

    // Set up frame, include your name in the title
       JFrame mainFrame = new JFrame("Project 5 - Cade Ward");
       mainFrame.setSize(1600, 900);
       mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Set up Panel for input selections
       JPanel ctlPanel = new JPanel();
       ctlPanel.setLayout(new FlowLayout());

    // Play Button
       JButton playButton = new JButton("Play");
       playButton.setBackground(WHITE);

    // CheckBox to enable/disable stops
       JCheckBox stopsCheckBox = new JCheckBox("Stops");     
       JComboBox<Integer> timeComboBox = new JComboBox<Integer>();    

    // ComboBox to pick animation time
       Arrays.asList(5, 15, 30, 60, 90).forEach(timeComboBox::addItem);

       timeComboBox.setEnabled(true);
       timeComboBox.setBackground(WHITE);

    // Add all to top panel
       ctlPanel.add(playButton);
       ctlPanel.add(timeComboBox);
       ctlPanel.add(stopsCheckBox);

    // Set up mapViewer
       JMapViewer mapView = new JMapViewer();
       mapView.setTileSource(new OsmTileSource.TransportMap());
       mapView.setCenter(new Point(400, 800));
       mapView.setZoom(6);

    // Set the map center and zoom level
       mainFrame.setVisible(true);
       mainFrame.setLayout(new BorderLayout());
       mainFrame.add(ctlPanel, BorderLayout.NORTH);
       mainFrame.add(mapView, BorderLayout.CENTER);      

    // Add listeners for GUI components
       ActionListener playActionListener = new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) 
           {
               mapView.removeAllMapMarkers();
               mapView.removeAllMapPolygons();

               int animationInterval = timeComboBox.getSelectedIndex() * 15;
               boolean includeStops = stopsCheckBox.isSelected();
               ArrayList<TripPoint> tripPoints;

               if (includeStops) 
               {
                   tripPoints = TripPoint.getTrip();
               } 
               else 
               {
                   tripPoints = TripPoint.getMovingTrip();
               }

               javax.swing.Timer animaTimer = new javax.swing.Timer(animationInterval, new ActionListener() {
                   int currentIndex = 0;
                   Coordinate currentCoord;
                   Coordinate previousCoord;

                   @Override
                   public void actionPerformed(ActionEvent e) {
                	 
                       currentCoord = new Coordinate(tripPoints.get(currentIndex).getLat(), tripPoints.get(currentIndex).getLon());
                       mapView.removeAllMapMarkers();
                       mapView.addMapMarker(new IconMarker(currentCoord, raccoonImage));

                       if(previousCoord != null)
                       {    
                           Coordinate[] coordinates = new Coordinate[]{previousCoord, currentCoord, currentCoord};
                           MapPolygonImpl pathLine = new MapPolygonImpl(coordinates);
                           System.out.println(pathLine.getPoints());

                           pathLine.setBackColor(RED);
                           pathLine.setColor(RED);
                           pathLine.setVisible(true);
                           pathLine.setStroke(new BasicStroke(4));                         
                           mapView.addMapPolygon(pathLine);
                       }
                       mapView.setMapMarkerVisible(true);

                       if (currentIndex < tripPoints.size()){
                           previousCoord = currentCoord;
                           currentIndex++;
                       }
                   }
               });
               animaTimer.start();
           }
       };
       playButton.addActionListener(playActionListener);
       System.out.println(":)");
   }
}
