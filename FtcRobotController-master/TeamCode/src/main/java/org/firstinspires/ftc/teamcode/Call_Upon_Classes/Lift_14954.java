package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift_14954 {
    private double speed = 0;

    private DcMotor lift = null;
    private int max = 3500;
    private int top = 3000;
    private int mid = 1600;
    private int low = 100;
    private boolean a = false;
    private boolean x = false;
    private boolean y = false;
    private boolean b = false;


    public void init_lift (HardwareMap map, String name) {
        lift  = map.get(DcMotor.class, name);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setTargetPosition(0);
    }

    //simple arm program to move arm up; will work on position holding soon
    public void runlift(Gamepad gp, Telemetry telemetry) {
        int pos = lift.getCurrentPosition();

        double ltrigger = gp.left_trigger;
        double rtrigger = gp.right_trigger;

        if (ltrigger > 0) {
            speed = .6;
        } else if (rtrigger > 0 ) {
            speed = -.1;
        } else if (pos == 0 || pos == max ){
            speed = 0;
        } else {
            speed = 0;
        }

        lift.setPower(speed);
        telemetry.addData("encoder: ", pos);
    }

    public void runlift2 (Gamepad gp, Telemetry telemetry) {
        a = gp.a;
        x = gp.x;
        y = gp.y;
        b = gp.b;

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

    //attempt for main lift program that hold position
    public void runlift_auto(Telemetry telemetry) {
        lift.setTargetPosition(top);
        lift.setPower(.5);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lift.setPower(0);
//        lift.setTargetPosition(mid);
//        lift.setPower(.5);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lift.setPower(0);
//        lift.setTargetPosition(low);
//        lift.setPower(.5);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


}
