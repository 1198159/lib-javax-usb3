/*
 * Copyright (C) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved. Provided and licensed under the terms and
 * conditions of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 *
 * Copyright (C) 2014 Key Bridge LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package javax.usb.event;

import java.util.EventObject;
import javax.usb.IUsbDevice;
import javax.usb.IUsbServices;

/**
 * Class for a USB services event.
 *
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @author Jesse Caulfield
 */
public class UsbServicesEvent extends EventObject {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   *
   * @param source The source IUsbServices.
   * @param device The IUsbDevice involved in the event.
   */
  public UsbServicesEvent(IUsbServices source, IUsbDevice device) {
    super(source);
    usbDevice = device;
  }

  /**
   * Get the IUsbServices.
   *
   * @return The associated IUsbServices.
   */
  public IUsbServices getUsbServices() {
    return (IUsbServices) getSource();
  }

  /**
   * Get the IUsbDevice.
   *
   * @return The associated IUsbDevice.
   */
  public IUsbDevice getUsbDevice() {
    return usbDevice;
  }

  private transient IUsbDevice usbDevice = null;
}
