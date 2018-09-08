package org.geotools.tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.factory.Hints;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.StyleLayer;
import org.geotools.styling.ChannelSelection;
import org.geotools.styling.ContrastEnhancement;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.SelectedChannelType;
import org.geotools.swing.action.SafeAction;
import org.opengis.style.ContrastMethod;

public class finalprog extends JFrame implements ActionListener{
	JComboBox<String> sr1;
	JComboBox<String> ds1;
	JLabel sr;
	JLabel ds;
	JButton b1;
	JLabel pic;
	Timer tm;
	int x = 0;
	String[] list = {
			"C:/Users/Nimit Johri/Desktop/images/img1.jpg",       
			"C:/Users/Nimit Johri/Desktop/images/img2.jpg",       
			"C:/Users/Nimit Johri/Desktop/images/img3.jpg",       

	};

	private StyleFactory sf = CommonFactoryFinder.getStyleFactory();
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
    private enum GeomType { POINT, LINE, POLYGON };
    private JMapFrame frame;
    private GridCoverage2DReader reader;
    private static final Color LINE_COLOUR = null;
    private static final Color FILL_COLOUR = Color.CYAN;
    private static final Color SELECTED_COLOUR = Color.YELLOW;
    private static final float OPACITY = 1.0f;
    private static final float LINE_WIDTH = 2.0f;
    private static final float POINT_SIZE = 10.0f;
    String source;
    String destination;
    private String geometryAttributeName;
    private GeomType geometryType;
    SimpleFeatureSource shapefileSource;
    SimpleFeatureSource shapefileSource1;
    SimpleFeatureSource shapefileSource2;
    Filter filter;
	finalprog()
	{sr=new JLabel("Source");
	ds=new JLabel("Destination");
		sr1=new JComboBox<String>();
		ds1=new JComboBox<String>();
		sr1.addItem("First Block");
		sr1.addItem("Second Block");
		sr1.addItem("Third Block");
		sr1.addItem("Fourth Block");
		sr1.addItem("Fifth Block");
		sr1.addItem("Sixth Block");
		sr1.addItem("Seventh Block");
		sr1.addItem("Eightth Block");
		sr1.addItem("Ninth Block");
		sr1.addItem("Tenth Block");
		sr1.addItem("Food Court(FC)");
		//sr1.addItem("Food Court(FC)Kitchen");
		sr1.addItem("IT Tower");
		sr1.addItem("Iburpp");
		sr1.addItem("Hostel Food Area");
		sr1.addItem("Research And Development Block");
		sr1.addItem("College Girls Hostel");
		sr1.addItem("College Boys Hostel");
		sr1.addItem("Main Amphitheatre");
		sr1.addItem("Gandhi Chowk");
		sr1.addItem("Parking Area 1");
		sr1.addItem("Placement Cell");
		sr1.addItem("Library");
		sr1.addItem("Cafeteria");
		sr1.addItem("Playground");
		sr1.addItem("Entry Gate");

		//sr1.addItem("Food Court(FC)Kitchen");
		//sr1.addItem("Non-CIT Labs");
		//sr1.addItem("Mechanical Labs");
		//sr1.addItem("Managemengt Development Center");
		//sr1.addItem("Parking area for tatra car,upes fire service");
		//sr1.addItem("Parking Lane Zone 1");
		//sr1.addItem("Parking Area 1");
		//sr1.addItem("Women Empowerment Block");
		//sr1.addItem("Parking Lane Zone 2");
		//sr1.addItem("Ninth Block Extension 1");
		//sr1.addItem("Ninth Block Extension 2");
		//sr1.addItem("ONGC Museum");
		//sr1.addItem("Mess of college hostel");
		//sr1.addItem("Electrical Sub Station");
		//sr1.addItem("MIG 23 Green Area");
		//sr1.addItem("WasteLand Area");
		//sr1.addItem("Library Front Green Area");
		//sr1.addItem("ONGC Museum Back Green Area");
		//sr1.addItem("FC Back Green Area");
		
//ds1.setEditable(true);
//ds1.addItem("Energy Block");
ds1.addItem("First Block");
ds1.addItem("Second Block");
ds1.addItem("Third Block");
ds1.addItem("Fourth Block");
ds1.addItem("Fifth Block");
ds1.addItem("Sixth Block");
ds1.addItem("Seventh Block");
ds1.addItem("Eightth Block");
ds1.addItem("Ninth Block");
ds1.addItem("Tenth Block");
ds1.addItem("Food Court(FC)");
ds1.addItem("Food Court(FC)Kitchen");
ds1.addItem("IT Tower");
ds1.addItem("Iburpp");
ds1.addItem("Hostel Food Area");
ds1.addItem("Research And Development Block");
ds1.addItem("College Girls Hostel");
ds1.addItem("College Boys Hostel");
ds1.addItem("Main Amphitheatre");
ds1.addItem("Gandhi Chowk");
ds1.addItem("Parking Area 1");
ds1.addItem("Placement Cell");
ds1.addItem("Library");
ds1.addItem("Cafeteria");
ds1.addItem("Playground");
ds1.addItem("Entry Gate");

//ds1.addItem("Non-CIT Labs");
//ds1.addItem("Mechanical Labs");
//ds1.addItem("Enrollment Office");
//ds1.addItem("Managemengt Development Center");
//ds1.addItem("Exit gate");
//ds1.addItem("Parking area for tatra car,upes fire service");
//ds1.addItem("Parking Lane Zone 1");
//ds1.addItem("Amphitheatre");
//ds1.addItem("Women Empowerment Block");
//ds1.addItem("Parking Lane Zone 2");
//ds1.addItem("Ninth Block Extension 1");
//ds1.addItem("Ninth Block Extension 2");
//ds1.addItem("ONGC Museum");
//ds1.addItem("Mess of college hostel");
//ds1.addItem("Electrical Sub Station");
//ds1.addItem("MIG 23 Green Area");
//ds1.addItem("WasteLand Area");
//ds1.addItem("Library Front Green Area");
//ds1.addItem("ONGC Museum Back Green Area");
//ds1.addItem("FC Back Green Area");
//ds1.addItem("Research And Development Green Area");
b1=new JButton("Calculate Path");
b1.addActionListener(this);
pic = new JLabel();
pic.setBounds(40, 30, 200,100);



//Call The Function SetImageSize
SetImageSize(2);



//set a timer
tm = new Timer(500,new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        SetImageSize(x);
        x += 1;
        if(x >= list.length )
            x = 0; 
    }
});
BorderLayout g=new BorderLayout();
setLayout(g);
JPanel p1=new JPanel();
JPanel p2=new JPanel();

