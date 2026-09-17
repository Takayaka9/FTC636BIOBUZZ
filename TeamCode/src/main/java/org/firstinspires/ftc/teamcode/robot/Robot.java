package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;

public class Robot {
    //name to be changed

    //public final Flywheel fly;
    //public final Hood hood;
    //public final Intake intake;
    public final Stopper stop;
    //public final Turret turret;
    public Robot(HardwareMap h){
        stop = new Stopper(h);
    }
}
