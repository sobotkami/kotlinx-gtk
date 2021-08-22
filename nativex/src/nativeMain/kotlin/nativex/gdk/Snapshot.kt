package nativex.gdk

import gtk.GdkSnapshot
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
open class Snapshot(val gSnapShotPointer: CPointer<GdkSnapshot>) : KGObject(gSnapShotPointer.reinterpret()) {
}