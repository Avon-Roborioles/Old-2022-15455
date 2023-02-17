package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Lift_15455 {

    private double speed = 0;

    private DcMotor lift = null;

    private int max =5720;
    private int top =max-40;
    private int mid =(int) (2./3.*max)+200;
    private int low =(int) (1./3.*max)-200;


    int hold = 1;
    boolean up = false;
    boolean down = false;


    public void init_lift (HardwareMap map, String name) {
        lift  = map.get(DcMotor.class, name);
//        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //simple arm program to move arm up will work on position holding soon
    public void lift_fix(){
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void run_lift_constant(Gamepad gp, Telemetry telemetry){
        double ltrigger = gp.left_trigger;
        double rtrigger = gp.right_trigger;

        if (ltrigger > 0 && lift.getCurrentPosition()<max) {
            speed = 1;
        } else if (rtrigger > 0 && lift.getCurrentPosition()>40 ) {
            speed = -1;
        } else {
            speed = 0.05;
        }

        lift.setPower(speed);
        get_telemetry(telemetry);


    }


    public void auto_lift_set(int zone, Telemetry telemetry) throws InterruptedException {
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        if (zone==0) {
            lift.setTargetPosition(5);
        } else if (zone==1) {
            lift.setTargetPosition(-low);
        } else if (zone==2) {
            lift.setTargetPosition(-mid);
        } else if (zone==3) {
            lift.setTargetPosition(-top);
        }
        lift.setPower(-1);

        while (lift.isBusy()){
            get_telemetry(telemetry);
            telemetry.update();
        }

    }



    public void get_telemetry (Telemetry telemetry) {
        telemetry.addData("Position",lift.getCurrentPosition());
        telemetry.addData("target", lift.getTargetPosition());
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
