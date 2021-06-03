package nativex.glib

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 03 / 06 / 2021
 */
class KGBinding internal constructor(
	internal val pointer: CPointer<GBinding>
) {

	fun bind(sourceProperty: String?, target: KObject, targetProperty: String?, flags: Flags) {
		memScoped {
			g_object_bind_property(pointer, sourceProperty, target.pointer, targetProperty, flags.glib)
		}
	}

	enum class Flags(val key: Int, internal val glib: GBindingFlags) {
		DEFAULT(0, G_BINDING_DEFAULT),
		BIDIRECTIONAL(1, G_BINDING_BIDIRECTIONAL),
		SYNC_CREATE(2, G_BINDING_SYNC_CREATE),
		INVERT_BOOLEAN(3, G_BINDING_INVERT_BOOLEAN)
		;

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			internal fun valueOf(glib: GBindingFlags) = values().find { it.glib == glib }
		}
	}

	companion object {
		internal fun CPointer<GBinding>?.wrap() =
			this?.wrap()

		internal fun CPointer<GBinding>.wrap() =
			KGBinding(this)
	}

}