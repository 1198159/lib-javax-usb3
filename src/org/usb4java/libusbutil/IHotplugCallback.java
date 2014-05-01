/*
 * Copyright 2013 Luca Longinotti <l@longi.li>
 * See LICENSE.md for licensing information.
 */
package org.usb4java.libusbutil;

import org.usb4java.Context;
import org.usb4java.Device;

/**
 * Hotplug callback interface.
 * <p>
 * When requesting hotplug event notifications, you pass a callback of this
 * type.
 * <p>
 * @author Luca Longinotti (l@longi.li)
 */
public interface IHotplugCallback {

  /**
   * Process a hotplug event.
   * <p>
   * This callback may be called by an internal event thread and as such it is
   * recommended the callback do minimal processing before returning.
   * <p>
   * libusb will call this function later, when a matching event had happened on
   * a matching device.
   * <p>
   * It is safe to call either null null null null null null null   {@link LibUsb#hotplugRegisterCallback(Context, int, int, int, int, int,
     * HotplugCallback, Object, HotplugCallbackHandle)} or
   * {@link LibUsb#hotplugDeregisterCallback(Context, HotplugCallbackHandle)}
   * from within a callback.
   * <p>
   * @param context  Context of this notification.
   * @param device   Device this event occurred on.
   * @param event    Event that occurred.
   * @param userData user data provided when this callback was registered
   * @return Whether this callback is finished processing events. Returning 1
   *         will cause this callback to be deregistered.
   */
  public int processEvent(Context context, Device device, int event, Object userData);
}
