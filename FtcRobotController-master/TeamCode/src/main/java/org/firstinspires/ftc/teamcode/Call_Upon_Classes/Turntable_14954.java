package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Turntable_14954 {
    //turntable power
    private double tp = 0;
    //turntable servo
    private CRServo table = null;
    //D pad buttons
    //right
    private boolean dr = false;
    //left
    private boolean dl = false;


    public void init_turntable_14954(HardwareMap hardwareMap, String name){
        table = hardwareMap.get(CRServo.class, name);
    }
    public void run_turntable_14954(Gamepad gamepad, Telemetry telemetry){
        //buttons
        dr = gamepad.dpad_right;
        dl = gamepad.dpad_left;

        // We might have to reverse this

        if(dl){
            //ts.setPosition(0.4);
            tp = 0.4;
        }
        else if(dr){
            //ts.setPosition(-0.4);
            tp = -0.4;
        } else {
            tp = 0;
        }
        //set servo power to turntable power "tp" var
        table.setPower(tp);
    }
}
