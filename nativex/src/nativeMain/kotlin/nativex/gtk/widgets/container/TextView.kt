package nativex.gtk.widgets.container

import glib.gpointer
import gobject.GCallback
import gtk.*
import gtk.GtkTextExtendSelection.GTK_TEXT_EXTEND_SELECTION_LINE
import gtk.GtkTextExtendSelection.GTK_TEXT_EXTEND_SELECTION_WORD
import gtk.GtkTextViewLayer.*
import gtk.GtkTextWindowType.*
import gtk.GtkWrapMode.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.PopulatePopupFunction
import nativex.async.populatePopupSignalManager
import nativex.async.signalFlow
import nativex.async.staticBooleanCallback
import nativex.gdk.Rectangle
import nativex.gdk.Rectangle.Companion.wrap
import nativex.gdk.Window
import nativex.gdk.Window.Companion.wrap
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gmodule.WrappedList.Companion.toList
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gtk.Scrollable
import nativex.gtk.TextBuffer
import nativex.gtk.TextIter
import nativex.gtk.TextIter.Companion.wrap
import nativex.gtk.TextMark
import nativex.gtk.common.enums.DeleteType
import nativex.gtk.common.enums.ScrollStep
import nativex.gtk.common.events.ExtenedMoveCursorEvent
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 08 / 03 / 2021
 *
 * @see <a href=""></a>
 */