p1.add(pic);
add("North",p1);
tm.start();
//setLayout(null);
//	setLayout(new GridLayout(2,2));
	p2.add(sr);
	p2.add(sr1);
	p2.add(ds);
	p2.add(ds1);
	p2.add(b1);
	add("Center",p2);

	setSize(800, 400);
	getContentPane().setBackground(Color.decode("#CCFFFF"));
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);


setTitle("Choose Source & Destination");
	}
	 public void SetImageSize(int i){
	        ImageIcon icon = new ImageIcon(list[i]);
	        Image img = icon.getImage();
	        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon newImc = new ImageIcon(newImg);
	        pic.setIcon(newImc);
	    }

	public static void main(String args[]) throws Exception
	{
		finalprog q=new finalprog();
	//	q.getLayersAndDisplay();
		//q.setVisible(true);
		//q.setSize(220,200);
		//q.setTitle("Choose Source & Destination");
		}

	private void getLayersAndDisplay() throws Exception {
    	File imageFile = new File("C:/Users/Nimit Johri/Desktop/upes12.tif");
displayLayers(imageFile);
}
    private void displayLayers(File rasterFile) throws Exception {
        try{
    	AbstractGridFormat format = GridFormatFinder.findFormat( rasterFile ); 
        //this is a bit hacky but does make more geotiffs work
        Hints hints = new Hints();
        if (format instanceof GeoTiffFormat) {
            hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
        }
        reader = format.getReader(rasterFile, hints);
        

        // Initially display the raster in greyscale using the
        // data from the first image band
        Style rasterStyle = createRGBStyle();//createGreyscaleStyle(1);

        // Connect to the shapefile
        File shpFile = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/GREEN.shp");
        File shpFile1 = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/POLYGON.shp");
        File shpFile2 = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/Road_campus.shp");
        
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(shpFile);
         shapefileSource = dataStore
                .getFeatureSource();
         FileDataStore dataStore1 = FileDataStoreFinder.getDataStore(shpFile1);
          shapefileSource1 = dataStore1
                 .getFeatureSource();
         FileDataStore dataStore2 = FileDataStoreFinder.getDataStore(shpFile2);
         shapefileSource2 = dataStore2
                 .getFeatureSource();
         setGeometry(); 
         final MapContent map = new MapContent();
         map.setTitle("ImageLab");
         
if(source.equalsIgnoreCase("Entry Gate") && destination.equalsIgnoreCase("Enrollment Office"))
{         filter=ECQL.toFilter("IN('Road_campus.2')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.46','Road_campus.81')");
	
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.55')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.24','Road_campus.25','Road_campus.26')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.24','Road_campus.25','Road_campus.27','Road_campus.48','Road_campus.50','Road_campus.56','Road_campus.66','Road_campus.68')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Seventh Block")){

	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.47','Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.24','Road_campus.23','Road_campus.21','Road_campus.71','Road_campus.70')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.155','Road_campus.16','Road_campus.12','Road_campus.10','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.155','Road_campus.16','Road_campus.34','Road_campus.33','Road_campus.32')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.148','Road_campus.150','Road_campus.151')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.47','Road_campus.48','Road_campus.50','Road_campus.57','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Playground")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.148','Road_campus.149')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.155','Road_campus.16','Road_campus.12','Road_campus.10','Road_campus.7')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.104')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.46','Road_campus.81')");
	
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.55')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.67')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.69','Road_campus.70','Road_campus.80')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.47','Road_campus.27','Road_campus.24','Road_campus.23','Road_campus.22','Road_campus.20','Road_campus.16','Road_campus.12','Road_campus.10','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.41','Road_campus.40','Road_campus.38','Road_campus.37','Road_campus.35','Road_campus.33','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.148','Road_campus.150','Road_campus.151')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.159','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.57','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.46','Road_campus.47','Road_campus.27','Road_campus.24','Road_campus.23','Road_campus.22','Road_campus.20','Road_campus.16','Road_campus.12','Road_campus.10','Road_campus.7')");
}
else if(source.equalsIgnoreCase("Second Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.81','Road_campus.46','Road_campus.41','Road_campus.40','Road_campus.39','Road_campus.158','Road_campus.104')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Playground")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.148','Road_campus.149')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.55')");
	
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.55')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.131','Road_campus.133')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.69','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.53','Road_campus.153','Road_campus.50','Road_campus.48','Road_campus.27','Road_campus.25','Road_campus.23','Road_campus.22','Road_campus.20','Road_campus.16','Road_campus.13','Road_campus.6','Road_campus.4','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.51','Road_campus.39','Road_campus.38','Road_campus.37','Road_campus.35','Road_campus.33','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.145')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.52','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.53','Road_campus.153','Road_campus.57','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.53','Road_campus.153','Road_campus.50','Road_campus.48','Road_campus.27','Road_campus.24','Road_campus.23','Road_campus.22','Road_campus.20','Road_campus.16','Road_campus.12','Road_campus.10','Road_campus.7')");
}
else if(source.equalsIgnoreCase("Third Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.154','Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.24','Road_campus.25','Road_campus.26')");
	
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.67')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.131','Road_campus.133')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.69','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.50','Road_campus.48','Road_campus.47','Road_campus.41','Road_campus.40','Road_campus.38','Road_campus.37','Road_campus.33','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.145')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.64','Road_campus.63','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7')");
}
else if(source.equalsIgnoreCase("Fourth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.66','Road_campus.56','Road_campus.52','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.159','Road_campus.104')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.50','Road_campus.49','Road_campus.46','Road_campus.45')");
	
}

