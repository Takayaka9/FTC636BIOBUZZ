package org.firstinspires.ftc.teamcode.util;

import static com.pedropathing.api.Paths.line;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

@Configurable
public class AutoPaths {
    public static double startNonFlowerX = 56; public static double startNonFlowerY = 8; public static double startNonFlowerAngle = 90;
    public static double flower1PickupX = 15.4495; public static double flower1PickupY = 46.9089; public static double flower1PickupAngle = 180;
    public static double flower1PickupControl1X = 46.136; public static double flower1PickupControl1Y = 46.5416; public static double flower1PickupControl1Angle = 0;
    public static double flowerInsertX = 6.5743; public static double flowerInsertY = 46.7316; public static double flowerInsertAngle = 180;
    public static double shoot1X = 47.0598; public static double shoot1Y = 118.878; public static double shoot1Angle = -113.712;
    public static double shootStartX = 47.0598; public static double shootStartY = 118.878; public static double shootStartAngle = -90;
    public static double shootX = 47.06; public static double shootY = 118.88; public static double shootAngle = -90;
    public static double flower2AlignX = 47.06; public static double flower2AlignY = 118.88; public static double flower2AlignAngle = 90;
    public static double flower2InsertStartX = 47.06; public static double flower2InsertStartY = 118.88; public static double flower2InsertStartAngle = 90;
    public static double flower2InsertX = 46.8594; public static double flower2InsertY = 135.7262; public static double flower2InsertAngle = 90;
    public static double flower2RetractX = 47.06; public static double flower2RetractY = 118.88; public static double flower2RetractAngle = 90.6821;
    public static double point9StartX = 47.06; public static double point9StartY = 118.88; public static double point9StartAngle = 90;
    public static double point9X = 47.06; public static double point9Y = 118.88; public static double point9Angle = -90;
    public static double parkX = 12.91417910447761; public static double parkY = 109.87873134328358; public static double parkAngle = 0;
    public Pose startNonFlower;
    public Pose flower1Pickup;
    public Pose flower1PickupControl1;
    public Pose flowerInsert;
    public Pose shoot1;
    public Pose shootStart;
    public Pose shoot;
    public Pose flower2Align;
    public Pose flower2InsertStart;
    public Pose flower2Insert;
    public Pose flower2Retract;
    public Pose point9Start;
    public Pose point9;
    public Pose park;

    public AutoPaths(Alliance a) {
        PoseFactory factory = (a == Alliance.BLUE)
                ? PoseFactory.degrees().mirrorAroundPoint(72, 72)
                : PoseFactory.degrees();

        // Initialize poses using public static double variables and the configured factory
        startNonFlower = factory.of(startNonFlowerX, startNonFlowerY, startNonFlowerAngle);
        flower1Pickup = factory.of(flower1PickupX, flower1PickupY, flower1PickupAngle);
        flower1PickupControl1 = factory.of(flower1PickupControl1X, flower1PickupControl1Y, flower1PickupControl1Angle);
        flowerInsert = factory.of(flowerInsertX, flowerInsertY, flowerInsertAngle);
        shoot1 = factory.of(shoot1X, shoot1Y, shoot1Angle);
        shootStart = factory.of(shootStartX, shootStartY, shootStartAngle);
        shoot = factory.of(shootX, shootY, shootAngle);
        flower2Align = factory.of(flower2AlignX, flower2AlignY, flower2AlignAngle);
        flower2InsertStart = factory.of(flower2InsertStartX, flower2InsertStartY, flower2InsertStartAngle);
        flower2Insert = factory.of(flower2InsertX, flower2InsertY, flower2InsertAngle);
        flower2Retract = factory.of(flower2RetractX, flower2RetractY, flower2RetractAngle);
        point9Start = factory.of(point9StartX, point9StartY, point9StartAngle);
        point9 = factory.of(point9X, point9Y, point9Angle);
        park = factory.of(parkX, parkY, parkAngle);

    }
    //TODO: make paths
}
