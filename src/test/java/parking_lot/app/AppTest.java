package parking_lot.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.*;
import java.util.*;
import java.io.*;

public class AppTest 
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void normalTest()
    {
        String[] args = new String[]{
            "file_inputs.txt"
        };
        parking_lot.app.App.main(args);

        String expectedStatus = "Slot No.\tRegistration No\tColour\n" +
            "1\tKA-01-HH-1234\tWhite\n" +
            "2\tKA-01-HH-9999\tWhite\n" +
            "3\tKA-01-BB-0001\tBlack\n" +
            "5\tKA-01-HH-2701\tBlue\n" +
            "6\tKA-01-HH-3141\tBlack";
        String expectedResult = "Created a parking lot with 6 slots\n"+
            "Allocated slot number: 1\n"+
            "Allocated slot number: 2\n"+
            "Allocated slot number: 3\n"+
            "Allocated slot number: 4\n"+
            "Allocated slot number: 5\n"+
            "Allocated slot number: 6\n"+
            "Slot number 4 is free\n"+
            expectedStatus + "\n" +
            "Allocated slot number: 4\n"+
            "Sorry, parking lot is full\n"+
            "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n"+
            "1, 2, 4\n"+
            "6\n"+
            "Not found\n";
        assertEquals(expectedResult, outContent.toString());
    }
}
