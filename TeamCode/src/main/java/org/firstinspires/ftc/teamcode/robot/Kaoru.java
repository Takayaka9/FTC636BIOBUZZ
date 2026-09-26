package org.firstinspires.ftc.teamcode.robot;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
import org.firstinspires.ftc.teamcode.util.Sides;

@Configurable
public class Kaoru {
    //our robot class; name to be changed
    //add all functions that require multiple subsystems that are used in both auto and tele here
    public Follower follower;
    public final Flywheel fly;
    public final Hood hood;
    public final Intake intake;
    public final Stopper stop;
    public final Turret turret;
    public Pose hivePos;
    public Pose flowerHivePos; //flower side hive
    public Pose hiveAim; //the alliance AND side we're aiming at (naming up for debate)
    Sides side;
    //initializing all of our subsystems
    public Kaoru(HardwareMap h, Alliance a){
        follower = Constants.create(h);
        fly = new Flywheel(h);
        turret = new Turret(h);
        hood = new Hood(h);
        intake = new Intake(h);
        stop = new Stopper(h);
        setHives(a);
        hiveAim = hivePos; //default hive side is non-flower side (up at start of auto)
        side = Sides.NONFLOWER; //default hive side is non-flower side (up at start of auto)
    }
    public void periodic(){
        follower.update();
        fly.run(fly.getTarget());
    }
    public void automatic(){
        turret.aim(hiveAim, follower.pose(), follower);
        hood.angleHood(hiveAim, follower.pose());
        fly.setTarget(hiveAim, follower.pose());
    }
    public void manual(){
        turret.turnTurret(0);
        //hood.setPosition(0);
        //fly.setTarget(1000);
        //find neutral values for both!
    }
    private void setHives(Alliance a){
        if(a == Alliance.RED){
            hivePos = RobotConstants.redHive;
            flowerHivePos = RobotConstants.redHiveFlower;
        }
        else if(a == Alliance.BLUE){
            hivePos = RobotConstants.blueHive;
            flowerHivePos = RobotConstants.blueHiveFlower;
        }
    }
    public void switchSide(){
        if(side == Sides.NONFLOWER){
            hiveAim = flowerHivePos;
            side = Sides.FLOWER;
        }
        else if(side == Sides.FLOWER){
            hiveAim = hivePos;
            side = Sides.NONFLOWER;
        }
    }
}
