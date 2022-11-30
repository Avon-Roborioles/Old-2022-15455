package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.util.concurrent.TransferQueue;


@Autonomous

public class Auto extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
        init_classes(true);
        lift.init_lift(hardwareMap,"lift");
        waitForStart();
        intake.auto_intake(false);
        Thread.sleep(1000);
        lift.auto_lift_set(1);
        auto_motors.go_to_park(zone,0.55);
        arm.auto_arm(0);
        lift.auto_lift_set(0);



    }
}