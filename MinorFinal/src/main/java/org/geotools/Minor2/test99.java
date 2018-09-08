package org.geotools.Minor2;

import java.io.File;
import java.io.IOException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Query;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public class test99 {
	 SimpleFeatureSource shapefileSource2;
public static void main(String[] args) throws Exception {
    test99 me=new test99();
    me.calculate();
   
}
private void calculate() throws Exception
{
	File shpFile2 = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/ROADS.shp");
    
    FileDataStore dataStore = FileDataStoreFinder.getDataStore(shpFile2);
     shapefileSource2 = dataStore
            .getFeatureSource();
     SimpleFeatureType schema=shapefileSource2.getSchema();
     Query query=new Query(schema.getTypeName(),Filter.INCLUDE);
     int count=shapefileSource2.getCount(query);
     if(count==-1){
    	 SimpleFeatureCollection collection=shapefileSource2.getFeatures(query);
    	 count=collection.size();
     }
     System.out.println("there are "+count+" "+schema.getTypeName()+" features");
    
}


}