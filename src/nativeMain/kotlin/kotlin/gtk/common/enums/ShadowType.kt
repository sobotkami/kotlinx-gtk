package kotlin.gtk.common.enums

import gtk.GtkOrientation
import gtk.GtkShadowType
import gtk.GtkShadowType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ShadowType(val key: Int, internal val gtk: GtkShadowType) {
	NONE(0, GTK_SHADOW_NONE),
	IN(1, GTK_SHADOW_IN),
	OUT(2, GTK_SHADOW_OUT),
	ETCHED_IN(3, GTK_SHADOW_ETCHED_IN),
	ETCHED_OUT(4, GTK_SHADOW_ETCHED_OUT);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkShadowType) =
			values().find { it.gtk == gtk }
	}
}