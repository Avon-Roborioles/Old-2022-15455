package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@Autonomous
@Disabled
public class Left extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
        lift.auto_lift_set(camera.zone(),telemetry);

}}