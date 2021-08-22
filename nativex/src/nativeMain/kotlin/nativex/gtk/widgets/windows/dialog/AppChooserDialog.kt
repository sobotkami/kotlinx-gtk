package nativex.gtk.widgets.windows.dialog

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gio.File
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class AppChooserDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val aboutDialogPointer: CPointer<GtkAppChooserDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {
	constructor(window: Window, dialogFlags: Flags, file: File) : this(
		gtk_app_chooser_dialog_new(
			window.windowPointer,
			dialogFlags.gtk,
			file.filePointer
		)!!.reinterpret()
	)

	constructor(window: Window, dialogFlags: Flags, contentType: String) : this(
		gtk_app_chooser_dialog_new_for_content_type(
			window.windowPointer,
			dialogFlags.gtk,
			contentType
		)!!.reinterpret()
	)

	val widget: Widget?
		get() = gtk_app_chooser_dialog_get_widget(aboutDialogPointer)?.wrap()

	var heading: String?
		get() =
			gtk_app_chooser_dialog_get_heading(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_app_chooser_dialog_set_heading(aboutDialogPointer, value)

}