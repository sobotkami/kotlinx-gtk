package org.gtk.dsl.gtk

import nativex.GtkDsl
import nativex.gtk.widgets.windows.Window
import nativex.gtk.widgets.windows.dialog.Dialog
import nativex.gtk.widgets.windows.dialog.MessageDialog

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
	messageFormat,
	false
)
