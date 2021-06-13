package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.dialog.ColorChooserDialog

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
