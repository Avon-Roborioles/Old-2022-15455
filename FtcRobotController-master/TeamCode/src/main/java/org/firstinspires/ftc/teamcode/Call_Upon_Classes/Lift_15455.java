package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Lift_15455 {

    private double speed = 0;

    private DcMotor lift = null;

    private int max =-1*(5*716)+100;
    private int top =max-30;
    private int mid =(int) (2./3.*max)+200;
    private int low =(int) (1./3.*max)-200;


    public void init_lift (HardwareMap map, String name) {
        lift  = map.get(DcMotor.class, name);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    //simple arm program to move arm up will work on position holding soon
    public void lift_fix(){
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
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


        boolean is_powered = true;
        boolean a = gp.a;
        boolean x = gp.x;
        boolean y = gp.y;
        boolean b = gp.b;

        if (a) {
            lift.setTargetPosition(10);
        } else if (x) {
            lift.setTargetPosition(low);
        } else if (y) {
            lift.setTargetPosition(mid);
        } else if (b) {
            lift.setTargetPosition(top);
        }
        if(a||b||x||y)
            lift.setPower(.7);
        else if (lift.getCurrentPosition() >= 5 && lift. getCurrentPosition() <= 15)
            lift.setPower(0);

        get_telemetry(telemetry);


    }

    public void get_telemetry (Telemetry telemetry) {
        telemetry.addData("Position",lift.getCurrentPosition());
    }


}
