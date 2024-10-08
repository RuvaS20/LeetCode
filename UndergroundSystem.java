import java.util.HashMap;
import java.util.ArrayList;

/**
 * UndergroundSystem class simulates an underground travel system where
 * customers check in and check out
 * at different stations. It keeps track of travel times between stations and
 * allows for calculating the
 * average travel time between any two stations.
 * 
 * Author: Ruvarashe Sadya
 * Time Complexity:
 * - checkIn: O(1)
 * - checkOut: O(1)
 * - getAverageTime: O(n), where n is the number of travel times recorded
 * between the specified stations.
 */

public class UndergroundSystem {

    // hashmap for storing customer checkin details
    HashMap<Integer, ArrayList<Object>> checkIns; // hashmap has id map -> Object(StationName, checkinTime) so list has
                                                  // 2 elements

    // hashmap for storing stationName pairs and their total travel checkinTime
    HashMap<ArrayList<String>, ArrayList<Integer>> travelTimes; // e.g [station1, station2] maps -> [1, 2, 3]

    // constructor
    public UndergroundSystem() {
        checkIns = new HashMap<>();
        travelTimes = new HashMap<>();
    }

    void checkIn(int id, String stationName, int t) {
        // checkin details Object list, storing name and t
        ArrayList<Object> checkInDetails = new ArrayList<>();
        checkInDetails.add(stationName);
        checkInDetails.add(t);

        // checking in a customer
        checkIns.put(id, checkInDetails);
    }

    void checkOut(int id, String stationName, int t) {
        // calculate the travel travelTime

        // find person's details with that id
        ArrayList<Object> checkInDetails = checkIns.get(id);
        String startStation = (String) checkInDetails.get(0); // type casting because the list is defined as one of
                                                              // objects
        int startTime = (int) checkInDetails.get(1);

        // calculate travelTime
        int travelTime = t - startTime;

        // create station pair list
        ArrayList<String> stationPair = new ArrayList<>();
        stationPair.add(startStation);
        stationPair.add(stationName);

        // add travelTime and stationName to 2nd hashmap
        travelTimes.putIfAbsent(stationPair, new ArrayList<>());
        travelTimes.get(stationPair).add(travelTime); // retrieves the list of travel times associated with the
                                                      // stationPair key and adds the new travelTime to that list

        // remove the checkedin person from checkIns coz journey is done
        checkIns.remove(id);
    }

    double getAverageTime(String startStation, String endStation) {
        // create the key
        ArrayList<String> stationPair = new ArrayList<>();
        stationPair.add(startStation);
        stationPair.add(endStation);

        // find the list
        ArrayList<Integer> times = travelTimes.get(stationPair);

        // sum from list
        double sum = 0;
        for (int time : times) {
            sum += time;
        }

        // average
        double average = sum / times.size();
        return average;
    }
}
