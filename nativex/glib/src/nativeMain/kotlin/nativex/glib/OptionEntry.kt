package nativex.glib

import glib.GOptionEntry
import glib.gint
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 04 / 2021
 * TODO
 */
class OptionEntry(
	val struct: CPointer<GOptionEntry>
) {
	var arg: OptionArg
		get() {
			TODO()
		}
		set(value) {}

	//var arg_data: gtk.gpointer?

	var argDescription: String
		get() {
			TODO()
		}
		set(value) {}

	var description: String
		get() {
			TODO()
		}
		set(value) {}
	var flags: gint
		get() {
			TODO()
		}
		set(value) {}

	var longName: String
		get() {
			TODO()
		}
		set(value) {}

	var shortName: Char
		get() {
			TODO()
		}
		set(value) {}
}