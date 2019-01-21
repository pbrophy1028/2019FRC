/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoystick;

/**
 * Add your docs here.
 */
public class DriveTrain_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Talon leftFront, leftRear, rightFront, rightRear;
  SpeedControllerGroup leftMotors, rightMotors;
  MecanumDrive drive;

  public DriveTrain_Subsystem() {
    leftFront = new Talon (RobotMap.leftFront);
    leftRear = new Talon(RobotMap.leftRear);
    // leftMotors = new SpeedControllerGroup(leftFront, leftRear);

    rightFront = new Talon(RobotMap.rightFront);
    rightRear = new Talon(RobotMap.rightRear);
    // rightMotors = new SpeedControllerGroup(rightFront, rightRear);

    drive = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);

  }

  // public void arcadeDrive(double xValue, double yValue) {
  // drive.arcadeDrive(xValue, yValue);
  // }

  // public void curvatureDrive(double xValue, double yValue, boolean
  // quickTurnButton) {

  // drive.curvatureDrive(xValue, yValue, quickTurnButton);

  // }
 
  public void cartesianDrive(double yVal, double xVal, double zVal) {
    drive.driveCartesian(yVal, xVal, zVal);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveWithJoystick());
  }
}