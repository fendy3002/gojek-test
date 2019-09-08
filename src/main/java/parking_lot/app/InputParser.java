package parking_lot.app;
import parking_lot.app.domainmodel.*;
import parking_lot.app.exception.*;
import java.util.*;

public class InputParser{
    private ParkingLot parkingLot;
    
    public ParkingLot getParkingLot(){
        return this.parkingLot;
    };
    public String parse(String input) throws FullSlotException, SlotIsEmptyException{
        String[] parts = input.split(" ");

        if(parts[0].equals("create_parking_lot")){
            Integer slot = Integer.parseInt(parts[1]);
            this.parkingLot = new ParkingLot(slot);

            return "Created a parking lot with " + slot.toString() + " slots";
        }
        else if(parts[0].equals("park")){
            String registrationNo = parts[1];
            String colour = parts[2];
            ParkingLot.AllocationResponse response = this.parkingLot.insert(registrationNo, colour);
            return "Allocated slot number: " + response.slot.toString();
        }
        else if(parts[0].equals("leave")){
            Integer slot = Integer.parseInt(parts[1]);
            ParkingLot.AllocationResponse response = this.parkingLot.remove(slot);
            return "Slot number " + response.slot.toString() + " is free";
        }
        else if(parts[0].equals("status")){
            ParkingLot.AllocationResponse[] response = this.parkingLot.status();
            String result = "Slot No.\tRegistration No\tColour";
            for (ParkingLot.AllocationResponse each : response) {
                result += "\n";
                result += each.slot.toString() + "\t" + each.car.getRegistrationNo() + "\t" + each.car.getColour();
            }
            return result;
        }
        else if(parts[0].contains("_for_")){
            String display = parts[0].substring(0, parts[0].indexOf("_for_"));
            String search = parts[0].substring(parts[0].indexOf("_for_") + 5);

            ParkingLot.AllocationResponse[] response = null;
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
                for (ParkingLot.AllocationResponse each : response) {
                    result.add(each.car.getRegistrationNo());
                }
            }
            else if(display.contains("slot_number")){
                for (ParkingLot.AllocationResponse each : response) {
                    result.add(each.slot.toString());
                }
            }
            return String.join(", ", result);
        }
        return "";
    }
}