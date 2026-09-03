package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public interface KickerConstants {
  int kickerMotorCanID = 40;
  int beamBreakPort = 0;

  int peakCurrentLimit = 40;
  int peakCurrentDuration = 100;
  int continuousCurrentLimit = 40;

  InvertType motorInverted = InvertType.InvertMotorOutput;
  NeutralMode neutralMode = NeutralMode.Brake;
}
