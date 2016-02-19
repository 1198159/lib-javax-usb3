/*
 * Based on libusb <http://libusb.info/>:
 *
 * Copyright 2001 Johannes Erdfelt <johannes@erdfelt.com>
 * Copyright 2007-2009 Daniel Drake <dsd@gentoo.org>
 * Copyright 2010-2012 Peter Stuge <peter@stuge.se>
 * Copyright 2008-2013 Nathan Hjelm <hjelmn@users.sourceforge.net>
 * Copyright 2009-2013 Pete Batard <pete@akeo.ie>
 * Copyright 2009-2013 Ludovic Rousseau <ludovic.rousseau@gmail.com>
 * Copyright 2010-2012 Michael Plante <michael.plante@gmail.com>
 * Copyright 2011-2013 Hans de Goede <hdegoede@redhat.com>
 * Copyright 2012-2013 Martin Pieuchot <mpi@openbsd.org>
 * Copyright 2012-2013 Toby Gray <toby.gray@realvnc.com>
 * Copyright 2013 Luca Longinotti
 * Copyright 2014-2016 Jesse Caulfield
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.usb4java;

/**
 * Hotplug Callback Handle.
 * <p>
 * Callback handles are generated by {@link LibUsb#hotplugRegisterCallback(
 * Context, int, int, int, int, int, HotplugCallback, Object,
 * HotplugCallbackHandle)} and can be used to deregister callbacks. Callback
 * handles are unique per {@link Context} and it is safe to call
 * {@link LibUsb#hotplugDeregisterCallback(Context, HotplugCallbackHandle)} on
 * an already deregistered callback.
 *
 * @author Luca Longinotti
 * @author Jesse Caulfield
 */
public final class HotplugCallbackHandle {
  // Maps to JNI native class

  /**
   * The hotplug callback handle, it's an integer (int) in C.
   */
  private long hotplugCallbackHandleValue;

  /**
   * Constructs a new hot plug callback handle. Must be passed to
   * {@link LibUsb#hotplugRegisterCallback(Context, int, int, int, int, int, HotplugCallback, Object, HotplugCallbackHandle)}
   * before passing it to any other method.
   */
  public HotplugCallbackHandle() {
    // Empty
  }

  /**
   * Returns the hot plug callback handle value.
   *
   * @return The hot plug callback handle value.
   */
  public long getValue() {
    return this.hotplugCallbackHandleValue;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + (int) (this.hotplugCallbackHandleValue
                                       ^ (this.hotplugCallbackHandleValue >>> 32));
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final HotplugCallbackHandle other = (HotplugCallbackHandle) obj;
    return this.hotplugCallbackHandleValue == other.getValue();
  }

  @Override
  public String toString() {
    return String.format("libusb hotplug callback handle 0x%x",
                         this.hotplugCallbackHandleValue);
  }
}
