package nativex.gobject

import glib.gpointer
import gobject.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped

/**
 * kotlinx-gtk
 * 03 / 06 / 2021
 */
class KGBinding(
	val pointer: CPointer<GBinding>
) {

	fun bind(sourceProperty: String?, target: gpointer, targetProperty: String?, flags: Flags) {
		memScoped {
			g_object_bind_property(pointer, sourceProperty, target, targetProperty, flags.glib)
		}
	}

	enum class Flags(val key: Int, val glib: GBindingFlags) {
		DEFAULT(0, G_BINDING_DEFAULT),
		BIDIRECTIONAL(1, G_BINDING_BIDIRECTIONAL),
		SYNC_CREATE(2, G_BINDING_SYNC_CREATE),
		INVERT_BOOLEAN(3, G_BINDING_INVERT_BOOLEAN)
		;

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(glib: GBindingFlags) = values().find { it.glib == glib }
		}
	}

	companion object {
		fun CPointer<GBinding>?.wrap() =
			this?.wrap()

		fun CPointer<GBinding>.wrap() =
			KGBinding(this)
	}

}