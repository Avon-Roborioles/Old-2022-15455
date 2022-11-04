package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class Camera_14954 extends OpMode {
    OpenCvWebcam webcam = null;

    @Override
    public void init(){
       WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
       int cameraMonitorVeiwId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorVeiwId", "id", hardwareMap.appContext.getPackageName());
       webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorVeiwId);

       webcam.setPipeline(new samplePipeline());

       webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
           @Override
           public void onOpened() {
               webcam.startStreaming(1280,720,OpenCvCameraRotation.UPRIGHT);
           }

           @Override
           public void onError(int errorCode) {
           }
       });
    }
    public void loop(){

    }
    class samplePipeline extends OpenCvPipeline {
        Mat YCbCr = new Mat();
        Mat midcrop;
        double redavgfin;
        double blueavgfin;
        double greenavgfin;
        double zone = 0;
        Mat outPut = new Mat();
        Scalar rectcolor = new Scalar(255.0, 0.0, 0.0);
        public Mat processFrame(Mat input){
            Imgproc.cvtColor(input,YCbCr,Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("Pipeline Running");

            Rect midrect = new Rect(427, 240, 426, 239);
            //Red Average
            input.copyTo(outPut);
            Imgproc.rectangle(outPut, midrect, rectcolor, 2);

            midcrop = YCbCr.submat(midrect);

            Core.extractChannel(midcrop, midcrop, 2);

            Scalar midavg = Core.mean(midcrop);

            redavgfin = midavg.val[0];

            //Blue Average
            input.copyTo(outPut);
            Imgproc.rectangle(outPut, midrect, rectcolor, 2);

            midcrop = YCbCr.submat(midrect);

            Core.extractChannel(midcrop, midcrop, 1);

            midavg = Core.mean(midcrop);

            blueavgfin = midavg.val[0];

            //Green Average
            input.copyTo(outPut);
            Imgproc.rectangle(outPut, midrect, rectcolor, 2);

            midcrop = YCbCr.submat(midrect);

            Core.extractChannel(midcrop, midcrop, 3);

            midavg = Core.mean(midcrop);

            redavgfin = midavg.val[0];

            //Comparing
            if(blueavgfin>redavgfin && blueavgfin > greenavgfin) {
                zone = 1;
            } else if(redavgfin>blueavgfin && redavgfin>greenavgfin) {
                zone = 2;
            } else if(greenavgfin>redavgfin && greenavgfin>blueavgfin) {
                zone = 3;
            }
            telemetry.addData("Parking Zone: ", zone);


            return(outPut);
        }
    }
}