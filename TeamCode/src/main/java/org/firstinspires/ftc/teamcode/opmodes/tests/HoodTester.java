package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

@TeleOp
public class HoodTester extends OpMode {
    Hood hood;
    public static double position = 0;
    Pose hive = RobotConstants.redHive;
    public TelemetryManager telemetry;
    Follower follower;
    @Override
    public void init() {
        hood = new Hood(hardwareMap);
        telemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.update();
        hood.setPosition(position);
        telemetry.addData("distance from non-flower red hive", hive.distance(follower.pose()));
        telemetry.debug("hood angle", position);
    }
}
