/*
 * Copyright (C) 2011 Klaus Reimer <k@ailis.de>
 * See LICENSE.md for licensing information.
 */
package org.usb4java.javax.descriptors;

import java.util.Objects;
import javax.usb.IUsbConfigurationDescriptor;
import javax.usb.ri.enumerated.EDescriptorType;
import javax.usb.ri.request.ConfigurationAttributes;
import org.usb4java.ConfigDescriptor;

/**
 * 9.6.3 Configuration Descriptor implementation.
 * <p>
 * Devices report their attributes using descriptors. A descriptor is a data
 * structure with a defined format.
 * <p>
 * The configuration descriptor describes information about a specific device
 * configuration. The descriptor contains a bConfigurationValue field with a
 * value that, when used as a parameter to the SetConfiguration() request,
 * causes the device to assume the described configuration.
 * <p>
 * The descriptor describes the number of interfaces provided by the
 * configuration. Each interface may operate independently.
 * <p>
 * For example, an ISDN device might be configured with two interfaces, each
 * providing 64 Kb/s bi-directional channels that have separate data sources or
 * sinks on the host. Another configuration might present the ISDN device as a
 * single interface, bonding the two channels into one 128 Kb/s bi-directional
 * channel.
 * <p>
 * When the host requests the configuration descriptor all related interface and
 * endpoint descriptors are returned.
 * <p>
 * @author Klaus Reimer (k@ailis.de)
 * @author Jesse Caulfield <jesse@caulfield.org>
 */
public final class UsbConfigurationDescriptor extends AUsbDescriptor implements IUsbConfigurationDescriptor {

  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The total length.
   */
  private final short wTotalLength;

  /**
   * The number of interfaces.
   */
  private final byte bNumInterfaces;

  /**
   * The configuration value.
   */
  private final byte bConfigurationValue;

  /**
   * The configuration string descriptor index.
   */
  private final byte iConfiguration;

  /**
   * The attributes.
   */
  private final ConfigurationAttributes bmAttributes;

  /**
   * The maximum power.
   */
  private final byte bMaxPower;

  /**
   * Construct a new UsbConfigurationDescriptor instance.
   * <p>
   * @param wTotalLength        The total length of data returned for this
   *                            configuration. Includes the combined length of
   *                            all descriptors (configuration, interface,
   *                            endpoint, and class- or vendor-specific)
   *                            returned for this configuration.
   * @param bNumInterfaces      The number of interfaces supported by this
   *                            configuration
   * @param bConfigurationValue The configuration value to use as an argument to
   *                            the SetConfiguration() request to select this
   *                            configuration
   * @param iConfiguration      The configuration string descriptor index.
   * @param bmAttributes        The configuration attributes.
   * @param bMaxPower           The maximum power.
   */
  public UsbConfigurationDescriptor(final short wTotalLength,
                                    final byte bNumInterfaces,
                                    final byte bConfigurationValue,
                                    final byte iConfiguration,
                                    final ConfigurationAttributes bmAttributes,
                                    final byte bMaxPower) {
    super(EDescriptorType.CONFIGURATION);
    this.wTotalLength = wTotalLength;
    this.bNumInterfaces = bNumInterfaces;
    this.bConfigurationValue = bConfigurationValue;
    this.iConfiguration = iConfiguration;
    this.bmAttributes = bmAttributes;
    this.bMaxPower = bMaxPower;
  }

  /**
   * Construct a new UsbConfigurationDescriptor instance from a libusb4java JNI
   * ConfigDescriptor instance.
   * <p>
   * @param descriptor The JNI descriptor instance from which to copy the data.
   */
  public UsbConfigurationDescriptor(final ConfigDescriptor descriptor) {
    this(descriptor.wTotalLength(),
         descriptor.bNumInterfaces(),
         descriptor.bConfigurationValue(),
         descriptor.iConfiguration(),
         ConfigurationAttributes.getInstance(descriptor.bmAttributes()),
         descriptor.bMaxPower());
  }

