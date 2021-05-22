package nativex.glib

import gtk.*

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
enum class OptionFlags(val key: Int, internal val gtk: GOptionFlags) {
	NONE(0, G_OPTION_FLAG_NONE),
	HIDDEN(1, G_OPTION_FLAG_HIDDEN),
	IN_MAIN(2, G_OPTION_FLAG_IN_MAIN),
	REVERSE(3, G_OPTION_FLAG_REVERSE),
	NO_ARG(4, G_OPTION_FLAG_NO_ARG),
	FILENAME(5, G_OPTION_FLAG_FILENAME),
	OPTIONAL_ARG(6, G_OPTION_FLAG_OPTIONAL_ARG),
	NOALIAS(7, G_OPTION_FLAG_NOALIAS);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		
		fun valueOf(gtk: GOptionFlags) =
			values().find { it.gtk == gtk }
	}
}