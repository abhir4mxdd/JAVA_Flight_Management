import java.io.*;
import java.util.Scanner;
public class CSV
{
    public static void main(String[] args) throws Exception
    {
//parsing a CSV file into Scanner class constructor  
        Scanner sc = new Scanner(new File("C://Users//andra//IdeaProjects//Flight/flight_records_2023-10-27_060222.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.print(sc.next());  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }
}  