package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
@Configurable
//@TeleOp
public class Tele extends BaseOpMode {
    Robot robot;
    Alliance a;
    boolean automatic = false;
    boolean wedgeDown = true;
    public static double shootDelay = 1;
    public static double slowSpeed = 0.35; // drive speed multiplier while gamepad1 right bumper is held
    ElapsedTime shootTimer = new ElapsedTime();
    boolean wasShooting = false;
    public Tele(Alliance a){
        this.a = a;
    }

    @Override
    public void init() {
        super.init();
        robot = new Robot(hardwareMap, a);
        robot.turret.setOffset(RobotConstants.turretPosTransfer);
        robot.follower.setPose(RobotConstants.poseTransfer);
    }

    @Override
    public void loop() {
        super.loop();
        robot.periodic();

        double forward = -gamepad1.left_stick_y;
        double lateral = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

// Slow mode for precise positioning
        if (gamepad1.right_bumper) {
            forward *= slowSpeed;
            lateral *= slowSpeed;
            turn *= slowSpeed;
        }



        robot.follower.manual(forward, lateral, turn);
        robot.manual();

        // Flywheel only spins while gamepad 2 holds right trigger
        if (gamepad2.right_trigger > 0.3) {
            robot.fly.setTarget(Robot.flySpeedManual);
        } else {
            robot.fly.setTarget(0);
        }
//        if(automatic){
//            robot.automatic();
//        } else{
//            robot.manual();
//        }

        /*if(gamepad1.aWasPressed()){
            automatic = !automatic;
        }*/

        if (gamepad2.left_bumper) {
            if (!wasShooting) {          // first loop of this press
                shootTimer.reset();
                wasShooting = true;
            }
            robot.stop.open().schedule();
            if (shootTimer.seconds() > shootDelay) {
                robot.intake.in().schedule();     // delay is over, feed the balls
            } else {
                robot.intake.stop().schedule();   // wait for the stopper to clear
            }
        } else {
            wasShooting = false;
            robot.stop.close().schedule();
            if (gamepad2.right_bumper) {
                robot.intake.in().schedule();
            } else if (gamepad2.left_trigger > 0.3) {
                robot.intake.out().schedule();
            } else {
                robot.intake.stop().schedule();
            }
        }

        if (gamepad2.yWasPressed()) {
            if (wedgeDown) {
                robot.wedge.down().schedule();
            } else {
                robot.wedge.up().schedule();
            }
            wedgeDown = !wedgeDown;
        }

        telemetry.addData("flywheel speed", robot.fly.telemetry());
        telemetry.addData("flywheel target", robot.fly.getTarget());
        telemetry.update();

    }
}
