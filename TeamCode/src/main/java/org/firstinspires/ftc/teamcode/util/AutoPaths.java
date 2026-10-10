package org.firstinspires.ftc.teamcode.util;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class AutoPaths {
    PoseFactory red = PoseFactory.degrees();
    PoseFactory blue = red.mirrorAroundPoint(72, 72);
    private final Pose startNonFlower = red.of(56, 8.8, 180);
    private final Pose gardenStart = red.of(21.1, 8.8, 180);
    private final Pose flower1 = red.of(9.009, 47.087, 180);
    private final Pose shoot2 = red.of(47.08022388059702, 122.8555270522388, 90);
    private final Pose flower2 = red.of(47.08022388059702, 131.294776119403, 90);
    private final Pose park = red.of(12.91417910447761, 109.87873134328358, 0);
    public Path startToGarden(){
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
        return line(flower2,shoot2 ).linear(flower2,shoot2);
    }
    public Path park() {
        return line(shoot2,park ).linear(shoot2,park);
    }
}
