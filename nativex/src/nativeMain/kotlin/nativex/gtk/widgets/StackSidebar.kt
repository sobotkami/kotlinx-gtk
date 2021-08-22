package nativex.gtk.widgets

import gtk.GtkStackSidebar
import gtk.gtk_stack_sidebar_get_stack
import gtk.gtk_stack_sidebar_new
import gtk.gtk_stack_sidebar_set_stack
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSidebar.html">GtkStackSiderbar</a>
 */
class StackSidebar(
	 val stackSidebarPointer: CPointer<GtkStackSidebar>
) : Widget(stackSidebarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSidebar.html#gtk-stack-sidebar-new">gtk_stack_sidebar_new</a>
	 */
	constructor() : this(gtk_stack_sidebar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSidebar.html#gtk-stack-sidebar-get-stack">gtk_stack_sidebar_get_stack</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStackSidebar.html#gtk-stack-sidebar-set-stack">gtk_stack_sidebar_set_stack</a>
	 */
	var stack: Stack?
		get() = gtk_stack_sidebar_get_stack(stackSidebarPointer)?.let { Stack(it) }
		set(value) = gtk_stack_sidebar_set_stack(
			stackSidebarPointer,
			value?.stackPointer
		)
}