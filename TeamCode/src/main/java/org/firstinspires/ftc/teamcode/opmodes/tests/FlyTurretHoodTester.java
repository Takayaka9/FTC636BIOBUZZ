package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

@TeleOp
@Configurable
public class FlyTurretHoodTester extends BaseOpMode {
    Hood hood;
    Flywheel fly;
    Intake i;
    Turret t;
    Follower f;
    TelemetryManager tm;

    // live-tunable in Panels
    public static double position = 0.9;
    public static double flypower = 1350;
    public static boolean aimAtFlower = true;
    public static boolean turretAimOn = true;

    Pose startPose = new Pose(72, 72, Math.toRadians(0)); //72, 72, 0/180/270

    @Override
    public void init() {
        super.init();
        hood = new Hood(hardwareMap);
        fly = new Flywheel(hardwareMap);
        i = new Intake(hardwareMap);
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

        Pose goal = aimAtFlower ? RobotConstants.redHiveFlower : RobotConstants.redHive;

        // turret
        if (turretAimOn) {
            t.aim(goal, f.pose(), f);
        } else {
            t.turnTurret(0); // hold center
        }

        // hood + flywheel
        hood.setPosition(position);
        fly.run(flypower);

        // intake
        if (gamepad1.right_bumper) {
            i.setMotorPower(0.8);
        } else if (gamepad1.left_bumper) {
            i.setMotorPower(-1);
        } else {
            i.setMotorPower(0);
        }

        double distance = goal.distance(f.pose());

        // Panels
        tm.addData("fly speed", fly.telemetry());
        tm.addData("fly target", flypower);
        tm.addData("hood position", position);
        tm.addData("distance to goal", distance);
        tm.addData("turret ticks", t.getPosition());
        tm.addData("heading (deg)", Math.toDegrees(f.pose().heading()));
        tm.addData("pose", f.pose());
        tm.update();

        // Driver Station
        telemetry.addData("distance to goal", distance);
        telemetry.addData("fly speed", fly.telemetry());
        telemetry.addData("hood position", position);
        telemetry.addData("turret ticks", t.getPosition());
        telemetry.addData("pose", f.pose());
        telemetry.update();
    }
}