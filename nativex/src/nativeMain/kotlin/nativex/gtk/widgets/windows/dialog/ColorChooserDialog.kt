package nativex.gtk.widgets.windows.dialog

import gtk.GtkColorChooser
import gtk.GtkDialog
import gtk.gtk_color_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.ColorChooser
import nativex.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class ColorChooserDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val aboutDialogPointer: CPointer<GtkDialog>
) : Dialog(aboutDialogPointer.reinterpret()), ColorChooser {
	constructor(window: Window?, title: String?) : this(
		gtk_color_chooser_dialog_new(
			title,
			window?.windowPointer
		)!!.reinterpret()
	)

	override val colorChooserPointer: CPointer<GtkColorChooser> by lazy {
		aboutDialogPointer.reinterpret()
	}
}