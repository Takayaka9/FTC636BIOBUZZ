package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
@Configurable
public class Tele extends BaseOpMode {
    Robot robot;
    Alliance a;
    boolean automatic = true;
    boolean wedgeDown = true;
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
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        robot.follower.manual(forward, lateral, turn);

        if(automatic){
            robot.automatic();
        } else{
            robot.manual();
        }

        if(gamepad1.aWasPressed()){
            automatic = !automatic;
        }

        if (gamepad1.left_bumper) {
            robot.stop.open().schedule();
            robot.intake.in().schedule();
        } else {
            robot.stop.close().schedule();
            if (gamepad1.right_bumper) {
                robot.intake.in().schedule();
            } else if (gamepad1.left_trigger > 0.3) {
                robot.intake.out().schedule();
            } else {
                robot.intake.stop().schedule();
            }
        }

        if (gamepad1.yWasPressed()) {
            if (wedgeDown) {
                robot.wedge.down().schedule();
            } else {
                robot.wedge.up().schedule();
            }
            wedgeDown = !wedgeDown;
        }

        if(gamepad1.bWasPressed()){
            robot.switchSide();
        }
    }
}
