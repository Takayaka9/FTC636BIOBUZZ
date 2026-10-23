package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

//import com.bylazar.configurables.annotations.Configurable;
import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Configurable
public class Wedge {
    //to be renamed, ugly name
    private Servo pivotL;
    private Servo pivotR;
    public static double up = 1;
    public static double down = 0.5;
    public Wedge(HardwareMap h){
        pivotL = h.get(Servo.class, "wedgeL");
        pivotR = h.get(Servo.class, "wedgeR");
    }
    public void setPosition(double position){
        pivotL.setPosition(position);
        pivotR.setPosition(position);
    }
    //public Wedge(HardwareMap h){ pivotR = h.get(Servo.class, "wedgeR")};
    public CommandBuilder up(){
        return instant(() -> setPosition(up));
    }
    public CommandBuilder down(){
        return instant(() -> setPosition(down));
    }
}
