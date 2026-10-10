package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.robot.Kaoru;
import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wedge;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.util.Alliance;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class scrimmsAuto0 extends OpMode{
    public scrimmsAuto0(Alliance a){
        r = new Kaoru(hardwareMap, a);
    }
    Kaoru r;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(56, 8, 90);
    private final Pose flower1 = p.of(9.009, 47.087, 180);

    private final Pose shoot2 = p.of(47.08022388059702, 122.8555270522388, 90);

    private final Pose flower2 = p.of(47.08022388059702, 131.294776119403, 90);
    private final Pose park = p.of(12.91417910447761, 109.87873134328358, 0);
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
    private Path Park() {
        return line(shoot2,park ).linear(shoot2,park);

    }






    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                r.shoot(),
                r.intake.in(),
                r.wedge.down(),
                r.follow(flower1pickUp()),
                waitMs(1000),
                r.follow(Shoot1()),
                r.shoot(),
                r.follow(fLower2PickUp()),
                waitMs(1000),
                r.follow(Shoot2()),
                r.shoot(),
                r.wedge.up(),
                r.intake.stop(),
                r.follow(Park())
        );
    }

    public void init() {
        Scheduler.reset();
        r.follower.setPose(startPose);
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