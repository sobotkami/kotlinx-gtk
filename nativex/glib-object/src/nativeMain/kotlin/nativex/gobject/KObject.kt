package nativex.gobject

import glib.GDestroyNotify
import gobject.GBinding
import gobject.GObject
import gobject.g_object_set_property
import gobject.g_object_unref
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
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

	fun unref() {
		g_object_unref(pointer)
	}


	@Target(AnnotationTarget.VALUE_PARAMETER)
	annotation class Property


	companion object {
		val staticUnrefFunction: GDestroyNotify = staticCFunction { data ->
			g_object_unref(data)
			Unit
		}

		fun CPointer<GObject>?.wrap() =
			this?.wrap()

		fun CPointer<GObject>.wrap() =
			KObject(this)
	}

	fun asKGBinding(): KGBinding? =
		pointer.reinterpretOrNull<GBinding>().wrap()
}