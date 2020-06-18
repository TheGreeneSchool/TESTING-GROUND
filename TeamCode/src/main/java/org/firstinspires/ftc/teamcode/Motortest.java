package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp// @Autonomous(...) is the other common choice
// @Disabled
public class Motortest extends LinearOpMode {

    DcMotor motorOne = null;

    @Override
    public void runOpMode() {
        motorOne = hardwareMap.dcMotor.get("motortest");
        motorOne.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
           motorOne.setPower(gamepad1.left_stick_y);


        }
    }
