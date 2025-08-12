// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.arm.AngleCommand;
import frc.robot.commands.auto.DriveBackwardsCommand;
import frc.robot.commands.mouth.RotateCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.MouthSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


public class RobotContainer
{
    SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    XboxController xbox = new XboxController(1);
    ArmSubsystem arm = new ArmSubsystem();
        MouthSubsystem mouthSubsystem = new MouthSubsystem();

    Joystick joystick = new Joystick(2);
    NetworkTableEntry dropCoral= NetworkTableInstance.getDefault()
            .getEntry("AutoDropCoral?");

    public RobotContainer()

    {
        CameraServer.startAutomaticCapture();
        CommandScheduler.getInstance().setDefaultCommand(swerveSubsystem,new TeleopDriveCommand(swerveSubsystem,xbox));

        configureBindings();
        dropCoral.setBoolean(true);
    }
    
    
    private void configureBindings() {
        //Out
        new Trigger(() -> joystick.getRawButton(3)).whileTrue(new RotateCommand(mouthSubsystem, 0.5));
        //In
        new Trigger(() -> joystick.getRawButton(4)).whileTrue(new RotateCommand(mouthSubsystem, -0.5));
        //Station Pickup
        new Trigger(() -> joystick.getRawButton(9)).onTrue(new AngleCommand(arm, 65));
        //Drop Off
        new Trigger(() -> joystick.getRawButton(12)).onTrue(new AngleCommand(arm, 30));
        //Ground Pickup
        new Trigger(() -> joystick.getRawButton(11)).onTrue(new AngleCommand(arm, 0).repeatedly());
        //Climb
        new Trigger(() -> joystick.getRawButton(10)).onTrue(new AngleCommand(arm, 100));
        new Trigger(() -> xbox.getYButton()).onTrue(new InstantCommand(() -> swerveSubsystem.zeroGyro()));

    }

    
    
    public Command getAutonomousCommand()
    {

        if(dropCoral.getBoolean(true)){
            return new AngleCommand(arm,30).andThen(
                    new DriveBackwardsCommand(swerveSubsystem, 1.25,.3),
                    new RotateCommand(mouthSubsystem,0.5).withTimeout(1)
            );


        }
        else{return new AngleCommand(arm,30).andThen(
                new DriveBackwardsCommand(swerveSubsystem, 0.5,.3),
                new RotateCommand(mouthSubsystem,0).withTimeout(1)
        );}

        
    }
}
