import java.util.ArrayList;

abstract public class FlightRecord {
    protected String flight_name;
    protected int flight_id;
    protected int capacity;
    protected String flight_class;
    public FlightRecord(String name , int id , int cap , String fclass) {flight_name = name; flight_id = id; capacity = cap; flight_class = fclass;}

    public String get_flight_name() {return flight_name;}
    public int get_flight_id() {return flight_id;}
    public String get_flight_class() {return flight_class;}

    public static class FlightManager {
        private ArrayList<FlightRecord> flight;
        public FlightManager() {

            flight = new ArrayList<>();
        }
        public void addFlight(FlightRecord fr) {
            flight.add(fr);
        }
        public static void display(FlightRecord fr) {
            if (fr.flight_class.equals("VIP")) {
                System.out.println("\nClass : VIP");
                System.out.println("Flight Name : " + fr.flight_name);
                System.out.println("Flight ID : " + fr.flight_id);
                System.out.println("Flight Capacity : " + fr.capacity + "\n");
            } else if (fr.flight_class.equals("VVIP")) {
                System.out.println("\nClass : VVIP");
                System.out.println("Flight Name : " + fr.flight_name);
                System.out.println("Flight ID : " + fr.flight_id);
                System.out.println("Flight Capacity : " + fr.capacity + "\n");
            } else if (fr.flight_class.equals("Public")) {
                System.out.println("\nClass : Public Flight");
                System.out.println("Flight Name : " + fr.flight_name);
                System.out.println("Flight ID : " + fr.flight_id);
                System.out.println("Flight Capacity : " + fr.capacity + "\n");
            }
        }
        public static void display_ids(FlightRecord fr) {
            System.out.println("ID : "  +fr.flight_id);
        }

        public void set_flight_name(int id , String name)  {
            boolean valid = false;
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    fr.flight_name = name;
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
        }
        public void set_flight_cap(int id , int cap)  {
            boolean valid = false;
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    fr.capacity = cap;
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
        }
        public void set_flight_id(int id , int new_id)  {
            boolean valid = false;
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    fr.flight_id = new_id;
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
        }
        public void set_flight_class(int id , String fclass)  {
            FlightRecord tempObj = null;

            boolean valid = false;
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    fr.flight_class = fclass;
                    tempObj = fr;
                    fr = null;
                    valid = true;
                    break;
                }
            }

            FlightRecord newRec;
            if(fclass.equals("VIP")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name , tempObj.flight_id , tempObj.capacity );}
            if(fclass.equals("VVIP")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name , tempObj.flight_id , tempObj.capacity );}
            if(fclass.equals("Public")) {
                assert tempObj != null;
                newRec = new VIP_Class(tempObj.flight_name , tempObj.flight_id , tempObj.capacity );}

            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
        }
        public String get_flight_class(int id) {
            boolean valid = false;
            String getClass = "Unknown";
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    getClass = fr.flight_class;
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
            return getClass;
        }

        public String get_flight_name(int id) {
            boolean valid = false;
            String getName = "Unknown";
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    getName = fr.flight_name;
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                System.out.println("Enter a valid ID ! ");
            }
            return getName;
        }

        public int get_flight_capacity(int id) {
            boolean valid = false;
            int getCap = -1;
            for(FlightRecord fr : flight) {
                if(fr.get_flight_id() == (id)) {
                    getCap = fr.capacity;
                    valid = true;
                    break;
                }
            }
            if(getCap == -1) {
                System.out.println("Enter a valid ID ! ");
            }
            return getCap;
        }

    }
}
