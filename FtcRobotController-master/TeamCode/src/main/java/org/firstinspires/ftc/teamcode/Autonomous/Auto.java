package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


import java.util.concurrent.TransferQueue;


@Autonomous

public class Auto extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
        init_classes(true);
        waitForStart();
        camera.init_camera(hardwareMap, "camera",telemetry);
        lift.lift_fix();
        intake.auto_intake(false);
        Thread.sleep(1000);
        lift.auto_lift_set(1, telemetry);
        arm.auto_arm(0);
        auto_motors.go_to_park(camera.zone(),0.65);
        lift.auto_lift_set(0, telemetry);
        intake.auto_intake(true);
        Thread.sleep(1000);



    }
}