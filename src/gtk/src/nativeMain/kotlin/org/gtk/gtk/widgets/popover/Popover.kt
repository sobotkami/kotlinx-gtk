package org.gtk.gtk.widgets.popover

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gdk.Rectangle
import org.gtk.glib.bool
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.common.enums.PositionType
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html">GtkPopover</a>
 */
open class Popover(
	val popoverPointer: CPointer<GtkPopover>
) : Widget(popoverPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-new">gtk_popover_new</a>
	 */
	constructor() : this(gtk_popover_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-popup">gtk_popover_popup</a>
	 */
	fun popup() {
		gtk_popover_popup(popoverPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-popdown">
	 *     gtk_popover_popdown</a>
	 */
	fun popdown() {
		gtk_popover_popdown(popoverPointer)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-pointing-to">
	 *     gtk_popover_set_pointing_to</a>
	 */
	fun setPointingTo(rectangle: Rectangle) {
		gtk_popover_set_pointing_to(popoverPointer, rectangle.rectanglePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-pointing-to">
	 *     gtk_popover_get_pointing_to</a>
	 */
	fun getPointingTo(rectangle: Rectangle): Boolean =
		gtk_popover_get_pointing_to(
			popoverPointer,
			rectangle.rectanglePointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-position">
	 *     gtk_popover_get_position</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-position">
	 *     gtk_popover_set_position</a>
	 */
	var positionType: PositionType
		set(value) = gtk_popover_set_position(popoverPointer, value.gtk)
		get() = PositionType.valueOf(
			gtk_popover_get_position(
				popoverPointer
			)
		)!!


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-default-widget">
	 *     gtk_popover_get_default_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-default-widget">
	 *     gtk_popover_set_default_widget</a>
	 */
	fun setDefaultWidget(value: Widget?) =
		gtk_popover_set_default_widget(popoverPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopoverMenu.html">GtkPopoverMenu</a>
	 */
	class Menu(
		val popoverMenuPointer: CPointer<GtkPopoverMenu>
	) : Popover(popoverMenuPointer.reinterpret()) {
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#GtkPopover-closed">closed</a>
	 */
	fun addOnClosedCallback(action: () -> Unit) =
		addSignalCallback(Signals.CLOSED,action)

	companion object {
		inline fun CPointer<GtkPopover>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkPopover>.wrap() =
			Popover(this)
	}
}