import java.time.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.io.*;

/**
 * This class called StackTest is used to test all the functions within the Stack class
 */
public class HeapTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * This method sets up System.setout to a new outputStreamCaptor for testing
     */
    @BeforeEach
    public void setOut(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * This method sets back System.setout to standardOut to restore the environment before testing
     */
    @AfterEach
    public void restoreOut(){
        System.setOut(standardOut);
    }

    /**
     * private Ride[] Make19RideArray(): Makes a 19 ride array for easier testing
     * @return
     */
    private Ride[] Make19RideArray(){
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        Ride r4 = new Ride(4,LocalTime.parse("02:00:00"),"Darius Draven",007,010);
        Ride r5 = new Ride(5,LocalTime.parse("04:00:00"),"Elise Evelynn",011,012);
        Ride r6 = new Ride(6,LocalTime.parse("05:00:00"),"Fiora Fiddlesticks",013,014);
        Ride r7 = new Ride(7,LocalTime.parse("07:00:00"),"Garen Gragas",015,016);
        Ride r8 = new Ride(8,LocalTime.parse("08:00:00"),"Hwei Heimerdinger",017,020);
        Ride r9 = new Ride(9,LocalTime.parse("09:00:00"),"Illaoi Irelia",021,022);
        Ride r10 = new Ride(10,LocalTime.parse("10:00:00"),"Jarvan Janna",023,024);
        Ride r11 = new Ride(11,LocalTime.parse("11:00:00"),"Kayle Katarina",025,026);
        Ride r12 = new Ride(12,LocalTime.parse("12:00:00"),"Lee Leona",027,030);
        Ride r13 = new Ride(13,LocalTime.parse("13:00:00"),"Mordekaiser Malzahar",031,032);
        Ride r14 = new Ride(14,LocalTime.parse("14:00:00"),"Nilah Nocturne",033,034);
        Ride r15 = new Ride(15,LocalTime.parse("15:00:00"),"Orianna Olaf",035,036);
        Ride r16 = new Ride(16,LocalTime.parse("16:00:00"),"Pantheon Poppy",037,040);
        Ride r17 = new Ride(17,LocalTime.parse("17:00:00"),"Quinn Qiyana",041,042);
        Ride r18 = new Ride(18,LocalTime.parse("18:00:00"),"Riven Reksai",043,044);
        Ride r19 = new Ride(19,LocalTime.parse("19:00:00"),"Soraka Sivir",045,046);
        Ride[] testArray = new Ride[]{null,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,null,null};
        return testArray;
    }

    /**
     *  Test insert(Ride r), insert valid ride in empty heap
     */
    @Test
    @DisplayName("Test insert(Ride r), insert valid ride in empty heap")
    public void TestInsertValidEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        //Act
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        boolean actual = minHeap.insert(r1);
        //Assert
        Assertions.assertEquals(actual,true);
    }

    /**
     *  Test insert(Ride r), insert valid ride in heap with 1 existing ride
     */
    @Test
    @DisplayName("Test insert(Ride r), insert valid ride in heap with 1 existing ride")
    public void TestInsertValidSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",101,102);
        boolean actual = minHeap.insert(r2);
        //Assert
        Assertions.assertEquals(actual,true);
    }

    /**
     * Test insert(Ride r), insert valid ride in heap with many existing rides, dependant on upheap() and swap()
     */
    @Test
    @DisplayName("Test insert(Ride r), insert valid ride in heap with many existing rides, dependant on upheap() and dump() and swap()")
    public void TestInsertMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        Ride r4 = new Ride(4,LocalTime.parse("05:00:00"),"Darius Draven",007,010);
        boolean actual = minHeap.insert(r4);
        //Assert
        Assertions.assertEquals(actual,true);
    }

    /**
     *  Test insert(Ride r), insert ride in heap with 1 existing ride
     */
    @Test
    @DisplayName("Test insert(Ride r), insert duplicate ride in heap with 1 existing ride")
    public void TestInsertDuplicateSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        boolean actual = minHeap.insert(r1);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     *  Test insert(Ride r), insert ride in heap with many existing rides, dependant on upheap() and swap()
     */
    @Test
    @DisplayName("Test insert(Ride r), insert duplicate ride in heap with many existing rides, dependant on upheap() and swap()")
    public void TestInsertDuplicateMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        boolean actual = minHeap.insert(r3);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     *  Test insert(Ride r), insert ride with no passengers in empty heap
     */
    @Test
    @DisplayName("Test insert(Ride r), insert ride with no passengers in empty heap")
    public void TestInsertNoPassEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r2  = new Ride(2,LocalTime.parse("06:00:00"),null,002,003);
        //Act
        boolean actual = minHeap.insert(r2);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     *  Test insert(Ride r), insert ride with no passengers in heap with 1 ride
     */
    @Test
    @DisplayName("Test insert(Ride r), insert ride with no passengers in heap with 1 ride")
    public void TestInsertNoPassSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        Ride r2  = new Ride(2,LocalTime.parse("06:00:00"),null,002,003);
        boolean actual = minHeap.insert(r2);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     *  Test insert(Ride r), insert ride with no passengers in heap with many existing rides, dependant on upheap() and swap()
     */
    @Test
    @DisplayName("Test insert(Ride r), insert ride with no passengers in heap with many existing rides, dependant on upheap() and swap()")
    public void TestInsertNoPassMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        Ride r4 = new Ride(4,LocalTime.parse("05:00:00"),null,007,010);
        boolean actual = minHeap.insert(r2);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     *  Test insert(Ride r), insert ride into heap with 19 rides (edge case), dependant on upheap() and swap()
     */
    @Test
    @DisplayName("Test insert(Ride r), insert ride into heap with 19 rides (edge case), dependant on upheap() and swap()")
    public void TestInsertMax20Rides(){
        // Arrange
        MinHeap minHeap = new MinHeap(21);
        Ride[] insertArray = Make19RideArray();
        for(int i = 1; i< 20;i++){
            minHeap.insert(insertArray[i]);
        }
        //Act
        Ride r20 = new Ride(20,LocalTime.parse("20:00:00"),"Tristana Taric",047,050);
        boolean actual = minHeap.insert(r20);
        //Assert
        Assertions.assertTrue(actual);
    }

    /**
     *  Test insert(Ride r), insert ride into heap with 20 rides, dependant on upheap() and swap()
     */
    @Test
    @DisplayName("Test insert(Ride r), insert ride into heap with 20 rides, dependant on upheap() and swap()")
    public void TestInsertMax21Rides(){
        // Arrange
        MinHeap minHeap = new MinHeap(21);
        Ride[] insertArray = Make19RideArray();
        for(int i = 1; i< 20;i++){
            minHeap.insert(insertArray[i]);
        }
        Ride r20 = new Ride(20,LocalTime.parse("20:00:00"),"Tristana Taric",047,050);
        minHeap.insert(r20);
        //Act
        Ride r21 = new Ride(21,LocalTime.parse("21:00:00"),"Urgot Udyr",051,052);
        boolean actual = minHeap.insert(r21);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test Remove(Ride r), remove a ride from a heap with a single ride, dependant on insert()
     */
    @Test
    @DisplayName("Test Remove(Ride r), remove a ride from a heap with a single ride, dependant on insert()")
    public void TestRemoveValidSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        boolean actual = minHeap.remove(r1);
        //Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Test Remove(Ride r), remove a ride from a heap with many rides, dependant on insert() and downheap() and swap() and upheap()
     */
    @Test
    @DisplayName("Test Remove(Ride r), remove a ride from a heap with many rides, dependant on downheap() and swap() and insert() and upheap()")
    public void TestRemoveValidMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        boolean actual = minHeap.remove(r3);
        //Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Test Remove(Ride r), remove invalid ride from an empty heap
     */
    @Test
    @DisplayName("Test Remove(Ride r), remove invalid ride from an empty heap")
    public void TestRemoveInvalidEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        //Act
        boolean actual = minHeap.remove(r2);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test Remove(Ride r), remove invalid ride from a heap with a single ride, dependant on insert()
     */
    @Test
    @DisplayName("Test Remove(Ride r), remove invalid ride from a heap with a single ride, dependant on insert()")
    public void TestRemoveInvalidSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        //Act
        boolean actual = minHeap.remove(r2);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test Remove(Ride r), remove invalid ride from a heap with many rides, dependant on insert() and upheap() and swap()
     */
    @Test
    @DisplayName("Test Remove(Ride r), remove invalid ride from a heap with many rides,dependant on insert() and upheap() and swap()")
    public void TestRemoveInvalidMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        Ride r4 = new Ride(4,LocalTime.parse("02:00:00"),"Darius Draven",007,010);
        //Act
        boolean actual = minHeap.remove(r4);
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test isEmpty() when heap is empty
     */
    @Test
    @DisplayName("Test isEmpty() when heap is empty")
    public void TestIsEmptyEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        //Act
        boolean actual = minHeap.isEmpty();
        //Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Test isEmpty() when heap is empty, after inserting and deleting a ride, dependant on insert() and remove()
     */
    @Test
    @DisplayName("Test isEmpty() when heap is empty, after inserting and deleting a ride, dependant on insert() and remove()")
    public void TestIsEmptyEmpty2(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        minHeap.remove(r1);
        //Act
        boolean actual = minHeap.isEmpty();
        //Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Test isEmpty() when heap is not empty (single ride), dependant on insert()
     */
    @Test
    @DisplayName("Test isEmpty() when heap is not empty (single ride), dependant on insert()")
    public void TestIsEmptySingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        boolean actual = minHeap.isEmpty();
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test isEmpty() when heap is not empty (many rides), dependant on insert() and upheap()
     */
    @Test
    @DisplayName("Test isEmpty() when heap is not empty (many rides), dependant on insert()and upheap()")
    public void TestIsEmptyMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        boolean actual = minHeap.isEmpty();
        //Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Test peek() when heap is empty
     */
    @Test
    @DisplayName("Test peek() when heap is empty")
    public void TestPeekEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        //Act
        Ride actual = minHeap.peek();
        //Assert
        Assertions.assertEquals(actual,null);
    }

    /**
     * Test peek() when heap is empty, after inserting and deleting a ride, dependant on insert() and remove()
     */
    @Test
    @DisplayName("Test peek() when heap is empty, after inserting and deleting a ride, dependant on insert() and remove()")
    public void TestPeekEmpty2(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        minHeap.remove(r1);
        //Act
        Ride actual = minHeap.peek();
        //Assert
        Assertions.assertEquals(actual,null);
    }

    /**
     * Test peek() when heap has one ride, dependant on insert()
     */
    @Test
    @DisplayName("Test peek() when heap has one ride,dependant on insert()")
    public void TestPeekSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        Ride actual = minHeap.peek();
        //Assert
        Assertions.assertEquals(actual,r1);
    }

    /**
     * Test peek() when heap has many rides, dependant on insert() and upheap()
     */
    @Test
    @DisplayName("Test peek() when heap has many rides,dependant on insert() and upheap()")
    public void TestPeekMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        Ride actual = minHeap.peek();
        //Assert
        Assertions.assertEquals(actual,r3);
    }

    /**
     * Test heapify(Ride[] rides, int rideNum) when heap has many rides, dependant on downheap() and dump()
     */
    @Test
    @DisplayName("Test heapify(Ride[] rides, int rideNum) when heap has many rides, dependant on downheap()")
    public void TestHeapifyMany(){
        //Arrange
        Ride r10 = new Ride(10,LocalTime.parse("10:00:00"),"Jarvan Janna",023,024);
        Ride r11 = new Ride(11,LocalTime.parse("11:00:00"),"Kayle Katarina",025,026);
        Ride r12 = new Ride(12,LocalTime.parse("12:00:00"),"Lee Leona",027,030);
        Ride r13 = new Ride(13,LocalTime.parse("13:00:00"),"Mordekaiser Malzahar",031,032);
        Ride[] testArray = new Ride[]{null,r13,r12,r11,r10};

        MinHeap minHeap = new MinHeap(21);
        //Act
        minHeap.heapify(testArray,4);
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 010 -------\nTime: 10:00:00\nStart ID: 019\nEnd ID: 020\nPassengers:\nJarvan Janna\n--------------------\n" +
                        "--- Ride 012 -------\nTime: 12:00:00\nStart ID: 023\nEnd ID: 024\nPassengers:\nLee Leona\n--------------------\n" +
                "--- Ride 011 -------\nTime: 11:00:00\nStart ID: 021\nEnd ID: 022\nPassengers:\nKayle Katarina\n--------------------\n" +
                "--- Ride 013 -------\nTime: 13:00:00\nStart ID: 025\nEnd ID: 026\nPassengers:\nMordekaiser Malzahar\n--------------------") ;
    }

    /**
     * Test heapify(Ride[] rides, int rideNum) when heap has one ride, dependant on dump()
     */
    @Test
    @DisplayName("Test heapify(Ride[] rides, int rideNum) when heap has one ride")
    public void TestHeapifySingle(){
        //Arrange
        Ride r10 = new Ride(10,LocalTime.parse("10:00:00"),"Jarvan Janna",023,024);
        Ride[] testArray = new Ride[]{null,r10};

        MinHeap minHeap = new MinHeap(21);
        //Act
        minHeap.heapify(testArray,1);
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 010 -------\nTime: 10:00:00\nStart ID: 019\nEnd ID: 020\nPassengers:\nJarvan Janna\n--------------------") ;
    }

    /**
     * Test heapify(Ride[] rides, int rideNum) when heap is empty, dependant on dump()
     */
    @Test
    @DisplayName("Test heapify(Ride[] rides, int rideNum) when heap is empty, dependant on dump()")
    public void TestHeapifyEmpty(){
        //Arrange
        Ride[] testArray = new Ride[0];
        MinHeap minHeap = new MinHeap(21);
        //Act
        minHeap.heapify(testArray,0);
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"Array of rides passed in is empty");
    }

    /**
     * Test heapify(Ride[] rides, int rideNum) when heap has more than 20 rides
     */
    @Test
    @DisplayName("Test heapify(Ride[] rides, int rideNum) when heap has more than 20 rides")
    public void TestHeapify20Rides(){
        // Arrange
        MinHeap minHeap = new MinHeap(21);
        Ride[] insertArray = Make19RideArray();
        Ride r20 = new Ride(20,LocalTime.parse("20:00:00"),"Tristana Taric",047,050);
        Ride r21 = new Ride(21,LocalTime.parse("21:00:00"),"Urgot Udyr",051,052);
        insertArray[20] = r20;
        insertArray[21] = r21;
        //Act
        minHeap.heapify(insertArray,21);
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"Too many rides");
    }

    /**
     *  Test dump(), when heap is empty
     */
    @Test
    @DisplayName("Test dump(), when heap is empty")
    public void TestDumpEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(21);
        //Act
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"");
    }

    /**
     *  Test dump(), when heap has one ride, dependant on insert() and dump()
     */
    @Test
    @DisplayName("Test dump(), when heap has one ride, dependant on insert() and dump()")
    public void TestDumpSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(3);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test dump(), when heap has many rides, dependant on insert() and dump() and upheap() and swap()
     */
    @Test
    @DisplayName("Test dump(), when heap has many rides, dependant on insert() and dump() and upheap() and swap()")
    public void TestDumpMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 003 -------\nTime: 01:00:00\nStart ID: 005\nEnd ID: 006\nPassengers:\nCamille Corki\n--------------------\n"+
                "--- Ride 002 -------\nTime: 06:00:00\nStart ID: 003\nEnd ID: 004\nPassengers:\nBriar Brand\n--------------------\n" +
                "--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test sort(), when heap is empty
     */
    @Test
    @DisplayName("Test sort(), when heap is empty")
    public void TestSortEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(21);
        //Act
        Ride[] actual = minHeap.sort();
        //Assert
        Assertions.assertEquals(actual,null);
    }

    /**
     *  Test sort(), when heap has single ride, dependant on remove() and insert() and toString()
     */
    @Test
    @DisplayName("Test sort(), when heap has single ride, dependant on remove() and insert() and toString()")
    public void TestSortSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        Ride[] testSort = minHeap.sort();
        for(int i = 0; i< testSort.length;i++){
            if(testSort[i] != null){
                System.out.println(testSort[i].toString());
            }
        }
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test sort(), when heap has many rides, dependant on remove() and insert() and toString() and upheap() and swap()
     */
    @Test
    @DisplayName("Test sort(), when heap has many rides, dependant on remove() and insert() and toString() and upheap() and swap()")
    public void TestSortMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        Ride[] testSort = minHeap.sort();
        for(int i = 0; i< testSort.length;i++){
            if(testSort[i] != null){
                System.out.println(testSort[i].toString());
            }
        }
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 003 -------\nTime: 01:00:00\nStart ID: 005\nEnd ID: 006\nPassengers:\nCamille Corki\n--------------------\n"+
                        "--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------\n" +
                "--- Ride 002 -------\nTime: 06:00:00\nStart ID: 003\nEnd ID: 004\nPassengers:\nBriar Brand\n--------------------");
    }

    /**
     *  Test toString(), when ride is invalid, dependant on toString()
     */
    @Test
    @DisplayName("Test toString(), when ride is invalid, dependant on toString()")
    public void TestToStringInvalid(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        Ride r1 = new Ride(0,null,null,0,0);
        //Act
        String actual = r1.toString();
        //Assert
        Assertions.assertEquals(actual,"Invalid ride");
    }

    /**
     *  Test toString(), with valid ride, dependant on insert()
     */
    @Test
    @DisplayName("Test toString(), with valid ride, dependant on insert()")
    public void TestToStringSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        String actual = r1.toString();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test toString(), with many valid rides, dependant on insert() and swap()
     */
    @Test
    @DisplayName("Test toString(), with many valid rides, dependant on insert() and swap()")
    public void TestToStringMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        Ride[] testArray = new Ride[]{null,r1,r2,r3};
        //Act
        for(int i = 1; i<testArray.length;i++){
            System.out.println(testArray[i].toString());
        }
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual, "--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------\n"+
                "--- Ride 002 -------\nTime: 06:00:00\nStart ID: 003\nEnd ID: 004\nPassengers:\nBriar Brand\n--------------------\n" +
                        "--- Ride 003 -------\nTime: 01:00:00\nStart ID: 005\nEnd ID: 006\nPassengers:\nCamille Corki\n--------------------");
    }

    /**
     *  Test compareTo(Ride r), when r1.localTime<r2.localTime and r1 and r2 are in heap, dependant on insert() and swap()
     */
    @Test
    @DisplayName("Test compareTo(Ride r), when r1.localTime<r2.localTime and r1 and r2 are in heap, dependant on insert() and swap()")
    public void TestCompareToLessHeap(){
        // Arrange
        MinHeap minHeap = new MinHeap(12);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        Ride r3 = new Ride(3,LocalTime.parse("01:00:00"),"Camille Corki",005,006);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        //Act
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 003 -------\nTime: 01:00:00\nStart ID: 005\nEnd ID: 006\nPassengers:\nCamille Corki\n--------------------\n"+
                "--- Ride 002 -------\nTime: 06:00:00\nStart ID: 003\nEnd ID: 004\nPassengers:\nBriar Brand\n--------------------\n" +
                "--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test compareTo(Ride r), when r1.localTime<r2.localTime but not involving heap
     */
    @Test
    @DisplayName("Test compareTo(Ride r), =, dependant on insert() and swap()")
    public void TestCompareToLessNoHeap(){
        // Arrange
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("06:00:00"),"Briar Brand",003,004);
        //Act
        int actual = r1.compareTo(r2);
        //Assert
        Assertions.assertEquals(actual,-1);
    }

    /**
     *  Test compareTo(Ride r), when LocalTimes are equal, not involving heap
     */
    @Test
    @DisplayName("Test compareTo(Ride r), =, dependant on insert() and swap()")
    public void TestCompareToEqual(){
        // Arrange
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("03:00:00"),"Briar Brand",003,004);
        //Act
        int actual = r1.compareTo(r2);
        //Assert
       Assertions.assertEquals(actual,0);
    }

    /**
     *  Test compareTo(Ride r), when r1.localTime>r2.localTime
     */
    @Test
    @DisplayName("Test compareTo(Ride r), when r1.localTime>r2.localTime")
    public void TestCompareToGreater(){
        // Arrange
        Ride r1 = new Ride(1,LocalTime.parse("06:00:00"),"Annie Anivia",001,002);
        Ride r2 = new Ride(2,LocalTime.parse("03:00:00"),"Briar Brand",003,004);
        //Act
        int actual = r1.compareTo(r2);
        //Assert
        Assertions.assertEquals(actual,1);
    }

    /**
     *  Test combineRides(), when heap is empty
     */
    @Test
    @DisplayName("Test combineRides(), when heap is empty")
    public void TestCombineRidesEmpty(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        //Act
        minHeap.combineRides();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"Empty heap");
    }

    /**
     *  Test combineRides(), when heap has single ride, dependant on insert() and dump()
     */
    @Test
    @DisplayName("Test combineRides(), when heap has single ride, dependant on insert() and dump()")
    public void TestCombineRidesSingle(){
        // Arrange
        MinHeap minHeap = new MinHeap(2);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Annie Anivia",001,002);
        minHeap.insert(r1);
        //Act
        minHeap.combineRides();
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 001 -------\nTime: 03:00:00\nStart ID: 001\nEnd ID: 002\nPassengers:\nAnnie Anivia\n--------------------");
    }

    /**
     *  Test combineRides(), when heap has many rides, dependant on insert(),dump(),timeDiff(),compareTo()
     */
    @Test
    @DisplayName("Test combineRides(), when heap has many rides, dependant on insert(),dump(),timeDiff(),compareTo()")
    public void TestCombineRidesMany(){
        // Arrange
        MinHeap minHeap = new MinHeap(7);
        Ride r1 = new Ride(1,LocalTime.parse("03:00:00"),"Joe Bloggs\nMary Smith\nCharlie Brown",324,586);
        Ride r2 = new Ride(2,LocalTime.parse("03:10:00"),"Mama Joe",324,586);
        Ride r3 = new Ride(3,LocalTime.parse("03:20:00"),"Father Joe",324,586);
        Ride r4 = new Ride(4,LocalTime.parse("03:30:00"),"Brother Joe",324,586);
        Ride r5 = new Ride(5,LocalTime.parse("03:40:00"),"Sister Joe",324,586);
        Ride r6 = new Ride(6,LocalTime.parse("03:50:00"),"Jo Joe",324,586);
        minHeap.insert(r1);
        minHeap.insert(r2);
        minHeap.insert(r3);
        minHeap.insert(r4);
        minHeap.insert(r5);
        minHeap.insert(r6);

        //Act
        minHeap.combineRides();
        minHeap.dump();
        String actual = outputStreamCaptor.toString().trim();
        //Assert
        Assertions.assertEquals(actual,"--- Ride 001 -------\nTime: 03:10:00\nStart ID: 324\nEnd ID: 586\nPassengers:\nMama Joe\nJoe Bloggs\nMary Smith\nCharlie Brown\n--------------------\n"+
                "--- Ride 002 -------\nTime: 03:30:00\nStart ID: 324\nEnd ID: 586\nPassengers:\nBrother Joe\nFather Joe\n--------------------\n" +
                "--- Ride 003 -------\nTime: 03:50:00\nStart ID: 324\nEnd ID: 586\nPassengers:\nJo Joe\nSister Joe\n--------------------");
    }
}
