package frc.robot.commands;

import edu.wpi.first.math.util.Units;
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
        if ( Math.abs(xSpeed)<0.15){
            xSpeed=0;
        }
        if (Math.abs(ySpeed)<0.15) {
            ySpeed = 0;
        }
        if (Math.abs(turnSpeed)<0.2){
            turnSpeed=0;
         }

        if (xbox.getPOV()==0){
            xSpeed=0.25;
        }
        if (xbox.getPOV()==90){
            ySpeed=-0.25;
        }
        if (xbox.getPOV()==180){
            xSpeed=-0.25;
        }
        if (xbox.getPOV()==270){
            ySpeed=0.25;
        }
        if (xbox.getRightStickButton()){
            xSpeed=xSpeed*2;
            ySpeed=ySpeed*2;
            turnSpeed=turnSpeed*2;
        }

        swerveSubsystem.drive(xSpeed*1,ySpeed*1,turnSpeed*Math.PI);

    }
}
