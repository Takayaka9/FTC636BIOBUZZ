package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Stopper {
    private Servo s;
    public static double open = 0.5;
    public Stopper(HardwareMap h){
        s = h.get(Servo.class, "stopper");
    }
    public CommandBuilder open(){
        return instant(() -> s.setPosition(open));
    }
}
