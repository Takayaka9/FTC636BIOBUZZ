package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Stopper {
    private final Servo s;
    //positions
    public static double open = 0.5;
    public static double close = 0;
    public Stopper(HardwareMap h){
        s = h.get(Servo.class, "stopper");
    }
    public CommandBuilder open(){
        return instant(() -> s.setPosition(open));
    }
    public CommandBuilder close(){
        return instant(() -> s.setPosition(close));
    }
}
