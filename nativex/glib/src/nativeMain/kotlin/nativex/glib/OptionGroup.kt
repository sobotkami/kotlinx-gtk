package nativex.glib

import glib.GOptionGroup
import glib.g_option_group_new
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 04 / 2021
 * TODO
 */
class OptionGroup(
	val optionPointer: CPointer<GOptionGroup>
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