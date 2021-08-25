package nativex.gtk.widgets.windows.dialog

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback
import nativex.gtk.widgets.HeaderBar
import nativex.gtk.widgets.HeaderBar.Companion.wrap
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.box.Box
import nativex.gtk.widgets.box.Box.Companion.wrap
import nativex.gtk.widgets.button.Button
import nativex.gtk.widgets.button.Button.Companion.wrap
import nativex.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 *
 * 08 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html">GtkDialog</a>
 */
open class Dialog(val dialogPointer: CPointer<GtkDialog>) : Window(dialogPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-new">gtk_dialog_new</a>
	 */
	constructor() : this(gtk_dialog_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-response">
	 *     gtk_dialog_response</a>
	 */
	fun response(responseId: ResponseType) {
		gtk_dialog_response(dialogPointer, responseId.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-add-button">
	 *     gtk_dialog_add_button</a>
	 */
	fun addButton(text: String, responseId: ResponseType): Button =
		gtk_dialog_add_button(dialogPointer, text, responseId.gtk)!!.reinterpret<GtkButton>().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-add-action-widget">
	 *     gtk_dialog_add_action_widget</a>
	 */
	fun addActionWidget(child: Widget, responseId: ResponseType) {
		gtk_dialog_add_action_widget(dialogPointer, child.widgetPointer, responseId.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-set-default-response">
	 *     gtk_dialog_set_default_response</a>
	 */
	fun setDefaultResponse(responseId: ResponseType) {
		gtk_dialog_set_default_response(dialogPointer, responseId.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-set-response-sensitive">
	 *     gtk_dialog_set_response_sensitive</a>
	 */
	fun setResponseSensitive(responseId: ResponseType, setting: Boolean) {
		gtk_dialog_set_response_sensitive(dialogPointer, responseId.gtk, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-get-response-for-widget">
	 *     gtk_dialog_get_response_for_widget</a>
	 */
	fun getResponseForWidget(widget: Widget): ResponseType =
		ResponseType.valueOfGtk(gtk_dialog_get_response_for_widget(dialogPointer, widget.widgetPointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-get-widget-for-response">
	 *     gtk_dialog_get_widget_for_response</a>
	 */
	fun getWidgetForResponse(responseId: ResponseType): Widget? =
		gtk_dialog_get_widget_for_response(dialogPointer, responseId.gtk).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-get-content-area">
	 *     gtk_dialog_get_content_area</a>
	 */
	val contentArea: Box
		get() = gtk_dialog_get_content_area(dialogPointer)!!.reinterpret<GtkBox>().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#gtk-dialog-get-header-bar">
	 *     gtk_dialog_get_header_bar</a>
	 */
	val headerBar: HeaderBar
		get() = gtk_dialog_get_header_bar(dialogPointer)!!.reinterpret<GtkHeaderBar>().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#GtkDialog-close">close</a>
	 */
	fun addOnCloseCallback(action: () -> Unit): SignalManager =
		addSignalCallback(Signals.CLOSE, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#GtkDialog-response">response</a>
	 */
	fun addOnResponseCallback(action: (ResponseType) -> Unit): SignalManager =
		addSignalCallback(Signals.RESPONSE, action, staticResponseCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#GtkDialogFlags">GtkDialogFlags</a>
	 */
	enum class Flags(val gtk: GtkDialogFlags) {
		/**
		 * Make the constructed dialog modal, see [Window.isModal]
		 */
		MODAL(GTK_DIALOG_MODAL),

		/**
		 * Destroy the dialog when its parent is destroyed, see [Window.destroyWithParent]
		 */
		DESTROY_WITH_PARENT(GTK_DIALOG_DESTROY_WITH_PARENT),

		/**
		 * Create dialog with actions in header bar instead of action area.
		 * @since 3.12
		 */
		USE_HEADER_BAR(GTK_DIALOG_USE_HEADER_BAR);

		companion object {

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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkDialog.html#GtkResponseType">GtkResponseType</a>
	 */
	enum class ResponseType(val gtk: GtkResponseType) {
		/**
		 * Returned if an action widget has no response id, or if the dialog gets programmatically hidden or destroyed
		 */
		NONE(GTK_RESPONSE_NONE),

		/**
		 * Generic response id, not used by GTK+ dialogs
		 */
		REJECT(GTK_RESPONSE_REJECT),

		/**
		 * Generic response id, not used by GTK+ dialogs
		 */
		ACCEPT(GTK_RESPONSE_ACCEPT),

		/**
		 * Returned if the dialog is deleted
		 */
		DELETE_EVENT(GTK_RESPONSE_DELETE_EVENT),

		/**
		 * Returned by OK buttons in GTK+ dialogs
		 */
		OK(GTK_RESPONSE_OK),

		/**
		 * Returned by Cancel buttons in GTK+ dialogs
		 */
		CANCEL(GTK_RESPONSE_CANCEL),

		/**
		 * Returned by Close buttons in GTK+ dialogs
		 */
		CLOSE(GTK_RESPONSE_CLOSE),

		/**
		 * Returned by Yes buttons in GTK+ dialogs
		 */
		YES(GTK_RESPONSE_YES),

		/**
		 * Returned by No buttons in GTK+ dialogs
		 */
		NO(GTK_RESPONSE_NO),

		/**
		 * Returned by Apply buttons in GTK+ dialogs
		 */
		APPLY(GTK_RESPONSE_APPLY),

		/**
		 * Returned by Help buttons in GTK+ dialogs
		 */
		HELP(GTK_RESPONSE_HELP);

		fun isId(id: Int): Boolean =
			gtk == id

		companion object {
			fun valueOfGtk(gtk: GtkResponseType) =
				values().find { it.gtk == gtk }
		}
	}
}