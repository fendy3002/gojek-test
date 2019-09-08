package parking_lot.app;
import java.io.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);

        String input = "";
        System.out.println("");
        input = sc.nextInt();

        do{
            input = sc.next();
            System.out.println(input);
        }
        while(!input.equals("exit"));
    }
}
