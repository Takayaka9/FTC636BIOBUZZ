package org.firstinspires.ftc.teamcode.opmodes.tests;

//import com.bylazar.configurables.annotations.Configurable;
//import com.bylazar.panels.Panels;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.BaseOpMode;
import org.firstinspires.ftc.teamcode.robot.subsystems.Hood;
import org.firstinspires.ftc.teamcode.robot.subsystems.Stopper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wedge;

@TeleOp
@Configurable
public class ServoTester extends BaseOpMode {
    Stopper stopper;
    Hood hood;
    Wedge wedge;
    public static String mode = "nothing";
    public static double position = 0;
    @Override
    public void init() {
        //telemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        stopper = new Stopper(hardwareMap);
        hood = new Hood(hardwareMap);
        wedge = new Wedge(hardwareMap);
    }

    @Override
    public void loop() {
        stopper.setPosition(position);
    }
}
