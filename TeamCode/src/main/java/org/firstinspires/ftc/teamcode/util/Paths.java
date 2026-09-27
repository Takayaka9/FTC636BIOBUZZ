package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class Paths {
    PoseFactory red = PoseFactory.degrees();
    PoseFactory blue = red.mirrorAroundPoint(72, 72);
}
