package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
@TeleOp
public class TurretTester extends BaseOpMode {
    Turret t;
    Follower f;
    @Override
    public void init() {
        super.init();
        t = new Turret(hardwareMap);
        f = Constants.create(hardwareMap);
    }
    PoseFactory p = PoseFactory.degrees();
    Pose startPose = p.of(72, 72, 0);
    @Override
    public void start(){
        f.setPose(startPose);
    }
    Pose goalFlower = RobotConstants.redHiveFlower;
    Pose goal = RobotConstants.redHive;
    @Override
    public void loop() {
        super.loop();
        f.update();
        t.aim(goalFlower, f.pose(), f);
    }
}
