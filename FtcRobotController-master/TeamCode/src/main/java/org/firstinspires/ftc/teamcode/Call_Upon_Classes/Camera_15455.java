package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp
public class Camera_15455 extends LinearOpMode
{
    OpenCvWebcam webcam;
    PipeLine pipeline;


    @Override
    public void runOpMode()
    {
        //camera+monitor
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewId);

        //Specify the image processing pipeline we wish to invoke upon receipt of a frame from the camera.
        pipeline = new PipeLine(5);
        webcam.setPipeline(pipeline);
//        webcam.setViewportRenderingPolicy(OpenCvWebcam.ViewportRenderingPolicy.OPTIMIZE_VIEW);
//        webcam.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);

        webcam.setMillisecondsPermissionTimeout(10000); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
            @Override
            //on sucsessful opening
            public void onOpened(){webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);}
            //on error
            @Override
            public void onError(int errorCode) {System.out.println("eroor???");}
        });//start cam in async

        telemetry.addLine("Waiting for start");
        telemetry.update();


        waitForStart();

        while (opModeIsActive())
        {
            sleep(100);
        }
    }

    //opmode and pipeline are run on different threads

    public static class PipeLine extends OpenCvPipeline {
        private static final Scalar BLUE = new Scalar(0, 0, 255);
        public enum SIDE {
            YELLOW,PURPLE,BLUE
        }
        private int width;
        private SIDE side;

        public PipeLine (int width){
            this.width = width;
        }




        @Override
        public Mat processFrame(Mat input) {
            Mat mat = new Mat();
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV_FULL);

            Point TopLeft=new Point(50,50);
            Point BottomRight= new Point(100,100);

            Mat Region_1;

            Scalar lowYELLOW = new Scalar(25, 127,127);
            Scalar highYELLOW = new Scalar(35,255,255);
            Mat thresh = new Mat();

            Core.inRange(mat, lowYELLOW, highYELLOW, thresh);


//            public void init() {
//                Region_1 = mat.submat(new Rect(TopLeft, BottomRight));
//            }



            Imgproc.rectangle(input, TopLeft, BottomRight, BLUE, 2);


            return null;
        }






    }



    public static class SamplePipeline extends OpenCvPipeline {
        private static final Scalar BLUE = new Scalar(0, 0, 255);

        private static final int THRESHOLD = 107;

        Point topLeft = new Point(50, 50);
        Point bottomRight = new Point(100, 100);

        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();

        private volatile int average;
        private volatile TYPE type = TYPE.BALL;

        private void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
        }

        @Override
        public void init(Mat input) {
            inputToCb(input);

            region1_Cb = Cb.submat(new Rect(topLeft, bottomRight));
        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            average = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(input, topLeft, bottomRight, BLUE, 2);

            if (average > THRESHOLD) {
                type = TYPE.BALL;
            } else {
                type = TYPE.CUBE;
            }

            return input;
        }

        public TYPE getType() {
            return type;
        }

        public int getAverage() {
            return average;
        }

        public enum TYPE {
            BALL, CUBE
        }
    }
}