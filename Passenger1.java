import java.util.*;
public class Passenger1 {
    String name;
    int age;
    String berthPreference;
    static int id = 1;
    int passengerid;
    int seatnumber;
    String allotted;

    public Passenger1(String name, int age, String berthPreference) {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.passengerid = id++;
        seatnumber = 1;
        allotted = "";

    }
}
