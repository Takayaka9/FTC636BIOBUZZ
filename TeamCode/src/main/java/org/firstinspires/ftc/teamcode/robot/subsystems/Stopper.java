package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Configurable
public class Stopper {
    private final Servo s;
    //positions
    public static double open = 0.5;
    public static double close = 0;
    public Stopper(HardwareMap h){
        s = h.get(Servo.class, "stopper");
    }
    public void setPosition(double position){
        s.setPosition(position);
    }
    public CommandBuilder open(){
        return instant(() -> setPosition(open));
    }
    public CommandBuilder close(){
        return instant(() -> setPosition(close));
    }
}
