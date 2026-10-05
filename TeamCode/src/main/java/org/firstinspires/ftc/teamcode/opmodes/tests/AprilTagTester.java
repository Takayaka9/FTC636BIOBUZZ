package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robot.subsystems.cameras.Webcam;

public class AprilTagTester extends OpMode {
    Webcam cam;
    MultipleTelemetry telemetry;
    @Override
    public void init() {
        cam = new Webcam(hardwareMap);
        cam.create();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        telemetry.update();
        telemetry.addData("ids", cam.getDetectedIds());
    }

    @Override
    public void stop() {
        cam.close();
    }
}
