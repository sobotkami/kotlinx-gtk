package nativex.gtk

import gtk.GtkSnapshot
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Snapshot

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Snapshot(val snapshotPointer: CPointer<GtkSnapshot>) : Snapshot(snapshotPointer.reinterpret()) {
}