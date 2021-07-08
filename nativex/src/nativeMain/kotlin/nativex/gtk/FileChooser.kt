package nativex.gtk

import gio.g_file_load_contents
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import nativex.gio.File

/**
 * kotlinx-gtk
 * 13 / 06 / 2021
 */
interface FileChooser {
	val fileChooserPointer: CPointer<GtkFileChooser>

	var filePath: String?
		get() = gtk_file_chooser_get_filename(fileChooserPointer)?.toKString()
		set(value) {
			gtk_file_chooser_set_filename(fileChooserPointer, value)
		}

	val file: File
		get() = File(gtk_file_chooser_get_file(fileChooserPointer)!!)



	enum class Action(val key: Int, val gtk: GtkFileChooserAction) {
		ACTION_OPEN(0, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_OPEN),
		ACTION_SAVE(1, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SAVE),
		SELECT_FOLDER(2, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER),
		CREATE_FOLDER(3, GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }


			fun valueOf(gtk: GtkFileChooserAction) =
				values().find { it.gtk == gtk }
		}
	}
}