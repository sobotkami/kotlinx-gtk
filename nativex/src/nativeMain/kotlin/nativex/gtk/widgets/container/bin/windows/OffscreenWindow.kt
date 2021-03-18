package nativex.gtk.widgets.container.bin.windows

import gtk.GtkOffscreenWindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class OffscreenWindow internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val offScreenWindowPointer: CPointer<GtkOffscreenWindow>
) : Window(offScreenWindowPointer.reinterpret())