package nativex.gio

import gtk.GBinding
import gtk.GObject
import gtk.g_object_set_property
import gtk.g_object_unref
import kotlinx.cinterop.CPointer
import nativex.glib.KGBinding
import nativex.glib.KGBinding.Companion.wrap
import nativex.glib.KGValue
import nativex.reinterpretOrNull

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class KObject constructor(val pointer: CPointer<GObject>) {
	fun set(@Property propertyName: String, boolean: Boolean) {
		g_object_set_property(pointer, propertyName, KGValue(boolean).pointer)
	}

	fun unref() {
		g_object_unref(pointer)
	}


	@Target(AnnotationTarget.VALUE_PARAMETER)
	annotation class Property()


	companion object {
		 fun CPointer<GObject>?.wrap() =
			this?.wrap()

		 fun CPointer<GObject>.wrap() =
			KObject(this)
	}

	fun asKGBinding(): KGBinding? =
		pointer.reinterpretOrNull<GBinding>().wrap()
}