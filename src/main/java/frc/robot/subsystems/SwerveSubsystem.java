package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

import java.io.File;

public class SwerveSubsystem extends SubsystemBase {
    SwerveDrive swerveDrive;

    public SwerveSubsystem() {
        try {
            double maximumSpeed = Units.feetToMeters(4.5);
            File directory = new File(Filesystem.getDeployDirectory(), "swerve");
            swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drive(double xSpeed,double ySpeed,double turnSpeed) {
        swerveDrive.drive(new ChassisSpeeds(xSpeed,ySpeed,turnSpeed));
    }

}