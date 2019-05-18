/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CompressorControl;
import frc.robot.commands.ConnectionFailsafe;
import frc.robot.commands.MagnetControl;
import frc.robot.commands.Parking;
import frc.robot.commands.PistonControl;
import frc.robot.commands.SlowMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  public Joystick driveStick = new Joystick(0);
  public JoystickButton pistonButton = new JoystickButton(driveStick, 1);//A button
  public JoystickButton compressorButton = new JoystickButton(driveStick, 2);//B button
  public JoystickButton magnetButton = new JoystickButton(driveStick, 3);//X button
  public JoystickButton parkStart = new JoystickButton(driveStick, 4);// Y Button
  public JoystickButton slowBumper = new JoystickButton(driveStick, 5);//Left Bumper
  public JoystickButton connectionOveride = new JoystickButton(driveStick, 6);//Right Bumper

  public OI() {
    pistonButton.toggleWhenPressed(new PistonControl());
    compressorButton.toggleWhenPressed(new CompressorControl());
    magnetButton.toggleWhenPressed(new MagnetControl());
    parkStart.whileHeld(new Parking());
    slowBumper.whileHeld(new SlowMode());
    connectionOveride.whenPressed(new ConnectionFailsafe());
  }

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand())

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}