package parking_lot.app;
import java.io.*;
import java.util.Scanner;
import parking_lot.app.exception.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static void parseInput(InputParser parser, String input){
        try{
            System.out.println(parser.parse(input));
        } catch (FullSlotException ex){
            System.out.println("Sorry, parking lot is full");
        } catch (SlotIsEmptyException ex){
            System.out.println("Slot is empty");
        }
    }
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        InputParser parser = new InputParser();

        String input = "";
        parseInput(parser, "create_parking_lot 6");
        parseInput(parser, "park KA-01-HH-1234 White");
        parseInput(parser, "park KA-01-HH-9999 White");
        parseInput(parser, "park KA-01-BB-0001 Black");
        parseInput(parser, "park KA-01-HH-7777 Red");
        parseInput(parser, "park KA-01-HH-2701 Blue");
        parseInput(parser, "park KA-01-HH-3141 Black");
        parseInput(parser, "leave 4");
        parseInput(parser, "status");
        parseInput(parser, "park KA-01-P-333 White");
        parseInput(parser, "park DL-12-AA-9999 White");
        parseInput(parser, "registration_numbers_for_cars_with_colour White");
        parseInput(parser, "slot_numbers_for_cars_with_colour White");
        parseInput(parser, "slot_number_for_registration_number KA-01-HH-3141");
        parseInput(parser, "slot_number_for_registration_number MH-04-AY-1111");
        
        return ;/*
        do{
            input = sc.next();
            try{
                System.out.println(parser.parse(input));
            } catch (FullSlotException ex){

            } catch (SlotIsEmptyException ex){
                
            }
        }
        while(!input.equals("exit"));*/
    }
}
