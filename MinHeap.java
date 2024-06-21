/**
 * public class MinHeap: Implements a dynamic replaceable heap as an
 * array, that supports the following public methods: insert(Ride r),
 * remove(Ride r), isEmpty(), peek(), heapify(Ride[] rides, int Ridenum)
 * dump(), sort(), combineRides(). It also supports the following private methods:
 * upheap(), downheap(), swap().
 */
import java.time.*;
public class MinHeap {
    Ride root;
    Ride[] rideArray;
    int next;
    int rideCount= 0;

    /**
     * public MinHeap: initialises the rideArray to size set in and sets next to 1
     * @param size
     */
    public MinHeap(int size){
        rideArray = new Ride[size];
        next = 1;
    }

    /**
     * public boolean remove(Ride r): removes the specified ride from the heap
     * while maintaining heap order. Returns true if the ride was removed, otherwise false
     * @param r
     * @return
     */
    public boolean remove(Ride r){
        int posLast = next-1;
        //return false if empty heap
        if(posLast == 0){
            return false;
        }

        for(int i = 1; i< next;i++){
            //if specified ride is in heap
            if (rideArray[i].rideID == r.rideID) {
                int posR = i;
                //if specified is last ride
                if(posLast == posR){
                    rideArray[posR] = null;
                }
                else{
                    swap(posR,posLast);
                    rideArray[posLast] = null;
                    downheap(rideArray,posR);
                }
                rideCount--;
                next--;
                return true;
            }
        }
        return false;
    }

    /**
     * public boolean insert(Ride r): Removes the specified ride from the heap while
     * maintaining heap order. Returns true if the ride was added, otherwise false
     * @param r
     * @return
     */
    public boolean insert(Ride r){
        for(int i = 1; i< rideArray.length;i++) {
            //check for duplicate rides
            if((rideArray[i] !=null && r.rideID == rideArray[i].rideID)){
                return false;
            }
            //check for no passengers
            else if(r.passName == null || r.passName == ""){
                return false;
            }
            //check for max rides
            else if(rideCount==20){
                return false;
            }
        }
        rideArray[next] = r;
        upheap(next);
        next++;
        rideCount++;
        return true;
    }

    /**
     * private void upheap(int pos): performs the upheap operation (after an insertion)
     * @param pos
     */
    private void upheap(int pos){
        int posChild = pos;
        while(posChild>1){
            int posParent = posChild/2;
            //check if child should swap with parent
            if(rideArray[posChild].compareTo(rideArray[posParent]) < 0){
                swap(posChild,posParent);
            }
            posChild = posParent;
        }
    }

    /**
     * private void downheap(Ride[] rides, int r): performs the downheap operation
     * (after deletion and for heapify)
     * @param rides
     * @param r
     */
    private void downheap(Ride[] rides, int r){
        int smallest = r;
        int parent = r;
        while(parent <next){
            int left = parent * 2;
            int right = (parent * 2) + 1;

            //change left/right if out of boundary
            if(left > next-1){
                left =parent;
            }
            if(right > next-1){
                right =parent;
            }

            //check left boundary
            if(left <= next && rides[left] != null){
                //check if left is smaller than parent
                if (left < next && (rides[left].compareTo(rides[parent]) < 0)) {
                    smallest = left;
                }
            }

            //check right boundary
            if(right <= next && rides[right] != null){
                //check if right is smaller than parent
                if (right < next && (rides[right].compareTo(rides[smallest]) < 0)) {
                    smallest = right;
                }
            }

            //if parent isnt smallest, swap with smallest
            if(parent != smallest){
                swap(smallest,parent);
                parent = smallest;
            }
            else{
                return;
            }
        }
    }

    /**
     * private void swap(int posC, int posP): Swaps two rides at the given indexes.
     * @param posC
     * @param posP
     */
    private void swap(int posC, int posP){
        Ride tempValue = rideArray[posC];
        rideArray[posC] = rideArray[posP];
        rideArray[posP] = tempValue;
    }

