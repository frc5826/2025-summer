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

    @Override
    public void initialize() {
        super.initialize();
        mouth.rotate(rotateSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        mouth.rotate(0);
    }
}
