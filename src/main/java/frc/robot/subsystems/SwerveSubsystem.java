package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.math.SwerveMath;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;

import java.io.File;

public class SwerveSubsystem extends SubsystemBase {
    SwerveDrive swerveDrive;

    public SwerveSubsystem() {
        double angleConversionFactor = SwerveMath.calculateDegreesPerSteeringRotation(
                12.8, 1);

        double driveConversionFactor = SwerveMath.calculateMetersPerRotation(
                Units.inchesToMeters(4), 6.12, 1);
        try {
            double maximumSpeed = Units.feetToMeters(16.6);
            File directory = new File(Filesystem.getDeployDirectory(), "swerve");
            swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed, angleConversionFactor, driveConversionFactor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwerveDriveTelemetry.verbosity = SwerveDriveTelemetry.TelemetryVerbosity.HIGH;
    }

    @Override
    public void periodic() {
        super.periodic();
        swerveDrive.updateOdometry();
    }
    public void resetOdometry(){
        swerveDrive.resetOdometry(new Pose2d(0,0,new Rotation2d(Math.PI)));
    }
    public void drive(double xSpeed, double ySpeed, double turnSpeed) {
        swerveDrive.driveFieldOriented(new ChassisSpeeds(xSpeed,ySpeed,turnSpeed));
    }

    public void zeroGyro (){

        swerveDrive.zeroGyro();

    }

    public double getDistance(){
        return swerveDrive.getPose().getX();
    }

}