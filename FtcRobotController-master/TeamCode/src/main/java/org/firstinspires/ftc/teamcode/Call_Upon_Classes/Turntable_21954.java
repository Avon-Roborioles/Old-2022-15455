package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Turntable_21954 {
    // Turntable power
    private double tp = 0;
    // Turntable servo
    private CRServo ts = null;
    // D pad buttons
    // Right
    private boolean dr = false;
    // Left
    private boolean dl = false;

    public void init_turntable_21954(HardwareMap hardwareMap, String name){
        // Mapping the turntable
        ts = hardwareMap.get(CRServo.class, name);
    }
    public void run_turntable_21954(Gamepad gamepad, Telemetry telemetry){
        // Buttons
        dr = gamepad.dpad_right;
        dl = gamepad.dpad_left;

        // Turntable power set
        // (We might have to reverse this)
        if(dr){
            tp = 0.4;
        }
        else if(dl){
            tp = -0.4;
        }
        else {
            tp = 0;
        }
        // Set servo power to turntable power "tp" var (turntable)
        ts.setPower(tp);
    }
}
