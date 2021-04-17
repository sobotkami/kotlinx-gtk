package nativex.glib

import gtk.GOptionGroup
import gtk.g_option_group_new
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 04 / 2021
 * TODO
 */
class OptionGroup internal constructor(
	internal val optionPointer: CPointer<GOptionGroup>
) {
	constructor(
		name: String,
		description: String,
		helpDescription: String
	) : this(
		g_option_group_new(
			name,
			description,
			helpDescription,
			null,
			null
		)!!
	)

}