package org.gtk.gtk

import gtk.GtkSnapshot
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Snapshot(val snapshotPointer: CPointer<GtkSnapshot>) : org.gtk.gdk.Snapshot(snapshotPointer.reinterpret()) {
}