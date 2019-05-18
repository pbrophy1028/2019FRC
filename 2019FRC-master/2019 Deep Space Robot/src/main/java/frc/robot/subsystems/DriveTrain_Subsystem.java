/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
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
  Talon leftFront, leftRear;
  Victor rightFront, rightRear;
  SpeedControllerGroup leftMotors, rightMotors;
  public MecanumDrive drive;

  public DriveTrain_Subsystem() {
    leftFront = new Talon (RobotMap.leftFront);
    leftRear = new Talon(RobotMap.leftRear);

    rightFront = new Victor(RobotMap.rightFront);
    rightRear = new Victor(RobotMap.rightRear);

    drive = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);
  }
 
  public void cartesianDrive(double yVal, double xVal, double zVal) {
    if (Robot.connectionStatus == true){
      drive.driveCartesian(xVal, yVal, zVal);
      drive.feedWatchdog();
    }
  }

  public void snapTurning(int dpadVal){
    if (dpadVal == 0){

    }
    else if (dpadVal == 90){

    }
    else if (dpadVal == 180){

    }
    else{

    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveWithJoystick());
  }
}
