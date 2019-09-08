package parking_lot.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import org.easymock.*;

import org.junit.Test;
import parking_lot.app.*;
import parking_lot.app.anon.*;
import parking_lot.app.repository.*;

public class InputParserTest 
{
    private EasyMockSupport support = new EasyMockSupport();

    @Test
    public void normalTest()
    {
        IMocksControl ctrl = support.createControl();
        IParkingLot parkingLot = ctrl.createMock(IParkingLot.class);
        InputParser parser = new InputParser(new Builder<IParkingLot, Integer>(){
            public IParkingLot build(Integer slot){
                assertEquals((int)slot, (int)6);
                return parkingLot;
            }
        });
        try {
            EasyMock.expect(parkingLot.add("KA-01-HH-1234", "White")).andReturn(new ParkingLotRecord(1, "KA-01-HH-1234", "White"));
            EasyMock.expect(parkingLot.add("KA-01-HH-9999", "White")).andReturn(new ParkingLotRecord(2, "KA-01-HH-9999", "White"));
            EasyMock.expect(parkingLot.add("KA-01-BB-0001", "Black")).andReturn(new ParkingLotRecord(3, "KA-01-BB-0001", "Black"));
            EasyMock.expect(parkingLot.add("KA-01-HH-7777", "Red")).andReturn(new ParkingLotRecord(4, "KA-01-HH-7777", "Red"));
            EasyMock.expect(parkingLot.add("KA-01-HH-2701", "Blue")).andReturn(new ParkingLotRecord(5, "KA-01-HH-2701", "Blue"));
            EasyMock.expect(parkingLot.add("KA-01-HH-3141", "Black")).andReturn(new ParkingLotRecord(6, "KA-01-HH-3141", "Black"));
            support.replayAll();

            parser.parse("create_parking_lot 6");
            parser.parse("park KA-01-HH-1234 White");
            parser.parse("park KA-01-HH-9999 White");
            parser.parse("park KA-01-BB-0001 Black");
            parser.parse("park KA-01-HH-7777 Red");
            parser.parse("park KA-01-HH-2701 Blue");
            //parser.parse("park KA-01-HH-3141 Black");
            support.verifyAll(); // 6
        } catch(parking_lot.app.exception.FullSlotException ex){
            
        } catch(parking_lot.app.exception.SlotIsEmptyException ex){
            
        } catch(parking_lot.app.exception.LotInitializedException ex){
            
        } catch(Exception ex){
            throw ex;
        }
    }
}
