package frc.robot;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Guess my number!?");
        int x = (int) (System.currentTimeMillis() % 100);
        int y = -1;
        while (x != y) {
            var s = new Scanner(System.in);
            y = s.nextInt();
            if(x>y){
                System.out.println("to low");
            } else if (x<y) {
                System.out.println("To high");
            }
        }
    }
}
//Emmett Record for a billion 21m 20s COOPER RECORD 21m 19.9999999s