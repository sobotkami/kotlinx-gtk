package nativex.gtk.widgets.container.box

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.async.staticIntCallback
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.Signals
import nativex.gtk.asWidget
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.windows.dialog.MessageDialog

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

	val actionArea: Widget
		get() = gtk_info_bar_get_action_area(infoBarPointer).asWidget()

	val contentArea: Widget
		get() = gtk_info_bar_get_content_area(infoBarPointer).asWidget()

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

	
	@ExperimentalCoroutinesApi
	val closeSignal: Flow<Unit> by signalFlow(Signals.CLOSE)


	
	@ExperimentalCoroutinesApi
	val responseSignal: Flow<Int> by signalFlow(Signals.RESPONSE, staticIntCallback)


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