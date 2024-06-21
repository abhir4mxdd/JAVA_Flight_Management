import java.time.*;

public class PublicFlights extends FlightRecord{
    public PublicFlights(String name, int id, int cap , LocalDateTime dep , LocalDateTime reach) { super(name, id, cap, "Public" , dep , reach); }

//    @Override
//    void display() {
//
//    }
}
