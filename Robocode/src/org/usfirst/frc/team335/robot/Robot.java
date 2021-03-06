
package org.usfirst.frc.team335.robot;

//'''''''''
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Robot extends SampleRobot {
	
	public double speedZero;
	public double speed;
    RobotDrive myRobot;
    Joystick stick;                     /* Xbox 360 Controller joystick object */
    Joystick logatt; 					/* Logictech Attack 3 joystick object */
    Jaguar jaguar1;                 /* Pulley System Jaguar object */
    Jaguar jaguar2;                 /* Window motor Jaguar object */
    Compressor compressor;             /* Compressor object */
    DoubleSolenoid armOpen;           /* To open the arms */
    DoubleSolenoid armClose;         /* To close the arms  */
    boolean solenoidState;
    boolean j1On; //Jaguar one boolean; determines whether jag is in use or not
    boolean j2On; //Jaguar two boolean; determines whether jag is in use or not
    
    public Robot() {
        myRobot = new RobotDrive(0, 1);
        myRobot.setExpiration(0.1);
        stick = new Joystick(0);        
        logatt= new Joystick(1);
        jaguar1 = new Jaguar(2);
        jaguar2 = new Jaguar(3);
        compressor = new Compressor (0);
        armOpen = new DoubleSolenoid (0,1);
        solenoidState = false;
        
       //Button button2 = new JoystickButton(logatt, 2);
        
        
       
        
        
    }
    
   
    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
    	
    	solenoidState = true;   // Opens the arms
    	Timer.delay(1.25);     //needs test
    	jaguar2.set(.10);    // Lowers the arms 
    	Timer.delay(1.25);   //needs test
    	jaguar2.set(0);    //Stops the arms 
    	Timer.delay(1.25); //needs test 
    	
    	solenoidState = false;     // Closes the arms 
    	jaguar1.set(-.05);      // Raises arms
    	Timer.delay(1.25);       //needs test
    	jaguar1.set(0.0);     // Pulley stops 
    	
    	
    	
    	// Remember to write the turn code
    	
        myRobot.setSafetyEnabled(false);
        myRobot.drive(0.5, 0.0); // Change 0.0 to -0.5	// drive forwards half speed
        Timer.delay(5.0);		//    for 2 seconds
        myRobot.drive(0.0, 0.0); // stop robot
        Timer.delay(5.0);
        
        
  
    }
    
    
    

    /** `
     * Runs the motors with arcade steering.
     */
    
    
    
    public void operatorCompress(){
    
    while(true){
    compressor.setClosedLoopControl(true); // Starts the compressor 
    }
    	
    
    }
    
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        
        operatorCompress();
        
        
        while (isOperatorControl() && isEnabled()) {
            myRobot.tankDrive(-stick.getRawAxis(1), -stick.getRawAxis(5)); // Drive with tankDrive with Xbox Controller
            
            Timer.delay(0.005);		// wait for a motor update time
         
            	  
            if (logatt.getRawButton(3))
            {
            	jaguar1.set(speed); // Arm up
            	
            	//arm port 4 wheel port 2  maybe port 3 
            }
            if (logatt.getRawButton(2) && j1On == true)
             {
            	
            	jaguar1.set(.10); //Arm Down 
                                    }
             else
             {
            	 
            	 
            	 jaguar1.set(speedZero);
                                   }
           
           
            
            if(logatt.getRawButton(4))
            {
            	solenoidState = true;
                                       }
            
            else if (logatt.getRawButton(5))
            {
            	solenoidState = false;  
            
                                     }
            
           
            if (logatt.getRawButton(10))
            {
            	
            	compressor.setClosedLoopControl(false); // Stops the compressor 
                                                        }
          
            if (solenoidState == true)
            {
            	armOpen.set(DoubleSolenoid.Value.kForward); // Opens the arms 
                                                         }
            
            else if (solenoidState == false)
            {
            	armOpen.set(DoubleSolenoid.Value.kReverse); // Closes the arms 
                                                              }
            
        }
        
    }

   

	/**
     * Runs during test mode
     */
    public void test() {
    	
    	
    }
    
    }
    

