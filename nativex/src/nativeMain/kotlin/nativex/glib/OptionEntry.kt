package nativex.glib

import gtk.GOptionEntry
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 04 / 2021
 * TODO
 */
class OptionEntry internal constructor(
	val struct: CPointer<GOptionEntry>
) {
	var arg: OptionArg
		get() {
			TODO()
		}
		set(value) {}

	//var arg_data: gtk.gpointer?

	var arg_description: String
		get() {
			TODO()
		}
		set(value) {}

	var description: String
		get() {
			TODO()
		}
		set(value) {}
	var flags: gtk.gint
		get() {
			TODO()
		}
		set(value) {}

	var long_name: String
		get() {
			TODO()
		}
		set(value) {}

	var short_name: Char
		get() {
			TODO()
		}
		set(value) {}
}