package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

//import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Configurable
public class Intake {
    private final DcMotorEx intaker;
    private final DcMotorEx intakel;
    public static double in = 1;
    public static double out = -1;
    public Intake(HardwareMap hardwareMap){
        intaker = hardwareMap.get(DcMotorEx.class, "ir");
        intakel = hardwareMap.get(DcMotorEx.class, "il");
        intaker.setDirection(DcMotorEx.Direction.REVERSE);
        intaker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
    public void setMotorPower(double power){
        intaker.setPower(power);
        intakel.setPower(power);
    }
    //could be nice to use pid for intake too
    private void setMotorPowerPID(double target){
        double vel = intaker.getVelocity();
        double error = target - vel;
        double feedForward = Ks + (target * Kv);
        double output = error* Kp + feedForward;

        intaker.setPower(output);
        intakel.setPower(output);
    }
    //TODO: to be tuned
    public static double Kp = 0;
    public static double Kd = 0;
    public static double Kv = 0;
    public static double Ks = 0;
    public CommandBuilder in(){
        return instant(() -> setMotorPower(in));
    }
    public CommandBuilder out(){
        return instant(() -> setMotorPower(out));
    }
    public CommandBuilder stop(){
        return instant(() -> setMotorPower(0));
    }
}
