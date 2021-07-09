package nativex.gtk

import gtk.GtkAccelGroup
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * kotlinx-gtk
 *
 * 09 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class AccelGroup(val accelGroupPointer: CPointer<GtkAccelGroup>) : KObject(accelGroupPointer.reinterpret()) {
}