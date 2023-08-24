package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.*;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class  Mecanum_Methods_Autonomus {


    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;
    private DcMotor x_encoder=null;
    private DcMotor y_encoder=null;
    private Telemetry telemetry = null;


    public void init_encoders(HardwareMap hardwareMap){
        x_encoder = hardwareMap.get(DcMotor.class, "x");
        y_encoder = hardwareMap.get(DcMotor.class, "y");
    }
    /*
    for deadwheels
    D=35mm
    C=110mm/2.80in
    1 rotation = 8192 ticks
    thus 8192 ticks is 2.8 in
    307.9 ticks is approx 1 inch
     */
    /*
    make one here for normal wheels
     */

    private void init_individual_motor(DcMotor motor, boolean isLeft){
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

    public void setRelativeTargetAll(int target) {
        fl.setTargetPosition(fl.getCurrentPosition() + target);
        bl.setTargetPosition(bl.getCurrentPosition() + target);
        fr.setTargetPosition(fr.getCurrentPosition() + target);
        br.setTargetPosition(br.getCurrentPosition() + target);
    }

    public void setRelativeTargetIndividual(int fl_target, int bl_target, int fr_target, int br_target) {
        fl.setTargetPosition(fl_target + fl.getCurrentPosition());
        bl.setTargetPosition(bl_target + bl.getCurrentPosition());
        fr.setTargetPosition(fr_target + fr.getCurrentPosition());
        br.setTargetPosition(br_target + br.getCurrentPosition());
    }

    public void setPowerAll(double power) {
        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(power);
        br.setPower(power);
    }

    public void setPowerIndividual(double FL, double FR, double BR, double BL){
        fl.setPower(FL);
        br.setPower(BR);
        bl.setPower(BL);
        fr.setPower(FR);

    }

    public void read_encoders(){
    y_encoder.getCurrentPosition();
    x_encoder.getCurrentPosition();
    }


    public void goToSpot(double inches, double power)throws InterruptedException{
        inches*=307.9;
        setRelativeTargetAll((int) inches);
        setPowerAll(power);
        while (isBusy()){Thread.sleep(100);}
    }

    public void stopMotors() {setPowerAll(0);}

    public void turn90left (double power)throws InterruptedException{
        setRelativeTargetIndividual((int) Math.floor(-1440*1.2),(int) Math.floor(-1440*1.2),(int) Math.floor(1440*1.2), (int) Math.floor(1440*1.2));
        setPowerIndividual(-power,power,power,-power);
        while (isBusy()){Thread.sleep(100);}
    }
    public void turn90right (double power)throws InterruptedException{
        setRelativeTargetIndividual((int) Math.floor(1440*1.2),(int) Math.floor(1440*1.2),(int) Math.floor(-1440*1.2), (int) Math.floor(-1440*1.2));
        setPowerIndividual(power,-power,-power,power);
        while (isBusy()){Thread.sleep(100);}
    }
    public void turn45left (double power)throws InterruptedException{
        setRelativeTargetIndividual((int) Math.floor(-1440*1.2*.5),(int) Math.floor(-1440*1.2*.5),(int) Math.floor(1440*1.2*.5), (int) Math.floor(1440*1.2*.5));
        setPowerIndividual(-power,power,power,-power);
        while (isBusy()){Thread.sleep(100);}
    }
    public void turn45right (double power)throws InterruptedException{
        setRelativeTargetIndividual((int) Math.floor(1440*1.2*.5),(int) Math.floor(1440*1.2*.5),(int) Math.floor(-1440*1.2*.5), (int) Math.floor(-1440*1.2*.5));
        setPowerIndividual(power,-power,-power,power);
        while (isBusy()){Thread.sleep(100);}
    }

    public void strafeLeft(double power, double inches) throws InterruptedException{
        //107 ticks= 1 inch
        inches*=307.9;
        setRelativeTargetIndividual((int)-inches,(int) inches,(int)inches,(int)-inches);
        setPowerIndividual(-power, power, -power, power);
        while (isBusy()){Thread.sleep(100);}
    }
    public void strafeRight(double power, double inches) throws InterruptedException {
        inches*=307.9;
        setRelativeTargetIndividual((int)inches,(int)-inches,(int)-inches,(int)inches);
        setPowerIndividual(power, -power, power, -power);
        while (isBusy()){Thread.sleep(100);}
    }

    // probably should re-write this one
    public boolean isBusy (){
//        getTelemetry(telemetry);
        int totalBusy=0;
        if (fl.isBusy())
            totalBusy++;
        if (bl.isBusy())
            totalBusy++;
        if (br.isBusy())
            totalBusy++;
        if (fr.isBusy())
            totalBusy++;

        if (totalBusy>=3)
            return true;
        else
            return false;
    }


    public void go_to_park(int pos, double power) throws InterruptedException {
        if (pos == 1) {
            strafeLeft(power, 4.6);//was 4.1
            Thread.sleep(1000);
            goToSpot(5.8, power);


        } else if (pos == 3) {
            strafeRight(power, 3.95);
            Thread.sleep(1000);
            goToSpot(6.05, power);

        } else {//pos 2 default
            goToSpot(5.9, power);
        }
    }


    public void getTelemetry (Telemetry telemetry){
        telemetry.addData("fl encoder value: ",fl.getCurrentPosition());
        telemetry.addData("fr encoder value: ",fr.getCurrentPosition());
        telemetry.addData("bl encoder value: ",bl.getCurrentPosition());
        telemetry.addData("br encoder value: ",br.getCurrentPosition());
    }


    /*
    //double that holds the value of inches that want to be traveled
    double desiredValue = 24;

    //takes integer turnTimes and executes a loop depending on its positive or negative disposition to turn left or right certain amount of times
    //as would be specified by the absolute value of the passed integer
    public void turn(int turnTimes) {
        if (turnAmount > 0) {
            for (int i = 0; turnAmount > i; i++) {
                turn90Right(0.5);
            }
        }
        else if (turnAmount < 0) {
            for (int i = 0; turnAmount < i; i--) {
                turn90Left(0.5);
            }

        }
    }

  //move method takes axis the bot is meant to move on and the amount of tiles it is meant to move (and simplify calling)
  public void move(String axis, double tiles) {
    //determine what axis wants to be moved on
    if (axis == "x") {
      //determines if the robot needs to strafe left or right
      if (tiles > 0) {
        desiredValue *= tiles;
        //has the robot move right until it reaches the value of desiredValue which is equal to tiles*24
        strafeRight(0.5, desiredValue);
      }
      else if (tiles < 0) {
        desiredValue *= java.lang.Math.abs(tiles);
        //has the robot move left until it reaches the value of the desiredValue which is equal to tiles*24
        strafeLeft(0.5, desiredValue);
      }
    }
    else if (axis == "y") {
      //determines if robot needs to move forward/backward
      if (tiles > 0) {
        desiredValue *= tiles;
        //has the robot move forward until it reaches the value of desiredValue which is equal to tiles*24
        goToSpot(desiredValue, 0.5); //CAN I CHANGE int inches IN goToSpot METHOD TO double?
      }
      else if (tiles < 0) {
        desiredValue *= tiles;
        //has the robot move backward until it reaches the value of desiredValue which is equal to tiles*24
        goToSpot(desiredValue, 0.5); //CAN I CHANGE int inches IN goToSpot METHOD TO double?
      }
    }
  }
    */
}
