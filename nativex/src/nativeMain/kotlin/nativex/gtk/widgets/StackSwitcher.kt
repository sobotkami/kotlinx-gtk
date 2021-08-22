package nativex.gtk.widgets

import gtk.GtkStackSwitcher
import gtk.gtk_stack_switcher_get_stack
import gtk.gtk_stack_switcher_new
import gtk.gtk_stack_switcher_set_stack
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Stack
import nativex.gtk.widgets.Stack.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 06 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSwitcher.html">GtkStackSwitcher</a>
 */
class StackSwitcher(val stackSwitcherPointer: CPointer<GtkStackSwitcher>) : Widget(stackSwitcherPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSwitcher.html#gtk-stack-switcher-new">
	 *     gtk_stack_switcher_new</a>
	 */
	constructor() : this(gtk_stack_switcher_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSwitcher.html#gtk-stack-switcher-get-stack">
	 *     gtk_stack_switcher_get_stack</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSwitcher.html#gtk-stack-switcher-set-stack">
	 *     gtk_stack_switcher_set_stack</a>
	 */
	var stack: Stack?
		get() = gtk_stack_switcher_get_stack(stackSwitcherPointer).wrap()
		set(value) = gtk_stack_switcher_set_stack(stackSwitcherPointer, value?.stackPointer)
}