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

        robot.follower.manual(forward, lateral, turn);
        robot.manual();
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


    }
}
