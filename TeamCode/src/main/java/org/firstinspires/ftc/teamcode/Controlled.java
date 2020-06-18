package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp// @Autonomous(...) is the other common choice
// @Disabled
public class Controlled extends LinearOpMode {

        /* Declare OpMode members. */
        private ElapsedTime runtime = new ElapsedTime();
        DcMotor leftFrontMotor = null;
        DcMotor rightFrontMotor = null;
        DcMotor leftRearMotor = null;
        DcMotor rightRearMotor = null;
        // declare motor speed variables
        double RF; double LF; double RR; double LR;
        // declare joystick position variables
        double X1; double Y1; double X2; double Y2;
        // operational constants
        double joyScale = 0.5;
        double motorMax = 1; // Limit motor power to this value for Andymark RUN_USING_ENCODER mode

        @Override
        public void runOpMode() {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            leftFrontMotor = hardwareMap.dcMotor.get("motor2");
            rightFrontMotor = hardwareMap.dcMotor.get("motor1");
            leftRearMotor = hardwareMap.dcMotor.get("motor3");
            rightRearMotor = hardwareMap.dcMotor.get("motor4");

            // Set the drive motor direction:
            // "Reverse" the motor that runs backwards when connected directly to the battery
            // These polarities are for the Neverest 20 motors
            leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
            rightRearMotor.setDirection(DcMotor.Direction.REVERSE);



            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.update();

                // Reset speed variables
                LF = 0; RF = 0; LR = 0; RR = 0;

                // Get joystick values
                Y1 = -gamepad1.right_stick_y * joyScale; // invert so up is positive
                X1 = gamepad1.right_stick_x * joyScale;
                Y2 = -gamepad1.left_stick_y * joyScale; // Y2 is not used at present
                X2 = gamepad1.left_stick_x * joyScale;

                // Forward/back movement
                LF += Y1; RF += Y1; LR += Y1; RR += Y1;

                // Side to side movement
                LF += X1; RF -= X1; LR -= X1; RR += X1;

                // Rotation movement
                LF += X2; RF -= X2; LR += X2; RR -= X2;

                // Clip motor power values to +-motorMax
                LF = Math.max(-motorMax, Math.min(LF, motorMax));
                RF = Math.max(-motorMax, Math.min(RF, motorMax));
                LR = Math.max(-motorMax, Math.min(LR, motorMax));
                RR = Math.max(-motorMax, Math.min(RR, motorMax));

                // Send values to the motors
                leftFrontMotor.setPower(LF);
                rightFrontMotor.setPower(RF);
                leftRearMotor.setPower(LR);
                rightRearMotor.setPower(RR);

            }
        }
}