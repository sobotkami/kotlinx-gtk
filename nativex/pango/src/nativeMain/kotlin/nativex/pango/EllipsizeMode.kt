package nativex.pango

import pango.PangoEllipsizeMode
import pango.PangoEllipsizeMode.*

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
enum class EllipsizeMode(  val pango: PangoEllipsizeMode) {
	NONE( PANGO_ELLIPSIZE_NONE),
	START( PANGO_ELLIPSIZE_START),
	MIDDLE( PANGO_ELLIPSIZE_MIDDLE),
	END( PANGO_ELLIPSIZE_END);

	companion object {
		fun valueOf(pango: PangoEllipsizeMode) =
			values().find { it.pango == pango }
	}
}