package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wedge;

public class HalfTele extends BaseOpMode {
    Intake i;
    Wedge w;
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;
    //Follower f;
    @Override
    public void init() {
        super.init();
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        i = new Intake(hardwareMap);
        w = new Wedge(hardwareMap);
    }

    @Override
    public void loop() {
        super.loop();
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }
}
