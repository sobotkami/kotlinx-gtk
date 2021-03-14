package kotlin.gtk.container.bin.windows

import gtk.GtkDialog
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 * TODO gtkx
 */
class Plug internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val plugPointer: CPointer<GtkDialog>
) : Window(plugPointer.reinterpret())