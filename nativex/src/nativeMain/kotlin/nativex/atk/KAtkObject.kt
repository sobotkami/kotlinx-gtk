package nativex.atk

import gtk.AtkObject
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * @see <a href="https://gtk.developpez.com/doc/en/atk/AtkObject.html">
 *     AtkObject</a>
 */
class KAtkObject(val atkObjectPointer: CPointer<AtkObject>) : KObject(atkObjectPointer.reinterpret()) {
	companion object {
		 inline fun CPointer<AtkObject>?.wrap() =
			this?.let { KAtkObject(it) }

		 inline fun CPointer<AtkObject>.wrap() =
			KAtkObject(this)
	}
}