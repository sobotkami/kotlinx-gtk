package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.dialog.Dialog
import nativex.gtk.widgets.container.bin.windows.dialog.MessageDialog

/**
 * kotlinx-gtk
 * 09 / 03 / 2021
 */


@GtkDsl
fun Window.messageDialog(
	flags: Dialog.Flags,
	messageType: MessageDialog.MessageType,
	buttonsType: MessageDialog.ButtonsType,
	messageFormat: String
) = MessageDialog(
	this,
	flags,
	messageType,
	buttonsType,
	false,
	messageFormat
)
