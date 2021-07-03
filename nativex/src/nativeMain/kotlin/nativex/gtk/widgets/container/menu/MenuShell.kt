package nativex.gtk.widgets.container.menu

import gtk.GtkMenuShell
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.Container

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
open class MenuShell(
	 val menuShellPointer: CPointer<GtkMenuShell>
) : Container(menuShellPointer.reinterpret())