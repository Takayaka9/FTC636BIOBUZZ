package org.firstinspires.ftc.teamcode.opmodes.tele;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name = "Red Tele", group = "Tele")
public class RedTele extends Tele{
    public RedTele(){
        super(Alliance.RED);
    }
}
