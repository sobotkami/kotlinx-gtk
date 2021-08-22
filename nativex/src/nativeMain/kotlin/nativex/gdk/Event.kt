package nativex.gdk

import gtk.GdkEvent
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 03 / 2021
 * TODO
 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-Event-Structures.html#GdkEvent">GdkEvent union</a>
 */
open class Event(val eventPointer: CPointer<GdkEvent>) {
	companion object {

		inline fun CPointer<GdkEvent>?.wrap() =
			this?.let { Event(it) }

		inline fun CPointer<GdkEvent>.wrap() =
			Event(this)
	}
}