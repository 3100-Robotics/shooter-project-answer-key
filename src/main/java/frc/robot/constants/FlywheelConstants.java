package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public interface FlywheelConstants {
  int flywheelMotorCanID = 50;

  int peakCurrentLimit = 40;
  int peakCurrentDuration = 100;
  int continuousCurrentLimit = 40;

  InvertType motorInverted = InvertType.InvertMotorOutput;
  NeutralMode neutralMode = NeutralMode.Coast;
}
