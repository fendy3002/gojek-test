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
        } catch(LotInitializedException ex){
            System.out.println("Parking lot already initialized");
        }
    }
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        InputParser parser = new InputParser();

        String input = "";
        do{
            input = sc.next();
            try{
                System.out.println(parser.parse(input));
            } catch (FullSlotException ex){

            } catch (SlotIsEmptyException ex){
                
            }
        }
        while(!input.equals("exit"));
    }
}
