package nativex.gtk.widgets.container

import gtk.*
import gtk.GtkTextExtendSelection.GTK_TEXT_EXTEND_SELECTION_LINE
import gtk.GtkTextExtendSelection.GTK_TEXT_EXTEND_SELECTION_WORD
import gtk.GtkTextViewLayer.*
import gtk.GtkTextWindowType.*
import gtk.GtkWrapMode.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.PointerHolder
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
import nativex.async.staticBooleanCallback
import nativex.gtk.*
import nativex.gtk.common.enums.DeleteType
import nativex.gtk.common.enums.ScrollStep
import nativex.gtk.common.events.ExtenedMoveCursorEvent
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class TextView(
	 val textViewPointer: CPointer<GtkTextView>
) : Container(
	textViewPointer.reinterpret()
), Scrollable {
	constructor() : this(gtk_text_view_new()!!.reinterpret())

	constructor(textBuffer: TextBuffer) : this(
		gtk_text_view_new_with_buffer(
			textBuffer.textBufferPointer
		)!!.reinterpret()
	)

	override val scrollablePointer: PointerHolder<GtkScrollable>
		get() = PointerHolder(textViewPointer.reinterpret())


	var buffer: TextBuffer?
		get() = gtk_text_view_get_buffer(textViewPointer)?.let { TextBuffer(it) }
		set(value) = gtk_text_view_set_buffer(
			textViewPointer,
			value?.textBufferPointer
		)


	
	@ExperimentalCoroutinesApi
	val backSpaceSignal: Flow<Unit> by signalFlow(Signals.BACKSPACE)

	@ExperimentalCoroutinesApi
	val copyClipboardSignal: Flow<Unit> by signalFlow(Signals.COPY_CLIPBOARD)

	@ExperimentalCoroutinesApi
	val cutClipBoardSignal: Flow<Unit> by signalFlow(Signals.CUT_CLIPBOARD)

	data class DeleteFromCursorEvent(
		val type: DeleteType,
		val count: Int
	)

	@ExperimentalCoroutinesApi
	
	val deleteFromCursorSignal: Flow<DeleteFromCursorEvent> by signalFlow(
			Signals.DELETE_FROM_CURSOR,
			staticDeleteFromCursorCallback
		)

	data class ExtendSelectionEvent(
		val granularity: TextExtendSelection,
		val location: TextIter,
		val start: TextIter,
		val end: TextIter,
	)

	@ExperimentalCoroutinesApi
	
	val extentSelectionSignal: Flow<ExtendSelectionEvent> by signalFlow(
			Signals.EXTEND_SELECTION,
			staticExtendSelectionCallback
		)

	@ExperimentalCoroutinesApi
	
	val insertAtCursorSignal: Flow<Char> by lazy {
		//callbackSignalFlow(
		//	Signals.INSERT_AT_CURSOR,
		//	staticInsertAtCursorCallback
		//)
		TODO("How to figure out char")
	}

	
	@ExperimentalCoroutinesApi
	val insertEmojiSignal: Flow<Unit> by signalFlow(Signals.INSERT_EMOJI)


	@ExperimentalCoroutinesApi
	
	val moveCursorSignal: Flow<ExtenedMoveCursorEvent> by signalFlow(
			Signals.MOVE_CURSOR,
			ExtenedMoveCursorEvent.staticMoveCursorCallback
		)

	data class MoveViewPortEvent(
		val step: ScrollStep,
		val count: Int
	)

	@ExperimentalCoroutinesApi
	
	val moveViewPortSignal: Flow<MoveViewPortEvent> by signalFlow(Signals.MOVE_VIEWPORT, staticMoveViewportCallback)

	
	@ExperimentalCoroutinesApi
	val pasteClipboardSignal: Flow<Unit> by signalFlow(Signals.PASTE_CLIPBOARD)

	@ExperimentalCoroutinesApi
	
	val populatePopupSignal: Flow<Widget> by signalFlow(Signals.POPULATE_POPUP, staticPopulatePopupCallback)

	val preeditChangedSignal: Flow<Char>
		get() {
			TODO("Figure out C Char")
		}

	
	@ExperimentalCoroutinesApi
	val selectAllSignal: Flow<Boolean> by signalFlow(Signals.SELECT_ALL, staticBooleanCallback)

	
	@ExperimentalCoroutinesApi
	val setAnchorSignal: Flow<Unit> by signalFlow(Signals.SET_ANCHOR)

	
	@ExperimentalCoroutinesApi
	val toggleCursorVisibleSignal: Flow<Unit> by signalFlow(Signals.TOGGLE_CURSOR_VISIBLE)

	
	@ExperimentalCoroutinesApi
	val toggleOverwriteSignal: Flow<Unit> by signalFlow(Signals.TOGGLE_OVERWRITE)

	enum class Layer(
		val key: Int,
		 val gtk: GtkTextViewLayer
	) {
		BELOW(0, GTK_TEXT_VIEW_LAYER_BELOW),
		ABOVE(1, GTK_TEXT_VIEW_LAYER_ABOVE),
		BELOW_TEXT(2, GTK_TEXT_VIEW_LAYER_BELOW_TEXT),
		ABOVE_TEXT(3, GTK_TEXT_VIEW_LAYER_ABOVE_TEXT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			 fun valueOf(gtk: GtkTextViewLayer) =
				values().find { it.gtk == gtk }
		}
	}

	enum class TextWindowType(
		val key: Int,
		 val gtk: GtkTextWindowType
	) {
		PRIVATE(0, GTK_TEXT_WINDOW_PRIVATE),
		WIDGET(1, GTK_TEXT_WINDOW_WIDGET),
		TEXT(2, GTK_TEXT_WINDOW_TEXT),
		LEFT(3, GTK_TEXT_WINDOW_LEFT),
		RIGHT(4, GTK_TEXT_WINDOW_RIGHT),
		TOP(5, GTK_TEXT_WINDOW_TOP),
		BOTTOM(6, GTK_TEXT_WINDOW_BOTTOM);

		companion object {
			fun valueOf(key: Int) =
				values()
					.find { it.key == key }

			 fun valueOf(gtk: GtkTextWindowType) =
				values()
					.find { it.gtk == gtk }
		}
	}

	enum class TextExtendSelection(
		val key: Int,
		 val gtk: GtkTextExtendSelection
	) {
		PRIVATE(0, GTK_TEXT_EXTEND_SELECTION_WORD),
		BOTTOM(1, GTK_TEXT_EXTEND_SELECTION_LINE);

		companion object {
			fun valueOf(key: Int) =
				values()
					.find { it.key == key }

			 fun valueOf(gtk: GtkTextExtendSelection) =
				values()
					.find { it.gtk == gtk }
		}
	}

	enum class WrapMode(
		val key: Int,
		 val gtk: GtkWrapMode
	) {
		PRIVATE(0, GTK_WRAP_NONE),
		WIDGET(1, GTK_WRAP_CHAR),
		TOP(2, GTK_WRAP_WORD),
		BOTTOM(3, GTK_WRAP_WORD_CHAR);

		companion object {
			fun valueOf(key: Int) =
				values()
					.find { it.key == key }

			 fun valueOf(gtk: GtkWrapMode) =
				values()
					.find { it.gtk == gtk }
		}
	}

	companion object {
		 val staticDeleteFromCursorCallback: GCallback =
			staticCFunction { _: gpointer?, type: GtkDeleteType, count: Int, data: gpointer? ->
				data?.asStableRef<(DeleteFromCursorEvent) -> Unit>()?.get()
					?.invoke(
						DeleteFromCursorEvent(
							type = DeleteType.valueOf(type)!!,
							count = count
						)
					)
				Unit
			}.reinterpret()

		 val staticExtendSelectionCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  granularity: GtkTextExtendSelection,
			                  location: CPointer<GtkTextIter>,
			                  start: CPointer<GtkTextIter>,
			                  end: CPointer<GtkTextIter>,
			                  data: gpointer? ->
				data?.asStableRef<(ExtendSelectionEvent) -> Unit>()?.get()
					?.invoke(
						ExtendSelectionEvent(
							granularity = TextExtendSelection.valueOf(
								granularity
							)!!,
							location = TextIter(location),
							start = TextIter(start),
							end = TextIter(end),
						)
					)
				Unit
			}.reinterpret()

		 val staticInsertAtCursorCallback: GCallback
			get() {
				/*
					staticCFunction { _: gpointer?,
								  string: Char,
								  data: gpointer? ->
					data?.asStableRef<(Char) -> Unit>()?.get()?.invoke(string)
					Unit
				}.reinterpret()
				 */
				TODO("Figure out char")
			}
/*
		 val staticSelectAllCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  select: gboolean,
			                  data: gpointer? ->
				data?.asStableRef<(Boolean) -> Unit>()?.get()
					?.invoke(select.bool)
				Unit
			}.reinterpret()
*/
		 val staticPreeditChangedCallback: GCallback
			get() {
				/*staticCFunction { _: gpointer?,
								  preedit: CPointer<ByteVar>,
								  data: gpointer? ->
					data?.asStableRef<(Char) -> Unit>()?.get()?.invoke(preedit.toKString())
					Unit
				}.reinterpret()
				 */
				TODO("Figure out char")
			}

		 val staticPopulatePopupCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  popup: CPointer<GtkWidget>,
			                  data: gpointer? ->
				data?.asStableRef<(Widget) -> Unit>()?.get()
					?.invoke(Widget(popup))
				Unit
			}.reinterpret()


		 val staticMoveViewportCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  step: GtkScrollStep,
			                  count: Int,
			                  data: gpointer? ->
				data?.asStableRef<(MoveViewPortEvent) -> Unit>()?.get()
					?.invoke(
						MoveViewPortEvent(
							ScrollStep.valueOf(step)!!,
							count
						)
					)
				Unit
			}.reinterpret()
	}
}