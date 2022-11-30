package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class Red_Right_15 extends org.firstinspires.ftc.teamcode.Autonomous.Auto_Base_15 {

    public void runOpMode() throws InterruptedException {
        /*
        //POTENTIALLY ADD MORE MEDIUM/LOW JUNCTION SCORING IF EXTRA TIME

        //add method below that takes signal cone color


        //moves away from wall, scores on medium junction, and picks up new cone
        moveElevator(2);
        move(y, 1.6);
        pivot(-1);
        openGripper();
        move(y, 0.5);
        pivot(1);
        moveElevator(0);
        move(x, -1.5);

        //takes which parking spot the signal cone wants to be parked in (3 being the closest slot to the wall and 1 being the closest to the middle of the field)
        if (parkingSlot == 3) {
            //scores cone on medium junction
            moveElevator(2);
            move(x, -1.5);
            pivot(0);
            openGripper();

            //picks up another cone
            pivot(1);
            moveElevator(0);
            move(x, 1.5);
            moveElevator(1);

            //scores cone on low junction
            move(x, -0.5);
            pivot(0);
            openGripper();

            //parks in slot 3
            move(x, 0.5);
            move(y, -1.5);
        }
        else if (parkingSlot == 2) {
            //scores cone on low junction
            moveElevator(1);
            move(x, -0.5);
            pivot(0);
            openGripper();

            //picks up another cone
            pivot(1);
            moveElevator(0);
            move(x, 0.5);
            moveElevator(2);

            //scores cone on medium junction
            move(x, -1.5);
            pivot(0);
            openGripper();

            //parks in slot 2
            move(x, -0.5);
            move(y, -1.5);
        }
        else if (parkingSlot == 1) {
            //scores cone on medium junction
            moveElevator(2);
            move(x, -1.5);
            pivot(0);
            openGripper();

            //picks up another cone
            pivot(1);
            moveElevator(0);
            move(x, 1.5);
            moveElevator(1);

            //scores cone on low junction
            move(x, -0.5);
            pivot(0);
            openGripper();

            //parks in slot 1
            move(x, -0.5);
            move(y, -1.5);
        }
        else {
            //scores cone on low junction
            moveElevator(1);
            move(x, -0.5);
            pivot(0);
            openGripper();

            //picks up another cone
            pivot(1);
            moveElevator(0);
            move(x, 0.5);
            moveElevator(2);

            //scores cone on medium junction
            move(x, -1.5);
            pivot(0);
            openGripper();

            //parks in slot 2
            move(x, -0.5);
            move(y, -1.5);
        }
    }
*/
}}