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
  public double xAct, yAct, zAct;

  public DriveWithJoystick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    xAct = 0;
    yAct = 0;
    zAct = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double yValue = Robot.oi.driveStick.getRawAxis(1), xValue = Robot.oi.driveStick.getRawAxis(0),
        zValue = Robot.oi.driveStick.getRawAxis(4);

    double[] sticks = { yValue, xValue, zValue };

    for (int i = 0; i < sticks.length; i++) {
      if (sticks[i] < 0.05 && sticks[i] > 0)
        sticks[i] = 0;
      //sticks[i] *= .75;

    }
    if (yAct < sticks[0] && yAct < .65)
      yAct += 0.05;
    else if (yAct > sticks[0] && yAct > -.65)
      yAct -= 0.05;

    if (xAct < sticks[1] && xAct < .65)
      xAct += 0.05;
    else if (xAct > sticks[1] && xAct > -.65)
      xAct -= 0.05;

    if (zAct < sticks[2] && zAct < .70)
      zAct += 0.05;
    else if (zAct > sticks[2])
      zAct -= 0.05;

    /*
    yAct += (sticks[0] - yAct) / 2;
    xAct += (sticks[1] - xAct) / 2;
    zAct += (sticks[2] - zAct) / 2;
    */ 

    Robot.driveTrain.cartesianDrive(yAct, xAct, zAct);
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
