package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MergedTeleOp extends LinearOpMode {
    DcMotor motor0 = null;
    DcMotor motor1 = null;
    DcMotor motor2 = null;
    DcMotor fl, fr, br, bl, in, shooter;
    DcMotor ar, al;
    Servo claw, wrist;

    @Override
    public void runOpMode() {
        motor0 = hardwareMap.get(DcMotor.class, "intake");
        motor1 = hardwareMap.get(DcMotor.class, "outtakeone");
        
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        in = hardwareMap.get(DcMotor.class, "in");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        ar = hardwareMap.get(DcMotor.class, "ar");
        al = hardwareMap.get(DcMotor.class, "al");
        
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
        
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        al.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            double motorPowerScale = 1.0;
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.right_stick_x;
            double turn = gamepad1.left_stick_x;
            double flPower = (drive - strafe - turn) * motorPowerScale;
            double frPower = (drive + strafe + turn) * motorPowerScale;
            double brPower = (drive + strafe - turn) * motorPowerScale;
            double blPower = (drive - strafe + turn) * motorPowerScale;
            
            fr.setPower(frPower);
            fl.setPower(flPower);
            br.setPower(brPower);
            bl.setPower(blPower);

            ar.setPower(0.0);
            al.setPower(0.0);
            
            if (gamepad1.right_trigger > 0.1) {
                ar.setPower(-1.0);
                al.setPower(-1.0);
            } else if (gamepad1.left_trigger > 0.1) {
                ar.setPower(1.0);
                al.setPower(1.0);
            }
            
            if (gamepad1.b) {
                motor0.setPower(1);
            } else if (gamepad1.y) {
                motor0.setPower(0);
                wrist.setPosition(0);
            }
            
            if (gamepad1.a) {
                motor1.setPower(0.5);
                claw.setPosition(0);
            } else if (gamepad1.x) {
                motor1.setPower(0);
                wrist.setPosition(0.57);
                claw.setPosition(0.3);
            }
        }
    }
}
