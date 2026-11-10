package org.firstinspires.ftc.teamcode.opmodes.tests;

//import com.bylazar.configurables.annotations.Configurable;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.util.RobotConstants;
//@Configurable
@TeleOp
@Configurable
public class FlyHoodTester extends BaseOpMode {
    Hood hood;
    Flywheel fly;
    Intake i;
    Stopper s;
    public static double position = 0.9;
    public static double flypower = 1350;
    TelemetryManager telemetryManager;
    DcMotorEx frontRight;
    Pose hive = RobotConstants.redHive;
    //public TelemetryManager telemetry;
    //Follower follower;
    @Override
    public void init() {
        hood = new Hood(hardwareMap);
        fly = new Flywheel(hardwareMap);
        i = new Intake(hardwareMap);
        s = new Stopper(hardwareMap);
        frontRight = hardwareMap.get(DcMotorEx.class, "fr");
        telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();
        //telemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
        telemetryManager.update();
        telemetryManager.addData("fly speed", fly.telemetry());
        telemetryManager.addData("target", flypower);
        //telemetry.update();
        hood.setPosition(position);
        fly.run(flypower);
        if(gamepad1.right_bumper){
            i.setMotorPower(0.8);
        }
        else if(gamepad1.left_bumper){
            i.setMotorPower(-1);
            frontRight.setPower(1);
        }
        else{
            i.setMotorPower(0);
        }
        if(gamepad1.a){
            schedule(s.open());
        }
        if(gamepad1.b){
            schedule(s.close());
        }
        //telemetry.addData("distance from non-flower red hive", hive.distance(follower.pose()));
//        telemetry.debug("hood angle", position);
//        telemetry.debug("flywheel power", flypower);
    }
    public static int shootWait = 1000;
    public Command shoot(){
        return sequential(
                s.open(),
                i.in(),
                waitMs(shootWait),
                i.stop(),
                s.close()
        );
    }
}
