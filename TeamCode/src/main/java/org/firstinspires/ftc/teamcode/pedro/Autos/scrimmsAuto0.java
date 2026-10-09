package org.firstinspires.ftc.teamcode.pedro.Autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class scrimmsAuto0 extends OpMode{
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(56, 8, 90);
    private final Pose flower1 = p.of(9.009, 47.087, 180);

    private final Pose shoot2 = p.of(47.08022388059702, 122.8555270522388, 90);

    private final Pose flower2 = p.of(47.08022388059702, 131.294776119403, 90);
    private Path flower1pickUp() {
        return line(startPose, flower1).linear(startPose, flower1);

    }
    private Path Shoot1() {
        return line(flower1, shoot2).linear(flower1, shoot2);

    }
    private Path fLower2PickUp() {
        return line(shoot2, flower2).linear(shoot2, flower2);

    }
    private Path Shoot2() {
        return line(flower2,shoot2 ).linear(flower2,shoot2);

    }


    private Command autoRoutine() {
        return sequential(

                // Add mechanism commands here.
                follow(follower, flower1pickUp()),
                follow(follower, Shoot1()),
                follow(follower, fLower2PickUp()),
                follow(follower, Shoot2())
        );
    }

    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();

    }

    @Override
    public void start() {
        schedule(autoRoutine());


    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

    }
}