package parking_lot.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import org.easymock.*;

import org.junit.*;
import org.junit.Test;
import org.junit.rules.*;
import parking_lot.app.*;
import parking_lot.app.domainmodel.*;
import parking_lot.app.anon.*;
import parking_lot.app.repository.*;
import parking_lot.app.exception.*;

public class InputParserTest 
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private EasyMockSupport support = new EasyMockSupport();

    @Test
    public void normalTest() throws FullSlotException, SlotIsEmptyException, LotInitializedException
    {
        IMocksControl ctrl = support.createControl();
        IParkingLot parkingLot = ctrl.createMock(IParkingLot.class);
        InputParser parser = new InputParser(new Builder<IParkingLot, Integer>(){
            public IParkingLot build(Integer slot){
                assertEquals((int)slot, (int)6);
                return parkingLot;
            }
        });
        ParkingLotRecord[] records = new ParkingLotRecord[]{
            new ParkingLotRecord(1, new Car("KA-01-HH-1234", "White")),
            new ParkingLotRecord(2, new Car("KA-01-HH-9999", "White")),
            new ParkingLotRecord(3, new Car("KA-01-BB-0001", "Black")),
            new ParkingLotRecord(4, new Car("KA-01-HH-7777", "Red")),
            new ParkingLotRecord(5, new Car("KA-01-HH-2701", "Blue")),
            new ParkingLotRecord(6, new Car("KA-01-HH-3141", "Black")),
            new ParkingLotRecord(4, new Car("KA-01-P-333", "White")),
        };
        
        EasyMock.expect(parkingLot.add("KA-01-HH-1234", "White")).andReturn(records[0]);
        EasyMock.expect(parkingLot.add("KA-01-HH-9999", "White")).andReturn(records[1]);
        EasyMock.expect(parkingLot.add("KA-01-BB-0001", "Black")).andReturn(records[2]);
        EasyMock.expect(parkingLot.add("KA-01-HH-7777", "Red")).andReturn(records[3]);
        EasyMock.expect(parkingLot.add("KA-01-HH-2701", "Blue")).andReturn(records[4]);
        EasyMock.expect(parkingLot.add("KA-01-HH-3141", "Black")).andReturn(records[5]);
        EasyMock.expect(parkingLot.remove(4)).andReturn(records[3]);
        EasyMock.expect(parkingLot.status()).andReturn(new ParkingLotRecord[]{
            records[0],
            records[1],
            records[2],
            records[4],
            records[5]
        });
        EasyMock.expect(parkingLot.add("KA-01-P-333", "White")).andReturn(records[6]);
        EasyMock.expect(parkingLot.add("DL-12-AA-9999", "White")).andThrow(new parking_lot.app.exception.FullSlotException());
        EasyMock.expect(parkingLot.find("colour", "White")).andReturn(new ParkingLotRecord[]{
            records[0],
            records[1],
            records[6]
        });
        EasyMock.expect(parkingLot.find("colour", "White")).andReturn(new ParkingLotRecord[]{
            records[0],
            records[1],
            records[6]
        });
        EasyMock.expect(parkingLot.find("registration_number", "KA-01-HH-3141")).andReturn(new ParkingLotRecord[]{
            records[5]
        });
        EasyMock.expect(parkingLot.find("registration_number", "MH-04-AY-1111")).andReturn(new ParkingLotRecord[]{});
        support.replayAll();

        assertEquals("Created a parking lot with 6 slots", parser.parse("create_parking_lot 6"));
        assertEquals("Allocated slot number: 1", parser.parse("park KA-01-HH-1234 White"));
        assertEquals("Allocated slot number: 2", parser.parse("park KA-01-HH-9999 White"));
        assertEquals("Allocated slot number: 3", parser.parse("park KA-01-BB-0001 Black"));
        assertEquals("Allocated slot number: 4", parser.parse("park KA-01-HH-7777 Red"));
        assertEquals("Allocated slot number: 5", parser.parse("park KA-01-HH-2701 Blue"));
        assertEquals("Allocated slot number: 6", parser.parse("park KA-01-HH-3141 Black"));
        
        assertEquals("Slot number 4 is free", parser.parse("leave 4"));
        parser.parse("status");
        assertEquals("Allocated slot number: 4", parser.parse("park KA-01-P-333 White"));
        exception.expect(parking_lot.app.exception.FullSlotException.class);
        parser.parse("park DL-12-AA-9999 White");
        assertEquals("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333", parser.parse("registration_numbers_for_cars_with_colour White"));
        assertEquals("1, 2, 4", parser.parse("slot_numbers_for_cars_with_colour White"));
        assertEquals("6", parser.parse("slot_number_for_registration_number KA-01-HH-3141"));
        assertEquals("Not found", parser.parse("slot_number_for_registration_number MH-04-AY-1111"));

        support.verifyAll();
    }
}
