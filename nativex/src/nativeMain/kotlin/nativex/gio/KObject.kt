package nativex.gio

import gtk.GObject
import gtk.g_object_set_property
import gtk.g_object_unref
import kotlinx.cinterop.CPointer
import nativex.glib.KGValue

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class KObject internal constructor(
	internal val pointer: CPointer<GObject>
) {
	fun set(@Property propertyName: String, boolean: Boolean) {
		g_object_set_property(pointer, propertyName, KGValue(boolean).pointer)
	}

	fun unref() {
		g_object_unref(pointer)
	}

	@Target(AnnotationTarget.VALUE_PARAMETER)
	annotation class Property()


	companion object {
		internal fun CPointer<GObject>?.wrap() =
			this?.wrap()

		internal fun CPointer<GObject>.wrap() =
			KObject(this)
	}
}