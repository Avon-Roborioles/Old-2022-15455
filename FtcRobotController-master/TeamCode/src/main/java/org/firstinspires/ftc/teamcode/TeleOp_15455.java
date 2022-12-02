package org.firstinspires.ftc.teamcode;

//import classes for objects;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
@TeleOp
public class TeleOp_15455 extends LinearOpMode {
    //objects for each function of the robot
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Mecanum_Methods_TeleOp drivingMotors = new Mecanum_Methods_TeleOp(false);
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Lift_15455 lift = new Lift_15455();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake_15455 intake = new Intake_15455();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.arm_15455 arm = new arm_15455();

    public void runOpMode() throws InterruptedException {
        //Initialize objects
        drivingMotors.init_drive_motors(hardwareMap);
        lift.init_lift(hardwareMap, "lift");
        intake.init_intake(hardwareMap, "intake");
        arm.init_arm(hardwareMap, "arm");

        waitForStart();
//        lift.lift_fix();
        while (opModeIsActive()) {
                //Driver 1 Functions
            drivingMotors.run_drive_motors_15(gamepad1, telemetry); //driving
                //Driver 2 Functions
            lift.run_lift_constant(gamepad2, telemetry);
            intake.run_intake(gamepad2, telemetry);
            arm.run_arm(gamepad2, telemetry);


            telemetry.update();
        }
    }
}

