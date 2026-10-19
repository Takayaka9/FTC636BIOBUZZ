package org.firstinspires.ftc.teamcode.util;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
public class AutoPaths {
    public Pose startNonFlower;
    public Pose gardenStart;
    public Pose flower1;
    public Pose shoot2;
    public Pose flower2;
    public Pose park;
    public AutoPaths(Alliance a) {
        //if alliance is blue, mirror; otherwise keep as original
        PoseFactory factory = (a == Alliance.BLUE)
                ? PoseFactory.degrees().mirrorAroundPoint(72, 72)
                : PoseFactory.degrees();

        // Initialize poses using the configured factory
        startNonFlower = factory.of(56, 8.8, 180);
        gardenStart = factory.of(21.1, 8.8, 180);
        flower1 = factory.of(9.009, 47.087, 180);
        shoot2 = factory.of(47.08022388059702, 122.8555270522388, 90);
        flower2 = factory.of(47.08022388059702, 131.294776119403, 90);
        park = factory.of(12.91417910447761, 109.87873134328358, 0);
    }
    public Path startToGarden() {
        return Paths.line(startNonFlower, gardenStart);
    }
    public Path flower1PickUp() {
        return line(startNonFlower, flower1).linear(startNonFlower, flower1);
    }
    public Path shoot1() {
        return line(flower1, shoot2).linear(flower1, shoot2);
    }
    public Path flower2PickUp() {
        return line(shoot2, flower2).linear(shoot2, flower2);
    }
    public Path shoot2() {
        return line(flower2, shoot2).linear(flower2, shoot2);
    }
    public Path park() {
        return line(shoot2, park).linear(shoot2, park);
    }
}
