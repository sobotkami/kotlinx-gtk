package org.gtk.gtk

import gtk.GtkFileChooser
import gtk.GtkFileChooserAction
import gtk.gtk_file_chooser_get_file
import kotlinx.cinterop.CPointer
import org.gtk.gio.File

/**
 * kotlinx-gtk
 * 13 / 06 / 2021
 */
interface FileChooser {
	val fileChooserPointer: CPointer<GtkFileChooser>

	val file: File
		get() = File(gtk_file_chooser_get_file(fileChooserPointer)!!)

	enum class Action(val gtk: GtkFileChooserAction) {
		ACTION_OPEN(GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_OPEN),
		ACTION_SAVE(GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SAVE),
		SELECT_FOLDER(GtkFileChooserAction.GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER);

		companion object {
			fun valueOf(gtk: GtkFileChooserAction) =
				values().find { it.gtk == gtk }
		}
	}
}