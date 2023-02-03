import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Ticketbooker1 {
    static int availableLowerBerths = 1;// normally 21
    static int availableMiddleBerths = 1;// normally 21
    static int availableUpperBerths = 1;// normally 21
    static int availableRacTickets = 1;// normally 18
    static int availableWaitingList = 1;// normally 10

    static List<Integer> lowerBerthPosition = new ArrayList<>(Arrays.asList(1));// normally 1,2,.
    static List<Integer> middleBerthPosition = new ArrayList<>(Arrays.asList(1));// normally 1,2,.
    static List<Integer> upperBerthPosition = new ArrayList<>(Arrays.asList(1));// normally 1,2,.
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,...18
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,...10

    static Queue<Integer> racList = new LinkedList<>();// queu of RAC passengers
    static Queue<Integer> waitingList = new LinkedList<>();// queue of WL passengers
    static List<Integer> bookedTicketList = new ArrayList<>();// list of bookedticket passengers

    static Map<Integer, Passenger1> passengers = new HashMap<>();

    public void bookTicket(Passenger1 p, String allotted, int seatnumber) {
        p.allotted = allotted;
        p.seatnumber = seatnumber;
        passengers.put(p.passengerid, p);
        bookedTicketList.add(p.passengerid);
        System.out.println("your passenger id: " + p.passengerid);
        System.out.println("Booked successfully ");

    }

    public void addToRAC(Passenger1 p, String allotted, int seatnumber) {
        p.allotted = allotted;
        p.seatnumber = seatnumber;
        passengers.put(p.passengerid, p);
        racList.add(p.passengerid);
        System.out.println("your passenger id: " + p.passengerid);
        System.out.println("added to rac ");

    }

    public void addToWl(Passenger1 p, String allotted, int seatnumber) {
        p.allotted = allotted;
        p.seatnumber = seatnumber;
        passengers.put(p.passengerid, p);
        waitingList.add(p.passengerid);
        System.out.println("your passenger id: " + p.passengerid);
        System.out.println("added to waiting list ");

    }

    public void cancelTicket1(int passengerid) {
        Passenger1 p = passengers.get(passengerid);
        passengers.remove(Integer.valueOf(passengerid));
        bookedTicketList.remove(Integer.valueOf(passengerid));
        int removedSeatnumber = p.seatnumber;
        System.out.println("---------------cancelled Successfully");

        // add the free position to the correspoding type of list (either L,M,U)
        if (p.allotted.equals("l")) {
            availableLowerBerths++;
            lowerBerthPosition.add(removedSeatnumber);
        } else if (p.allotted.equals("m")) {
            availableMiddleBerths++;
            middleBerthPosition.add(removedSeatnumber);
        } else if (p.allotted.equals("u")) {
            availableUpperBerths++;
            upperBerthPosition.add(removedSeatnumber);
        }
        // check if any RAC is there
        if (racList.size() > 0) {
            Passenger1 prac = passengers.get(racList.poll());
            int racRemovedSeatNumber = prac.seatnumber;
            racPositions.add(racRemovedSeatNumber);
            racList.remove(Integer.valueOf(prac.passengerid));
            availableRacTickets++;

            if (waitingList.size() > 0) {
                Passenger1 pwl = passengers.get(waitingList.poll());
                int wlRmovedSeatNumber = pwl.seatnumber;
                waitingListPositions.add(wlRmovedSeatNumber);
                waitingList.remove(Integer.valueOf(pwl.passengerid));

                pwl.seatnumber = racPositions.get(0);
                pwl.allotted = "RAC";
                racPositions.remove(0);
                racList.add(pwl.passengerid);

                availableWaitingList++;
                availableRacTickets--;

            }
            Main1.bookTicket(prac);

        }

    }

    public void printAvailable() {
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Middle Berths " + availableMiddleBerths);
        System.out.println("Available Upper Berths " + availableUpperBerths);
        System.out.println("Availabel RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("--------------------------");

    }

    public void printPassengers() {
        if (passengers.size() == 0) {
            System.out.println("No details of passengers");

        }
        for (Passenger1 p : passengers.values()) {
            System.out.println("PASSENGER ID " + p.passengerid);
            System.out.println(" Name " + p.name);
            System.out.println(" Age " + p.age);
            System.out.println(" Status " + p.seatnumber + p.allotted);
            System.out.println("--------------------------");
        }
    }

}
