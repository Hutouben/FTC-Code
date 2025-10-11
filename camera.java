import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import april.tag.AprilTagDetector;
import april.tag.TagDetection;

import java.util.List;

public class AprilTagCamera {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Camera not accessible");
            return;
        }


        AprilTagDetector detector = new AprilTagDetector();

        Mat frame = new Mat();
        while (true) {
            camera.read(frame);
            if (frame.empty()) continue;


            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);


            List<TagDetection> detections = detector.detect(gray);

            for (TagDetection detection : detections) {
                int tagId = detection.id;
                Point center = new Point(detection.cx, detection.cy);

                String label;
                Scalar color;

                if (tagId == 1) {
                    label = "Tag 1 detected - Action A";
                    color = new Scalar(0, 255, 0); // Green
                } else if (tagId == 2) {
                    label = "Tag 2 detected - Action B";
                    color = new Scalar(0, 0, 255); // Red
                } else {
                    label = "Unknown Tag " + tagId + " - Default action";
                    color = new Scalar(255, 0, 0); // Blue
                }


                Imgproc.circle(frame, center, 10, color, -1);
                Imgproc.putText(frame, label, new Point(center.x + 10, center.y),
                        Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, color, 2);
            }




         if ()
        }

        camera.release();
    }
}
