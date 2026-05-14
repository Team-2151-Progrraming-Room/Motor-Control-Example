// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.MotorArrangementValue;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTestingSubsystem extends SubsystemBase {
  private final TalonFX TestMotor = new TalonFX(1);
  private final TalonFXConfiguration TestMotorConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs CurrentLimit = new CurrentLimitsConfigs();
  /** Creates a new ExampleSubsystem. */
  public MotorTestingSubsystem() {
    TestMotor.stopMotor();

    CurrentLimit.withSupplyCurrentLimit(40);

    TestMotorConfig.withCurrentLimits(CurrentLimit);

    TestMotorConfig.Slot0.kS = 0; // Add 0.1 V output to overcome static friction
    TestMotorConfig.Slot0.kV = 0.13; // A velocity target of 1 rps results in 0.13 V output
    TestMotorConfig.Slot0.kP = 0.13; // An error of 1 rps results in 0.13 V output
    TestMotorConfig.Slot0.kI = 0; // no output for integrated error
    TestMotorConfig.Slot0.kD = 0; // no output for error derivative

    TestMotor.getConfigurator().apply(TestMotorConfig);
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command runMotorCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          final VelocityVoltage m_request = new VelocityVoltage(0).withSlot(0);
          TestMotor.setControl(m_request.withVelocity(5).withFeedForward(0.2));
        });
  }

  public Command stopMotor() {
    return runOnce(
        () -> {
          TestMotor.stopMotor();
        });
  }
  public Command jackshit() {
    return runOnce(
        () -> {
          System.out.println("jackshit");
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
