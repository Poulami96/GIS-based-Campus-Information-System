package org.geotools.Minor2;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.swing.JMapPane;

public class fram  {

    static JMapPane mapPane;
      public static void main(String[] args) throws Exception {
        fram me = new fram();
         
        me.getLayersAndDisplay();
        
		//me.listenForClicks(mapPane);
    }
    private void getLayersAndDisplay() throws Exception {
      
    	/*File file = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/GREEN.shp");
        FileDataStore myData = FileDataStoreFinder.getDataStore( file );
        SimpleFeatureSource source = myData.getFeatureSource();
        SimpleFeatureType schema = source.getSchema();

        Query query = new Query(schema.getTypeName());
        query.setMaxFeatures(1);
        
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(query);
        try (FeatureIterator<SimpleFeature> features = collection.features()) {
            while (features.hasNext()) {
                SimpleFeature feature = features.next();
                System.out.println(feature.getID() + ": ");
                for (Property attribute : feature.getProperties()) {
                    System.out.println("\t"+attribute.getName()+":"+attribute.getValue() );
 //               }
            }
        }
     */
    	FileInputStream fis = new FileInputStream( "F:/workplace/Minor2/src/main/java/org/geotools/Minor2/POLYGON.dbf" );
    	DbaseFileReader dbfReader =  new DbaseFileReader(fis.getChannel(),
    	false,  Charset.forName("ISO-8859-1"));

    	while ( dbfReader.hasNext() ){
    	   final Object[] fields = dbfReader.readEntry();
    	   Integer field1 = (Integer) fields[0];

    	   String field2 = (String) fields[2];
    	   
    	   System.out.println("DBF field 1 value is: " + field1);
    	   System.out.println("DBF field 2 value is: " + field2);
    	}

    	dbfReader.close();
    	fis.close();
    }
        
}