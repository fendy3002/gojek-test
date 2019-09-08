package parking_lot.app;
import parking_lot.app.domainmodel.*;
import parking_lot.app.repository.*;
import parking_lot.app.exception.*;
import parking_lot.app.anon.*;
import java.util.*;

public class InputParser{
    public InputParser(){
        this(new Builder<IParkingLot, Integer>(){
            public IParkingLot build(Integer slot){
                return new ParkingLotMemory(slot);
            }
        });
    }
    public InputParser(Builder<IParkingLot, Integer> builder){
        this.parkingLotBuilder = builder;
    }
    private IParkingLot parkingLot;
    private Builder<IParkingLot, Integer> parkingLotBuilder;
    
    public IParkingLot getParkingLot(){
        return this.parkingLot;
    };
    public String parse(String input) throws FullSlotException, SlotIsEmptyException, LotInitializedException{
        String[] parts = input.split(" ");

        if(parts[0].equals("create_parking_lot")){
            if(this.parkingLot != null){
                throw new LotInitializedException();
            }
            Integer slot = Integer.parseInt(parts[1]);
            this.parkingLot = this.parkingLotBuilder.build(slot);

            return "Created a parking lot with " + slot.toString() + " slots";
        }
        else if(parts[0].equals("park")){
            String registrationNo = parts[1];
            String colour = parts[2];
            ParkingLotRecord response = this.parkingLot.add(registrationNo, colour);
            return "Allocated slot number: " + response.getSlot().toString();
        }
        else if(parts[0].equals("leave")){
            Integer slot = Integer.parseInt(parts[1]);
            ParkingLotRecord response = this.parkingLot.remove(slot);
            return "Slot number " + response.getSlot().toString() + " is free";
        }
        else if(parts[0].equals("status")){
            ParkingLotRecord[] response = this.parkingLot.status();
            String result = "Slot No.\tRegistration No\tColour";
            for (ParkingLotRecord each : response) {
                result += "\n";
                result += each.getSlot().toString() + "\t" + each.getCar().getRegistrationNo() + "\t" + each.getCar().getColour();
            }
            return result;
        }
        else if(parts[0].contains("_for_")){
            String display = parts[0].substring(0, parts[0].indexOf("_for_"));
            String search = parts[0].substring(parts[0].indexOf("_for_") + 5);

            ParkingLotRecord[] response = null;
            if(search.equals("cars_with_colour")){
                response = this.parkingLot.find("colour", parts[1]);
            }
            else if(search.equals("registration_number")){
                response = this.parkingLot.find("registration_number", parts[1]);
            }

            if(response.length == 0){
                return "Not found";
            }
            List<String> result = new ArrayList<String>();
            if(display.equals("registration_numbers")){
                for (ParkingLotRecord each : response) {
                    result.add(each.getCar().getRegistrationNo());
                }
            }
            else if(display.contains("slot_number")){
                for (ParkingLotRecord each : response) {
                    result.add(each.getSlot().toString());
                }
            }
            return String.join(", ", result);
        }
        return "";
    }
}