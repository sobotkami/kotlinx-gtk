package nativex.pango

import pango.PangoEllipsizeMode
import pango.PangoEllipsizeMode.*

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
enum class EllipsizeMode(val key: Int,  val pango: PangoEllipsizeMode) {
	NONE(0, PANGO_ELLIPSIZE_NONE),
	START(1, PANGO_ELLIPSIZE_START),
	MIDDLE(2, PANGO_ELLIPSIZE_MIDDLE),
	END(3, PANGO_ELLIPSIZE_END);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		fun valueOf(pango: PangoEllipsizeMode) =
			values().find { it.pango == pango }
	}
}