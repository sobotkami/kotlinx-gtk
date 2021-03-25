package nativex.gtk.common.enums

import gtk.GtkReliefStyle
import gtk.GtkReliefStyle.GTK_RELIEF_NONE
import gtk.GtkReliefStyle.GTK_RELIEF_NORMAL

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ReliefStyle(val key: Int, internal val gtk: GtkReliefStyle) {
	NORMAL(0, GTK_RELIEF_NORMAL),
	NON(1, GTK_RELIEF_NONE);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkReliefStyle) =
			values().find { it.gtk == gtk }
	}
}