package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.GtkFontChooserDialog
import gtk.gtk_font_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class FontChooserDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkFontChooserDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	constructor(
		parent: Window? = null,
		title: String? = null
	) : this(
		gtk_font_chooser_dialog_new(
			title,
			parent?.windowPointer
		)!!.reinterpret()
	)
}