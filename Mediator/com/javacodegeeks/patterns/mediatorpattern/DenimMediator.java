package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>DenimMediator</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class DenimMediator implements MachineMediator {
  private final Machine machine;
  private final Heater heater;
  private final Motor motor;
  private final Sensor sensor;
  private final SoilRemoval soilRemoval;
  private final Valve valve;

  /**
   * Creates a new <code>DenimMediator</code> instance.
   *
   * @param machine a <code>Machine</code> value
   * @param heater a <code>Heater</code> value
   * @param motor a <code>Motor</code> value
   * @param sensor a <code>Sensor</code> value
   * @param soilRemoval a <code>SoilRemoval</code> value
   * @param valve a <code>Valve</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public DenimMediator(
      Machine machine,
      Heater heater,
      Motor motor,
      Sensor sensor,
      SoilRemoval soilRemoval,
      Valve valve) {
    this.machine = machine;
    this.heater = heater;
    this.motor = motor;
    this.sensor = sensor;
    this.soilRemoval = soilRemoval;
    this.valve = valve;
    System.out.println("Setting up for DENIM program");
  }

  @Override
  public void start() {
    machine.start();
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void wash() {
    motor.startMotor();
    motor.rotateDrum(1400);
    System.out.println("Adding detergent");
    soilRemoval.medium();
    System.out.println("Adding softener");
  }

  @Override
  public void open() {
    valve.open();
  }

  @Override
  public void closed() {
    valve.closed();
  }

  @Override
  @SuppressWarnings("PMD.ShortMethodName")
  public void on() {
    heater.on(30);
  }

  @Override
  public void off() {
    heater.off();
  }

  @Override
  public boolean checkTemperature(int temp) {
    return sensor.checkTemperature(temp);
  }
}
