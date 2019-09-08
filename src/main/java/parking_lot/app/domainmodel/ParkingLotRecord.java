package parking_lot.app.domainmodel;
import java.util.*;
import parking_lot.app.exception.*;
import parking_lot.app.anon.*;

public class ParkingLotRecord{
    public ParkingLotRecord(Integer slot, Car car){
        this.slot = slot;
        this.car = car;
    }
    
    private Integer slot;
    private Car car;

    public Integer getSlot(){
        return this.slot;
    };
    public Car getCar(){
        return this.car;
    };
}