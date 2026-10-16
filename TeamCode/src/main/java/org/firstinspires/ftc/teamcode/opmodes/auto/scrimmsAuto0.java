package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.robot.Robot;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AutoPaths;

import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class scrimmsAuto0 extends OpMode{
    public scrimmsAuto0(Alliance a){
        r = new Robot(hardwareMap, a);
    }
    Robot r;
    AutoPaths ap;
    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                r.shoot(),
                r.intake.in(),
                r.wedge.down(),
                r.followPath(ap.flower1PickUp()),
                waitMs(1000),
                r.followPath(ap.shoot1()),
                r.shoot(),
                r.followPath(ap.flower2PickUp()),
                waitMs(1000),
                r.followPath(ap.shoot2()),
                r.shoot(),
                r.wedge.up(),
                r.intake.stop(),
                r.followPath(ap.park())
        );
    }

    public void init() {
        Scheduler.reset();
        r.follower.setPose(ap.startNonFlower);
        r.follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        r.periodic();
        r.follower.update();
        Scheduler.execute();
    }
}