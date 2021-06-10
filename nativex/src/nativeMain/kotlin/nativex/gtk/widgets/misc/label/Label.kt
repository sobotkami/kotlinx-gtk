package nativex.gtk.widgets.misc.label

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gtk.Signals
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.common.enums.Justification
import nativex.gtk.common.events.ExtenedMoveCursorEvent
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.menu.Menu
import nativex.gtk.widgets.misc.Misc
import nativex.pango.AttrList
import nativex.pango.EllipsizeMode
import nativex.pango.Layout
import nativex.pango.WrapMode

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
open class Label internal constructor(
	internal val labelPointer: CPointer<GtkLabel>
) : Misc(labelPointer.reinterpret()) {
	var text: String
		get() = gtk_label_get_text(labelPointer)!!.toKString()
		set(value) = gtk_label_set_text(labelPointer, value)

	
	val mnemonicKeyval: UInt
		get() = gtk_label_get_mnemonic_keyval(labelPointer)

	var justification: Justification
		get() = Justification.valueOf(gtk_label_get_justify(labelPointer))!!
		set(value) = gtk_label_set_justify(labelPointer, value.gtk)

	var xAlign: Float
		get() = gtk_label_get_xalign(labelPointer)
		set(value) = gtk_label_set_xalign(labelPointer, value)

	var yAlign: Float
		get() = gtk_label_get_yalign(labelPointer)
		set(value) = gtk_label_set_yalign(labelPointer, value)

	var ellipsize: EllipsizeMode
		get() = EllipsizeMode.valueOf(
			gtk_label_get_ellipsize(
				labelPointer
			)
		)!!
		set(value) = gtk_label_set_ellipsize(labelPointer, value.pango)

	var widthChars: Int
		get() = gtk_label_get_width_chars(labelPointer)
		set(value) = gtk_label_set_width_chars(labelPointer, value)

	var maxWidthChars: Int
		get() = gtk_label_get_max_width_chars(labelPointer)
		set(value) = gtk_label_set_max_width_chars(labelPointer, value)

	var label: String
		get() = gtk_label_get_label(labelPointer)!!.toKString()
		set(value) = gtk_label_set_label(labelPointer, value)

	var layout: Layout
		get() = TODO("gtk_label_get_layout")
		set(value) = TODO("gtk_label_set_justify")

	var lineWrap: Boolean
		get() = gtk_label_get_line_wrap(labelPointer).bool
		set(value) = gtk_label_set_line_wrap(labelPointer, value.gtk)

	var lineWrapMode: WrapMode
		get() = TODO("gtk_label_get_line_wrap_mode")
		set(value) = TODO("gtk_label_set_line_wrap_mode")

	var attributes: AttrList?
		get() = TODO("gtk_label_get_attributes")
		set(value) = TODO("gtk_label_set_attributes")

	var lines: Int
		get() = gtk_label_get_lines(labelPointer)
		set(value) = gtk_label_set_lines(labelPointer, value)

	var mnemonicWidget: Widget?
		get() = gtk_label_get_mnemonic_widget(labelPointer).asWidgetOrNull()
		set(value) = gtk_label_set_mnemonic_widget(
			labelPointer,
			value?.widgetPointer
		)

	val selectionBounds: Pair<Int, Int>?
		get() = memScoped {
			val s = cValue<IntVar>()
			val e = cValue<IntVar>()
			if (gtk_label_get_selection_bounds(labelPointer, s, e).bool)
				s.ptr.pointed.value to e.ptr.pointed.value
			else null
		}

	val layoutOffsets: Pair<Int, Int>?
		get() = memScoped {
			val x = cValue<IntVar>()
			val y = cValue<IntVar>()
			gtk_label_get_layout_offsets(labelPointer, x, y)
			x.ptr.pointed.value to y.ptr.pointed.value
		}

	var useMarkup: Boolean
		get() = gtk_label_get_use_markup(labelPointer).bool
		set(value) = gtk_label_set_use_markup(labelPointer, value.gtk)

	var useUnderline: Boolean
		get() = gtk_label_get_use_underline(labelPointer).bool
		set(value) = gtk_label_set_use_underline(labelPointer, value.gtk)

	var singleLineMode: Boolean
		get() = gtk_label_get_single_line_mode(labelPointer).bool
		set(value) = gtk_label_set_single_line_mode(labelPointer, value.gtk)

	var angle: Double
		get() = gtk_label_get_angle(labelPointer)
		set(value) = gtk_label_set_angle(labelPointer, value)

	val currentUri: String
		get() = gtk_label_get_current_uri(labelPointer)!!.toKString()

	var trackVisitedLinks: Boolean
		get() = gtk_label_get_line_wrap(labelPointer).bool
		set(value) = gtk_label_set_line_wrap(labelPointer, value.gtk)

	var selectable: Boolean
		get() = gtk_label_get_selectable(labelPointer).bool
		set(value) = gtk_label_set_selectable(labelPointer, value.gtk)

	@ExperimentalCoroutinesApi
	
	val activateCurrentLinkSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.ACTIVATE_CURRENT_LINK)
	}
	val activateLinkSignal: Flow<Char>
		get() = TODO("Figure out char")

	@ExperimentalCoroutinesApi
	
	val copyClipboardSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.COPY_CLIPBOARD)
	}

	@ExperimentalCoroutinesApi
	
	val moveCursorSignal: Flow<ExtenedMoveCursorEvent> by lazy {
		callbackSignalFlow(
			Signals.MOVE_CURSOR,
			ExtenedMoveCursorEvent.staticMoveCursorCallback
		)
	}

	@ExperimentalCoroutinesApi
	
	val populatePopup: Flow<Menu> by lazy {
		callbackSignalFlow(Signals.POPULATE_POPUP, staticPopulatePopupCallback)
	}

	constructor(
		label: String?, isMnemonic: Boolean = false
	) : this(
		(if (!isMnemonic) gtk_label_new(label) else gtk_label_new_with_mnemonic(
			label
		))!!.reinterpret()
	)

	fun setMarkup(string: String, isMnemonic: Boolean = false) {
		if (!isMnemonic)
			gtk_label_set_markup(labelPointer, string)
		else gtk_label_set_markup_with_mnemonic(labelPointer, string)
	}

	fun setPattern(pattern: String) {
		gtk_label_set_pattern(labelPointer, pattern)
	}

	fun setTextMnemonic(text: String) {
		gtk_label_set_text_with_mnemonic(labelPointer, text)
	}

	fun selectRegion(startOffset: Int, endOffset: Int) {
		gtk_label_select_region(labelPointer, startOffset, endOffset)
	}

	companion object {
		internal val staticPopulatePopupCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  popup: CPointer<GtkMenu>,
			                  data: gpointer? ->
				data?.asStableRef<(Widget) -> Unit>()?.get()
					?.invoke(Menu(popup))
				Unit
			}.reinterpret()

	}
}