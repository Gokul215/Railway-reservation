import java.util.Scanner;
import java.util.*;

public class Main1 {
    public static void bookTicket(Passenger1 p) {
        Ticketbooker1 booker = new Ticketbooker1();
        if (Ticketbooker1.availableWaitingList == 0) {
            System.out.println("no tickets available");
        }
        if ((p.berthPreference.equals("l") && Ticketbooker1.availableLowerBerths > 0) ||
                (p.berthPreference.equals("m") && Ticketbooker1.availableMiddleBerths > 0) ||
                (p.berthPreference.equals("u") && Ticketbooker1.availableUpperBerths > 0)) {
            System.out.println("given berth available");

            if (p.berthPreference.equals("l") && Ticketbooker1.availableLowerBerths > 0) {
                System.out.println("lower birth given");
                booker.bookTicket(p, "l", Ticketbooker1.lowerBerthPosition.get(0));
                Ticketbooker1.availableLowerBerths--;
                Ticketbooker1.lowerBerthPosition.remove(0);
            }

            else if (p.berthPreference.equals("m") && Ticketbooker1.availableMiddleBerths > 0) {
                System.out.println("middle birth given");
                booker.bookTicket(p, "m", Ticketbooker1.middleBerthPosition.get(0));
                Ticketbooker1.availableMiddleBerths--;
                Ticketbooker1.middleBerthPosition.remove(0);
            }

            else if (p.berthPreference.equals("u") && Ticketbooker1.availableUpperBerths > 0) {
                System.out.println("upper birth given");
                booker.bookTicket(p, "u", Ticketbooker1.upperBerthPosition.get(0));
                Ticketbooker1.availableUpperBerths--;
                Ticketbooker1.upperBerthPosition.remove(0);
            }

        } else if (Ticketbooker1.availableLowerBerths > 0) {
            System.out.println("lower birth given");
            booker.bookTicket(p, "l", Ticketbooker1.lowerBerthPosition.get(0));
            Ticketbooker1.availableLowerBerths--;
            Ticketbooker1.lowerBerthPosition.remove(0);
        } else if (Ticketbooker1.availableMiddleBerths > 0) {
            System.out.println("middle birth given");
            booker.bookTicket(p, "m", Ticketbooker1.middleBerthPosition.get(0));
            Ticketbooker1.availableMiddleBerths--;
            Ticketbooker1.middleBerthPosition.remove(0);
        } else if (Ticketbooker1.availableUpperBerths > 0) {
            System.out.println("upper birth given");
            booker.bookTicket(p, "u", Ticketbooker1.upperBerthPosition.get(0));
            Ticketbooker1.availableUpperBerths--;
            Ticketbooker1.upperBerthPosition.remove(0);
        } else if (Ticketbooker1.availableRacTickets > 0) {
            System.out.println("RAC available");
            booker.addToRAC(p, "RAC", Ticketbooker1.racPositions.get(0));
            Ticketbooker1.availableRacTickets--;
            Ticketbooker1.racPositions.remove(0);

        } else if (Ticketbooker1.availableWaitingList > 0) {
            System.out.println("waiting list available");
            booker.addToWl(p, "WL", Ticketbooker1.waitingListPositions.get(0));
            Ticketbooker1.availableWaitingList--;
            Ticketbooker1.waitingListPositions.remove(0);
        }

    }

    public static void cancelTicket(int id) {
        Ticketbooker1 booker = new Ticketbooker1();
        if (Ticketbooker1.bookedTicketList.contains(id)) {
            booker.cancelTicket1(id);
        } else {
            System.out.println("passenger is not available");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        boolean bool = true;

        while (bool) {
            System.out.println(
                    " 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
            System.out.println("enter your choice :");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter passenger name: ");
                    String name = sc.next();
                    System.out.print("Enter age: ");
                    int age = s.nextInt();
                    System.out.print("Enter berth Preference (l,u,m): ");
                    String berthPreference = sc.next();

                    Passenger1 p = new Passenger1(name, age, berthPreference);
                    bookTicket(p);
                    break;

                }
                case 2: {
                    System.out.println("enter passenger id to cancel: ");
                    int id = s.nextInt();
                    cancelTicket(id);
                    break;
                }
                case 3: {
                    Ticketbooker1 booker = new Ticketbooker1();
                    booker.printAvailable();
                    break;
                }
                case 4: {
                    Ticketbooker1 booker = new Ticketbooker1();
                    booker.printPassengers();
                    break;
                }
                case 5: {
                    bool = false;
                    break;
                }
            }
        }

    }
}