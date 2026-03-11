package Project7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        initGraph(g);
        //startUserIteraction(); 
    }

    private static void initGraph(Graph g){
        g.loadRoutes("Project7_1/sl_routes.txt");
        g.loadStops("Project7_1/sl_stops.txt");
        g.loadTrips("Project7_1/sl_trips.txt");
        g.loadTimes("Project7_1/sl_stop_times.txt");
        g.connectStops();

        System.out.println(g.findShotestPathWithId("740012883", "740021659"));
        //g.printRoads();
    }

    private static void startUserIteraction(){
        System.out.println("Från: \tTill: \tStarttid:  (. för nu)");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = r.readLine();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
