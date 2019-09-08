package parking_lot.app.repository;
import java.util.*;
import parking_lot.app.domainmodel.*;
import parking_lot.app.exception.*;
import parking_lot.app.anon.*;

public interface IParkingLot{
    Integer getSlot();
    ParkingLotRecord add(String registrationNo, String colour) throws FullSlotException;
    ParkingLotRecord add(Car car) throws FullSlotException;
    ParkingLotRecord remove(Integer slot) throws SlotIsEmptyException;
    ParkingLotRecord[] status();
    ParkingLotRecord[] find(String field, String term);
}