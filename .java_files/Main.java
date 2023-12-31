import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter total flights : ");
        Scanner scanInt = new Scanner(System.in);
        int total_flights = scanInt.nextInt();
        Scanner scanStr = new Scanner(System.in);
        Random r = new Random();
        FlightRecord[] flights;
        flights = new FlightRecord[total_flights];
        String[] flight_names = {"Qatar Airways" , "Lufthansa" , "Emirates" , "Air India" , "SpiceJet" , "Indigo"};
        String[] flight_classes = {"VIP" , "VVIP" , "Public"};
        System.out.println("""
                                Enter a valid choice :
                                1 : Enter Details Manually
                                2 : Enter Details Randomly
                                """);
            System.out.print("Choice : ");
            int c = scanInt.nextInt();
            switch(c) {
                case 1 -> {
                    for(int i = 0; i < total_flights; i++) {
                        System.out.println("Enter Details of Flight no." + (i + 1));
                        System.out.print("Enter Flight Name : ");
                        String name = scanStr.nextLine();
                        System.out.print("Enter Flight ID : ");
                        int id = scanInt.nextInt();
                        System.out.print("Enter Flight Capacity : ");
                        int cap = scanInt.nextInt();
                        System.out.print("Enter Flight Class [VIP , VVIP , Public] : ");
                        String fc = scanStr.nextLine();
                        System.out.print("Enter Flight takeoff Day and Time in [YYYY-MM-DD HH:MM] : ");
                        String depString = scanStr.nextLine();
                        DateTimeFormatter depFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        System.out.print("Enter Flight Departure Day and Time in [YYYY-MM-DD HH:MM] : ");
                        String takeoffStr = scanStr.nextLine();
                        DateTimeFormatter offFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        if(fc.equals("VIP")) {
                            flights[i] = new VIP_Class(name , id , cap , LocalDateTime.parse(depString , depFormat) , LocalDateTime.parse(takeoffStr , offFormat));
                        }
                        if(fc.equals("VVIP")) {
                            flights[i] = new VVIP_Class(name , id , cap , LocalDateTime.parse(depString , depFormat) , LocalDateTime.parse(takeoffStr , offFormat));
                        }
                        if(fc.equals("Public")) {
                            flights[i] = new PublicFlights(name , id , cap , LocalDateTime.parse(depString , depFormat) , LocalDateTime.parse(takeoffStr , offFormat));
                        }
                    }
                }
                case 2 -> {
                    ArrayList<Integer> randID = new ArrayList<Integer>();
                    for (int i = 0; i < total_flights; i++) randID.add(r.nextInt(500 , 1500));
                    Collections.shuffle(randID);
                    for (int i = 0; i < total_flights; i++) {
                        int n = r.nextInt(flight_names.length);
                        int m = r.nextInt(flight_classes.length);
                        int o = r.nextInt(100);
                        int p = r.nextInt(500, 1000) * 10;
                        int yr = 2023;
                        int month = 12;
                        int date = r.nextInt(1,20);
                        int hr = r.nextInt(0,20);
                        int min = r.nextInt(0 , 29);
                        if (flight_classes[m].equals("VIP")) {
                            flights[i] = new VIP_Class(flight_names[n], randID.get(i) , p , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + 2 , min + 30));
                        }
                        if (flight_classes[m].equals("VVIP")) {
                            flights[i] = new VVIP_Class(flight_names[n], randID.get(i) , p  , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + 2 , min + 30));
                        }
                        if (flight_classes[m].equals("Public")) {
                            flights[i] = new PublicFlights(flight_names[n], randID.get(i) , p , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + 2 , min + 30));
                        }
                    }
                }
                default -> System.out.println("Enter a valid Choice !! ");
            }
        FlightRecord.FlightManager fm = new FlightRecord.FlightManager();
        for(int i = 0; i < total_flights; i++) {
            fm.addFlight(flights[i]);
        }
        while(true) {
            System.out.println("""
                            Check flight details :
                            1 : Display All Flights
                            2 : Get all Flight IDs
                            3 : Update using Flight ID
                            4 : Display specific Flight details
                            5 : Delete Flight details
                            0 : EXIT""");
            System.out.print("Choice : ");
            int c2 = scanInt.nextInt();
            switch(c2) {
                case 1 -> {
                    for(int i = 0; i < total_flights ; i++) {
                        FlightRecord.FlightManager.display(flights[i]);
                    }
                }

                case 2 -> {
                    for(int i = 0; i < total_flights ; i++) {
                        FlightRecord.FlightManager.display_ids(flights[i]);
                    }
                }

                case 3 -> {
                    System.out.print("Enter flight ID to update detail : ");
                    int id = scanInt.nextInt();
                    if(!fm.check_exist(id)) {
                        System.out.println("Enter a Valid ID ! ");
                    }
                    else {
                        System.out.println("""
                                1 : Update Flight Name
                                2 : Update Flight Capacity
                                3 : Update Flight ID
                                4 : Update Flight Class
                                5 : Update Flight Departure Time
                                """);
                        System.out.print("Choice : ");
                        int c3 = scanInt.nextInt();
                        switch (c3) {
                            case 1 -> {
                                System.out.print("Enter New Name : ");
                                String name = scanStr.nextLine();
                                fm.set_flight_name(id, name);
                            }
                            case 2 -> {
                                System.out.print("Enter new Capacity : ");
                                int cap = scanInt.nextInt();
                                fm.set_flight_cap(id, cap);
                            }
                            case 3 -> {
                                System.out.print("Enter new ID : ");
                                int id2 = scanInt.nextInt();
                                fm.set_flight_id(id, id2);
                            }
                            case 4 -> {
                                System.out.print("Enter new Class : ");
                                String new_class = scanStr.nextLine();
                                fm.set_flight_class(id, new_class);
                            }
                            case 5 -> {
                                fm.set_new_departure(id);
                            }
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter flight ID to view detail : ");
                    int id = scanInt.nextInt();
                    if(!fm.check_exist(id)) {
                        System.out.println("Enter a valid ID ! ");
                    }
                    else {
                        System.out.println("""
                                1 : View Flight Name
                                2 : View Flight Capacity
                                3 : View Flight Class
                                4 : View Departure Time
                                5 : View Boarding Time
                                6 : Check if delayed
                                7 : View whole details
                                """);
                        System.out.print("Choice : ");
                        int c3 = scanInt.nextInt();
                        switch (c3) {
                            case 1 -> {
                                System.out.println("Flight name for id " + id + " is : " + fm.get_flight_name(id) + "\n");
                            }
                            case 2 -> {
                                System.out.println("Flight Capacity for id " + id + " is : " + fm.get_flight_capacity(id) + "\n");
                            }
                            case 3 -> {
                                System.out.println("Flight Class for id " + id + " is : " + fm.get_flight_class(id) + "\n");
                            }
                            case 4 -> {
                                System.out.print("Departure TIme for id : " + id + "is : " +fm.get_depart_time(id) + "\n");
                            }
                            case 5 -> {
                                System.out.print("Boarding TIme for id : " + id + "is : " +fm.get_boarding_time(id) + "\n");
                            }
                            case 6 -> {
                                fm.checkDelay(id);
                            }
                            case 7 -> {
                                System.out.println("\nDetails for flight ID : " + id);
                                System.out.println("Name : " + fm.get_flight_name(id));
                                System.out.println("Capacity : " + fm.get_flight_capacity(id));
                                System.out.println("Class : " + fm.get_flight_class(id));
                                System.out.println("Departure Time : " +fm.get_depart_time(id));
                                System.out.println("Boarding TIme : " +fm.get_boarding_time(id) + "\n");
                            }
                            default -> System.out.println("Enter a valid choice ! ");
                        }
                    }
                }
                case 5 -> {
                    System.out.print("Enter flight ID to Delete : ");
                    int id = scanInt.nextInt();
                    if(!fm.check_exist(id)) {
                        System.out.println("Enter a valid ID !");
                    }
                    else {
                        fm.delete_flight(id);
                    }
                }
                case 0 -> System.exit(0);

                default -> System.out.println("Enter a valid choice ! ");
            }
        }
    }
}
