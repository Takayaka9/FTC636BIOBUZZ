package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;

public class Kaoru {
    //name to be changed

    //public final Flywheel fly;
    //public final Hood hood;
    //public final Intake intake;
    public final Stopper stop;
    //public final Turret turret;
    public Kaoru(HardwareMap h){
        stop = new Stopper(h);
    }
}
