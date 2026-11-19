package org.firstinspires.ftc.teamcode.opmodes.tele;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.Alliance;


@TeleOp(name = "Blue Tele", group = "Tele")
public class BlueTele extends Tele{
    public BlueTele(){
        super(Alliance.BLUE);
    }
}
