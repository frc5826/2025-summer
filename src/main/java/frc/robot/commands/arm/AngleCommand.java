package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class AngleCommand extends Command {
    double angle;
    ArmSubsystem arm;

    public AngleCommand (ArmSubsystem arm,double angle) {
        this.angle=angle;
        this.arm=arm;
        addRequirements(arm);
        //go to an angle

    }


}
