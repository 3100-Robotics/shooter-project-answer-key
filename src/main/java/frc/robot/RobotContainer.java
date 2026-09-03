// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.sbdc.loggerhead.logging.LogMode;
import com.sbdc.loggerhead.logging.Loggerhead;
import com.sbdc.loggerhead.logging.compoundlogger.LogSubsystemCommands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.BallShooterCommands;
import frc.robot.constants.GeneralConstants;
import frc.robot.controller.BallShooterController;
import frc.robot.controller.BallShooterPS4Controller;
import frc.robot.controller.BallShooterXboxController;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Kicker;

public class RobotContainer {
  private final Flywheel flywheel = new Flywheel();
  private final Kicker kicker = new Kicker();

  private final BallShooterController m_driverController;

  public RobotContainer() {
    if (GeneralConstants.usePlaystationController) {
      m_driverController = new BallShooterPS4Controller(0);
    } else {
      m_driverController = new BallShooterXboxController(0);
    }

    // Configure the trigger bindings
    configureBindings();
    Loggerhead.getInstance().getConfigurator().setConfigureCallback(this::configureLogging);
    Loggerhead.getInstance().initializeLogging(true);
  }

  public void addPeriodics(Robot robot) {
    robot.addPeriodic(Loggerhead.getInstance()::update, 0.02);
  }

  public void configureLogging() {
    LogMode logMode = LogMode.NetworkOnly;

    // spotless:off
    Loggerhead.getInstance()
        .getRootTable()
          .getSubTable("Flywheel")
            .addLoggable(flywheel, logMode)
            .addCompoundLogger(new LogSubsystemCommands("Commands", logMode, flywheel))
        .getParent()
          .getSubTable("Kicker")
            .addLoggable(kicker, logMode)
            .addCompoundLogger(new LogSubsystemCommands("Commands", logMode, kicker));
    ;
    // spotless:on
  }

  private void configureBindings() {
    m_driverController.intakeButton().onTrue(BallShooterCommands.intakeCommand(kicker));
    m_driverController.shootButton().onTrue(BallShooterCommands.shootCommand(kicker, flywheel));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return Commands.none();
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
  }
}
