/*
 * Copyright (C) 2011 Klaus Reimer <k@ailis.de>
 * See readme.md for licensing information.
 */
package javax.usb;

import javax.usb.event.UsbDeviceDataEvent;
import javax.usb.exception.UsbException;

/**
 * A concurrent queue manager for USB I/O Request packets.
 * <p>
 * An IrpQueue contains a thread safe FIFO queue and a threaded
 * processUsbIrpQueueor to handle each IRP that is placed into the queue.
 * <p>
 * Developer note: The default operation of an IrpQueue is to support
 * Asynchronous operation (e.g. processUsbIrpQueue in a separate thread.) To
 * implement synchronous IRP queue handling implement a WAIT lock on the
 * {@link IUsbIrp.isComplete() isComplete} method IUsbIrp.isComplete().
 *
 * @author Klaus Reimer (k@ailis.de)
 * @author Jesse Caulfield
 */
public final class UsbControlIrpQueue extends AUsbIrpQueue<IUsbControlIrp> {

  /**
   * The USB device listener list.
   */
  private final UsbDeviceListener listeners;

  /**
   * Constructor.
   *
   * @param device    The USB device.
   * @param listeners The USB device listener list.
   */
  public UsbControlIrpQueue(final AUsbDevice device, final UsbDeviceListener listeners) {
    super(device);
    this.listeners = listeners;
  }

  /**
   * Processes the IRP.
   *
   * @param irp The IRP to processUsbIrpQueue.
   * @throws UsbException When processUsbIrpQueueing the IRP fails.
   */
  @Override
  protected void processIrp(IUsbControlIrp irp) throws UsbException {
    processControlIrp(irp);
  }

  /**
   * Called after IRP has finished. This can be implemented to send events for
   * example.
   *
   * @param irp The IRP which has been finished.
   */
  @Override
  protected void finishIrp(final IUsbIrp irp) {
    this.listeners.dataEventOccurred(new UsbDeviceDataEvent(this.usbDevice, (IUsbControlIrp) irp));
  }
}
