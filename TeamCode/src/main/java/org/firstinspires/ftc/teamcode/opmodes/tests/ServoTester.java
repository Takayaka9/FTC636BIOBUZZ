package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.panels.Panels;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wedge;

@TeleOp
@Configurable
public class ServoTester extends BaseOpMode {
    //idrk if this will work but oh well
    TelemetryManager telemetry;
    Stopper stopper;
    Hood hood;
    Wedge wedge;
    public static String mode = "nothing";
    public static double position = 0;
    @Override
    public void init() {
        telemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        stopper = new Stopper(hardwareMap);
        hood = new Hood(hardwareMap);
        wedge = new Wedge(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.update();
        telemetry.debug("mode", mode);
        telemetry.debug("position", position);
        telemetry.addLine("options: stopper, hood, wedge");
        if(mode.equals("stopper")){
            stopper.setPosition(position);
        }
        else if(mode.equals("hood")){
            hood.setPosition(position);
        }
        else if(mode.equals("wedge")){
            wedge.setPosition(position);
        }
        else{
            telemetry.addLine("mode not set");
        }
    }
}
