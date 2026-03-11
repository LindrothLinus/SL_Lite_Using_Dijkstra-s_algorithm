package Project_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    Set<Route> routes;
    Set<Stop> stops;

    public Graph(){
        routes = new HashSet<>();
    }

    public void addRoutes(String filePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine())!= null){
                String[] lineParts = line.split(",");
                routes.add(new Route(lineParts[0], !lineParts[2].isEmpty() ? lineParts[2]:lineParts[3], lineParts[4]));
            }
            br.close();
        }catch(IOException e){
            System.out.println("Error reading file.");
        }
    }

    public void addStops(String filePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine())!= null){
                String[] lineParts = line.split(",");
                stops.add(new Stop(lineParts[0],lineParts[1],Double.parseDouble(lineParts[2]),Double.parseDouble(lineParts[3])));
            }
            br.close();
        }catch(IOException e){
            System.out.println("Error reading file.");
        }
    }
    
}
