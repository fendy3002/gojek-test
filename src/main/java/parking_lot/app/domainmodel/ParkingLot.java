package parking_lot.app.domainmodel;
import java.util.*;
import parking_lot.app.exception.*;
import parking_lot.app.anon.*;

public class ParkingLot{
    public class AllocationResponse{
        public AllocationResponse(int slot, Car car){
            this.slot = slot;
            this.car = car;
        }
        public int slot;
        public Car car;
    }

    public ParkingLot(int slot){
        this.slot = slot;
        for(int i = 1; i <= slot; i++){
            availableSlot.add(i);
        }
    }

    private List<Integer> availableSlot = new ArrayList<Integer>();
    private Map<Integer, Car> occupant = new HashMap<Integer, Car>();

    private int slot;
    public int getSlot(){
        return this.slot;
    }
    public AllocationResponse insert(Car car) throws FullSlotException{
        if(availableSlot.size() > 0){
            Integer assignedSlot = availableSlot.get(0);
            occupant.put(assignedSlot, car);
            availableSlot.remove(0);
            return new AllocationResponse(assignedSlot, car);
        }
        else{
            throw new FullSlotException();
        }
    }
    public AllocationResponse pop(int slot) throws SlotIsEmptyException{
        if(!occupant.containsKey(slot)){
            throw new SlotIsEmptyException(slot);
        }
        Car leaving = occupant.get(slot);
        occupant.remove(slot);
        availableSlot.add(slot);

        return new AllocationResponse(slot, leaving);
    }
    public AllocationResponse[] status(){
        List<AllocationResponse> response = new ArrayList<AllocationResponse>();
        for (Map.Entry<Integer, Car> entry : occupant.entrySet()) {
            response.add(
                new AllocationResponse(entry.getKey(), entry.getValue())
            );
        }
        return response.toArray(new AllocationResponse[response.size()]);
    }
    public AllocationResponse[] find(String field, String term){
        List<AllocationResponse> response = new ArrayList<AllocationResponse>();
        Map<
            String, 
            Validator<Car, String>
        > validator = new HashMap<
            String, 
            Validator<Car, String>
        >();
        validator.put("registration_number", new Validator<Car, String>(){
            public boolean compare(Car car, String term){
                return car.getRegistrationNo() == term;
            }
        });
        validator.put("colour", new Validator<Car, String>(){
            public boolean compare(Car car, String term){
                return car.getColour() == term;
            }
        });

        for (Map.Entry<Integer, Car> entry : occupant.entrySet()) {
            if(validator.get(field).compare(entry.getValue(), term)){
                response.add(
                    new AllocationResponse(entry.getKey(), entry.getValue())
                );
            }
        }

        return response.toArray(new AllocationResponse[response.size()]);
    }
}