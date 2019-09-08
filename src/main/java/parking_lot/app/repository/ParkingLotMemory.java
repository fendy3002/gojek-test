package parking_lot.app.repository;
import java.util.*;
import parking_lot.app.domainmodel.*;
import parking_lot.app.exception.*;
import parking_lot.app.anon.*;

public class ParkingLotMemory{
    public ParkingLotMemory(Integer slot){
        this.slot = slot;
        for(Integer i = 1; i <= slot; i++){
            availableSlot.add(i);
        }
    }

    private List<Integer> availableSlot = new ArrayList<Integer>();
    private Map<Integer, Car> occupant = new HashMap<Integer, Car>();

    private Integer slot;
    public Integer getSlot(){
        return this.slot;
    }
    public ParkingLotRecord add(String registrationNo, String colour) throws FullSlotException{
        return this.add(new Car(registrationNo, colour));
    }
    public ParkingLotRecord add(Car car) throws FullSlotException{
        if(availableSlot.size() > 0){
            Integer assignedSlot = availableSlot.get(0);
            occupant.put(assignedSlot, car);
            availableSlot.remove(0);
            return new ParkingLotRecord(assignedSlot, car);
        }
        else{
            throw new FullSlotException();
        }
    }
    public ParkingLotRecord remove(Integer slot) throws SlotIsEmptyException{
        if(!occupant.containsKey(slot)){
            throw new SlotIsEmptyException(slot);
        }
        Car leaving = occupant.get(slot);
        occupant.remove(slot);
        availableSlot.add(slot);

        return new ParkingLotRecord(slot, leaving);
    }
    public ParkingLotRecord[] status(){
        List<ParkingLotRecord> response = new ArrayList<ParkingLotRecord>();
        for (Map.Entry<Integer, Car> entry : occupant.entrySet()) {
            response.add(
                new ParkingLotRecord(entry.getKey(), entry.getValue())
            );
        }
        return response.toArray(new ParkingLotRecord[response.size()]);
    }
    public ParkingLotRecord[] find(String field, String term){
        List<ParkingLotRecord> response = new ArrayList<ParkingLotRecord>();
        Map<
            String, 
            Validator<Car, String>
        > validator = new HashMap<
            String, 
            Validator<Car, String>
        >();
        validator.put("registration_number", new Validator<Car, String>(){
            public boolean compare(Car car, String term){
                return car.getRegistrationNo().equals(term);
            }
        });
        validator.put("colour", new Validator<Car, String>(){
            public boolean compare(Car car, String term){
                return car.getColour().equals(term);
            }
        });

        for (Map.Entry<Integer, Car> entry : occupant.entrySet()) {
            if(validator.get(field).compare(entry.getValue(), term)){
                response.add(
                    new ParkingLotRecord(entry.getKey(), entry.getValue())
                );
            }
        }

        return response.toArray(new ParkingLotRecord[response.size()]);
    }
}