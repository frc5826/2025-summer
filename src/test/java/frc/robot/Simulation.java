package frc.robot;

import frc.robot.subsystems.ArmController;
import frc.robot.subsystems.PID;

public class Simulation {
    static PID pid = new PID(0, 0, 0, 1, 0, 0, ()->0);
    static ArmController controller = new ArmController(0.0001, 0.05, 1200, 2400,-1,1,pid,()->0);

    public static void main(String[] args) {
        controller.setGoal(0,100,0);
        while(true){
            System.out.println(controller.calculate(0.02));
        }
    }
}
