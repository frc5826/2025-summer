package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    SparkMax moter1;
    SparkMax moter2;
    SparkMax moter3;
    SparkMax moter4;

    public ArmSubsystem (){

        moter1=new SparkMax(9, SparkLowLevel.MotorType.kBrushless);
        moter2=new SparkMax(10, SparkLowLevel.MotorType.kBrushless);
        moter3=new SparkMax(11, SparkLowLevel.MotorType.kBrushless);
        moter4=new SparkMax(12, SparkLowLevel.MotorType.kBrushless);



    }
}
