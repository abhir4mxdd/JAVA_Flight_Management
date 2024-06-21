import java.io.*;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanInt = new Scanner(System.in);
        System.out.print("Enter total flights : ");
        int total_flights = scanInt.nextInt();
        Scanner scanStr = new Scanner(System.in);
        FlightRecord[] flights;
        flights = new FlightRecord[total_flights];
        Random r = new Random();
        String[] flight_names = {"Qatar Airways" , "Lufthansa" , "Emirates" , "Air India" , "SpiceJet" , "Indigo" , "Boeing"};
        String[] flight_classes = {"VIP" , "VVIP" , "Public"};
        System.out.println("""
                                Enter a valid choice :
                                1 : Enter Details Manually
                                2 : Enter Details Randomly
                                3 : Enter Details from a Preprocessed CSV
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
                            flights[i] = new VIP_Class(flight_names[n], randID.get(i) , p , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + r.nextInt(3) , min + r.nextInt(30)));
                        }
                        if (flight_classes[m].equals("VVIP")) {
                            flights[i] = new VVIP_Class(flight_names[n], randID.get(i) , p  , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + r.nextInt(3) , min + r.nextInt(30)));
                        }
                        if (flight_classes[m].equals("Public")) {
                            flights[i] = new PublicFlights(flight_names[n], randID.get(i) , p , LocalDateTime.of(yr , month , date , hr , min ) , LocalDateTime.of(yr , month , date + r.nextInt(0,2) , hr + r.nextInt(3) , min + r.nextInt(30)));
                        }
                    }
                }
                case 3 -> {
                    Scanner sc = new Scanner(new File("C://Users//andra//IdeaProjects//Flight/flight_records_2023-11-11_194059.csv"));
                    sc.useDelimiter(",");
                    if (sc.hasNextLine()) {
                        sc.nextLine();
                    }
                    while (sc.hasNext())
                  {
                      String name = sc.next();
                      int id = sc.nextInt();
                      int cap = sc.nextInt();
                      String fc = sc.next();
                      String dep = sc.next();
                      DateTimeFormatter depformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                      LocalDateTime depart = LocalDateTime.parse(dep , depformat);
                      String reach = sc.next();
                      DateTimeFormatter bformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                      LocalDateTime board = LocalDateTime.parse(reach , bformat);
                      if(fc.equals("VIP")) {
                          flights = new VIP_Class[]{new VIP_Class(name, id, cap, depart, board)};
                      }
                      if(fc.equals("VVIP")) {
                          flights = new VVIP_Class[]{new VVIP_Class(name, id, cap, depart, board)};
                      }
                      if(fc.equals("Public")) {
                          flights = new PublicFlights[]{new PublicFlights(name, id, cap, depart, board)};
                      }
                  }
                    System.out.println(sc.next());
                    sc.close();
                }
                default -> System.out.println("Enter a valid Choice !! ");
            }
        FlightRecord.FlightManager fm = new FlightRecord.FlightManager();
        for(int i = 0; i < total_flights; i++) {
            fm.addFlight(flights[i]);
        }
        mainLoop:
        while(true) {
            System.out.println("""
                            Check flight details :
                            1 : Display All Flights
                            2 : Get Total FLights
                            3 : Get all Flight IDs
                            4 : Update using Flight ID
                            5 : Display specific Flight details
                            6 : Delete Flight details
                            7 : Sorted Departure Time
                            8 : Sorted Arrival Time
                            9 : Sorted Stay Time
                            10 : Save current details
                            0 : EXIT""");
            System.out.print("Choice : ");
            int c2 = scanInt.nextInt();
            switch(c2) {
                case 1 -> {
                    for(int i = 0; i < total_flights ; i++) {
                        FlightRecord.FlightManager.display(flights[i]);
                    }
                }

                case 2-> System.out.println("Total Flights = " + fm.get_total_flights());

                case 3 -> {
                    for(int i = 0; i < total_flights ; i++) {
                        FlightRecord.FlightManager.display_ids(flights[i]);
                    }
                }

                case 4 -> {
                    System.out.print("Enter flight ID to update detail : ");
                    int id = scanInt.nextInt();
                    if(fm.check_exist(id)) {
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
                case 5 -> {
                    System.out.print("Enter flight ID to view detail : ");
                    int id = scanInt.nextInt();
                    if(fm.check_exist(id)) {
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
                                7 : Check Flight Stay Time
                                8 : View whole details
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
                            case 6 -> fm.checkDelay(id);

                            case 7 -> System.out.println("Stay Time for id : " + id + "is : " + fm.get_stay_time(id));

                            case 8 -> {
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
                case 6 -> {
                    System.out.print("Enter flight ID to Delete : ");
                    int id = scanInt.nextInt();
                    if(fm.check_exist(id)) {
                        System.out.println("Enter a valid ID !");
                    }
                    else {
                        fm.delete_flight(id);
                    }
                }
                case 7 -> fm.sorted_arrival();

                case 8 -> fm.sorted_departure();

                case 9 -> fm.sorted_stay();

                case 10 -> {
                    fm.FlightCSV();
                    System.out.println("Records saved at " +LocalDateTime.now());
                }

                case 0 -> {
                    break mainLoop;
                }
                default -> System.out.println("Enter a valid choice ! ");
            }
        }
        fm.FlightCSV();
        System.out.println("Flight Manager Exits .");
    }
}