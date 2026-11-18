package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
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
    TelemetryManager tm;
    Pose startPose = new Pose(72, 72, Math.toRadians(180));
    Pose goalFlower = RobotConstants.redHiveFlower;
    Pose goal = RobotConstants.redHive;

    @Override
    public void init() {
        super.init();
        t = new Turret(hardwareMap);
        f = Constants.create(hardwareMap);
        tm = PanelsTelemetry.INSTANCE.getTelemetry();

        f.update();
        f.setPose(startPose);
    }

    @Override
    public void start() {
        f.update();
        f.setPose(startPose);
    }

    @Override
    public void loop() {
        super.loop();
        f.update();
        tm.update();

        t.aim(goalFlower, f.pose(), f);

        telemetry.addData("turret ticks", t.getPosition());
        telemetry.addData("robot heading (deg)", Math.toDegrees(f.pose().heading()));
        telemetry.addData("pose", f.pose());

        tm.addData("turret ticks", t.getPosition());
        tm.addData("pose", f.pose());
        telemetry.update();
    }
}