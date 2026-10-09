package org.firstinspires.ftc.teamcode.pedro.Autos;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class scrimmsAuto0 extends OpMode{
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(24, 24, 0);//Random Numbers Replace later for actual Path
    private final Pose park = p.of(48, 48, 90);//Random Numbers Replace later for Actual Path
    //poses from before

    private Path park() {
        return line(startPose, park).linear(startPose, park);
    }


    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower = Constants.create(hardwareMap);
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);

    }

    @Override
    public void start() {
        schedule(follow(follower, park()));

    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

    }
}