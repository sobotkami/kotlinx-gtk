package nativex.gtk.common.enums

import gtk.GCallback
import gtk.GtkPositionType
import gtk.GtkPositionType.*
import gtk.gpointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class PositionType(val key: Int,  val gtk: GtkPositionType) {
	LEFT(0, GTK_POS_LEFT),
	RIGHT(1, GTK_POS_RIGHT),
	TOP(2, GTK_POS_TOP),
	BOTTOM(3, GTK_POS_BOTTOM);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkPositionType) =
			values().find { it.gtk == gtk }

		/** Generic callback for signals */
		 val staticPositionTypeCallback: GCallback =
			staticCFunction { _: gpointer, pos: GtkPositionType, data: gpointer? ->
				data?.asStableRef<(PositionType) -> Unit>()?.get()?.invoke(valueOf(pos)!!)
				Unit
			}.reinterpret()
	}
}