package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gio.File
import org.gtk.gtk.widgets.windows.Window
import org.gtk.gtk.widgets.windows.dialog.AppChooserDialog
import org.gtk.gtk.widgets.windows.dialog.Dialog


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
