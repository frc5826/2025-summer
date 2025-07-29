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
    double OFFSET = 0.193;
    DutyCycleEncoder encoder;
    PID pid = new PID(0.0025, 0, 0, 1, 0, 0, this::getAngle);
    ArmController controller = new ArmController(0.0006, 0.065, 1080, 1200,-1,1,pid,this::getAngle);

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
        SmartDashboard.putData("Arm/controller",controller);
        SmartDashboard.putData("Arm/encoder",encoder);
    }

    @Override
    public void periodic() {
        super.periodic();
        double output = controller.calculate(0.02);
        SmartDashboard.putNumber("Arm/angle", getAngle());
        motor1.set(-output);
    }

    public void setAngle(double angle) {
        if (angle >= 100) {
            angle = 100;
        } else if (angle <= 0) {
            angle = 0;
        }
        controller.setGoal(getAngle(),angle,0);
        goal = angle;
    }

    public double getAngle() {
        return (encoder.get() + -OFFSET) * 360;
    }

}
