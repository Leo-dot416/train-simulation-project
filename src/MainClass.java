import java.util.Scanner;

public class MainClass
{
    public static void main(String[] args)
    {
        MyRailway railway = new MyRailway();

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            String command = scanner.nextLine();

            railway.processCommand(command);
        }
    }
}
