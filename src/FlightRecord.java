import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.io.FileWriter;
import java.io.IOException;

abstract public class FlightRecord {
    protected String flight_name;
    protected int flight_id;
    protected int capacity;
    protected String flight_class;
    protected LocalDateTime depTime;
    protected LocalDateTime reachTime;
    protected LocalDateTime backupTime;
    protected Duration stayTime;
    public FlightRecord(String name , int id , int cap , String fclass , LocalDateTime dep , LocalDateTime reach) {flight_name = name; flight_id = id; capacity = cap; flight_class = fclass; depTime = dep; reachTime = reach; backupTime = dep; stayTime = Duration.between(depTime , reachTime);}

    public int get_flight_id() {return flight_id;}
    public String get_flight_name() {return flight_name;}
    public String get_flight_class() {return flight_class;}
    public int get_flight_cap() {return capacity;}
    public LocalDateTime get_flight_depart() {return depTime;}
    public LocalDateTime get_flight_board() {return reachTime;}
    public Duration get_flight_stay() {return stayTime;}

    public void setDepartureTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }
    public void set_backup_dep(LocalDateTime backup) {
        this.backupTime = backup;
    }
    public static class FlightManager {
        private static ArrayList<FlightRecord> flight = null;

        public FlightManager() {

            flight = new ArrayList<>();
        }

        public void addFlight(FlightRecord fr) {
            flight.add(fr);
        }

        public int get_total_flights(){
            return flight.size();
        }

        public static void display(FlightRecord fr) {
            switch (fr.flight_class) {
                case "VIP" -> {
                    System.out.println("\nClass : VIP");
                    System.out.println("Flight Name : " + fr.flight_name);
                    System.out.println("Flight ID : " + fr.flight_id);
                    System.out.println("Flight Capacity : " + fr.capacity);
                    System.out.println("Flight Boarding Time : " + fr.reachTime);
                    System.out.println("Flight Departure Time : " + fr.depTime);
                    System.out.println("Flight Stay Time : " + fr.stayTime + "\n");
                }
                case "VVIP" -> {
                    System.out.println("\nClass : VVIP");
                    System.out.println("Flight Name : " + fr.flight_name);
                    System.out.println("Flight ID : " + fr.flight_id);
                    System.out.println("Flight Capacity : " + fr.capacity);
                    System.out.println("Flight Boarding Time : " + fr.reachTime);
                    System.out.println("Flight Departure Time : " + fr.depTime);
                    System.out.println("Flight Stay Time : " + fr.stayTime + "\n");
                }
                case "Public" -> {
                    System.out.println("\nClass : Public Flight");
                    System.out.println("Flight Name : " + fr.flight_name);
                    System.out.println("Flight ID : " + fr.flight_id);
                    System.out.println("Flight Capacity : " + fr.capacity);
                    System.out.println("Flight Boarding Time : " + fr.reachTime);
                    System.out.println("Flight Departure Time : " + fr.depTime);
                    System.out.println("Flight Stay Time : " + fr.stayTime + "\n");
                }
            }
        }

        public void sortDepartureTime() {
            Comparator<FlightRecord> depTimeComparator = Comparator.comparing(FlightRecord::get_flight_depart);
            flight.sort(depTimeComparator);
        }
        public void sorted_departure() {
            sortDepartureTime();
            for (FlightRecord record : flight) {
                display(record);
            }
        }

        public void sortArrivalTime() {
            Comparator<FlightRecord> arrTimeComparator = Comparator.comparing(FlightRecord::get_flight_board);
            flight.sort(arrTimeComparator);
        }
        public void sorted_arrival() {
            sortArrivalTime();
            for (FlightRecord record : flight) {
                display(record);
            }
        }

        public void sortStayTime() {
            Comparator<FlightRecord> stayTimeComparator = Comparator.comparing(FlightRecord::get_flight_stay);
            flight.sort(stayTimeComparator);
        }
        public void sorted_stay() {
            sortStayTime();
            for (FlightRecord record : flight) {
                display(record);
            }
        }

        public static void display_ids(FlightRecord fr) {
            System.out.println("ID : " + fr.flight_id);
        }

        public void set_flight_name(int id, String name) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    fr.flight_name = name;
                    break;
                }
            }
        }

        public void set_flight_cap(int id, int cap) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    fr.capacity = cap;
                    break;
                }
            }
        }

        public void set_flight_id(int id, int new_id) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    fr.flight_id = new_id;
                    break;
                }
            }
        }

        public void set_flight_class(int id, String fclass) {
            FlightRecord tempObj = null;
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    fr.flight_class = fclass;
                    tempObj = fr;
                    fr = null;
                    break;
                }
            }

            FlightRecord newRec;
            if (fclass.equals("VIP")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name, tempObj.flight_id, tempObj.capacity, tempObj.depTime, tempObj.reachTime);
            }
            if (fclass.equals("VVIP")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name, tempObj.flight_id, tempObj.capacity, tempObj.depTime, tempObj.reachTime);
            }
            if (fclass.equals("Public")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name, tempObj.flight_id, tempObj.capacity, tempObj.depTime, tempObj.reachTime);
            }
        }

        public void set_new_departure(int id) {
            Random r = new Random();
            Scanner scanStr = new Scanner(System.in);
            Scanner scanInt = new Scanner(System.in);
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    fr.set_backup_dep(fr.depTime);
                    System.out.print("Enter Flight takeoff Day and Time in [YYYY-MM-DD HH:MM] : ");
                    String depString = scanStr.nextLine();
                    DateTimeFormatter depFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    fr.setDepartureTime(LocalDateTime.parse(depString, depFormat));
                    break;
                }
            }
        }

        public void checkDelay(int id) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    int res = fr.depTime.compareTo(fr.backupTime);
                    if (res < 0) {
                        System.out.println("Flight will be departing earlier .");
                    } else if (res > 0) {
                        System.out.println("Flight will be departing late than expected .");
                    } else {
                        System.out.println("NO delay in flight departure .");
                    }
                    break;
                }
            }
        }

        public String get_flight_class(int id) {
            String getClass = "";
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    getClass = fr.flight_class;
                    break;
                }
            }
            return getClass;
        }

        public String get_flight_name(int id) {
            String getName = "";
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    getName = fr.flight_name;
                    break;
                }
            }
            return getName;
        }

        public int get_flight_capacity(int id) {
            int getCap = 0;
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    getCap = fr.capacity;
                    break;
                }
            }
            return getCap;
        }

        public LocalDateTime get_depart_time(int id) {
            LocalDateTime temp = null;
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    temp = fr.depTime;
                    break;
                }
            }
            return temp;
        }

        public LocalDateTime get_boarding_time(int id) {
            LocalDateTime temp = null;
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    temp = fr.reachTime;
                    break;
                }
            }
            return temp;
        }

        public Duration get_stay_time(int id) {
            Duration temp = null;
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    temp = fr.stayTime;
                    break;
                }
            }
            return temp;
        }

        public void delete_flight(int id) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    flight.remove(fr);
                    System.out.println("Deleted flight ID : " + id);
                    break;
                }
            }
        }

        public boolean check_exist(int id) {
            for (FlightRecord fr : flight) {
                if (fr.get_flight_id() == (id)) {
                    return false;
                }
            }
            return true;
        }

        public void FlightCSV() {
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));
            String filename = "flight_records_" + currentDateTime + ".csv";
            try (FileWriter writer = new FileWriter(filename)) {
                // Write header
                writer.write("Flight Name,Flight ID,Capacity,Flight Class,Flight Departure Time,Flight Boarding Time\n");

                // Write each flight record as a line in the CSV file
                for (FlightRecord record : flight) {
                    writer.write(String.format("%s,%d,%d,%s,%s,%s,\n",
                            record.get_flight_name(), record.get_flight_id(), record.get_flight_cap(), record.get_flight_class(), record.get_flight_depart(), record.get_flight_board()));
                }

                System.out.println("Flight records saved to " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
