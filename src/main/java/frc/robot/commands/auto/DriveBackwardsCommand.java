package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveBackwardsCommand extends Command {
    double distance;
    double speed;
    SwerveSubsystem swerve;


    public DriveBackwardsCommand(SwerveSubsystem swerve, double distance, double speed){
    this.swerve=swerve;
    this.speed=-speed;
    this.distance=-distance;
    addRequirements(swerve);

    }

    @Override
    public void initialize() {
        super.initialize();
        swerve.resetOdometry();
    }

    @Override
    public void execute() {
        super.execute();
        swerve.drive(speed,0,0);
    }

    @Override
    public boolean isFinished() {
    return swerve.getDistance()<distance;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        swerve.drive(0,0,0);
    }
}

