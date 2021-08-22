package nativex.gtk.widgets.box

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.async.staticIntCallback
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.windows.dialog.MessageDialog

/**
 * kotlinx-gtk
 * 27 / 03 / 2021
 *
 * It is impossible to wrap
 * gtk_info_bar_new_with_buttons & gtk_info_bar_add_buttons
 * due to vararg. Thus they have not been encapsulated properly
 */
class InfoBar(
	val infoBarPointer: CPointer<GtkInfoBar>
) : Box(infoBarPointer.reinterpret()) {
	constructor() : this(gtk_info_bar_new()!!.reinterpret())
	// TODO Vararg : gtk_info_bar_new_with_buttons

	//constructor(firstButtonText: String, array: Array<Any?>) : this(
	//	gtk_info_bar_new_with_buttons(firstButtonText, *array)!!.reinterpret()
	//)

	/*
	constructor(buttons: Array<Pair<String, Int>>) : this(
		buttons.first().first,
		buttons.flatMapIndexed { index, buttonCouple ->
			buttonCouple.let { (text, responseID) ->
				mutableListOf<Any>(responseID).apply {
					if (index != 0)
						add(text.cstr)
				}
			}
		}.toTypedArray()
	)
	 */

	fun addActionWidget(widget: Widget, responseID: Int) {
		gtk_info_bar_add_action_widget(
			infoBarPointer,
			widget.widgetPointer,
			responseID
		)
	}

	fun addButton(buttonText: String, responseID: Int) {
		gtk_info_bar_add_button(infoBarPointer, buttonText, responseID)
	}

	/**
	 * Emulates gtk_info_bar_add_buttons by calling [addButton] for each index
	 */
	fun addButtons(buttons: Array<Pair<String, Int>>) {
		buttons.forEach { (name, responseID) ->
			addButton(name, responseID)
		}
		/*
		addButtons(
			buttons.first().first,
			buttons.flatMapIndexed { index, buttonCouple ->
				buttonCouple.let { (text, responseID) ->
					mutableListOf<Any>(responseID).apply {
						if (index != 0)
							add(text.cstr)
					}
				}
			}.toTypedArray()
		)
		*/
	}

	/*
	fun addButtons(firstText: String, array: Array<Any?>) {
		gtk_info_bar_add_buttons(infoBarPointer, firstText, *array)
	}
	 */

	fun setResponseSensitive(responseID: Int, setting: Boolean) {
		gtk_info_bar_set_response_sensitive(
			infoBarPointer,
			responseID,
			setting.gtk
		)
	}

	fun setDefaultResponse(responseID: Int) {
		gtk_info_bar_set_default_response(infoBarPointer, responseID)
	}

	fun response(responseID: Int) {
		gtk_info_bar_response(infoBarPointer, responseID)
	}


	var messageType: MessageDialog.MessageType
		get() = MessageDialog.MessageType.valueOf(
			gtk_info_bar_get_message_type(
				infoBarPointer
			)
		)!!
		set(value) = gtk_info_bar_set_message_type(infoBarPointer, value.gtk)

	var showCloseButton: Boolean
		get() = gtk_info_bar_get_show_close_button(infoBarPointer).bool
		set(value) = gtk_info_bar_set_show_close_button(
			infoBarPointer,
			value.gtk
		)

	var revealed: Boolean
		get() = gtk_info_bar_get_revealed(infoBarPointer).bool
		set(value) = gtk_info_bar_set_revealed(
			infoBarPointer,
			value.gtk
		)

	fun addOnCloseCallback(action: () -> Unit) =
		addSignalCallback(Signals.CLOSE, action)

	fun addOnResponseCallback(action: (Int) -> Unit) =
		addSignalCallback(Signals.RESPONSE, action, staticIntCallback)


	/*
	companion object {
		 val staticCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
				data?.asStableRef<(Int) -> Unit>()
					?.get()
					?.invoke(arg1)
				Unit
			}.reinterpret()

	}

	 */
}