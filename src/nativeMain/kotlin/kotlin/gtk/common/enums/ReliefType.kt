package kotlin.gtk.common.enums

import gtk.GtkOrientation
import gtk.GtkReliefStyle

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ReliefType(val key: Int, internal val gtk: GtkReliefStyle) {
	NORMAL(0, GtkReliefStyle.GTK_RELIEF_NORMAL),
	NON(1, GtkReliefStyle.GTK_RELIEF_NONE);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkReliefStyle) =
			values().find { it.gtk == gtk }
	}
}