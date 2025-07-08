package frc.robot.commands.mouth;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MouthSubsystem;

public class RotateCommand extends Command  {
    MouthSubsystem mouth ;
    double rotateSpeed;
    public RotateCommand (MouthSubsystem mouth, double rotateSpeed) {
        this.rotateSpeed=rotateSpeed;
                this.mouth=mouth;
        addRequirements(mouth); //Sets speed of the mouth wheels.
    }

}
