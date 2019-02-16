/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Parking extends Command {
  String direction;

  public Parking() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.pixy);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    direction = Robot.pixy.see();

    if (Robot.limitLeft.get() && !Robot.limitRight.get()) {
      Robot.driveTrain.cartesianDrive(0, .18, -.175);
    } else if (Robot.limitRight.get() && !Robot.limitLeft.get()) {
      Robot.driveTrain.cartesianDrive(0, .18, .175);
    }

    if (direction.indexOf("right") != -1) {
      Robot.driveTrain.cartesianDrive(0, 0, .18);
      System.out.println("rotate right");
    } else if (direction.indexOf("left") != -1) {
      Robot.driveTrain.cartesianDrive(0, 0, -.18);
      System.out.println("rotate left");

    }
    if (direction.indexOf("strafeLeft") != -1) {
      Robot.driveTrain.cartesianDrive(0, -.25, 0);
      System.out.println("strafe left");

    } else if (direction.indexOf("strafeRight") != -1) {
      Robot.driveTrain.cartesianDrive(0, .25, 0);
      System.out.println("strafe right");

    }
    if (direction.indexOf("notFound") != -1) {
      System.out.println("line not found");
      Robot.driveTrain.cartesianDrive(0, 0, 0);
    }

    //for (int i = 0; i < 100000000000; i++) {}
    Robot.driveTrain.drive.feedWatchdog();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (direction.indexOf("straight") != -1);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
