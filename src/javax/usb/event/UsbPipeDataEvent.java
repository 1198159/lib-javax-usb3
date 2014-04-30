package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation. All
 * Rights Reserved.
 * <p>
 * This software is provided and licensed under the terms and conditions of the
 * Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */
import java.util.Arrays;
import javax.usb.*;

/**
 * Indicates data was successfully transferred over the UsbPipe.
 * <p>
 * This event will be fired to all listeners for all data that is transferred
 * over the pipe.
 * <p>
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public class UsbPipeDataEvent extends UsbPipeEvent {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * <p>
   * This should only be used if there is no UsbIrp associated with this event.
   * <p>
   * @param source The UsbPipe.
   * @param d      The data.
   * @param aL     The actual length of data transferred.
   */
  public UsbPipeDataEvent(UsbPipe source, byte[] d, int aL) {
    super(source);
    data = d != null ? Arrays.copyOf(d, d.length) : null;
    actualLength = aL;
  }

  /**
   * Constructor.
   * <p>
   * @param source The UsbPipe.
   * @param uI     The UsbIrp.
   */
  public UsbPipeDataEvent(UsbPipe source, UsbIrp uI) {
    super(source, uI);
  }

  /**
   * Get the data.
   * <p>
   * If there is an associated UsbIrp, this returns a new byte[] containing only
   * the actual transferred data. If there is no associated UsbIrp, this returns
   * the actual data buffer used.
   * <p>
   * @return The transferred data.
   */
  public byte[] getData() {
    if (hasUsbIrp()) {
      byte[] newData = new byte[getUsbIrp().getActualLength()];
      System.arraycopy(getUsbIrp().getData(), getUsbIrp().getOffset(), newData, 0, newData.length);
      return newData;
    } else {
      return data != null ? Arrays.copyOf(data, data.length) : null;
    }
  }

  /**
   * Get the actual length.
   * <p>
   * @return The actual amount of transferred data.
   */
  public int getActualLength() {
    if (hasUsbIrp()) {
      return getUsbIrp().getActualLength();
    } else {
      return actualLength;
    }
  }

  private byte[] data = null;
  private int actualLength = 0;

}
