package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.util.InterpLUT;


@Configurable
public class Flywheel {
    private final DcMotorEx fly;
    private final InterpLUT lut = new InterpLUT();
    public Flywheel(HardwareMap hardwareMap){
        fly = hardwareMap.get(DcMotorEx.class, "shooter1");
        fly.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fly.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lut.add(0, r1);
        lut.add(d1, r1);
        lut.add(1000, r6);
        lut.createLUT();
    }
    //bangbang controller (not in use)
    private void bangbang(double target, DcMotorEx m){
        if(Math.abs(target - fly.getVelocity()) < 40){
            m.setPower(0);
        }
        else if(target > fly.getVelocity()){
            m.setPower(1);
        }
    }
    //pid for flywheel
    private void pid(double target, DcMotorEx m){
        double vel = fly.getVelocity();
        double error = target - vel;
        double feedForward = Ks + (target * Kv);
        double output = error* Kp + feedForward;

        m.setPower(output);
    }
    double target = 0;
    //sets target either by passing in pose and calculating distance or direct rpm
    public void setTarget(Pose goal, Pose current){
        target = lut.get(current.distance(goal));
    }
    public void setTarget(double t){
        target = t;
    }
    public double getTarget(){
        return target;
    }
    //runs the actual flywheel
    public void run(double target){
        pid(target, fly);
    }
    public void stop(){
        fly.setPower(0);
    }
    public boolean targetReached(double target){
        return Math.abs(target - fly.getVelocity()) < 110;
    }
    public static double Kp = 0.8;
    public static double Kd = 0;
    public static double Kv = 0.00049;
    public static double Ks = 0.17;
    public static double minActiveTps = 900;
    public static double maxError = 3;
    //public static double Kf = 0.00036;
    //really ugly interpLUT system
    static double d1 = 36; static double r1 = 850;//tuned
    static double d2 = 53.6; static double r2 = 1000;//tuned
    static double d3 = 73.5; static double r3 = 1075;//tuned
    static double d4 = 100; static double r4 = 1125;//tuned
    static double d5 = 135.5; static double r5 = 1350;//tuned
    static double d6 = 150; static double r6 = 1360;//tuned
    public static double brake = -0.3;
}