package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_IMU;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_Methods_Autonomus;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake_15455;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Lift_15455;
//import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_15455;

public abstract class Auto_Base_15 extends LinearOpMode {
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_Methods_Autonomus auto_motors = new Mecanum_Methods_Autonomus();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Lift_15455 lift = new Lift_15455();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake_15455 intake = new Intake_15455();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_IMU imu_drive = new Mecanum_IMU();
//    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_15455 camera = new Camera_15455();

    protected int inchToTicks = 91;
    protected int scorePosition;

    public void init_classes(boolean red_alliance) {
        auto_motors.init_auto_drive_motors(hardwareMap, telemetry);
        lift.init_lift(hardwareMap, "lift");
        intake.init_intake(hardwareMap, "intake");
        imu_drive.init_drive_motors(hardwareMap, telemetry, false);
    }





}
