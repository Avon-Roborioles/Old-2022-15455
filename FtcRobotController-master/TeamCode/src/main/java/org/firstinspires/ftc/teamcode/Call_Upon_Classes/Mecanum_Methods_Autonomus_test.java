package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

//import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.hardware.*;

//
//import com.arcrobotics.ftclib.drivebase.MecanumDrive;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.hardware.RevIMU;
//import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class  Mecanum_Methods_Autonomus_test {


    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;
    private Telemetry telemetry = null;


    private void init_individual_motor(DcMotor motor, boolean isLeft) {
        if (isLeft) motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void init_auto_drive_motors(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        fl = hardwareMap.get(DcMotor.class, "fl");
        init_individual_motor(fl, true);

        fr = hardwareMap.get(DcMotor.class, "fr");
        init_individual_motor(fr, false);

        bl = hardwareMap.get(DcMotor.class, "bl");
        init_individual_motor(bl, true);

        br = hardwareMap.get(DcMotor.class, "br");
        init_individual_motor(br, false);
    }

//    public void init_Mecanum_Drive(HardwareMap hardwareMap, Telemetry telemetry) {
//        MecanumDrive drive = new MecanumDrive(
//                new Motor(hardwareMap, "fl", Motor.GoBILDA.RPM_312),
//                new Motor(hardwareMap, "fr", Motor.GoBILDA.RPM_312),
//                new Motor(hardwareMap, "bl", Motor.GoBILDA.RPM_312),
//                new Motor(hardwareMap, "br", Motor.GoBILDA.RPM_312)
//        );
//        RevIMU imu = new RevIMU(hardwareMap);
//        imu.init();
//
//    }
}