package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class TurretTester extends BaseOpMode {
    Turret t;
    Follower f;
    @Override
    public void init() {
        super.init();
        t = new Turret(hardwareMap);
        f = Constants.create(hardwareMap);
        f.update();
        f.setPose(new Pose(72, 72, 0));
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
