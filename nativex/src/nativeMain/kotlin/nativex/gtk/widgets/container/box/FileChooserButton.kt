package nativex.gtk.widgets.container.box

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gtk.FileChooser
import nativex.gtk.widgets.container.bin.windows.dialog.Dialog

/**
 * kotlinx-gtk
 *
 * 06 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html">GtkFileChooserButton</a>
 */
class FileChooserButton(val fileChooserButtonPointer: CPointer<GtkFileChooserButton>) :
	Box(fileChooserButtonPointer.reinterpret()), FileChooser {
	override val fileChooserPointer: CPointer<GtkFileChooser> by lazy { fileChooserButtonPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-new">
	 *     gtk_file_chooser_button_new</a>
	 */
	constructor(title: String, action: FileChooser.Action) :
			this(gtk_file_chooser_button_new(title, action.gtk)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-new-with-dialog">
	 *     gtk_file_chooser_button_new_with_dialog</a>
	 */
	constructor(dialog: Dialog) :
			this(gtk_file_chooser_button_new_with_dialog(dialog.widgetPointer)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-get-title">
	 *     gtk_file_chooser_button_get_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-set-title">
	 *     gtk_file_chooser_button_set_title</a>
	 */
	var title: String
		get() = gtk_file_chooser_button_get_title(fileChooserButtonPointer)!!.toKString()
		set(value) = gtk_file_chooser_button_set_title(fileChooserButtonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-get-width-chars">
	 *     gtk_file_chooser_button_get_width_chars</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#gtk-file-chooser-button-set-width-chars">
	 *     gtk_file_chooser_button_set_width_chars</a>
	 */
	var widthChars: Int
		get() = gtk_file_chooser_button_get_width_chars(fileChooserButtonPointer)
		set(value) = gtk_file_chooser_button_set_width_chars(fileChooserButtonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFileChooserButton.html#GtkFileChooserButton-file-set">
	 *     file-set</a>
	 */
	fun addOnFileSetCallback(action: () -> Unit): SignalManager =
		SignalManager(
			fileChooserButtonPointer,
			fileChooserButtonPointer.connectSignal(
				Signals.FILE_SET,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)
}