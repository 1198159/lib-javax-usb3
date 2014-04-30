/**
 * Original Copyright (c) 1999 - 2001, International Business Machines
 * Corporation. All Rights Reserved. Provided and licensed under the terms and
 * conditions of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 * <p>
 * Modifications and improvements Copyright (c) 2014 Key Bridge Global LLC. All
 * Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package javax.usb;

/**
 * Interface for a control-type USB IRP (I/O Request Packet).
 * <p>
 * This is identical to a UsbIrp, except this also contains the Control-specific
 * setup packet information.
 * <p>
 * Each USB device is required to implement the Default Control Pipe as a
 * message pipe. This pipe is used by the USB System Software. The Default
 * Control Pipe provides access to the USB device’s configuration, status, and
 * control information. A function can, but is not required to, provide
 * endpoints for additional control pipes for its own implementation needs.
 * <p>
 * The USB device framework defines standard, device class, or vendor-specific
 * requests that can be used to manipulate a device’s state. Descriptors are
 * also defined that can be used to contain different information on the device.
 * Control transfers provide the transport mechanism to access device
 * descriptors and make requests of a device to manipulate its behavior.
 * <p>
 * @author Dan Streetman
 */
public interface UsbControlIrp extends UsbIrp {

  /**
   * Get the bmRequestType.
   * <p>
   * @return The bmRequestType.
   */
  public byte bmRequestType();

  /**
   * Get the bRequest.
   * <p>
   * @return The bRequest.
   */
  public byte bRequest();

  /**
   * Get the wValue.
   * <p>
   * @return The wValue.
   */
  public short wValue();

  /**
   * Get the wIndex.
   * <p>
   * @return The wIndex.
   */
  public short wIndex();

}
