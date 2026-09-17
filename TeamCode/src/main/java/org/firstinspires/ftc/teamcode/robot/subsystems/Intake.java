package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private final DcMotorEx intake;
    public static double in = 1;
    public static double out = -1;
    public Intake(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotorEx.class, "i");
    }
    public CommandBuilder in(){
        return instant(() -> intake.setPower(in));
    }
    public CommandBuilder out(){
        return instant(() -> intake.setPower(out));
    }
    public CommandBuilder stop(){
        return instant(() -> intake.setPower(0));
    }
}
