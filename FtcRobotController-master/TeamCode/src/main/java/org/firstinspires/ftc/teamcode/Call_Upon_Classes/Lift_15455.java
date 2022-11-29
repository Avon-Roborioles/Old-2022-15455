package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Lift_15455 {

    private double speed = 0;

    private DcMotor lift = null;

    private int max =11750;
    private int top =max;
    private int mid =(int) (2./3.*max)+200;
    private int low =(int) (1./3.*max)-200;


    int hold = 1;
    boolean up = false;
    boolean down = false;


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
        double upt = gp.left_trigger;
        double downt = gp.right_trigger;
        if (upt>0){
            up = true;
        }else{
            up = false;
        }
        if (downt>0){
            down = true;
        }else{
            down=false;
        }
        if (hold==1 && up){
            hold=0;
            lift.setTargetPosition(lift.getCurrentPosition()-400);
        }
        if (hold==1 && down){
            hold=0;
            lift.setTargetPosition(lift.getCurrentPosition()+400);
        }
        if (!up && !down){
            hold=1;
        }




        boolean a = gp.a;
        boolean x = gp.x;
        boolean y = gp.y;
        boolean b = gp.b;

        if (a) {
            lift.setTargetPosition(20);
        } else if (x) {
            lift.setTargetPosition(low);
        } else if (y) {
            lift.setTargetPosition(mid);
        } else if (b) {
            lift.setTargetPosition(top);
        }
        if(a||b||x||y)
            if (lift.getCurrentPosition()>lift.getTargetPosition()&&lift.isBusy())
                lift.setPower(.75);
            else
                lift.setPower(1);
        else if ((lift.getCurrentPosition() >= 10 && lift. getCurrentPosition() <= 20)||(lift.getCurrentPosition()>11900))
            lift.setPower(0);

        get_telemetry(telemetry);


    }

    public void auto_lift_set(int zone) throws InterruptedException {
        if (zone==0) {
            lift.setTargetPosition(15);
        } else if (zone==1) {
            lift.setTargetPosition(low);
        } else if (zone==2) {
            lift.setTargetPosition(mid);
        } else if (zone==3) {
            lift.setTargetPosition(top);
        }
        lift.setPower(1);

        while (lift.isBusy()){
            Thread.sleep(100);
        }
        lift.setPower(0);

    }



    public void get_telemetry (Telemetry telemetry) {
        telemetry.addData("Position",lift.getCurrentPosition());
        telemetry.addData("up", up);
        telemetry.addData("down", down);
    }

/*
    //takes the height incrament we would like to score on, 0 being at rest, 1 being low junction, 2 being medium junction, and 3 being high
    public void moveElevator(int incrament) {
        //add encoder values for the motor to hold at
        if (incrament == 0) {
            //add ground level below

        }
        else if (incrament == 1) {
            //add low level below

        }
        else if (incrament == 2) {
            //add medium level below

        }
        else if (incrament == 3) {
            //add high level below

        }
    }
    */
}
