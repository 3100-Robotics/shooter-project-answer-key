package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.sbdc.loggerhead.logging.LogMode;
import com.sbdc.loggerhead.logging.Loggable;
import com.sbdc.loggerhead.logging.Loggerhead;
import com.sbdc.loggerhead.logging.Table;
import com.sbdc.loggerhead.util.LightSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.DIOSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.constants.KickerConstants;

public class Kicker extends LightSubsystem implements Loggable {
  // Step B.2
  private TalonSRX rawMotor = new WPI_TalonSRX(-1);
  private final DigitalInput beamBreak = new DigitalInput(-1);

  private DIOSim beamBreakSim;

  // Step D.1

  public Kicker() {
    TalonSRXConfiguration motorConfig = new TalonSRXConfiguration();

    rawMotor.configFactoryDefault();

    motorConfig.peakCurrentLimit = KickerConstants.peakCurrentLimit;
    motorConfig.peakCurrentDuration = KickerConstants.peakCurrentDuration;
    motorConfig.continuousCurrentLimit = KickerConstants.continuousCurrentLimit;

    rawMotor.setInverted(KickerConstants.motorInverted);
    rawMotor.setNeutralMode(KickerConstants.neutralMode);

    rawMotor.configAllSettings(motorConfig);

    if (Robot.isSimulation()) {
      SmartDashboard.putBoolean("sim_beam_broken", false);
      beamBreakSim = new DIOSim(beamBreak);
    }
  }

  public void setDuty(double speed) {
    rawMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    rawMotor.set(ControlMode.Disabled, 0);
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {
    beamBreakSim.setValue(SmartDashboard.getBoolean("sim_beam_broken", false));
  }

  public void setupLogging(Table parentTable, LogMode logMode, Loggerhead loggerhead) {
    parentTable.addDoubleLogger("kickerMotorDutyCycle", logMode, rawMotor::getMotorOutputPercent);
  }
}
