package org.firstinspires.ftc.teamcode;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.util.Timer;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class test extends OpMode {

    private Follower follower;
    private Timer pathTimer;
    private Timer OpModeTimer;

    public enum PathState {
        // TODO START POSITION TO END POSITION
        DRIVE_POSITION_SHOOT_POS,
        SHOOT_PRELOAD,

        DRIVE_SHOOTPOS_ENDPOS,
    }

    PathState pathState;

    private final Pose startPose = new Pose(20, 122, Math.toRadians(138));
    private final Pose shootPose = new Pose(47.840531561461795, 95.20265780730897, Math.toRadians(138));
    private final Pose endPose = new Pose(66.65780730897009, 107.80066445182725, Math.toRadians(90));

    private PathChain driveStartPosShootPos, driveShootPosEndPos;

    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, endPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), endPose.getHeading())
                .build();
    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_POSITION_SHOOT_POS:
                follower.followPath(driveStartPosShootPos, true);
                setPathState(PathState.SHOOT_PRELOAD); // TODO reset timer and makes a new state
                break;
            case SHOOT_PRELOAD:
                // TODO check if the follower is done with its path
                // TODO also check to see if five seconds has elapsed
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 5) {
                        follower.followPath(driveShootPosEndPos, true);
                        setPathState(PathState.DRIVE_SHOOTPOS_ENDPOS);
                }
                break;

            case DRIVE_SHOOTPOS_ENDPOS:
                // TODO all done!!

                if (!follower.isBusy()) {
                    telemetry.addLine("Done All Paths");
                }

            default:
                telemetry.addLine("No State Commanded");

                break;
        }
    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }

    @Override
    public void init() {
        pathState = PathState.DRIVE_POSITION_SHOOT_POS;
        pathTimer = new Timer();
        OpModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        // TODO add in any init mechanisisms like flywheels

        buildPaths();
        follower.setPose(startPose);
    }

    public void start() {
        OpModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();

        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }
}

