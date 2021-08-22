package nativex.gtk.common.enums

import gtk.GTK_SIZE_REQUEST_CONSTANT_SIZE
import gtk.GTK_SIZE_REQUEST_HEIGHT_FOR_WIDTH
import gtk.GTK_SIZE_REQUEST_WIDTH_FOR_HEIGHT
import gtk.GtkSizeRequestMode

/**
 * kotlinx-gtk
 *
 * 01 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/enum.SizeRequestMode.html">GtkSizeRequestMode</a>
 */
enum class SizeRequestMode(val gtk: GtkSizeRequestMode) {
	/**
	 * Prefer height-for-width geometry management.
	 */
	HEIGHT_FOR_WIDTH(GTK_SIZE_REQUEST_HEIGHT_FOR_WIDTH),

	/**
	 * Prefer width-for-height geometry management.
	 */
	WIDTH_FOR_HEIGHT(GTK_SIZE_REQUEST_WIDTH_FOR_HEIGHT),

	/**
	 * Don’t trade height-for-width or width-for-height.
	 */
	CONSTANT_SIZE(GTK_SIZE_REQUEST_CONSTANT_SIZE);

	companion object {
		inline fun valueOf(gtk: GtkSizeRequestMode) = values().find { it.gtk == gtk }
	}
}