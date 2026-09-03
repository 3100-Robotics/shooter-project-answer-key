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
import frc.robot.constants.FlywheelConstants;

public class Flywheel extends LightSubsystem implements Loggable {
  private TalonSRX rawMotor = new WPI_TalonSRX(FlywheelConstants.flywheelMotorCanID);

  public Flywheel() {
    rawMotor.configFactoryDefault();

    TalonSRXConfiguration motorConfig = new TalonSRXConfiguration();
    motorConfig.peakCurrentLimit = FlywheelConstants.peakCurrentLimit;
    motorConfig.peakCurrentDuration = FlywheelConstants.peakCurrentDuration;
    motorConfig.continuousCurrentLimit = FlywheelConstants.continuousCurrentLimit;

    rawMotor.setInverted(FlywheelConstants.motorInverted);
    rawMotor.setNeutralMode(FlywheelConstants.neutralMode);

    rawMotor.configAllSettings(motorConfig);
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
  public void simulationPeriodic() {}

  public void setupLogging(Table parentTable, LogMode logMode, Loggerhead loggerhead) {
    parentTable.addDoubleLogger("flywheelMotorDutyCycle", logMode, rawMotor::getMotorOutputPercent);
  }
}
