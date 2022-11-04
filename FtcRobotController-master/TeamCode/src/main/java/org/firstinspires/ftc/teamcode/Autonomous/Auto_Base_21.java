package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_21954;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake_21954;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Lift_21954;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_IMU;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_Methods_Autonomus;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Turntable_21954;

public abstract class Auto_Base_21 extends LinearOpMode {
    //objects for each function of the robot
    //Chassis drive motors
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_Methods_Autonomus auto_motors = new Mecanum_Methods_Autonomus();
    //Lift
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Lift_21954 lift = new Lift_21954();
    //Intake
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake_21954 intake = new Intake_21954();
    //Movement tracking
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_IMU imu_drive = new Mecanum_IMU();
    //Camera
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_21954 camera = new Camera_21954();
    //Turntable
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Turntable_21954  turntable = new Turntable_21954();


    protected int inchToTicks = 91;
    protected int scorePosition;

    public void init_classes(boolean red_alliance) {
        //init
        auto_motors.init_auto_drive_motors(hardwareMap, telemetry);
        lift.init_lift_motor_21954(hardwareMap, "lift");
        intake.init_intake_motor_21954(hardwareMap, "intake");
        imu_drive.init_drive_motors(hardwareMap, telemetry, false);
        turntable.init_turntable_21954(hardwareMap, "turntable");
    }





}
