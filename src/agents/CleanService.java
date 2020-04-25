package agents;
import jade.core.Agent;
import java.util.Scanner;
import agents.Map;

public class CleanService extends Agent{
    public void setup(){
        menu();
    }

    public void menu(){
        System.out.flush();
        Boolean stay = true;

        while(stay) {
            print("");
            print("==== Welcome to the Clean Service Agent ===");
            print("Please choose a valid option: ");
            print("1. Start Cleaning process");
            print("2. Information about the Agent");
            print("3. Exit");
            print("============================================");
            Short opt = capture();
            switch (opt) {
                case 1:
                  startCleanning();
                    break;
                case 2:
                   info();
                    break;
                case 3:
                    stay = false;
                    break;
                default:
                    print("--> Please enter a valid value");
            };
        };
        print("");
        print("--> Thanks for using the Clean Service Agent");
        print("Please give a few seconds I'm turning off");
        System.exit(0);
    }
    public void startCleanning(){
        Map map = new Map();
    }
    public void info(){
        print("This is Agent created with Jade and Java.");
        print("Simulating the cleaning of a surface with a route gived by the end user");
        print("Date of development: 25 of April of 2020");
        print("Authors: Francisco Luna, Saul Rubio");
        print("");
    }

    public void print(String aux){
        System.out.println(aux);
    }

    public Short capture(){
        Scanner scan = new Scanner(System.in);
        return scan.nextShort();
    }
}
