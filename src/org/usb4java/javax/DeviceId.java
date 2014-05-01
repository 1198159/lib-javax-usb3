/*
 * Copyright (C) 2013 Klaus Reimer <k@ailis.de>
 * See LICENSE.md for licensing information.
 */
package org.usb4java.javax;

import java.io.Serializable;
import java.util.Objects;
import org.usb4java.javax.descriptors.SimpleUsbDeviceDescriptor;

/**
 * Unique USB device ID.
 * <p>
 * @author Klaus Reimer (k@ailis.de)
 */
public final class DeviceId implements Serializable {

  /**
   * The serial versionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The bus number.
   */
  private final int busNumber;

  /**
   * The device address.
   */
  private final int deviceAddress;

  /**
   * The port this device is connected to. 0 if unknown.
   */
  private final int portNumber;

  /**
   * The device descriptor.
   */
  private final SimpleUsbDeviceDescriptor deviceDescriptor;

  /**
   * Constructs a new device id.
   * <p>
   * @param busNumber        The number of the bus the device is connected to.
   * @param deviceAddress    The address of the device.
   * @param portNumber       The number of the port the device is connected to.
   *                         0 if unknown.
   * @param deviceDescriptor The device descriptor. Must not be null.
   */
  public DeviceId(final int busNumber, final int deviceAddress,
                  final int portNumber, final SimpleUsbDeviceDescriptor deviceDescriptor) {
    if (deviceDescriptor == null) {
      throw new IllegalArgumentException("deviceDescriptor must be set");
    }
    this.busNumber = busNumber;
    this.portNumber = portNumber;
    this.deviceAddress = deviceAddress;
    this.deviceDescriptor = deviceDescriptor;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash += 67 * hash + this.busNumber;
    hash += 67 * hash + this.deviceAddress;
    hash += 67 * hash + this.portNumber;
    hash += 67 * hash + Objects.hashCode(this.deviceDescriptor);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DeviceId other = (DeviceId) obj;
    if (this.busNumber != other.busNumber) {
      return false;
    }
    if (this.deviceAddress != other.deviceAddress) {
      return false;
    }
    if (this.portNumber != other.portNumber) {
      return false;
    }
    return Objects.equals(this.deviceDescriptor, other.deviceDescriptor);
  }

  /**
   * Returns the bus number.
   * <p>
   * @return The bus number.
   */
  public int getBusNumber() {
    return this.busNumber;
  }

  /**
   * Returns the device address.
   * <p>
   * @return The device address.
   */
  public int getDeviceAddress() {
    return this.deviceAddress;
  }

  /**
   * Returns the number of the port the device is connected to.
   * <p>
   * @return The port number or 0 if unknown.
   */
  public int getPortNumber() {
    return this.portNumber;
  }

  /**
   * Returns the device descriptor.
   * <p>
   * @return The device descriptor. Never null.
   */
  public SimpleUsbDeviceDescriptor getDeviceDescriptor() {
    return this.deviceDescriptor;
  }

  @Override
  public String toString() {
    return String.format("Bus %03d Device %03d: ID %04x:%04x",
                         this.busNumber, this.deviceAddress,
                         this.deviceDescriptor.idVendor(),
                         this.deviceDescriptor.idProduct());
  }

  /**
   * Checks if the specified two device IDs are equal. They are also equal if
   * they are both null.
   * <p>
   * @param a The first device ID.
   * @param b The second device ID.
   * @return True if the device IDs are equal, false if not.
   */
  public static boolean equals(final DeviceId a, final DeviceId b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      return false;
    }
    return a.equals(b);
  }
}
