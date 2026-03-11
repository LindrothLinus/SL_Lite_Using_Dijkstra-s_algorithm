package Project_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Trip {
	Route route;
	String id;
	Map<Stop,Integer[]> schedule = new HashMap<>();


	public Trip(Route route, String id){
		this.route = route;
		this.id = id;
	}

	public void setSchedule(String schedulePath, HashSet<Stop> stops){
		try{
            BufferedReader br = new BufferedReader(new FileReader(schedulePath));
            String line;
            while((line = br.readLine())!= null){

        	}

        }catch(IOException e){
            System.out.println("Error reading file.");
        }
	}
}

