package frc.robot.subsystems;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NTSendable;
import edu.wpi.first.networktables.NTSendableBuilder;

import java.util.function.DoubleSupplier;

public class ArmController implements NTSendable {

    protected double V, G;
    protected TrapezoidProfile profile;
    protected TrapezoidProfile.State setPoint;
    protected TrapezoidProfile.State goal;
    protected PID pid;
    protected double min, max;
    protected double output;
    protected DoubleSupplier angle;


    public ArmController(double V, double G, double maxVelocity, double maxAcceleration, double min, double max, PID pid, DoubleSupplier angle){

        this.pid = pid;
        this.V = V;
        this.G = G;
        this.min = min;
        this.max = max;
        this.angle = angle;

        profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration));
        setPoint = new TrapezoidProfile.State();
        goal = new TrapezoidProfile.State();

    }

    public void setGoal(double startPosition, double endPosition, double startVelocity){

        goal = new TrapezoidProfile.State(endPosition, 0);
        setPoint = new TrapezoidProfile.State(startPosition, startVelocity);

    }

    public double calculate(double deltaTime){

        setPoint = profile.calculate(deltaTime, setPoint, goal);
        pid.setGoal(setPoint.position);
        output = setPoint.velocity * V + G * Math.cos(angle.getAsDouble()*Math.PI/180) + pid.calculate();
        if (output>max){
            output = max;
        } else if (output<min){
            output = min;
        }
        return output;
    }

    private double getV() {
        return V;
    }

    private double getG() {
        return G;
    }

    private void setV(double v) {
        V = v;
    }

    private void setG(double g) {
        G = g;
    }

    private double getSetPoint() {
        return setPoint.position;
    }

    private double getOutput() {
        return output;
    }

    private double getGoal() {
        return goal.position;
    }

    public boolean isFinished(){
        return setPoint.position == goal.position;
    }

    @Override
    public void initSendable(NTSendableBuilder builder) {
        builder.setSmartDashboardType("5826-ElevatorController");
        builder.addDoubleProperty("V", this::getV, this::setV );
        builder.addDoubleProperty("G", this::getG, this::setG);
        builder.addDoubleProperty("setPoint", this::getSetPoint, null);
        builder.addDoubleProperty("Output", this::getOutput, null);
        builder.addDoubleProperty("Goal", this::getGoal, (x) -> setGoal(angle.getAsDouble(),x,0));
    }
}
