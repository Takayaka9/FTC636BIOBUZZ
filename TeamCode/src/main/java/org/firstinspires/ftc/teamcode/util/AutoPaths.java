package org.firstinspires.ftc.teamcode.util;

import static com.pedropathing.api.Paths.line;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
@Configurable
public class AutoPaths {
    public static double startNonFlowerX = 56;
    public static double startNonFlowerY = 8.8;
    public static double startNonFlowerAngle = 180;
    public static double gardenStartX = 21.1;
    public static double gardenStartY = 8.8;
    public static double gardenStartAngle = 180;
    public static double flower1X = 9.009;
    public static double flower1Y = 47.087;
    public static double flower1Angle = 180;
    public static double shoot2X = 47.08022388059702;
    public static double shoot2Y = 122.8555270522388;
    public static double shoot2Angle = 90;
    public static double flower2X = 47.08022388059702;
    public static double flower2Y = 131.294776119403;
    public static double flower2Angle = 90;
    public static double parkX = 12.91417910447761;
    public static double parkY = 109.87873134328358;
    public static double parkAngle = 0;
    public Pose startNonFlower;
    public Pose gardenStart;
    public Pose flower1;
    public Pose shoot2;
    public Pose flower2;
    public Pose park;
    public AutoPaths(Alliance a) {
        PoseFactory factory = (a == Alliance.BLUE)
                ? PoseFactory.degrees().mirrorAroundPoint(72, 72)
                : PoseFactory.degrees();

        // Initialize poses using public static double variables and the configured factory
        startNonFlower = factory.of(startNonFlowerX, startNonFlowerY, startNonFlowerAngle);
        gardenStart = factory.of(gardenStartX, gardenStartY, gardenStartAngle);
        flower1 = factory.of(flower1X, flower1Y, flower1Angle);
        shoot2 = factory.of(shoot2X, shoot2Y, shoot2Angle);
        flower2 = factory.of(flower2X, flower2Y, flower2Angle);
        park = factory.of(parkX, parkY, parkAngle);
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
