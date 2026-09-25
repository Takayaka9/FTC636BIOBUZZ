package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class BaseOpMode extends OpMode {
    //this whole thing is just for commands so we don't have to put it each time
    ///extend THIS not OpMode for all future tele/auto/tests!
    @Override
    public void init() {
        Scheduler.reset();
    }
    @Override
    public void loop() {
        Scheduler.execute();
    }
    public void schedule(Command...commands){
        Scheduler.schedule(commands);
    }
}