    /**
     * public boolean isEmpty(): returns true if there are no rides in the heap, otherwise false.
     * @return
     */
    public boolean isEmpty(){
        for(int i = 1; i < rideArray.length;i++){
            if(rideArray[i] != null){
                return false;
            }
        }
        return true;
    }

    /**
     * public Ride peek(): return the first ride in the heap (the min) without removing.
     * @return
     */
    public Ride peek(){
        return rideArray[1];
    }

    /**
     * public void heapify(Ride[] rides, int rideNum): takes a Ride array to replace the existing heap,
     * and puts them into heap order. If the array has more than 20 rides, do nothing.
     * @param rides
     * @param rideNum
     */
    public void heapify(Ride[] rides, int rideNum) {
        Ride[] tempArray = rideArray.clone();
        int tempNext = next;
        int tempRideCount = rideCount;
        rideArray = rides;

        if(isEmpty()==true){
            System.out.println("Array of rides passed in is empty");
            rideArray = tempArray;
        }
            next = 1;
            //reset count to account for heapify
            for(int i = 1; i < rides.length;i++){
                if(rides[i] != null){
                    next++;
                }
            }
            rideCount = next-1;
            //set rideCount, only heapify if rideCount is in boundary
            if(rideCount <21){
                int index = next/2;
                for(int i = index; i>0; i--){
                    downheap(rideArray,i);
                }
            }
            else{
                next = tempNext;
                rideCount = tempRideCount;
                System.out.println("Too many rides");
            }
    }

    /**
     * public void dump(): prints the rides in the heap to standard out
     */
    public void dump(){
        for(int i = 1; i < rideArray.length;i++){
            if(rideArray[i] != null){
                System.out.println(rideArray[i].toString());
            }
        }
    }

    /**
     * public Ride[] sort(): implements the heap sort algorithm and returns a
     * Ride array of all rides in the heap, in order.
     * @return
     */
    public Ride[] sort() {
        Ride[] inOrderArray = new Ride[rideArray.length];
        Ride[] tempArray = rideArray.clone();

        for (int i = 1; i < rideArray.length; i++) {
            //check conditions to sort
            if (rideArray[i] != null || rideArray[1] != null) {
                inOrderArray[i] = rideArray[1];
                remove(rideArray[1]);
            }
        }
        rideArray = tempArray;

        if(inOrderArray[1] == null){
            return null;
        }
        else{
            return inOrderArray;
        }
    }

    /**
     * public void combineRides: Combines trips in the heap if they are within 10 minutes of eachother.
     */
    public void combineRides() {
        if(isEmpty()){
            System.out.println("Empty heap");
            return;
        }

        for(int i = next -1; i > 1;i--){
            long rideTimeDiff = rideArray[i].timeDiff(rideArray[i-1]);
            //check if rides are within 10 minutes of eachother
            if (rideTimeDiff <= 10 && rideTimeDiff >= -10) {
                //calculate which ride.timeStamp should be used
                LocalTime newTimeStamp = rideArray[i].timeStamp;
                if (rideArray[i].compareTo(rideArray[i-1]) < 0) {
                    newTimeStamp = rideArray[i-1].timeStamp;
                }

                Ride combinedRide = new Ride(i-1, newTimeStamp, rideArray[i].passName +"\n"+ rideArray[i-1].passName, rideArray[i-1].startLocID, rideArray[i-1].endLocID);

                //delete the ride combined with the other, and add combinedRide to the heap
                rideArray[i] = null;
                rideArray[i-1] = combinedRide;

                //if deleted ride was NOT the last ride in the heap
                if(i != next-1){
                    //start from index of deleted ride
                    for(int o = i; o < next-1;o++){
                        //move all rides after index of deleted ride to the left by 1
                        rideArray[o] = rideArray[o+1];
                        //change the rideID of moved rides to account for shifts
                        if(rideArray[o] != null){
                            rideArray[o].rideID = o;
                        }
                        if(o == next){
                            System.out.println("Make last rideArray slot null");
                            rideArray[o] = null;
                        }
                    }
                }

            }
        }
    }
}

