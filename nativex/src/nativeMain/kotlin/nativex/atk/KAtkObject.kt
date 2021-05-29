package nativex.atk

import gtk.AtkObject
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject
import nativex.gtk.widgets.Widget

/**
 * @see <a href="https://gtk.developpez.com/doc/en/atk/AtkObject.html">
 *     AtkObject</a>
 */
class KAtkObject internal constructor(
	internal val atkObjectPointer: CPointer<AtkObject>
) : KObject(atkObjectPointer.reinterpret()) {
	companion object {
		internal inline fun CPointer<AtkObject>?.wrap() =
			this?.let { KAtkObject(it) }

		internal inline fun CPointer<AtkObject>.wrap() =
			KAtkObject(this)
	}

}