package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gobject.KGObject
import nativex.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 *
 * 08 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html">GtkFileChooserNative</a>
 */
class FileChooserNative(val fileChooserNativePointer: CPointer<GtkFileChooserNative>) :
	KGObject(fileChooserNativePointer.reinterpret()), FileChooser {
	override val fileChooserPointer: CPointer<GtkFileChooser> by lazy { fileChooserNativePointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html#gtk-file-chooser-native-new">
	 *     gtk_file_chooser_native_new</a>
	 */
	constructor(
		title: String? = null,
		parent: Window? = null,
		action: FileChooser.Action,
		acceptLabel: String? = null,
		cancelLabel: String? = null
	) : this(gtk_file_chooser_native_new(title, parent?.windowPointer, action.gtk, acceptLabel, cancelLabel)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html#gtk-file-chooser-native-get-accept-label">
	 *     gtk_file_chooser_native_get_accept_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html#gtk-file-chooser-native-set-accept-label">
	 *     gtk_file_chooser_native_set_accept_label</a>
	 */
	var acceptLabel: String?
		get() = gtk_file_chooser_native_get_accept_label(fileChooserNativePointer)?.toKString()
		set(value) = gtk_file_chooser_native_set_accept_label(fileChooserNativePointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html#gtk-file-chooser-native-get-cancel-label">
	 *     gtk_file_chooser_native_get_cancel_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkFileChooserNative.html#gtk-file-chooser-native-set-cancel-label">
	 *     gtk_file_chooser_native_set_cancel_label</a>
	 */
	var cancelLabel: String?
		get() = gtk_file_chooser_native_get_cancel_label(fileChooserNativePointer)?.toKString()
		set(value) = gtk_file_chooser_native_set_cancel_label(fileChooserNativePointer, value)

}