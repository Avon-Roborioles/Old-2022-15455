package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Mecanum_IMU {

    private DcMotor fl = null, fr = null, bl = null, br = null;
    private Telemetry telemetry = null;
    private BNO055IMU imu;

    double globalAngle, correction, power = .2;
    double zeroAngle = 0;
    Orientation lastAngles = new Orientation();
    int tipsyturnsy=1;

    public void init_drive_motors(HardwareMap hardwareMap, Telemetry telemetry, boolean upsidedown) {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        init_IMU(hardwareMap);
        init_auto_motors();
        this.telemetry = telemetry;
        if (upsidedown) {tipsyturnsy*=-1;}
    }

    private void init_auto_motors() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(0);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setTargetPosition(0);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setTargetPosition(0);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setTargetPosition(0);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void set_mode_RTP_motors() {
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void set_mode_RWE_motors() {
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void init_IMU(HardwareMap hardwareMap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void goToSpot(double inches, double power) {
        inches *= 91;
        setRelativeTargetAll(inches);

        while(isBusy()) {
            getTelemetry();
            correction = checkDirection();
            if(inches < 0)
                correction *= -1;

            fl.setPower(power - correction);
            bl.setPower(power - correction);
            fr.setPower(power + correction);
            br.setPower(power + correction);
        }
        setPowerAll(0);
    }

    public void setRelativeTargetIndividual(int fl_target, int bl_target, int fr_target, int br_target) {
        fl.setTargetPosition(fl_target + fl.getCurrentPosition());
        bl.setTargetPosition(bl_target + bl.getCurrentPosition());
        fr.setTargetPosition(fr_target + fr.getCurrentPosition());
        br.setTargetPosition(br_target + br.getCurrentPosition());
    }
    public void setRelativeTargetAll(double target) {
        fl.setTargetPosition(fl.getCurrentPosition() + (int)target);
        bl.setTargetPosition(bl.getCurrentPosition() + (int)target);
        fr.setTargetPosition(fr.getCurrentPosition() + (int)target);
        br.setTargetPosition(br.getCurrentPosition() + (int)target);
    }
    public void setPowerIndividual(double FL, double FR, double BR, double BL){
        fl.setPower(FL);
        br.setPower(BR);
        bl.setPower(BL);
        fr.setPower(FR);
    }
    public void setPowerAll(double power) {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    public void strafeLeft(double power, double inches) {
        //107 ticks= 1 inch
        inches*=130;
        setRelativeTargetIndividual((int)-inches,(int) inches,(int)inches,(int)-inches);


        while (isBusy()) {
            correction = checkDirection() * tipsyturnsy;
            //if positive correction needed, needs to go counter-clockwise -> adds power to front
            //if negative, needs to go clockwise -> adds power to back

            fl.setPower(-1 * (power + correction));
            bl.setPower(power - correction);
            fr.setPower(power + correction);
            br.setPower(-1 * (power - correction));

        }
        setPowerAll(0);

        this.turnToReset(.3);
    }
    public void strafeRight(double power, double inches) {
        //107 ticks= 1 inch
        inches*=107;
        setRelativeTargetIndividual((int)inches,(int)-inches,(int)-inches,(int)inches);


        while (isBusy()) {
            correction = checkDirection() * tipsyturnsy;
            //if positive correction needed, needs to go counter-clockwise -> adds power to front
            //if negative, needs to go clockwise -> adds power to back

            fl.setPower(power - correction);
            bl.setPower(-1 * (power + correction));
            fr.setPower(-1 * (power - correction));
            br.setPower(power + correction);

        }
        setPowerAll(0);

        this.turnToReset(.3);
    }
    public void turnToReset(double power) {
        this.set_mode_RWE_motors();
        while(Math.abs(getAngle()) > 1) {
            correction = checkDirection() * tipsyturnsy;

            if (correction > 0) { //positive correction needed - need to go counterclockwise
                fl.setPower(-1 * power);
                bl.setPower(-1 * power);
                fr.setPower(power);
                br.setPower(power);
            } else { //negative corr needed - need to go clockwise
                fl.setPower(power);
                bl.setPower(power);
                fr.setPower(-1 * power);
                br.setPower(-1 * power);
            }
            getTelemetry();
        }
        this.setPowerAll(0);
        this.set_mode_RTP_motors();
    }

    private double getAngle() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if(deltaAngle < -180) {
            deltaAngle += 360;
        } else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle - zeroAngle; //??
    }
    private double checkDirection() {
        double correction, gain = .05;

        correction  = -1 * getAngle(); //opposite of where we're currently heading
        while (correction >= 1) {
            correction *= gain;
        }

        return correction*tipsyturnsy;
    }
    public void setZero() {
        this.zeroAngle = this.getAngle();
    }

    public boolean isBusy (){
        getTelemetry();
        int totalBusy=0;
        if (fl.isBusy()) {
            totalBusy++;
        }
        if (bl.isBusy()) {
            totalBusy++;
        }
        if (br.isBusy()) {
            totalBusy++;
        }
        if (fr.isBusy()) {
            totalBusy++;
        }

        if (totalBusy>=4)
            return true;
        else
            return false;
    }

    public void getTelemetry() {
        this.telemetry.addData("heading: ", this.getAngle());
        this.telemetry.addData("zero angle", this.zeroAngle);
        this.telemetry.addData("true heading", this.globalAngle);
        this.telemetry.addData("correction: ", this.correction);
        telemetry.update();
    }

}
