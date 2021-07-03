package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.*
import gtk.GtkFileChooserAction.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.PointerHolder
import nativex.gtk.FileChooser
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class FileChooserDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val fileChooserDialogPointer: CPointer<GtkFileChooserDialog>
) : Dialog(fileChooserDialogPointer.reinterpret()), FileChooser {
	override val fileChooserPointer: PointerHolder<GtkFileChooser> by lazy { PointerHolder(fileChooserDialogPointer.reinterpret()) }

	constructor(
		action: FileChooser.Action,
		parent: Window? = null,
		title: String? = null,
		yesString: String,
		noString: String
	) : this(
		gtk_file_chooser_dialog_new(
			title,
			parent?.windowPointer,
			action.gtk,
			yesString,
			GTK_RESPONSE_ACCEPT,
			noString,
			GTK_RESPONSE_CANCEL,
			null
		)!!.reinterpret()
	)
}