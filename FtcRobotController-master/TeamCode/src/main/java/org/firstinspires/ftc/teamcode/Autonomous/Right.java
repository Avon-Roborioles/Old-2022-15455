package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous
public class Right extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
            init_classes(true);
            SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

            TrajectorySequence tj1 = drive.trajectorySequenceBuilder(new Pose2d(0,0,0))
                    .lineToLinearHeading(new Pose2d(37,0,0)
                    , SampleMecanumDrive.getVelocityConstraint(20, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))

                    .lineToLinearHeading(new Pose2d(0,-15,0)
                            , SampleMecanumDrive.getVelocityConstraint(20, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                    .build();

            waitForStart();

            drive.followTrajectorySequence(tj1);



        }}
