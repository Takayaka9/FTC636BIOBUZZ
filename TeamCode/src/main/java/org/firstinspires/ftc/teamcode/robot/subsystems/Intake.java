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
    public void setMotorPower(double power){
        intaker.setPower(power);
        intakel.setPower(power);
    }
    public Intake(HardwareMap hardwareMap){
        intaker = hardwareMap.get(DcMotorEx.class, "ir");
        intakel = hardwareMap.get(DcMotorEx.class, "il");
        intakel.setDirection(DcMotorEx.Direction.REVERSE);
        intaker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
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
