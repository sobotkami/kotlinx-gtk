package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.GtkDialog
import gtk.gtk_color_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class ColorChooserDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {
	constructor(window: Window, title: String) : this(
		gtk_color_chooser_dialog_new(
			title,
			window.windowPointer
		)!!.reinterpret()
	)
}