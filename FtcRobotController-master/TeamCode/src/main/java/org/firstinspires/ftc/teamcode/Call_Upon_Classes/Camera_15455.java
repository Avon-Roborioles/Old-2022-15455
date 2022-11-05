package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
        pipeline = new PipeLine();
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
        String string="";
        while (opModeIsActive())
        {
            if (pipeline.getSide() == PipeLine.SIDE.BLUE){
                string = "blue";
            }else if (pipeline.getSide() == PipeLine.SIDE.YELLOW) {
                string = "yellow";
            }else{
                string = "purple";
            }
            telemetry.addData("",string);
            telemetry.update();
            sleep(100);
        }
    }

    //opmode and pipeline are run on different threads

    public static class PipeLine extends OpenCvPipeline {
        private static final Scalar BLUE = new Scalar(0, 0, 255);


        private volatile int averageH;
        private volatile int averageS;
        private volatile int averageV;
        private volatile PipeLine.SIDE side = SIDE.BLUE;

        Point TopLeft=new Point(50,50);
        Point BottomRight= new Point(100,100);


        Mat mat = new Mat();
        Mat Hue = new Mat();
        Mat Sat = new Mat();
        Mat Val = new Mat();
        Mat H_region;
        Mat S_region;
        Mat V_region;

        private void inputToHSV(Mat in, int hsv) {
            Core.extractChannel(mat, in, hsv);
        }
        @Override
        public void init(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV_FULL);
            inputToHSV(Hue, 1);
            inputToHSV(Sat, 2);
            inputToHSV(Val, 3);

            H_region = Hue.submat(new Rect(TopLeft, BottomRight));
            S_region = Sat.submat(new Rect(TopLeft, BottomRight));
            V_region = Val.submat(new Rect(TopLeft, BottomRight));
        }




        @Override
        public Mat processFrame(Mat input) {

            int[] lowYELLOW = new int[]{25, 127,127};
            int[] highYELLOW = new int[] {35,255,255};
            //300,100,100
            int[] lowPURPLE = new int[] {145, 127, 127};
            int[] highPURPLE = new int[] {155, 255, 255};

            Core.extractChannel(mat, Hue, 0);
            Core.extractChannel(mat, Sat, 1);
            Core.extractChannel(mat, Val, 2);

            averageH = (int) Core.mean(H_region).val[0];
            averageS = (int) Core.mean(S_region).val[0];
            averageV = (int) Core.mean(V_region).val[0];


            Imgproc.rectangle(input, TopLeft, BottomRight, BLUE, 2);


            if (lowYELLOW[0]<averageH && averageH<highYELLOW[0] &&
                lowYELLOW[1]<averageS && averageS<highYELLOW[1] &&
                lowYELLOW[2]<averageV && averageV<highYELLOW[2])
            {
                side = SIDE.YELLOW;
            } else if(lowPURPLE[0]<averageH && averageH<highPURPLE[0] &&
                    lowPURPLE[1]<averageS && averageS<highPURPLE[1] &&
                    lowPURPLE[2]<averageV && averageV<highPURPLE[2])
            {
                side = SIDE.PURPLE;
            }else{
                side = SIDE.BLUE;
            }


            return input;

        }

        public SIDE getSide() {return side;}
        public enum SIDE {
            YELLOW,PURPLE,BLUE
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