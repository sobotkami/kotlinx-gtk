package nativex.gtk.widgets.container.bin.windows.dialog

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.signalManager
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.HeaderBar
import nativex.gtk.widgets.container.HeaderBar.Companion.wrap
import nativex.gtk.widgets.container.bin.button.Button
import nativex.gtk.widgets.container.bin.button.Button.Companion.wrap
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.box.Box
import nativex.gtk.widgets.container.box.Box.Companion.wrap

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class Dialog(
	@Suppress("MemberVisibilityCanBePrivate")
	val dialogPointer: CPointer<GtkDialog>
) : Window(dialogPointer.reinterpret()) {
	constructor() : this(gtk_dialog_new()!!.reinterpret())

	fun run(): ResponseType =
		ResponseType.valueOfGtk(gtk_dialog_run(dialogPointer))!!

	fun response(responseId: ResponseType) {
		gtk_dialog_response(dialogPointer, responseId.gtk)
	}

	fun addButton(text: String, responseId: ResponseType): Button =
		gtk_dialog_add_button(dialogPointer, text, responseId.gtk)!!.reinterpret<GtkButton>().wrap()

	fun addActionWidget(child: Widget, responseId: ResponseType) {
		gtk_dialog_add_action_widget(dialogPointer, child.widgetPointer, responseId.gtk)
	}

	fun setDefaultResponse(responseId: ResponseType) {
		gtk_dialog_set_default_response(dialogPointer, responseId.gtk)
	}

	fun setResponseSensitive(responseId: ResponseType, setting: Boolean) {
		gtk_dialog_set_response_sensitive(dialogPointer, responseId.gtk, setting.gtk)
	}

	fun getResponseForWidget(widget: Widget): ResponseType =
		ResponseType.valueOfGtk(gtk_dialog_get_response_for_widget(dialogPointer, widget.widgetPointer))!!

	fun getWidgetForResponse(responseId: ResponseType): Widget? =
		gtk_dialog_get_widget_for_response(dialogPointer, responseId.gtk).wrap()

	val contentArea: Box
		get() = gtk_dialog_get_content_area(dialogPointer)!!.reinterpret<GtkBox>().wrap()

	val headerBar: HeaderBar
		get() = gtk_dialog_get_header_bar(dialogPointer)!!.reinterpret<GtkHeaderBar>().wrap()

	fun addOnCloseCallback(action: () -> Unit): SignalManager =
		signalManager(dialogPointer, Signals.CLOSE, StableRef.create(action).asCPointer())

	fun addOnResponseCallback(action: (ResponseType) -> Unit): SignalManager =
		signalManager(dialogPointer, Signals.RESPONSE, StableRef.create(action).asCPointer(), staticResponseCallback)

	enum class Flags(val key: Int, val gtk: GtkDialogFlags) {
		MODAL(0, GTK_DIALOG_MODAL),
		DESTROY_WITH_PARENT(1, GTK_DIALOG_DESTROY_WITH_PARENT),
		USE_HEADER_BAR(2, GTK_DIALOG_USE_HEADER_BAR);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }


			fun valueOf(gtk: GtkDialogFlags) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		val staticResponseCallback: GCallback =
			staticCFunction { _: CPointer<GtkDialog>, id: Int, data: gpointer ->
				data.asStableRef<(ResponseType) -> Unit>().get().invoke(ResponseType.valueOfGtk(id)!!)
			}.reinterpret()
	}

	enum class ResponseType(val gtk: GtkResponseType) {
		NONE(GTK_RESPONSE_NONE),

		REJECT(GTK_RESPONSE_REJECT),

		ACCEPT(GTK_RESPONSE_ACCEPT),

		DELETE_EVENT(GTK_RESPONSE_DELETE_EVENT),

		OK(GTK_RESPONSE_OK),

		CANCEL(GTK_RESPONSE_CANCEL),

		CLOSE(GTK_RESPONSE_CLOSE),

		YES(GTK_RESPONSE_YES),

		NO(GTK_RESPONSE_NO),

		APPLY(GTK_RESPONSE_APPLY),

		HELP(GTK_RESPONSE_HELP);

		fun isId(id: Int): Boolean =
			gtk == id

		companion object {
			fun valueOfGtk(gtk: GtkResponseType) =
				values().find { it.gtk == gtk }
		}
	}
}