package nativex.gobject

import gobject.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.VoidPointer
import nativex.glib.reinterpretOrNull
import nativex.gobject.KGBinding.Companion.wrap

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class KObject constructor(val pointer: CPointer<GObject>) {
	fun set(@Property propertyName: String, boolean: Boolean) {
		g_object_set_property(pointer, propertyName, KGValue(boolean).pointer.reinterpret())
	}

	fun getData(key: String): VoidPointer? =
		g_object_get_data(pointer, key)

	fun unref() {
		g_object_unref(pointer)
	}


	@Target(AnnotationTarget.VALUE_PARAMETER)
	annotation class Property


	companion object {
		fun CPointer<GObject>?.wrap() =
			this?.wrap()

		fun CPointer<GObject>.wrap() =
			KObject(this)
	}

	fun asKGBinding(): KGBinding? =
		pointer.reinterpretOrNull<GBinding>().wrap()
}