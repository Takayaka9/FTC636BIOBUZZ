package org.firstinspires.ftc.teamcode.robot.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

@Configurable
public class Stopper implements Mechanism {
    private final NextServo s = new NextServo("stopper");
    //positions
    public static double open = 0.5;
    public static double close = 0;
    public void setPosition(double position){
        s.setPosition(position);
    }
    public Command open(){
        return instant(() -> setPosition(open));
    }
    public Command close(){
        return instant(() -> setPosition(close));
    }
}
