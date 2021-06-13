package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gio.File
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.dialog.AppChooserDialog
import nativex.gtk.widgets.container.bin.windows.dialog.Dialog

@GtkDsl
inline fun Window.appChooserDialog(
	dialogFlags: Dialog.Flags,
	file: File,
	builder: AppChooserDialog.() -> Unit={}
): AppChooserDialog = AppChooserDialog(this, dialogFlags, file).apply(builder)

@GtkDsl
inline fun Window.appChooserDialog(
	dialogFlags: Dialog.Flags,
	contentType: String,
	builder: AppChooserDialog.() -> Unit={}
): AppChooserDialog = AppChooserDialog(this, dialogFlags, contentType).apply(builder)
