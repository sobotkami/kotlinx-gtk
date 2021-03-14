package kotlinx.gtk

import kotlin.gtk.container.bin.windows.Window
import kotlin.gtk.container.bin.windows.dialog.Dialog
import kotlin.gtk.container.bin.windows.dialog.MessageDialog

/**
 * kotlinx-gtk
 * 09 / 03 / 2021
 */


@GtkDsl
fun Window.messageDialog(
	flags: Dialog.Flags,
	messageType: MessageDialog.MessageType,
	buttonsType: MessageDialog.ButtonsType,
	messageFormat: String? = null
) = MessageDialog(
	this,
	flags = Dialog.Flags.DESTROY_WITH_PARENT,
	messageType = MessageDialog.MessageType.INFO,
	buttonsType = MessageDialog.ButtonsType.CLOSE,
	messageFormat = messageFormat
)
