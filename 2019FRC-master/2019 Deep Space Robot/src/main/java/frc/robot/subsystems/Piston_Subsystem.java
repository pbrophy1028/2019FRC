/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class Piston_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DoubleSolenoid ds;
  public Compressor c;
  public int counter;

  public Piston_Subsystem() {
    ds = new DoubleSolenoid(0, 1);
    ds.set(DoubleSolenoid.Value.kOff);
    c = new Compressor();
    c.stop();
    counter = 0;
  }
  
  public void extend() {
    ds.set(DoubleSolenoid.Value.kForward);
  }
  public void close() {
    ds.set(DoubleSolenoid.Value.kReverse);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new Close_Piston());
  }
}
