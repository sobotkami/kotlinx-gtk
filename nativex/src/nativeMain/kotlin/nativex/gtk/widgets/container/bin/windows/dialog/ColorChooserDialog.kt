package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.GtkColorChooser
import gtk.GtkDialog
import gtk.gtk_color_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.gdk.RGBA
import nativex.gobject.Signals
import nativex.gtk.ColorChooser
import nativex.gtk.widgets.container.bin.windows.Window

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