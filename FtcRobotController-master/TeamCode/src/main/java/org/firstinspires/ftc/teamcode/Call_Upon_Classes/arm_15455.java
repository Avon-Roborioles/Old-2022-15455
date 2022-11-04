package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class arm_15455 {

    private Servo arm = null;
    private Telemetry telemetry = null;


    public void init_arm (HardwareMap map, String name) {arm = map.get(Servo.class, name);}


    public void run_arm(Gamepad gp, Telemetry telemetry) {
        boolean left = gp.dpad_left;
        boolean right = gp.dpad_right;
        boolean middle = gp.dpad_up;

        if (left) {
            arm.setPosition(0.56); //need to check
        } else if (right) {
            arm.setPosition(0.44); //need to check
        }else if(middle) {
            arm.setPosition(.5);
        }
        get_telemetry(telemetry);


    }

    public void get_telemetry (Telemetry telemetry) {
        telemetry.addData("Position",arm.getPosition());
    }




}