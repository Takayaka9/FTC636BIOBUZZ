package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

@Configurable
@Autonomous(name = "RUN THIS AUTO", group = "Auto")
public class RealScrimsAuto extends LinearOpMode {
    public static double spinUpTimeout = 3;  // max seconds to wait for the flywheel to get up to speed
    public static double stopperDelay = 0.5; // seconds between stopper opening and intake starting
    public static double feedTime = 4;       // seconds to run the intake (long enough for all 4 balls)
    public static double drivePower = 0.3;
    public static double driveTime = 0.15;   // seconds to drive forward; tune until it moves ~1 inch

    Flywheel fly;
    Intake intake;
    Stopper stop;
    Turret turret;
    DcMotor fl, fr, bl, br;
    boolean flywheelOn = true;

    @Override
    public void runOpMode() {
        fly = new Flywheel(hardwareMap);
        intake = new Intake(hardwareMap);
        stop = new Stopper(hardwareMap);
        turret = new Turret(hardwareMap);

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        // same directions as pedro/Constants.java
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        stop.setPosition(Stopper.close);
        fly.setTarget(Robot.flySpeedManual); // same speed as TeleOp

        waitForStart();

        // 1. Spin up the flywheel (stops waiting early once it's at speed)
        ElapsedTime timer = new ElapsedTime();
        while (opModeIsActive() && !fly.nearTarget(fly.getTarget()) && timer.seconds() < spinUpTimeout) {
            update();
        }

        // 2. Open the stopper and give it time to get out of the way
        stop.setPosition(Stopper.open);
        waitWhileUpdating(stopperDelay);

        // 3. Feed all 4 balls into the flywheel
        intake.setMotorPower(Intake.in);
        waitWhileUpdating(feedTime);
        intake.setMotorPower(0);
        stop.setPosition(Stopper.close);

        // 4. Turn off the flywheel
        flywheelOn = false;
        fly.stop();

        // 5. Drive forward a little
        setDrivePower(drivePower);
        waitWhileUpdating(driveTime);
        setDrivePower(0);

        // So TeleOp knows where the turret is
        RobotConstants.turretPosTransfer = turret.getPosition();
    }

    // Things that have to keep running every loop
    private void update() {
        if (flywheelOn) {
            fly.run(fly.getTarget());
        }
        turret.turnTurret(0); // hold the turret forward
        telemetry.addData("flywheel velocity", fly.telemetry());
        telemetry.update();
    }

    // Wait without freezing the flywheel/turret control
    private void waitWhileUpdating(double seconds) {
        ElapsedTime t = new ElapsedTime();
        while (opModeIsActive() && t.seconds() < seconds) {
            update();
        }
    }

    private void setDrivePower(double power) {
        fl.setPower(power);
        fr.setPower(power);
        bl.setPower(power);
        br.setPower(power);
    }
}