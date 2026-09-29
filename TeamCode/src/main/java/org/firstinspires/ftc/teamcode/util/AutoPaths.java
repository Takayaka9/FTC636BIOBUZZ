package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class AutoPaths {
    PoseFactory red = PoseFactory.degrees();
    PoseFactory blue = red.mirrorAroundPoint(72, 72);
    Pose startNonFlower = red.of(56, 8.8, 180);
    Pose gardenStart = red.of(21.1, 8.8, 180);
    public Path startToGarden(){
        return Paths.line(startNonFlower, gardenStart);
    }
}
