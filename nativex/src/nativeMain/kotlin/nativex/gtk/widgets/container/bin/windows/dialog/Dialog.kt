package nativex.gtk.widgets.container.bin.windows.dialog

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.glib.gtk
import nativex.gobject.Signals
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

	@ExperimentalCoroutinesApi
	val responseSignal: Flow<ResponseType> by signalFlow(Signals.RESPONSE, staticResponseCallback)

	enum class Flags(val key: Int,  val gtk: GtkDialogFlags) {
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

	enum class ResponseType(val key: Int,  val gtk: GtkResponseType) {
		NONE(0, GTK_RESPONSE_NONE),

		REJECT(1, GTK_RESPONSE_REJECT),

		ACCEPT(2, GTK_RESPONSE_ACCEPT),

		DELETE_EVENT(3, GTK_RESPONSE_DELETE_EVENT),

		OK(4, GTK_RESPONSE_OK),

		CANCEL(5, GTK_RESPONSE_CANCEL),

		CLOSE(6, GTK_RESPONSE_CLOSE),

		YES(7, GTK_RESPONSE_YES),

		NO(8, GTK_RESPONSE_NO),

		APPLY(9, GTK_RESPONSE_APPLY),

		HELP(10, GTK_RESPONSE_HELP);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			 fun valueOfGtk(gtk: GtkResponseType) =
				values().find { it.gtk == gtk }
		}
	}
}