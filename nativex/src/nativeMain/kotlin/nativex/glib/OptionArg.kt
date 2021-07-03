package nativex.glib

import gtk.GOptionArg
import gtk.GOptionArg.*

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
enum class OptionArg(val key: Int,  val gtk: GOptionArg) {
	NONE(0, G_OPTION_ARG_NONE),
	STRING(1, G_OPTION_ARG_STRING),
	INT(2, G_OPTION_ARG_INT),
	CALLBACK(3, G_OPTION_ARG_CALLBACK),
	FILENAME(4, G_OPTION_ARG_FILENAME),
	STRING_ARRAY(5, G_OPTION_ARG_STRING_ARRAY),
	FILENAME_ARRAY(6, G_OPTION_ARG_FILENAME_ARRAY),
	DOUBLE(7, G_OPTION_ARG_DOUBLE),
	INT64(8, G_OPTION_ARG_INT64);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		fun valueOf(gtk: GOptionArg) =
			values().find { it.gtk == gtk }
	}
}