else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.153','Road_campus.56','Road_campus.66','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.68')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.131','Road_campus.133')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.69','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.50','Road_campus.48','Road_campus.47','Road_campus.41','Road_campus.40','Road_campus.38','Road_campus.37','Road_campus.33','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.144','Road_campus.145')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN(''Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.64','Road_campus.63','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7')");
}
else if(source.equalsIgnoreCase("Fifth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.55','Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.66','Road_campus.56','Road_campus.52','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.159','Road_campus.104')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.93','Road_campus.94')");
	
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.83','Road_campus.78','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_ca'Road_campus.32'mpus.87','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94','Road_campus.69','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.100','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.91','Road_campus.92','Road_campus.159','Road_campus.104','Road_campus.43','Road_campus.42','Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.144,'Road_campus.146','Road_campus.148','Road_campus.150','Road_campus.151')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.100','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.94','Road_campus.57','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.91','Road_campus.92','Road_campus.159','Road_campus.104','Road_campus.43','Road_campus.42','Road_campus.30','Road_campus.28','Road_campus.8','Road_campus.4','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Sixth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.91','Road_campus.92',Road_campus.159',Road_campus.104')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.93','Road_campus.95')");
	
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.55','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.68','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.95')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.89','Road_campus.88','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.83','Road_campus.78','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_ca'Road_campus.32'mpus.87','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95','Road_campus.69','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.100','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.53','Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95','Road_campus.69','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.159','Road_campus.104','Road_campus.43','Road_campus.42','Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.122')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.144,'Road_campus.146','Road_campus.148','Road_campus.150','Road_campus.151')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.103','Road_campus.102','Road_campus.100','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.153','Road_campus.154','Road_campus.56','Road_campus.66','Road_campus.67','Road_campus.52','Road_campus.160','Road_campus.161','Road_campus.93','Road_campus.95','Road_campus.57','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.159','Road_campus.104','Road_campus.43','Road_campus.42','Road_campus.30','Road_campus.28','Road_campus.8','Road_campus.4','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Seventh Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.93','Road_campus.160','Road_campus.90','Road_campus.92',Road_campus.159',Road_campus.104')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.45','Road_campus.47','Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
	
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.53','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.93','Road_campus.87','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.83','Road_campus.78','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.89','Road_campus.88','Road_campus.86','Road_campus.85','Road_campus.84','Road_campus.83','Road_campus.78','Road_campus.77')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.77','Road_campus.75','Road_campus.73','Road_campus.72','Road_campus.80','Road_campus.70')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.124','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.100','Road_campus.101')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.124','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.113','Road_campus.114','Road_campus.108','Road_campus.109')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.77','Road_campus.75','Road_campus.73','Road_campus.72','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7','Road_campus.5','Road_campus.3','Road_campus.2','Road_campus.1')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.142')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.137')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.135','Road_campus.157','Road_campus.138','Road_campus.141','Road_campus.143')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.124','Road_campus.83','Road_campus.82','Road_campus.79','Road_campus.51','Road_campus.39','Road_campus.38','Road_campus.37','Road_campus.33','Road_campus.31')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.119','Road_campus.118','Road_campus.144','Road_campus.146','Road_campus.148','Road_campus.150','Road_campus.151')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.124','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.97','Road_campus.98','Road_campus.99')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.77','Road_campus.75','Road_campus.74','Road_campus.60','Road_campus.58','Road_campus.61','Road_campus.62')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.77','Road_campus.75','Road_campus.73','Road_campus.72','Road_campus.80','Road_campus.71','Road_campus.19','Road_campus.14','Road_campus.7')");
}
else if(source.equalsIgnoreCase("Eightth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.124','Road_campus.84','Road_campus.85','Road_campus.86','Road_campus.87','Road_campus.160','Road_campus.90','Road_campus.92','Road_campus.159','Road_campus.140')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.48','Road_campus.50','Road_campus.153','Road_campus.53','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
	
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.54','Road_campus.154','Road_campus.52','Road_campus.79','Road_campus.82','Road_campus.83','Road_campus.124','Road_campus.125','Road_campus.127','Road_campus.128','Road_campus.130')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.76','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.67','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.68','Road_campus.66','Road_campus.56','Road_campus.153','Road_campus.59','Road_campus.60','Road_campus.75','Road_campus.78','Road_campus.83','Road_campus.84','Road_campus.122','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.94','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.95','Road_campus.96','Road_campus.119','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.126','Road_campus.125','Road_campus.123','Road_campus.121','Road_campus.120','Road_campus.118','Road_campus.117','Road_campus.116')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Tenth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Ninth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("First Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
	
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Third Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Fourth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Fifth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Sixth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Seventh Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Eightth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Ninth Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Food Court(FC)")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("IT Tower")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Iburpp")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Hostel Food Area")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Research And Development Block")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("College Girls Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("College Boys Hostel")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Main Amphitheatre")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Gandhi Chowk")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Placement Cell")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Library")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Cafeteria")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Entry Gate")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}
else if(source.equalsIgnoreCase("Tenth Block") && destination.equalsIgnoreCase("Parking Area 1")){
	filter=ECQL.toFilter("IN('Road_campus.31','Road_campus.32')");
}

