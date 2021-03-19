package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.PointerHolder
import nativex.async.callbackSignalFlow
import nativex.gtk.Scrollable
import nativex.gtk.Signals
import nativex.gtk.TextBuffer

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class TextView internal constructor(
	internal val textViewPointer: CPointer<GtkTextView>
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


	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val backSpaceSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.BACKSPACE)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val copyClipboardSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.COPY_CLIPBOARD)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val cutClipBoardSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.CUT_CLIPBOARD)
	}

	val deleteFromCursorSignal: Flow<Unit>
		get() {
			TODO()
		}

	val extentSelectionSignal: Flow<Unit>
		get() {
			TODO()
		}

	val insertAtCursorSignal: Flow<Unit>
		get() {
			TODO()
		}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val insertEmojiSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.INSERT_EMOJI)
	}

	val moveCursorSignal: Flow<Unit>
		get() {
			TODO()
		}

	val moveViewPortSignal: Flow<Unit>
		get() {
			TODO()
		}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val pasteClipboardSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.PASTE_CLIPBOARD)
	}

	val populatePopupSignal: Flow<Unit>
		get() {
			TODO()
		}
	val preeditChangedSignal: Flow<Unit>
		get() {
			TODO()
		}
	val selectAllSignal: Flow<Unit>
		get() {
			TODO()
		}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val setAnchorSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.SET_ANCHOR)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val toggleCursorVisibleSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.TOGGLE_CURSOR_VISIBLE)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val toggleOverwriteSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.TOGGLE_OVERWRITE)
	}


}