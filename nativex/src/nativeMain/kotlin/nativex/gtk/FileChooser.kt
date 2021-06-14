package nativex.gtk

import gtk.GtkFileChooser
import gtk.GtkFileChooserAction
import gtk.gtk_file_chooser_get_filename
import gtk.gtk_file_chooser_set_filename
import kotlinx.cinterop.toKString
import nativex.PointerHolder

/**
 * kotlinx-gtk
 * 13 / 06 / 2021
 */
interface FileChooser {
	val fileChooserPointer: PointerHolder<GtkFileChooser>

	var filePath: String?
		get() = gtk_file_chooser_get_filename(fileChooserPointer.ptr)?.toKString()
		set(value) {
			gtk_file_chooser_set_filename(fileChooserPointer.ptr, value)
		}

	enum class Action(val key: Int, internal val gtk: GtkFileChooserAction) {
		ACTION_OPEN(0, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_OPEN),
		ACTION_SAVE(1, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SAVE),
		SELECT_FOLDER(2, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER),
		CREATE_FOLDER(3, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }


			internal fun valueOf(gtk: GtkFileChooserAction) =
				values().find { it.gtk == gtk }
		}
	}
}