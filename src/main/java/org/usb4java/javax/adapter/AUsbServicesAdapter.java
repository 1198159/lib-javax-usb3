/*
 * Copyright (C) 2013 Klaus Reimer <k@ailis.de>
 * See LICENSE.md for licensing information.
 */
package org.usb4java.javax.adapter;

import javax.usb.event.UsbServicesEvent;
import javax.usb.event.IUsbServicesListener;

/**
 * An abstract adapter class for receiving USB service events.
 * <p>
 * The methods in this class are empty. This class exists as convenience for
 * creating listener objects.
 * <p>
 * @author Klaus Reimer (k@ailis.de)
 */
public abstract class AUsbServicesAdapter implements IUsbServicesListener {

  @Override
  public void usbDeviceAttached(final UsbServicesEvent event) {
    // Empty
  }

  @Override
  public void usbDeviceDetached(final UsbServicesEvent event) {
    // Empty
  }
}
