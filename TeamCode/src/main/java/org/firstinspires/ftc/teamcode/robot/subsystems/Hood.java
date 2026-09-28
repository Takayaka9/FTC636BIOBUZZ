package org.firstinspires.ftc.teamcode.robot.subsystems;

import androidx.annotation.NonNull;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.util.InterpLUT;

import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

@Configurable
public class Hood implements Mechanism {
    private final NextServo h = new NextServo("hood");
    private final InterpLUT lut = new InterpLUT();
    private void createInterpLUT(){
        lut.add(0, p1);
        lut.add(d1, p1);
        lut.add(10000, p6);
        lut.createLUT();
    }
    @NonNull
    @Override
    public Command getDefaultCommand() {
        return instant(this::createInterpLUT);
    }

    @Override
    public void periodic() {

    }

    //dx is for distance from hive, px is for position
    static double d1 = 36; static double p1 = 0.99; //taka tuned
    static double d2 = 53.6; static double p2 = 0.88; //taka tuned
    static double d3 = 73.5; static double p3 = 0.85;//tuned
    static double d4 = 100; static double p4 = 0.85;//tuned
    static double d5 = 135.5; static double p5 = 0.85; //max
    static double d6 = 150; static double p6 = 0.85; //max
    //angles hood based on poses passed in
    public void angleHood(Pose target, Pose current) {
        double targetDistance = current.distance(target);
        double angle = lut.get(targetDistance);
        setPosition(angle);
    }
    public void setPosition(double position){
        h.setPosition(position);
    }
    //yeah idk when we'd use this but
    public void down(){
        setPosition(1);
    }
}