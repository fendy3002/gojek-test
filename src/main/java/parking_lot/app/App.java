package parking_lot.app;
import java.io.*;
import java.util.*;
import java.nio.file.*;
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
        InputParser parser = new InputParser();
        if(args.length == 0){
            Scanner sc = new Scanner(System.in);

            String input = "";
            do{
                input = sc.nextLine();
                parseInput(parser, input);
            }
            while(!input.equals("exit"));
        } else {
            String file = args[0];
            Path currentPath = Paths.get(System.getProperty("user.dir"));

            String fullFilePath = Paths.get(currentPath.toString(), file).toString();
            try {
                BufferedReader br = new BufferedReader(new FileReader(fullFilePath));

                String line;
                while ((line = br.readLine()) != null) {
                    parseInput(parser, line);
                }
            } catch(Exception ex){
                System.out.println("File cannot be read!");
            }
        }
    }
}
