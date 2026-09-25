package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Configurable
public class Wedge {
    //to be renamed, ugly name
    private Servo pivot;
    public static double up = 1;
    public static double down = 0.5;
    public Wedge(HardwareMap h){
        pivot = h.get(Servo.class, "wedge");
    }
    public void setPosition(double position){
        pivot.setPosition(position);
    }
    public CommandBuilder up(){
        return instant(() -> setPosition(up));
    }
    public CommandBuilder down(){
        return instant(() -> setPosition(down));
    }
}