//create a SimpleFeatureCollection object for the filtered features
       SimpleFeatureCollection fc=shapefileSource2.getFeatures(filter);


       //create a feature iterator to traverse through the selected features
       SimpleFeatureIterator iter=fc.features();

       //create a Set object to store the featureIdentifiers.
       Set<FeatureId> IDs=new HashSet<FeatureId>();

       //add the selected features to IDs
       try{

           while(iter.hasNext()){

               SimpleFeature f=iter.next();

               IDs.add(f.getIdentifier());

               System.out.println(" "+f.getIdentifier());

           }
       }
       finally{

           iter.close();

       }


         /*
          * Create the JMapFrame and set it to display the shapefile's features
          * with a default line and colour style
          */
                 Style style = createSelectedStyle(IDs);

         // Create a basic style with yellow lines and no fill
        Style shpStyle = SLD.createPolygonStyle(null, null, 0.0f);
        Style shpStyle1 = SLD.createPolygonStyle(null, null, 0.0f);
        Style shpStyle2 = SLD.createPolygonStyle(null, null, 0.0f);
        
        // Set up a MapContent with the two layers
        
        Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
        map.addLayer(rasterLayer);
        
        Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);
        map.addLayer(shpLayer);
        Layer shpLayer1 = new FeatureLayer(shapefileSource1, shpStyle1);
        map.addLayer(shpLayer1);
        Layer shpLayer2 = new FeatureLayer(shapefileSource2, style);
        map.addLayer(shpLayer2);
     
     

        // Create a JMapFrame with a menu to choose the display style for the
        frame = new JMapFrame(map);
        frame.setSize(800, 600);
        frame.enableStatusBar(true);
        //frame.enableTool(JMapFrame.Tool.ZOOM, JMapFrame.Tool.PAN, JMapFrame.Tool.RESET);
        frame.enableToolBar(true);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Raster");
        menuBar.add(menu);

        menu.add( new SafeAction("Grayscale display") {
            public void action(ActionEvent e) throws Throwable {
                Style style = createRGBStyle();
                if (style != null) {
                    ((StyleLayer) map.layers().get(0)).setStyle(style);
                    frame.repaint();
                }
            }
        });

        menu.add( new SafeAction("RGB display") {
            public void action(ActionEvent e) throws Throwable {
                Style style = createRGBStyle();
                if (style != null) {
                    ((StyleLayer) map.layers().get(0)).setStyle(style);
                    frame.repaint();
                }
           }
        });
        // Finally display the map frame.
        // When it is closed the app will exit.
        frame.setVisible(true);
        
        }catch(Exception e2)
        {
        	System.out.println(e2);
        }
    }
    
    
    
    private void setGeometry() {
        GeometryDescriptor geomDesc = shapefileSource.getSchema().getGeometryDescriptor();
        geometryAttributeName = geomDesc.getLocalName();

        Class<?> clazz = geomDesc.getType().getBinding();

        if (Polygon.class.isAssignableFrom(clazz) ||
                MultiPolygon.class.isAssignableFrom(clazz)) {
            geometryType = GeomType.POLYGON;

        } else if (LineString.class.isAssignableFrom(clazz) ||
                MultiLineString.class.isAssignableFrom(clazz)) {

            geometryType = GeomType.LINE;

        } else {
            geometryType = GeomType.POINT;
        }

    }
        /**
     * This method examines the names of the sample dimensions in the provided coverage looking for
     * "red...", "green..." and "blue..." (case insensitive match). If these names are not found
     * it uses bands 1, 2, and 3 for the red, green and blue channels. It then sets up a raster
     * symbolizer and returns this wrapped in a Style.
     *
     * @return a new Style object containing a raster symbolizer set up for RGB image
     */
    private Style createSelectedStyle(Set<FeatureId> IDs) {
        Rule selectedRule = createRule(SELECTED_COLOUR, SELECTED_COLOUR);
        selectedRule.setFilter(ff.id(IDs));

        Rule otherRule = createRule(LINE_COLOUR,FILL_COLOUR);
        otherRule.setElseFilter(true);

        FeatureTypeStyle fts = sf.createFeatureTypeStyle();
        fts.rules().add(selectedRule);
        fts.rules().add(otherRule);

        Style style2 = sf.createStyle();
        style2.featureTypeStyles().add(fts);

        return style2;
    }
    private Rule createRule(Color outlineColor, Color fillColor) {
  	  Symbolizer symbolizer = null;
  	  Fill fill = null;//not required if working with line
  	  Stroke stroke = sf.createStroke(ff.literal(outlineColor), ff.literal(LINE_WIDTH));

  	  symbolizer = sf.createLineSymbolizer(stroke, "the_geom");

  	  Rule rule = sf.createRule();
  	  rule.symbolizers().add(symbolizer);
  	  return rule;
  	}

    private Style createRGBStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        // We need at least three bands to create an RGB style
        int numBands = cov.getNumSampleDimensions();
        if (numBands < 3) {
            return null;
        }
        // Get the names of the bands
        String[] sampleDimensionNames = new String[numBands];
        for (int i = 0; i < numBands; i++) {
            GridSampleDimension dim = cov.getSampleDimension(i);
            sampleDimensionNames[i] = dim.getDescription().toString();
        }
        final int RED = 0, GREEN = 1, BLUE = 2;
        int[] channelNum = { -1, -1, -1 };
        // We examine the band names looking for "red...", "green...", "blue...".
        // Note that the channel numbers we record are indexed from 1, not 0.
        for (int i = 0; i < numBands; i++) {
            String name = sampleDimensionNames[i].toLowerCase();
            if (name != null) {
                if (name.matches("red.*")) {
                    channelNum[RED] = i + 1;
                } else if (name.matches("green.*")) {
                    channelNum[GREEN] = i + 1;
                } else if (name.matches("blue.*")) {
                    channelNum[BLUE] = i + 1;
                }
            }
        }
        // If we didn't find named bands "red...", "green...", "blue..."
        // we fall back to using the first three bands in order
        if (channelNum[RED] < 0 || channelNum[GREEN] < 0 || channelNum[BLUE] < 0) {
            channelNum[RED] = 1;
            channelNum[GREEN] = 2;
            channelNum[BLUE] = 3;
        }
        // Now we create a RasterSymbolizer using the selected channels
        SelectedChannelType[] sct = new SelectedChannelType[cov.getNumSampleDimensions()];
        ContrastEnhancement ce = sf.contrastEnhancement(ff.literal(1.0), ContrastMethod.NORMALIZE);
        for (int i = 0; i < 3; i++) {
            sct[i] = sf.createSelectedChannelType(String.valueOf(channelNum[i]), ce);
        }
        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct[RED], sct[GREEN], sct[BLUE]);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }


	public void actionPerformed(ActionEvent e)
	{
		String z=e.getActionCommand();
		if(z.equals("Calculate Path"))
		{
		 source=(String)sr1.getSelectedItem();
			 destination=(String)ds1.getSelectedItem();
			System.out.println("Source"+source+" Destination"+destination);
			try {
				getLayersAndDisplay();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			System.out.println("Wrong Values");
		}

		
	}
}