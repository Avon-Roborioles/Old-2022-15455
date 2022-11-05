package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Lift_15455 {

    private double speed = 0;

    private DcMotor lift = null;

    private int max = (int)(2.5*537-30);
    private int top = max-30;
    private int mid = (int) (2./3.*max);
    private int low = (int) (1./3.*max);


    public void init_lift (HardwareMap map, String name) {
        lift  = map.get(DcMotor.class, name);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int pos = lift.getCurrentPosition();
    }

    //simple arm program to move arm up will work on position holding soon
    public void runlift(Gamepad gp, Telemetry telemetry) {
//        double ltrigger = gp.left_trigger;
//        double rtrigger = gp.right_trigger;
//
//        if (ltrigger > 0) {
//            speed = 1;
//        } else if (rtrigger > 0 ) {
//            speed = -.7;
//        } else {
//            speed = 0;
//        }
//
//        lift.setPower(speed);
//
//
        int n = 0;
        if (n==0){
            n++;
            lift.setTargetPosition(0);
        }


        boolean a = gp.a;
        boolean x = gp.x;
        boolean y = gp.y;
        boolean b = gp.b;

        if (a) {
            lift.setTargetPosition(0);
        } else if (x) {
            lift.setTargetPosition(low);
        } else if (y) {
            lift.setTargetPosition(mid);
        } else if (b) {
            lift.setTargetPosition(top);
        }
        lift.setPower(.5);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }




}
