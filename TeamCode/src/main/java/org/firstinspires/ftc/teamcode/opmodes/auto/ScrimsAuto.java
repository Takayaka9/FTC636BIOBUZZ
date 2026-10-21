package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AutoPaths;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class ScrimsAuto extends BaseOpMode {
    Robot r;
    AutoPaths ap;
    Alliance a;
    public ScrimsAuto(Alliance a){
        ap = new AutoPaths(a);
        this.a = a;
    }
    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                r.shoot(),
                parallel(
                        r.intake.in(),
                        r.wedge.down(),
                        r.followPath(ap.flower1PickUp())
                ),
                waitMs(1000),
                r.intake.stop(),
                r.followPath(ap.shoot1()),
                r.shoot(),
                parallel(
                        r.intake.in(),
                        r.wedge.down(),
                        r.followPath(ap.flower2PickUp())
                ),
                waitMs(1000),
                r.intake.stop(),
                r.followPath(ap.shoot2()),
                r.shoot(),
                r.wedge.up(),
                r.followPath(ap.park())
        );
    }

    public void init() {
        r = new Robot(hardwareMap, a);
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
    }

    @Override
    public void stop(){
        r.endAuto();
    }
}