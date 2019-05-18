/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
  __  __         _____          _                   
 |  \/  |       / ____|        | |                  
 | \  / |_ __  | |     __ _ ___| |__   _____      __
 | |\/| | '__| | |    / _` / __| '_ \ / _ \ \ /\ / /
 | |  | | |_   | |___| (_| \__ \ | | |  __/\ V  V / 
 |_|  |_|_(_)   \_____\__,_|___/_| |_|\___| \_/\_/  
                                                                                        
                                   `..-::/+++++++//:--.`                                            
                             `.-/oosoooo++///////++oossss+/-`                                       
                         `-/osoo+//::::::::::::::::::::/++osso/.                                    
                      `-+so+::--------::::::::::::::::::::///+oss:`                                 
                    `/ss/:---------------:////::::::::::::::++++oyy/`                               
                  `/so:-....------------+o++++o+::::::::::/oo++++osyy:                              
                 .ss:.......-----://++oshyssoo+++:::::::/+syyyyyyyyyyho`                            
                `ys-.......------hhddddhdddddhhhy+/++/+yhhhhhhhhhhdddhhy.                           
                /h-.......-----oyddmhyhhdddhyyyyhddhhhdhyhhddddhyyyyddddy:.`                        
                +h......-------hddmhsdddddhyyyyyhdddddmsyddddhyyyyyyhmddddhyo/.                     
                -h/.....-------/+hdyydddhyyyyyydhdmdhdmshdddyyyyyyhdhmmdddmdddh.                    
                 /y/........-----sdhydhyyyyyyhdyymh:-+dyydhyyyyyydhyymd++sd/:::                     
                  :yo:--.........:ddhyyyyyyhdhyhdd+---odhyyyyyyhdyyhdds///h+                        
                   `:ossoo+++////::+shhhhhddhhdhy+.----:ohhhhhddhdhys+////sy                        
                       `..--::::/+oso/--:://///s:...------:////////+//////oh.                       
                                    -oy/...----:+--..-----::::/+oo/://////+h:                       
                                      -++--.-:::/+:-:+-/://::-.yy+::://////h/                       
                                  .:/+o/ys.--.:+:``` ` ` ``` ``s+:::://////hs+/-`                   
                               .+o+///+/sh--.-/++:--.:.-..`` `//::::://////ho//+oo/.                
                             `+o///oo+:.-h/-.-/s:---...```.-/+::::::://///+ho+o///+s+`              
                             ++:/+o+`   :h-..--------::::::::::::::://////oh` `+o:/+oo              
                             s/:/+s:   .yo...----...------:::::::::://////yo   .y::/+y              
                             /o:/++s. -yo-...----...-----::::::::::://///oh-  `o+::/os              
                             `s/:/+os/oo+/:.----...------:::::::::://////yy//:oo::/+s-              
                              .o++o/-.```-++----..------:::::::::://///+o:.`..:+o++s:               
                              .+y+-`      `::///:::----::::::::::/++++//-`     `-+y-                
                           `/ss+y-`           ``.-/+/-:::::::::++/-.``          `-s.                
                          :yo:-.+s`                `y:::::::::s:                 +o`                
                         :h/....:o                 `y::::::::/s.                 +:                 
                         ys-----/o`                -s::::::///o:                `+/                 
                        `sy-----:s-`            - `++::://///+so` .`           `.o-                 
                      .-:/ho:----:o//-     :  `:o/oy/////++sysoyo:+/`  -     `::+:                  
                     .::::/sy/:---:/oo-` `-o:/ooooo+++ossss+/::/oo+o+/:+-`  .+o:.                   
                     `-:::::+sso+////oo++ooy++sssssssso+/:::::::://+o++s+/::/+`                     
                       `.-::::/+oossssyyysssssoo++//::::::::::::::-.`--. `...                       
                          ``..--::::::::::::::::::::::::::::--..``                                  
                                  `````..............`````     
*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.ElectroMagnet_Subsystem;
import frc.robot.subsystems.Piston_Subsystem;
import frc.robot.subsystems.Pixy_Subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ElectroMagnet_Subsystem magnet;
  public static OI oi;
  public static DriveTrain_Subsystem driveTrain;
  public static Piston_Subsystem piston;
  public static SerialPort arduino;
  public static Pixy_Subsystem pixy;
  public static DigitalInput limitLeft, limitRight;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static Timer timer;
  public boolean startCompressor;
  public static boolean connectionStatus;
  public static boolean connectionOverride;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    try {
      arduino = new SerialPort(115200, SerialPort.Port.kUSB);
    } catch (Exception e) {
      System.out.println("You are ok.");
      try {
        arduino = new SerialPort(115200, SerialPort.Port.kUSB1);
      } catch (Exception e1) {
        System.out.println("You are almost dead.");
        try {
          arduino = new SerialPort(115200, SerialPort.Port.kUSB);
        } catch (Exception e2) {
          System.out.println("May Marco have mercy upon your soul.");
        }
      }
    }
    connectionStatus = DriverStation.getInstance().isFMSAttached();
    driveTrain = new DriveTrain_Subsystem();
    magnet = new ElectroMagnet_Subsystem();
    pixy = new Pixy_Subsystem();
    piston = new Piston_Subsystem();
    oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
// /*
    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
    cam.setResolution(256, 144);
    cam.setFPS(30);
    cam.setExposureManual(25);
    limitLeft = new DigitalInput(1);
    limitRight = new DigitalInput(0);
    timer = new Timer();
    startCompressor = true;
    driveTrain.drive.feedWatchdog();
    // */
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // if (timer.get() >= 5) {
    //   pixy.see();
    //   timer.reset();
    // }
    //  System.out.print(limit.get());
    if (connectionOverride == true){
      connectionStatus = true;
    }
    else{
      connectionStatus = DriverStation.getInstance().isFMSAttached();
    }
      driveTrain.drive.feedWatchdog();
      if (piston.counter >= 14){
        if (startCompressor) {
          timer.start();
          piston.c.start();
          startCompressor = false;
        }
        
        if (timer.get() >= 55){
          piston.c.stop();
          piston.counter = 0;
          timer.stop();
          timer.reset();
          startCompressor = true;
        }
      }
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().add(driveTrain.getDefaultCommand());
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
