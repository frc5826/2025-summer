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
                System.out.println("Too low");
            } else if (x<y) {
                System.out.println("Too high");
            }
        }
    }
}
