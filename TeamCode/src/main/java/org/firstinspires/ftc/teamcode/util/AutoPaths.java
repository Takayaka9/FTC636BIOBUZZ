package org.firstinspires.ftc.teamcode.util;

import static com.pedropathing.api.Paths.line;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

@Configurable
public class AutoPaths {
    public static double startNonFlowerX = 56; public static double startNonFlowerY = 8; public static double startNonFlowerAngle = Math.toRadians(90);
    public static double flower1PickupX = 15.4495; public static double flower1PickupY = 46.9089; public static double flower1PickupAngle = Math.toRadians(180);
    public static double flower1PickupControl1X = 46.136; public static double flower1PickupControl1Y = 46.5416; public static double flower1PickupControl1Angle = Math.toRadians(0);
    public static double flowerInsertX = 6.5743; public static double flowerInsertY = 46.7316; public static double flowerInsertAngle = Math.toRadians(180);
    public static double shoot1X = 47.0598; public static double shoot1Y = 118.878; public static double shoot1Angle = Math.toRadians(-113.712);
    public static double shoot2X = 47.06; public static double shoot2Y = 118.88; public static double shoot2Angle = Math.toRadians(-90);
    public static double flower2PickupX = 47.06; public static double flower2PickupY = 118.88; public static double flower2PickupAngle = Math.toRadians(90);
    public static double flower2InsertX = 46.8594; public static double flower2InsertY = 135.7262; public static double flower2InsertAngle = Math.toRadians(90);
    public static double parkX = 6.84; public static double parkY = 112.6; public static double parkAngle = Math.toRadians(0);
    public static double ramX = 9.69; public static double ramY = 61.7;
    public static double ramParkX = 9.73; public static double ramParkY = 105.34;
    public Pose startNonFlower;
    public Pose flower1Pickup;
    public Pose flower1PickupControl1;
    public Pose flower1Insert;
    public Pose shoot1;
    public Pose shoot2;
    public Pose flower2Pickup;
    public Pose flower2Insert;
    public Pose park;
    public Pose ram;
    public Pose ramPark;
    public AutoPaths(Alliance a) {
        PoseFactory factory = (a == Alliance.BLUE)
                ? PoseFactory.radians().mirrorAroundPoint(72, 72)
                : PoseFactory.radians();

        // Initialize poses using public static double variables and the configured factory
        startNonFlower = factory.of(startNonFlowerX, startNonFlowerY, startNonFlowerAngle);
        flower1Pickup = factory.of(flower1PickupX, flower1PickupY, flower1PickupAngle);
        flower1PickupControl1 = factory.of(flower1PickupControl1X, flower1PickupControl1Y, flower1PickupControl1Angle);
        flower1Insert = factory.of(flowerInsertX, flowerInsertY, flowerInsertAngle);
        shoot1 = factory.of(shoot1X, shoot1Y, shoot1Angle);
        shoot2 = factory.of(shoot2X, shoot2Y, shoot2Angle);
        flower2Pickup = factory.of(flower2PickupX, flower2PickupY, flower2PickupAngle);
        flower2Insert = factory.of(flower2InsertX, flower2InsertY, flower2InsertAngle);
        park = factory.of(parkX, parkY, parkAngle);
        ram = factory.of(ramX, ramY, 0);
        ramPark = factory.of(ramParkX, ramParkY, 0);
    }
    //TODO: make paths
    public Path flower1Set(){
        return Paths.curve(startNonFlower, flower1PickupControl1, flower1Pickup).linear(startNonFlowerAngle, flower1PickupAngle);
    }
    public Path flower1In(){
        return Paths.line(flower1Pickup, flower1Insert);
    }
    public Path flower1(){
        return Paths.path(flower1Set(), flower1In());
    }
    public Path flower1Out(){
        return Paths.line(flower1Insert, flower1Pickup);
    }
    public Path flower1ToShoot2(){
        return Paths.line(flower1Pickup, shoot2).reverseTangent();
    }
    public Path shootToFlower2(){
        return Paths.line(flower2Pickup, flower2Insert).tangent();
    }
    public Path flower2Out(){
        return Paths.line(flower2Insert, flower2Pickup).constant(Math.PI/2);
    }
    public Path turnToShoot2(){
        return Paths.line(flower2Pickup, shoot2).linear(flower2PickupAngle, shoot2Angle);
    }
    public Path flower2ToShoot2(){
        return Paths.path(flower2Out(), turnToShoot2());
    }
    public Path park(){
        return Paths.line(shoot2, park).tangent();
    }
    public Path ramSet(){
        return Paths.line(shoot2, ram).tangent();
    }
    public Path ramRam(){
        return Paths.line(ram, ramPark).reverseTangent();
    }
    public Path ram(){
        return Paths.path(ramSet(), ramRam());
    }
}
