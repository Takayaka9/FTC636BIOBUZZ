package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
@Config
public class Tele extends BaseOpMode {
    Robot robot;
    boolean automatic=true;
    public Tele(Alliance a){
        robot = new Robot(hardwareMap, a);
    }

    @Override
    public void init() {
        super.init();
        robot.turret.setOffset(RobotConstants.turretPosTransfer);
    }

    @Override
    public void loop() {
        super.loop();
        robot.periodic();

        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                robot.follower.pose().heading()
        );
        robot.follower.manual(powers);

        if(automatic){
            robot.automatic();
            if(gamepad1.aWasPressed()){
                automatic=false;
            }
        }
        else{
            robot.manual();
            if(gamepad1.aWasPressed()){
                automatic=true;
            }
        }

        if(gamepad1.leftBumperWasPressed()){
            robot.shoot();
        }

        if(gamepad1.right_bumper){
            robot.intake.in();
        }
        else if(gamepad1.left_trigger > 0.3){
            robot.intake.out();
        }
        else{
            robot.intake.stop();
        }

        if(gamepad1.b){
            robot.switchSide();
        }
    }
}
