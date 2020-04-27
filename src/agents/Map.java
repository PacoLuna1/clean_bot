package agents;
import java.util.ArrayList;
import agents.Cell;

import java.util.Scanner;

public class Map {
    Cell[][] map = new Cell[10][10];
    ArrayList<Integer> route = new ArrayList<Integer>();

    public Map() throws InterruptedException {
       main();
    }

    public void main(){
            createMap();
            addDirt();
            printMap();
            isClean();
            printIndexMap();
            selectRoute();
            printRouteMap();
            travelRouteMap();
            printMap();
            isClean();
    }

    public void createMap(){
        for (int i = 0; i < map[0].length ; i++) {
            for (int j = 0; j < map[0].length; j++) {
                this.map[i][j] = new Cell();
            }
        }
    }

    public void selectRoute(){
        Boolean stay = true;
        Boolean another = true;

        do {
            this.route.add(captureCoordenate());
            do {
                Scanner scan = new Scanner(System.in);
                print("Do you want to capture another? : (true/false) ");
                try {
                    Boolean response = scan.nextBoolean();
                    if (response) {
                        another = false;
                    } else {
                        stay = false;
                        another = false;
                    }
                } catch (Exception e) {
                    print("Please capture a valid value. \n");
                }
            } while (another);
        }while(stay);

        print("Starting the Cleaning Route ... \n");

    }

    public Integer captureCoordenate(){
        Boolean stay = true;
        Integer capture = -1;
        Double max = Math.pow(map[0].length, 2);

        do{
            Scanner scan = new Scanner(System.in);
            print("Index: ");
            try {
                capture = scan.nextInt();

                if (capture >= 0 && capture <= max) {
                    stay = false;
                    print("Valid Index \n");
                } else {
                    stay = true;
                    print("Please enter a valid Index. \n");
                }
            }
            catch (Exception e){
                print("Please enter a valid Index. \n");
            }

        }while(stay);

        return capture;
    }

    public void addDirt(){
        Boolean state;
        for (int i = 0; i < map[0].length ; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (Math.random() > 0.5){
                    state = true;
                }else{
                    state = false;
                }
                this.map[i][j].setCell(state);
            }
        }
    }

    public void isClean(){
        String response;
        Boolean isDirty = false;
        for (int i = 0; i < map[0].length ; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (! this.map[i][j].getCell()){
                    isDirty = true;
                    break;
                }
            }
            if(isDirty){
                break;
            }
        }
        if(isDirty){
            response = "The map is dirty \n";
        }else{
            response = "The map is clean \n";
        }
        print(response);
    }

    public void printMap(){
        String dirty = "░";
        String clean = "▓";

        print("\n");
        for (int i = 0; i < this.map[0].length ; i++) {
            print("|");
            for (int j = 0; j < this.map[0].length; j++) {
                if (this.map[i][j].getCell()){
                    print("_" + clean + "_");
                }else{
                    print("_" + dirty + "_");
                }
                if(j != (this.map[0].length - 1)){
                    print("|");
                }
            }
            print("| \n");
        }
        print("\n");
    }

    public void printIndexMap(){
        Integer index = 0;
        print("\n");
        for (int i = 0; i < this.map[0].length ; i++) {
            print("|");
            for (int j = 0; j < this.map[0].length; j++) {
                index  += 1;
                print("_" + index + "_");
                if(j != (this.map[0].length - 1)){
                    print("|");
                }
            }
            print("| \n");
        }
        print("\n");
    }

    public void printRouteMap(){
        String dirty = "░";
        String clean = "▓";
        String routeChar = "X";
        Integer index = 0;

        print("\n");
        for (int i = 0; i < this.map[0].length ; i++) {
            print("|");
            for (int j = 0; j < this.map[0].length; j++) {
                index  += 1;
                if (this.map[i][j].getCell()){
                    if(this.route.contains(index)){
                        print("_" + routeChar + "_");
                    }else{
                        print("_" + clean + "_");
                    }
                }else{
                    if(this.route.contains(index)) {
                        print("_" + routeChar + "_");
                    }else {
                        print("_" + dirty + "_");
                    }
                }
                if(j != (this.map[0].length - 1)){
                    print("|");
                }
            }
            print("| \n");
        }
        print("\n");
    }

    public void travelRouteMap() {
        String dirty = "░";
        String clean = "▓";
        String bot = "■";
        String routeChar = "X";
        Integer index = 0;
        String cleaning = "Cleaning...\n";
        String cleaned = "Cleaned \n";
        Boolean needsCleanning = false;

        print("\n");
        for (int k = 0; k < this.route.size() ; k++) {
            index = 0;
            for (int i = 0; i < this.map[0].length ; i++) {
                print("|");
                for (int j = 0; j < this.map[0].length; j++) {
                    index += 1;
                    if (this.map[i][j].getCell()){
                        if(this.route.get(k) == index){
                            print("_" + bot + "_");
                            this.route.set(k,-1);
                        }else{
                            if(this.route.contains(index)){
                                print("_" + routeChar + "_");
                            }else{
                                print("_" + clean + "_");
                            }
                        }
                    }else{
                        if(this.route.get(k) == index){
                            print("_" + bot + "_");
                            this.map[i][j].setCell(true);
                            this.route.set(k,-1);
                            needsCleanning = true;
                        }else{
                            if(this.route.contains(index)){
                                print("_" + routeChar + "_");
                            }else{
                                print("_" + dirty + "_");
                            }
                        }
                    }
                    if(j != (this.map[0].length - 1)){
                        print("|");
                    }
                }
                print("| \n");
                if(needsCleanning == true && i == (this.map[0].length - 1)){
                    cleanPlace(cleaned , cleaning);
                }else if(i == (this.map[0].length - 1)){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print(cleaned);
                }
            }
            print("\n");
        }
    }

    public void cleanPlace(String cleaned , String cleaning){
        print(cleaning);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(cleaned);
    }


    public void print(String aux){
        System.out.print(aux);
    }
}

