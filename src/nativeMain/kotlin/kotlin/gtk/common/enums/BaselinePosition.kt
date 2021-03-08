package kotlin.gtk.common.enums

import gtk.GtkBaselinePosition
import gtk.GtkBaselinePosition.GTK_BASELINE_POSITION_CENTER
import gtk.GtkBaselinePosition.GTK_BASELINE_POSITION_TOP

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class BaselinePosition(
	val key: Int,
	internal val gtk: GtkBaselinePosition
) {
	TOP(0, GTK_BASELINE_POSITION_TOP),
	CENTER(1, GTK_BASELINE_POSITION_CENTER),
	BOTTOM(2, GTK_BASELINE_POSITION_CENTER);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkBaselinePosition) =
			values().find { it.gtk == gtk }
	}
}