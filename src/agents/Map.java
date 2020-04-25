package agents;
import agents.Cell;

import java.util.Scanner;

public class Map {
    Cell[][] map = new Cell[3][3];
    Scanner scan = new Scanner(System.in);

    public Map(){
        createMap();
        printMap();
        isClean();
        addDirt();
        printMap();
        isClean();
        printIndexMap();
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

        while(stay){
            Short aux = captureCoordenate();
        }

    }

    public Short captureCoordenate(){
        return scan.nextShort();
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
        for (int i = 0; i < map[0].length ; i++) {
            print("|");
            for (int j = 0; j < map[0].length; j++) {
                if (this.map[i][j].getCell()){
                    print("_" + clean + "_");
                }else{
                    print("_" + dirty + "_");
                }
                if(j != (map[0].length - 1)){
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
        for (int i = 0; i < map[0].length ; i++) {
            print("|");
            for (int j = 0; j < map[0].length; j++) {
                index  += 1;
                print("_" + index + "_");
                if(j != (map[0].length - 1)){
                    print("|");
                }
            }
            print("| \n");
        }
        print("\n");
    }


    public void print(String aux){
        System.out.print(aux);
    }
}

