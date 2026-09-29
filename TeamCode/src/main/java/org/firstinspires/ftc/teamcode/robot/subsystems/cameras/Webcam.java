package org.firstinspires.ftc.teamcode.robot.subsystems.cameras;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.List;

public class Webcam {
    private final WebcamName webcamName;
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;
    public Webcam(HardwareMap hardwareMap){
        webcamName = hardwareMap.get(WebcamName.class, "webcam");
    }
    public void create(){
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, aprilTagProcessor);
    }
    public void close(){
        visionPortal.close();
    }
    public ArrayList<Integer> getDetectedIds() {
        ArrayList<AprilTagDetection> detections = aprilTagProcessor.getDetections();
        ArrayList<Integer> ids = new ArrayList<>();

        if (detections != null) {
            for (AprilTagDetection detection : detections) {
                if (detection instanceof AprilTagSingleDetection) {
                    ids.add(((AprilTagSingleDetection) detection).id);
                }
            }
        }
        return ids;
    }
}
