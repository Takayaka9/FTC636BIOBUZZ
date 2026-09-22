package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
@Configurable
@TeleOp
public class FlyHoodTester extends OpMode {
    Hood hood;
    Flywheel fly;
    public static double position = 0;
    public static double flypower = 1000;
    Pose hive = RobotConstants.redHive;
    public TelemetryManager telemetry;
    Follower follower;
    @Override
    public void init() {
        hood = new Hood(hardwareMap);
        fly = new Flywheel(hardwareMap);
        telemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.update();
        hood.setPosition(position);
        fly.run(flypower);
        telemetry.addData("distance from non-flower red hive", hive.distance(follower.pose()));
        telemetry.debug("hood angle", position);
        telemetry.debug("flywheel power", flypower);
    }
}
