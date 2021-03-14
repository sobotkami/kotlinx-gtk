package kotlin.gtk.widgets.range

import gtk.GtkRange
import gtk.GtkSensitivityType
import gtk.GtkSensitivityType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
open class Range internal constructor(
	internal val rangePointer: CPointer<GtkRange>
) : Widget(rangePointer.reinterpret()) {

	enum class SensitivityType(
		val key: Int,
		internal val gtk: GtkSensitivityType
	) {
		AUTO(0, GTK_SENSITIVITY_AUTO),
		ON(1, GTK_SENSITIVITY_ON),
		OFF(2, GTK_SENSITIVITY_OFF);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkSensitivityType) =
				values().find { it.gtk == gtk }
		}
	}
}