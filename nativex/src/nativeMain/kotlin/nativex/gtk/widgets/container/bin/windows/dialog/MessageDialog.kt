package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.*
import gtk.GtkButtonsType.*
import gtk.GtkMessageType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.cstr
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class MessageDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	val messageDialogPointer: CPointer<GtkMessageDialog>
) : Dialog(messageDialogPointer.reinterpret()) {

	/**
	 * TODO vararg management
	 */
	constructor(
		parent: Window? = null,
		flags: Flags,
		messageType: MessageType,
		buttonsType: ButtonsType,
		message: String,
		withMarkup: Boolean = false,
	) : this(
		if (!withMarkup)
			gtk_message_dialog_new(
				parent?.windowPointer,
				flags.gtk,
				messageType.gtk,
				buttonsType.gtk,
				"%s",
				message.cstr
			)!!.reinterpret()
		else
			gtk_message_dialog_new_with_markup(
				parent?.windowPointer,
				flags.gtk,
				messageType.gtk,
				buttonsType.gtk,
				"%s",
				message.cstr
			)!!.reinterpret()
	)


	var image: Widget?
		get() =
			gtk_message_dialog_get_image(messageDialogPointer)?.let {
				Widget(it)
			}
		set(value) =
			gtk_message_dialog_set_image(
				messageDialogPointer,
				value?.widgetPointer
			)


	fun setMarkup(markup: String) {
		gtk_message_dialog_set_markup(messageDialogPointer, markup)
	}

	/**
	 * TODO vararg
	 */
	fun formatSecondaryText(
		messageFormat: String? = null,
		vararg arguments: Any
	) {
		gtk_message_dialog_format_secondary_text(
			messageDialogPointer,
			messageFormat,
		)
	}

	/**
	 * TODO vararg
	 */
	fun formatSecondaryMarkup(
		messageFormat: String? = null,
		vararg arguments: Any
	) {
		gtk_message_dialog_format_secondary_markup(
			messageDialogPointer,
			messageFormat,
		)
	}

	val messageArea: Widget?
		get() = gtk_message_dialog_get_message_area(messageDialogPointer)?.let {
			Widget(
				it
			)
		}

	enum class MessageType(val key: Int, val gtk: GtkMessageType) {
		INFO(0, GTK_MESSAGE_INFO),
		WARNING(1, GTK_MESSAGE_WARNING),
		QUESTION(2, GTK_MESSAGE_QUESTION),
		ERROR(3, GTK_MESSAGE_ERROR),
		OTHER(4, GTK_MESSAGE_OTHER);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }


			fun valueOf(gtk: GtkMessageType) =
				values().find { it.gtk == gtk }
		}
	}

	enum class ButtonsType(val key: Int, val gtk: GtkButtonsType) {
		NONE(0, GTK_BUTTONS_NONE),
		OK(1, GTK_BUTTONS_OK),
		CLOSE(2, GTK_BUTTONS_CLOSE),
		CANCEL(3, GTK_BUTTONS_CANCEL),
		YES_NO(4, GTK_BUTTONS_YES_NO),
		OK_CANCEL(5, GTK_BUTTONS_OK_CANCEL);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }


			fun valueOf(gtk: GtkButtonsType) =
				values().find { it.gtk == gtk }
		}
	}
}