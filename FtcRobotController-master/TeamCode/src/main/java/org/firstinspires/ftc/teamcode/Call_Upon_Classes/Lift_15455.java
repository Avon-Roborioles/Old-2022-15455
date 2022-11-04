package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Lift_15455 {

    private double speed = 0;

    private DcMotor lift = null;


    public void init_lift (HardwareMap map, String name) {
        lift  = map.get(DcMotor.class, name);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int pos = lift.getCurrentPosition();
    }

    //simple arm program to move arm up will work on position holding soon
    public void runlift(Gamepad gp, Telemetry telemetry) {
        double ltrigger = gp.left_trigger;
        double rtrigger = gp.right_trigger;

        if (ltrigger > 0) {
            speed = 1;
        } else if (rtrigger > 0 ) {
            speed = -.7;
        } else {
            speed = 0;
        }

        lift.setPower(speed);
    }




}
