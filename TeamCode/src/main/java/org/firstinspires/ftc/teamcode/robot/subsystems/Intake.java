package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

//import com.bylazar.configurables.annotations.Configurable;
import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Configurable
public class Intake {
    private final DcMotorEx intake;
    private final DcMotorEx intake2;
    public static double in = 1;
    public static double out = -1;
    public void setMotorPower(double power){
        intake.setPower(power);
        intake2.setPower(power);
    }
    public Intake(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotorEx.class, "i");
        intake2 = hardwareMap.get(DcMotorEx.class, "i2");
        intake2.setDirection(DcMotorEx.Direction.REVERSE);
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
