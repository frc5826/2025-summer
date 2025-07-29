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

    @Override
    public void initialize() {
        super.initialize();
        arm.setAngle(angle);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(angle- arm.getAngle())<5;
    }
}

