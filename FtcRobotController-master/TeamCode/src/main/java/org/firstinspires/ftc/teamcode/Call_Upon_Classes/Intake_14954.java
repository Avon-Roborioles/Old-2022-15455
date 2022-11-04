package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake_14954 {

    private Servo intake = null;

    private Telemetry telemetry = null;

    public void init_intake (HardwareMap map, String name) {
        intake = map.get(Servo.class, name);
        //
    }


    public void run_intake_V1 (Gamepad gp, Telemetry telemetry) {
        boolean lbump = gp.left_bumper;
        boolean rbump = gp.right_bumper;

        if (lbump) {
            intake.setPosition(0);
        } else if (rbump) {
            intake.setPosition(1);
        }
        get_telemetry(telemetry);
    }


    public void get_telemetry (Telemetry telemetry) {
        //telemetry.addData("Position",intake.getPosition());
    }
}
