package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.Window.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 08 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-run">
 *     GtkNativeDialog</a>
 */
class NativeDialog(val nativeDialogPointer: CPointer<GtkNativeDialog>) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-show">
	 *     gtk_native_dialog_show</a>
	 */
	fun show() {
		gtk_native_dialog_show(nativeDialogPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-hide">
	 *     gtk_native_dialog_hide</a>
	 */
	fun hide() {
		gtk_native_dialog_hide(nativeDialogPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-destroy">
	 *     gtk_native_dialog_destroy</a>
	 */
	fun destroy() {
		gtk_native_dialog_destroy(nativeDialogPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-get-visible">
	 *     gtk_native_dialog_get_visible</a>
	 */
	val isVisible: Boolean
		get() = gtk_native_dialog_get_visible(nativeDialogPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-get-modal">
	 *     gtk_native_dialog_get_modal</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-set-modal">
	 *     gtk_native_dialog_set_modal</a>
	 */
	var modal: Boolean
		get() = gtk_native_dialog_get_modal(nativeDialogPointer).bool
		set(value) = gtk_native_dialog_set_modal(nativeDialogPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-get-title">
	 *     gtk_native_dialog_get_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-set-title">
	 *     gtk_native_dialog_set_title</a>
	 */
	var title: String?
		get() = gtk_native_dialog_get_title(nativeDialogPointer)?.toKString()
		set(value) = gtk_native_dialog_set_title(nativeDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-get-transient-for">
	 *     gtk_native_dialog_get_transient_for</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-set-transient-for">
	 *     gtk_native_dialog_set_transient_for</a>
	 */
	var transientFor: Window?
		get() = gtk_native_dialog_get_transient_for(nativeDialogPointer).wrap()
		set(value) = gtk_native_dialog_set_transient_for(nativeDialogPointer, value?.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#gtk-native-dialog-run">
	 *     gtk_native_dialog_run</a>
	 */
	fun run(): Int =
		gtk_native_dialog_run(nativeDialogPointer)


	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-GtkNativeDialog.html#GTK-TYPE-NATIVE-DIALOG:CAPS">
		 *     GTK_TYPE_NATIVE_DIALOG</a>
		 */
		val NATIVE_DIALOG: ULong by lazy { gtk_native_dialog_get_type() }
	}
}