class TextView(
	val textViewPointer: CPointer<GtkTextView>
) : Container(
	textViewPointer.reinterpret()
), Scrollable {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextView.html#gtk-text-view-new">gtk_text_view_new</a>
	 */
	constructor() : this(gtk_text_view_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextView.html#gtk-text-view-new-with-buffer">
	 *     gtk_text_view_new_with_buffer</a>
	 */
	constructor(textBuffer: TextBuffer) : this(
		gtk_text_view_new_with_buffer(
			textBuffer.textBufferPointer
		)!!.reinterpret()
	)

	override val scrollablePointer: CPointer<GtkScrollable>
		get() = textViewPointer.reinterpret()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextView.html#gtk-text-view-get-buffer">gtk_text_view_get_buffer</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextView.html#gtk-text-view-set-buffer">gtk_text_view_set_buffer</a>
	 */
	var buffer: TextBuffer?
		get() = gtk_text_view_get_buffer(textViewPointer)?.let { TextBuffer(it) }
		set(value) = gtk_text_view_set_buffer(
			textViewPointer,
			value?.textBufferPointer
		)

	fun scrollToMark(mark: TextMark, withinMargin: Double, useAlign: Boolean, xAlign: Double, yAlign: Double) {
		gtk_text_view_scroll_to_mark(textViewPointer, mark.markPointer, withinMargin, useAlign.gtk, xAlign, yAlign)
	}

	fun scrollToIter(iter: TextIter, withinMargin: Double, useAlign: Boolean, xAlign: Double, yAlign: Double) {
		gtk_text_view_scroll_to_iter(textViewPointer, iter.pointer, withinMargin, useAlign.gtk, xAlign, yAlign)
	}

	fun scrollMarkOnscreen(mark: TextMark) {
		gtk_text_view_scroll_mark_onscreen(textViewPointer, mark.markPointer)
	}

	fun moveMarkOnscreen(mark: TextMark) {
		gtk_text_view_move_mark_onscreen(textViewPointer, mark.markPointer)
	}

	fun placeCursorOnscreen() {
		gtk_text_view_place_cursor_onscreen(textViewPointer)
	}

	val visibleRectangle: Rectangle
		get() = memScoped {
			val rectangle = cValue<GdkRectangle>()
			gtk_text_view_get_visible_rect(textViewPointer, rectangle)
			rectangle.ptr.wrap()
		}


	fun getIterLocation(iter: TextIter): Rectangle =
		memScoped {
			val rectangle = cValue<GdkRectangle>()
			gtk_text_view_get_iter_location(textViewPointer, iter.pointer, rectangle)
			rectangle.ptr.wrap()
		}

	data class CursorLocations(
		val strong: Rectangle?,
		val weak: Rectangle?
	)

	fun getCursorLocations(iter: TextIter): CursorLocations =
		memScoped {
			val strong = cValue<GdkRectangle>()
			val weak = cValue<GdkRectangle>()
			gtk_text_view_get_cursor_locations(textViewPointer, iter.pointer, strong, weak)

			CursorLocations(
				strong.ptr.wrap(),
				weak.ptr.wrap()
			)
		}

	fun getLineAtY(y: Int): Pair<TextIter, Int> =
		memScoped {
			val targetIter = cValue<GtkTextIter>()
			val lineTop = cValue<IntVar>()
			gtk_text_view_get_line_at_y(textViewPointer, targetIter, y, lineTop)
			targetIter.ptr.wrap() to lineTop.ptr.pointed.value
		}

	data class YRange(
		val y: Int,
		val height: Int
	)

	fun getLineYRange(iter: TextIter): YRange =
		memScoped {
			val y = cValue<IntVar>()
			val height = cValue<IntVar>()
			gtk_text_view_get_line_yrange(textViewPointer, iter.pointer, y, height)
			YRange(y.ptr.pointed.value, height.ptr.pointed.value)
		}

	fun getIterAtPosition(x: Int, y: Int): Pair<TextIter, Int> =
		memScoped {
			val trailing = cValue<IntVar>()
			val iter = cValue<GtkTextIter>()
			gtk_text_view_get_iter_at_position(textViewPointer, iter, trailing, x, y)
			iter.ptr.wrap() to trailing.ptr.pointed.value
		}

	fun bufferToWindowCoords(winType: TextWindowType, bufferX: Int, bufferY: Int): Pair<Int, Int> =
		memScoped {
			val y = cValue<IntVar>()
			val x = cValue<IntVar>()

			gtk_text_view_buffer_to_window_coords(textViewPointer, winType.gtk, bufferX, bufferY, x, y)

			x.ptr.pointed.value to y.ptr.pointed.value
		}

	fun windowToBufferCords(winType: TextWindowType, windowX: Int, windowY: Int): Pair<Int, Int> =
		memScoped {
			val y = cValue<IntVar>()
			val x = cValue<IntVar>()

			gtk_text_view_window_to_buffer_coords(textViewPointer, winType.gtk, windowX, windowY, x, y)

			x.ptr.pointed.value to y.ptr.pointed.value
		}

	fun getWindow(window: TextWindowType): Window? =
		gtk_text_view_get_window(textViewPointer, window.gtk).wrap()

	fun getWindowType(window: Window): TextWindowType =
		TextWindowType.valueOf(gtk_text_view_get_window_type(textViewPointer, window.windowPointer))!!

	fun setBorderWindowSize(winType: TextWindowType, size: Int) {
		gtk_text_view_set_border_window_size(textViewPointer, winType.gtk, size)
	}

	fun getBorderWindowSize(winType: TextWindowType): Int =
		gtk_text_view_get_border_window_size(textViewPointer, winType.gtk)

	fun forwardDisplayLine(iter: TextIter): Boolean =
		gtk_text_view_forward_display_line(textViewPointer, iter.pointer).bool

	fun backwardDisplayLine(iter: TextIter): Boolean =
		gtk_text_view_backward_display_line(textViewPointer, iter.pointer).bool

	fun forwardDisplayLineEnd(iter: TextIter): Boolean =
		gtk_text_view_forward_display_line_end(textViewPointer, iter.pointer).bool

	fun backwardDisplayLineStart(iter: TextIter): Boolean =
		gtk_text_view_backward_display_line_start(textViewPointer, iter.pointer).bool

	fun startsDisplayLine(iter: TextIter): Boolean =
		gtk_text_view_starts_display_line(textViewPointer, iter.pointer).bool

	fun viewMoreVisually(iter: TextIter, count: Int): Boolean =
		gtk_text_view_move_visually(textViewPointer, iter.pointer, count).bool

	fun addChildAtAnchor(child: Widget, anchor: TextChildAnchor) {
		gtk_text_view_add_child_at_anchor(textViewPointer, child.widgetPointer, anchor.pointer)
	}


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val backSpaceSignal: Flow<Unit> by signalFlow(Signals.BACKSPACE)

	/**
	 * <a href=""></a>
	 */
	fun addOnCopyClipboardCallback(action: () -> Unit): SignalManager =
		SignalManager(
			textViewPointer,
			textViewPointer.connectSignal(
				Signals.COPY_CLIPBOARD,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val cutClipBoardSignal: Flow<Unit> by signalFlow(Signals.CUT_CLIPBOARD)

	/**
	 * <a href=""></a>
	 */
	data class DeleteFromCursorEvent(
		val type: DeleteType,
		val count: Int
	)

	/**
	 * <a href=""></a>
	 */
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

	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val extentSelectionSignal: Flow<ExtendSelectionEvent> by signalFlow(
		Signals.EXTEND_SELECTION,
		staticExtendSelectionCallback
	)

	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val insertAtCursorSignal: Flow<Char> by lazy {
		//callbackSignalFlow(
		//	Signals.INSERT_AT_CURSOR,
		//	staticInsertAtCursorCallback
		//)
		TODO("How to figure out char")
	}


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val insertEmojiSignal: Flow<Unit> by signalFlow(Signals.INSERT_EMOJI)


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val moveCursorSignal: Flow<ExtenedMoveCursorEvent> by signalFlow(
		Signals.MOVE_CURSOR,
		ExtenedMoveCursorEvent.staticMoveCursorCallback
	)

	data class MoveViewPortEvent(
		val step: ScrollStep,
		val count: Int
	)

	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val moveViewPortSignal: Flow<MoveViewPortEvent> by signalFlow(Signals.MOVE_VIEWPORT, staticMoveViewportCallback)


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val pasteClipboardSignal: Flow<Unit> by signalFlow(Signals.PASTE_CLIPBOARD)

	/**
	 * <a href=""></a>
	 */
	fun addOnPopulatePopupCallback(action: PopulatePopupFunction): SignalManager =
		populatePopupSignalManager(textViewPointer, action)

	/**
	 * <a href=""></a>
	 */
	val preeditChangedSignal: Flow<Char>
		get() {
			TODO("Figure out C Char")
		}


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val selectAllSignal: Flow<Boolean> by signalFlow(Signals.SELECT_ALL, staticBooleanCallback)


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val setAnchorSignal: Flow<Unit> by signalFlow(Signals.SET_ANCHOR)


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val toggleCursorVisibleSignal: Flow<Unit> by signalFlow(Signals.TOGGLE_CURSOR_VISIBLE)


	/**
	 * <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val toggleOverwriteSignal: Flow<Unit> by signalFlow(Signals.TOGGLE_OVERWRITE)

	/**
	 * <a href=""></a>
	 */
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

	/**
	 * <a href=""></a>
	 */
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

	/**
	 * <a href=""></a>
	 */
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

	/**
	 * <a href=""></a>
	 */
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

	class TextChildAnchor(val pointer: CPointer<GtkTextChildAnchor>) {

		constructor() : this(gtk_text_child_anchor_new()!!)

		val widgets: List<Widget> =
			gtk_text_child_anchor_get_widgets(pointer)!!.toList(
				{ reinterpret<GtkWidget>().wrap() },
				{ widgetPointer }
			)
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