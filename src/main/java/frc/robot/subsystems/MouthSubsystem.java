package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MouthSubsystem extends SubsystemBase {

    SparkMax motor;
    public MouthSubsystem () {

        motor = new SparkMax(13, SparkLowLevel.MotorType.kBrushless);

    }

    public void rotate (double rotateSpeed) {

        motor.set(rotateSpeed);

    }

}
