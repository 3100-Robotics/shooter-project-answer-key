package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class BallShooterPS4Controller implements BallShooterController {
  private final CommandPS4Controller controller;

  public BallShooterPS4Controller(int port) {
    controller = new CommandPS4Controller(port);
  }

  @Override
  public Trigger shootButton() {
    return controller.triangle();
  }

  @Override
  public Trigger intakeButton() {
    return controller.circle();
  }

  @Override
  public Trigger stopButton() {
    return controller.cross();
  }
}