  /**
   * Total length of data returned for this configuration. Includes the combined
   * length of all descriptors (configuration, interface, endpoint, and class-
   * or vendor-specific) returned for this configuration.
   * <p>
   * @return This descriptor's wTotalLength.
   * @see javax.usb.util.UsbUtil#unsignedInt(short) This is unsigned.
   */
  @Override
  public short wTotalLength() {
    return this.wTotalLength;
  }

  /**
   * Get this descriptor's bNumInterfaces.
   * <p>
   * @return This descriptor's bNumInterfaces.
   * @see javax.usb.util.UsbUtil#unsignedInt(byte) This is unsigned.
   */
  @Override
  public byte bNumInterfaces() {
    return this.bNumInterfaces;
  }

  /**
   * Number of interfaces supported by this configuration
   * <p>
   * @return This descriptor's bConfigurationValue.
   * @see javax.usb.util.UsbUtil#unsignedInt(byte) This is unsigned.
   */
  @Override
  public byte bConfigurationValue() {
    return this.bConfigurationValue;
  }

  /**
   * Value to use as an argument to the SetConfiguration() request to select
   * this configuration
   * <p>
   * @return This descriptor's iConfiguration.
   * @see javax.usb.util.UsbUtil#unsignedInt(byte) This is unsigned.
   */
  @Override
  public byte iConfiguration() {
    return this.iConfiguration;
  }

  /**
   * Get the configuration characteristics of the indicated device type.
   * <p>
   * @return This descriptor's bmAttributes.
   * @see javax.usb.util.UsbUtil#unsignedInt(byte) This is unsigned.
   */
  @Override
  public byte bmAttributes() {
    return this.bmAttributes != null ? bmAttributes.asByte() : null;
  }

  /**
   * Maximum power consumption of the USB device from the bus in this specific
   * configuration when the device is fully operational. Expressed in 2 mA units
   * (i.e., 50 = 100 mA).
   * <p>
   * Note: A device configuration reports whether the configuration is
   * bus-powered or self- powered. Device status reports whether the device is
   * currently self-powered. If a device is disconnected from its external power
   * source, it updates device status to indicate that it is no longer
   * self-powered.
   * <p>
   * A device may not increase its power draw from the bus, when it loses its
   * external power source, beyond the amount reported by its configuration.
   * <p>
   * If a device can continue to operate when disconnected from its external
   * power source, it continues to do so. If the device cannot continue to
   * operate, it fails operations it can no longer support. The USB System
   * Software may determine the cause of the failure by checking the status and
   * noting the loss of the device’s power source.
   * <p>
   * @return This descriptor's bMaxPower in units of 2mA.
   * @see javax.usb.util.UsbUtil#unsignedInt(byte) This is unsigned.
   */
  @Override
  public byte bMaxPower() {
    return this.bMaxPower;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash += 73 * hash + super.hashCode();
    hash += 73 * hash + this.wTotalLength;
    hash += 73 * hash + this.bNumInterfaces;
    hash += 73 * hash + this.bConfigurationValue;
    hash += 73 * hash + this.iConfiguration;
    hash += 73 * hash + Objects.hash(this.bmAttributes);
    hash += 73 * hash + this.bMaxPower;
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
    return this.hashCode() == obj.hashCode();
  }

  @Override
  public String toString() {
    return String.format(
      "USB Configuration Descriptor:%n"
      + "  bLength %18d%n"
      + "  bDescriptorType %10d%n"
      + "  wTotalLength %13d%n"
      + "  bNumInterfaces %11d%n"
      + "  bConfigurationValue %6d%n"
      + "  iConfiguration %11d%n"
      + "  bmAttributes %13s%n"
      + "    %s%n"
      + "%s"
      + "  bMaxPower %16smA%n",
      bLength() & 0xff,
      bDescriptorType() & 0xff,
      wTotalLength() & 0xffff,
      bNumInterfaces() & 0xff,
      bConfigurationValue() & 0xff,
      iConfiguration() & 0xff,
      String.format("0x%02x", bmAttributes() & 0xff),
      ((bmAttributes() & 64) == 0) ? "(Bus Powered)"
      : "Self Powered",
      ((bmAttributes() & 32) == 0) ? ""
      : String.format("    Remote Wakeup%n"),
      (bMaxPower() & 0xff) * 2);
  }
}
