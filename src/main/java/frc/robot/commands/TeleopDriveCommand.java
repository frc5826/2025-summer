package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class TeleopDriveCommand extends Command {
    XboxController xbox;
    SwerveSubsystem swerveSubsystem;

    public TeleopDriveCommand(SwerveSubsystem swerveSubsystem, XboxController xbox) {
        this.swerveSubsystem = swerveSubsystem;
        this.xbox = xbox;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void execute() {
        super.execute();
        double xSpeed = -xbox.getRightY();
        double ySpeed = -xbox.getRightX();
        double turnSpeed = -xbox.getLeftX();

        swerveSubsystem.drive(xSpeed,ySpeed,turnSpeed);

    }
}
