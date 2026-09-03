package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Kicker;

public class BallShooterCommands {
  public static Command intakeCommand(Kicker kicker) {
    return new FunctionalCommand(
            () -> {},
            () -> kicker.setDuty(0.5),
            inturupted -> kicker.stop(),
            kicker.ballPresent,
            kicker)
        .withName("intakeCommand"); // As the name suggests, `.withName` gives the command a name in things like the dashboard
  }

  // spotless: off
  public static Command shootCommand(Kicker kicker, Flywheel flywheel) {
    /* `Commands.race` (as the name suggests), races two other commands. This is useful here because we have a 
        first action (running the flywheel) happening for the entire duration of a second action (waiting,
        indexing, and then seeing if the ball has left), where we want the second action to be the one which "knows" 
        when the whole command is done. It works because line A will run until inturupted by anything, 
        it wont end on its own.
    */ 
    return Commands.race(
            Commands.runEnd(() -> flywheel.setDuty(1), flywheel::stop, flywheel), // <-- Line A
            Commands.sequence(
                Commands.waitSeconds(5),
                Commands.race(
                    Commands.runEnd(() -> kicker.setDuty(1), kicker::stop, kicker),
                    Commands.waitUntil(kicker.ballPresent.negate())
                )
            )
        ).withName("shootCommand");
  }
  // spotless: on

  /**
   * Returns a command which stops allsubsystems from doing anything
   * 
   * @param kicker
   * @param flywheel
   * @return The command
   */
  public static Command stopAllActions(Kicker kicker, Flywheel flywheel) {
    return Commands.run(flywheel::stop, flywheel).alongWith(Commands.run(kicker::stop, kicker));
  }
}
