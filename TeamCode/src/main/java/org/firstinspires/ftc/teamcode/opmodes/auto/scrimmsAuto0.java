//package org.firstinspires.ftc.teamcode.opmodes.auto;
//
//import com.pedropathing.follower.Follower;
//
//import org.firstinspires.ftc.teamcode.robot.Kaoru;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Flywheel;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Wedge;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
//import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
//import com.pedropathing.ivy.Command;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.pedropathing.api.PoseFactory;
//import com.pedropathing.math.Pose;
//
//import org.firstinspires.ftc.teamcode.pedro.Constants;
//import org.firstinspires.ftc.teamcode.util.Alliance;
//
//import static com.pedropathing.api.Paths.*;
//
//import com.pedropathing.paths.Path;
//import com.pedropathing.ivy.Scheduler;
//
//import static com.pedropathing.ivy.Scheduler.schedule;
//import static com.pedropathing.ivy.commands.Commands.waitMs;
//import static com.pedropathing.ivy.groups.Groups.sequential;
//import static com.pedropathing.ivy.pedro.PedroCommands.follow;
//
//@Autonomous
//public class scrimmsAuto0 extends OpMode{
//    public scrimmsAuto0(Alliance a){
//        r = new Kaoru(hardwareMap, a);
//    }
//    Kaoru r;
//    private Command autoRoutine() {
//        return sequential(
//                // Add mechanism commands here.
//                r.shoot(),
//                r.intake.in(),
//                r.wedge.down(),
//                r.follow(AutoPaths.flower1pickUp()),
//                waitMs(1000),
//                r.follow(Shoot1()),
//                r.shoot(),
//                r.follow(fLower2PickUp()),
//                waitMs(1000),
//                r.follow(Shoot2()),
//                r.shoot(),
//                r.wedge.up(),
//                r.intake.stop(),
//                r.follow(Park())
//        );
//    }
//
//    public void init() {
//        Scheduler.reset();
//        r.follower.setPose(startPose);
//        r.follower.update();
//    }
//
//    @Override
//    public void start() {
//        schedule(autoRoutine());
//    }
//
//    @Override
//    public void loop() {
//        r.periodic();
//        r.follower.update();
//        Scheduler.execute();
//    }
//}