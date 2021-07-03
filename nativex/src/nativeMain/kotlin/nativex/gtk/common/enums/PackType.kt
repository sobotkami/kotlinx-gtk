package nativex.gtk.common.enums

import gtk.GtkPackType
import gtk.GtkPackType.GTK_PACK_END
import gtk.GtkPackType.GTK_PACK_START

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class PackType(val key: Int,  val gtk: GtkPackType) {
	START(0, GTK_PACK_START),
	END(1, GTK_PACK_END);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkPackType) =
			values().find { it.gtk == gtk }
	}
}