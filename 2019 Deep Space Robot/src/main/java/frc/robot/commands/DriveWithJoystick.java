/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithJoystick extends Command {
  public DriveWithJoystick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.driveTrain.arcadeDrive(Robot.oi.driveStick.getRawAxis(1),
    // Robot.oi.driveStick.getRawAxis(5));
    // Robot.driveTrain.curvatureDrive(Robot.oi.driveStick.getRawAxis(1),
    // Robot.oi.driveStick.getRawAxis(5), Robot.oi.quickTurnButton.get());

    // System.out.println("Marco from NI");

    double yValue = Robot.oi.driveStick.getRawAxis(1), xValue = Robot.oi.driveStick.getRawAxis(0),
        zValue = Robot.oi.driveStick.getRawAxis(4);

    double[] sticks = { yValue, xValue, zValue };

    for (int i = 0; i < sticks.length; i++) {
      if (sticks[i] < 0.00001)
        sticks[i] = 0;
    }
    Robot.driveTrain.cartesianDrive(sticks[0], sticks[1], sticks[2]);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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