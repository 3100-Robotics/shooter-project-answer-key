package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public interface KickerConstants {
  // Step A.2
  int kickerMotorCanID = -1;
  int beamBreakPort = -1;

  // Step Z.2
  int peakCurrentLimit = 40;
  int peakCurrentDuration = 100;
  int continuousCurrentLimit = 40;

  InvertType motorInverted = InvertType.InvertMotorOutput;
  NeutralMode neutralMode = NeutralMode.Coast;
}
