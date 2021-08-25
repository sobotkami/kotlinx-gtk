package org.gtk.gtk.widgets.windows

import gtk.GtkDialog
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 * TODO gtkx
 */
class Plug(
	@Suppress("MemberVisibilityCanBePrivate")
	 val plugPointer: CPointer<GtkDialog>
) : Window(plugPointer.reinterpret())