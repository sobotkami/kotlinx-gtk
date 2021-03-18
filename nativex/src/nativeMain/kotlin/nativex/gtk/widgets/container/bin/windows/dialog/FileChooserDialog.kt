package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.GtkFileChooserAction
import gtk.GtkFileChooserAction.*
import gtk.GtkFileChooserDialog
import gtk.gtk_file_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class FileChooserDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val fileChooserDialogPointer: CPointer<GtkFileChooserDialog>
) : Dialog(fileChooserDialogPointer.reinterpret()) {

	constructor(
		action: Action,
		parent: Window? = null,
		title: String? = null,
		firstButtonText: String? = null
	) : this(
		gtk_file_chooser_dialog_new(
			title,
			parent?.windowPointer,
			action.gtk,
			firstButtonText
		)!!.reinterpret()
	)


	enum class Action(val key: Int, internal val gtk: GtkFileChooserAction) {
		MODAL(0, GTK_FILE_CHOOSER_ACTION_OPEN),
		ACTION_SAVE(1, GTK_FILE_CHOOSER_ACTION_SAVE),
		SELECT_FOLDER(2, GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER),
		CREATE_FOLDER(3, GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GtkFileChooserAction) =
				values().find { it.gtk == gtk }
		}
	}
}