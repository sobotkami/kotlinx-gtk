package org.gtk.gtk

import gtk.GtkEntryCompletion
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 03 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class EntryCompletion(val entryCompletionPointer: CPointer<GtkEntryCompletion>) {
	companion object {
		inline fun CPointer<GtkEntryCompletion>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkEntryCompletion>.wrap() =
			EntryCompletion(this)
	}
}