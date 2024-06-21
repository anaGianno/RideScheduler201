/**
 * public class Ride implements Comparable<Ride>: Defines a member variable to hold
 * rideID,timeStamp,passName,startLocID,endLocID, a constructor that has values passed in
 * and copies those values to the member values, and also supports the following public methods:
 * timeDiff,toString,compareto.
 */
import java.time.*;
import java.time.temporal.*;
public class Ride implements Comparable<Ride>{
    int rideID;
    LocalTime timeStamp;
    String passName;
    int startLocID;
    int endLocID;

    /**
     * public Ride(int ri,LocalTime ts,String pn,int sli, int eli): Takes in
     * arguments and copies the values into member variables.
     * @param ri
     * @param ts
     * @param pn
     * @param sli
     * @param eli
     */
    public Ride(int ri,LocalTime ts,String pn,int sli, int eli){
        rideID = ri;
        timeStamp = ts;
        passName = pn;
        startLocID = sli;
        endLocID = eli;
    }

    /**
     * public long TimeDiff(Ride r2): Returns the difference in timeStamps of two rides (minutes).
     * @param r2
     * @return
     */
    public long timeDiff(Ride r2){
        return timeStamp.until(r2.timeStamp,ChronoUnit.MINUTES);
    }

    /**
     * @Override public String toString(): Overrides the default toString method to return
     * the details of a ride in the correct format.
     * @return
     */
    @Override
    public String toString(){
        if(timeStamp !=null){
            String formatRideID = String.format("%03d",rideID);
            String formatStartLocID = String.format("%03d",startLocID);
            String formatEndLocID = String.format("%03d",endLocID);
            return "--- Ride " + formatRideID + " -------\n"
                    + "Time: " +timeStamp + ":00\nStart ID: " + formatStartLocID
                    + "\nEnd ID: " + formatEndLocID + "\nPassengers:\n" +passName
                    + "\n--------------------";
        }
        return "Invalid ride";
    }

    /**
     * public int compareTo(Ride ride2): Compares the timeStamps of two rides.
     * @param ride2
     * @return
     */
    @Override
    public int compareTo(Ride ride2){
        int value = timeStamp.compareTo(ride2.timeStamp);
        return value;
    }
}