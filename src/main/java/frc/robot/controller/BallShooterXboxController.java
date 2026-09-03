package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class BallShooterXboxController implements BallShooterController {
  private final CommandXboxController controller;

  public BallShooterXboxController(int port) {
    controller = new CommandXboxController(port);
  }

  @Override
  public Trigger shootButton() {
    return controller.a();
  }

  @Override
  public Trigger intakeButton() {
    return controller.b();
  }

  @Override
  public Trigger stopButton() {
    return controller.x();
  }
}
