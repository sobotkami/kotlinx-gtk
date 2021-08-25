package org.gtk.gtk.widgets.windows.dialog

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.FileChooser
import org.gtk.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class FileChooserDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val fileChooserDialogPointer: CPointer<GtkFileChooserDialog>
) : Dialog(fileChooserDialogPointer.reinterpret()), FileChooser {
	override val fileChooserPointer: CPointer<GtkFileChooser> by lazy { fileChooserDialogPointer.reinterpret() }

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