package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake_15455 {

    private Servo intake = null;

    private Telemetry telemetry = null;

    public void init_intake (HardwareMap map, String name) {
        intake = map.get(Servo.class, name);
    }


    public void run_intake(Gamepad gp, Telemetry telemetry) {
        boolean open = gp.left_bumper;
        boolean close = gp.right_bumper;
        if (open) {
            intake.setPosition(.20); //need to check
        } else if (close) {
            intake.setPosition(0.33); //need to check
        }

        get_telemetry(telemetry);
    }


    public void get_telemetry (Telemetry telemetry) {
        telemetry.addData("Position",intake.getPosition());
    }

    public void auto_intake(boolean open){
        if (open) intake.setPosition(.33);
        if (!open) intake.setPosition(.2);
    }

}
