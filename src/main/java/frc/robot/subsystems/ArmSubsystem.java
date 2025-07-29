package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableEvent;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    SparkMax motor1;
    SparkMax motor2;
    SparkMax motor3;
    SparkMax motor4;
    double goal;
    double OFFSET = 0.205;
    double G = 0.05;
    DutyCycleEncoder encoder;
    PID pid = new PID(0.05, 0, 0, 1, 0, 0, this::getAngle);
    NetworkTableEntry gNetwork=SmartDashboard.getEntry("Arm/G");


    public ArmSubsystem() {

        motor1 = new SparkMax(9, SparkLowLevel.MotorType.kBrushless);
        motor2 = new SparkMax(10, SparkLowLevel.MotorType.kBrushless);
        motor3 = new SparkMax(11, SparkLowLevel.MotorType.kBrushless);
        motor4 = new SparkMax(12, SparkLowLevel.MotorType.kBrushless);
        encoder = new DutyCycleEncoder(0);

        encoder.setInverted(true);

        SparkMaxConfig config = new SparkMaxConfig();
        config.inverted(false);
        SparkMaxConfig configFollowerInverted = new SparkMaxConfig();
        configFollowerInverted.follow(motor1, true);
        SparkMaxConfig follower = new SparkMaxConfig();
        follower.follow(motor1,false);
        motor1.configure(config, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        motor2.configure(follower, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        motor3.configure(configFollowerInverted, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        motor4.configure(configFollowerInverted, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);


        SmartDashboard.putData("Arm/PID",pid);
        SmartDashboard.putNumber("Arm/G",G);
        SmartDashboard.putData("Arm/encoder",encoder);
    }

    @Override
    public void periodic() {
        super.periodic();
        double output = pid.calculate()  + gNetwork.getDouble(0) * Math.cos(getAngle() * Math.PI / 180);
       SmartDashboard.putNumber("Arm/angle", getAngle());
        motor1.set(-output);
    }

    public void setAngle(double angle) {
        if (angle >= 90) {
            angle = 90;
        } else if (angle <= 0) {
            angle = 0;
        }
        pid.setGoal(angle);
        goal = angle;
    }

    public double getAngle() {
        return (encoder.get() + -OFFSET) * 360;
    }

}
