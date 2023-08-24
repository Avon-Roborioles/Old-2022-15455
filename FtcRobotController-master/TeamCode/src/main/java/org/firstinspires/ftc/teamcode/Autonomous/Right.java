package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous

public class Right extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
        init_classes(false);
        waitForStart();
        camera.init_camera(hardwareMap, "camera",telemetry);
        lift.lift_fix();
        intake.auto_intake(false);
        Thread.sleep(1000);
        lift.auto_lift_set(Math.max(4-camera.zone(), 2), telemetry,1);
        arm.auto_arm(-1);
        auto_motors.go_to_park(camera.zone(),0.4);

        if (camera.zone()==3){
            arm.auto_arm(-1);
            Thread.sleep(500);
            lift.auto_lift_set(1,telemetry,0.7);
            Thread.sleep(1000);
            intake.auto_intake(true);
            Thread.sleep(1000);
            arm.auto_arm(0);
            Thread.sleep(1000);

        }else if (camera.zone()==2){
            arm.auto_arm(-1);
            Thread.sleep(500);
            lift.auto_lift_set(2,telemetry,0.7);
            Thread.sleep(1000);
            intake.auto_intake(true);
            Thread.sleep(1000);
            arm.auto_arm(0);
            Thread.sleep(1000);

        }else{

            arm.auto_arm(-1);
            Thread.sleep(500);
            intake.auto_intake(true);
            Thread.sleep(1500);
            arm.auto_arm(0);
            Thread.sleep(1000);

        }


        lift.auto_lift_set(0, telemetry,1);
        Thread.sleep(1000);
//        intake.auto_intake(true);


    }
}