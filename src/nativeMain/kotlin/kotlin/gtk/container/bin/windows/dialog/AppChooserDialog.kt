package kotlin.gtk.container.bin.windows.dialog

import gtk.*
import kotlinx.cinterop.*
import kotlin.g.File
import kotlin.gtk.widgets.Widget
import kotlin.gtk.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class AppChooserDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkAppChooserDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {
	constructor(window: Window, dialogFlags: Flags, file: File) : this(
		gtk_app_chooser_dialog_new(
			window.windowPointer,
			dialogFlags.gtk,
			file.pointer
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
		get() = gtk_app_chooser_dialog_get_widget(aboutDialogPointer)?.let {
			Widget(
				it
			)
		}

	var heading: String?
		get() =
			gtk_app_chooser_dialog_get_heading(aboutDialogPointer)?.pointed?.ptr?.toKString()
		set(value) =
			gtk_app_chooser_dialog_set_heading(aboutDialogPointer, value)

}