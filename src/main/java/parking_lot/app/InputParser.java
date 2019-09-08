package parking_lot.app;

public class InputParser{
    public Car(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    private ParkingLot parkingLot;
    
    public String getParkingLot(){
        return this.parkingLot;
    };
    
}