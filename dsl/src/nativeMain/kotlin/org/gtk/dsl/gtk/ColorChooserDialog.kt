package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.windows.Window
import org.gtk.gtk.widgets.windows.dialog.ColorChooserDialog

/**
 * kotlinx-gtk
 * 13 / 06 / 2021
 */
@GtkDsl
inline fun Window.colorChooserDialog(title: String?, builder: ColorChooserDialog.() -> Unit={}) =
	ColorChooserDialog(this, title).apply(builder)

@GtkDsl
inline fun colorChooserDialogNoWindow(title: String?, builder: ColorChooserDialog.() -> Unit={}) =
	ColorChooserDialog(null, title).apply(builder)
