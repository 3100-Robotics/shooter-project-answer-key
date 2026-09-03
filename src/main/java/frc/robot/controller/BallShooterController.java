package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public interface BallShooterController {
  public Trigger shootButton();

  public Trigger intakeButton();

  public Trigger stopButton();
}
