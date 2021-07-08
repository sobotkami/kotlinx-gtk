package nativex.gtk.widgets.misc.label

import gtk.*
import kotlinx.cinterop.*
import nativex.async.ActivateLinkFunction
import nativex.async.PopulatePopupFunction
import nativex.async.activateLinkSignalManager
import nativex.async.populatePopupSignalManager
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.signalManager
import nativex.gtk.asWidgetOrNull
import nativex.gtk.common.enums.Justification
import nativex.gtk.common.events.ExtendedMoveCursorFunction
import nativex.gtk.common.events.staticExtendedMoveCursorFunction
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.misc.Misc
import nativex.pango.AttrList
import nativex.pango.AttrList.Companion.wrap
import nativex.pango.EllipsizeMode
import nativex.pango.Layout
import nativex.pango.Layout.Companion.wrap
import nativex.pango.WrapMode

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html">GtkLabel</a>
 */
open class Label(
	val labelPointer: CPointer<GtkLabel>
) : Misc(labelPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-text">gtk_label_get_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-text">gtk_label_set_text</a>
	 */
	var text: String
		get() = gtk_label_get_text(labelPointer)!!.toKString()
		set(value) = gtk_label_set_text(labelPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-mnemonic-keyval">
	 *     gtk_label_get_mnemonic_keyval</a>
	 */
	val mnemonicKeyval: UInt
		get() = gtk_label_get_mnemonic_keyval(labelPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-justify">
	 *     gtk_label_get_justify</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-justify">
	 *     gtk_label_set_justify</a>
	 */
	var justification: Justification
		get() = Justification.valueOf(gtk_label_get_justify(labelPointer))!!
		set(value) = gtk_label_set_justify(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-xalign">
	 *     gtk_label_get_xalign</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-xalign">
	 *     gtk_label_set_xalign</a>
	 */
	var xAlign: Float
		get() = gtk_label_get_xalign(labelPointer)
		set(value) = gtk_label_set_xalign(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-yalign">
	 *     gtk_label_get_yalign</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-yalign">
	 *     gtk_label_set_yalign</a>
	 */
	var yAlign: Float
		get() = gtk_label_get_yalign(labelPointer)
		set(value) = gtk_label_set_yalign(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-ellipsize">
	 *     gtk_label_get_ellipsize</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-ellipsize">
	 *     gtk_label_set_ellipsize</a>
	 */
	var ellipsize: EllipsizeMode
		get() = EllipsizeMode.valueOf(
			gtk_label_get_ellipsize(
				labelPointer
			)
		)!!
		set(value) = gtk_label_set_ellipsize(labelPointer, value.pango)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-width-chars">
	 *     gtk_label_get_width_chars</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-width-chars">
	 *     gtk_label_set_width_chars</a>
	 */
	var widthChars: Int
		get() = gtk_label_get_width_chars(labelPointer)
		set(value) = gtk_label_set_width_chars(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-max-width-chars">
	 *     gtk_label_get_max_width_chars</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-max-width-chars">
	 *     gtk_label_set_max_width_chars</a>
	 */
	var maxWidthChars: Int
		get() = gtk_label_get_max_width_chars(labelPointer)
		set(value) = gtk_label_set_max_width_chars(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-label">
	 *     gtk_label_get_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-label">
	 *     gtk_label_set_label</a>
	 */
	var label: String
		get() = gtk_label_get_label(labelPointer)!!.toKString()
		set(value) = gtk_label_set_label(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-layout">
	 *     gtk_label_get_layout</a>
	 */
	val layout: Layout
		get() = gtk_label_get_layout(labelPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-line-wrap">
	 *     gtk_label_get_line_wrap</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-line-wrap">
	 *     gtk_label_set_line_wrap</a>
	 */
	var lineWrap: Boolean
		get() = gtk_label_get_line_wrap(labelPointer).bool
		set(value) = gtk_label_set_line_wrap(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-line-wrap-mode">
	 *     gtk_label_get_line_wrap_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-line-wrap-mode">
	 *     gtk_label_set_line_wrap_mode</a>
	 */
	var lineWrapMode: WrapMode
		get() = WrapMode.valueOf(gtk_label_get_line_wrap_mode(labelPointer))!!
		set(value) = gtk_label_set_line_wrap_mode(labelPointer, value.pango)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-attributes">
	 *     gtk_label_get_attributes</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-attributes">
	 *     gtk_label_set_attributes</a>
	 */
	var attributes: AttrList?
		get() = gtk_label_get_attributes(labelPointer).wrap()
		set(value) = gtk_label_set_attributes(labelPointer, value?.attrListPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-lines">gtk_label_get_lines</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-lines">gtk_label_set_lines</a>
	 */
	var lines: Int
		get() = gtk_label_get_lines(labelPointer)
		set(value) = gtk_label_set_lines(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-mnemonic-widget">
	 *     gtk_label_get_mnemonic_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-mnemonic-widget">
	 *     gtk_label_set_mnemonic_widget</a>
	 */
	var mnemonicWidget: Widget?
		get() = gtk_label_get_mnemonic_widget(labelPointer).asWidgetOrNull()
		set(value) = gtk_label_set_mnemonic_widget(
			labelPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-selection-bounds">
	 *     gtk_label_get_selection_bounds</a>
	 */
	val selectionBounds: Pair<Int, Int>?
		get() = memScoped {
			val s = cValue<IntVar>()
			val e = cValue<IntVar>()
			if (gtk_label_get_selection_bounds(labelPointer, s, e).bool)
				s.ptr.pointed.value to e.ptr.pointed.value
			else null
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-layout-offsets">
	 *     gtk_label_get_layout_offsets</a>
	 */
	val layoutOffsets: Pair<Int, Int>
		get() = memScoped {
			val x = cValue<IntVar>()
			val y = cValue<IntVar>()
			gtk_label_get_layout_offsets(labelPointer, x, y)
			x.ptr.pointed.value to y.ptr.pointed.value
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-use-markup">
	 *     gtk_label_get_use_markup</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-use-markup">
	 *     gtk_label_set_use_markup</a>
	 */
	var useMarkup: Boolean
		get() = gtk_label_get_use_markup(labelPointer).bool
		set(value) = gtk_label_set_use_markup(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-use-underline">
	 *     gtk_label_get_use_underline</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-use-underline">
	 *     gtk_label_set_use_underline</a>
	 */
	var useUnderline: Boolean
		get() = gtk_label_get_use_underline(labelPointer).bool
		set(value) = gtk_label_set_use_underline(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-single-line-mode">
	 *     gtk_label_get_single_line_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-single-line-mode">
	 *     gtk_label_set_single_line_mode</a>
	 */
	var singleLineMode: Boolean
		get() = gtk_label_get_single_line_mode(labelPointer).bool
		set(value) = gtk_label_set_single_line_mode(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-angle">
	 *     gtk_label_get_angle</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-angle">
	 *     gtk_label_set_angle</a>
	 */
	var angle: Double
		get() = gtk_label_get_angle(labelPointer)
		set(value) = gtk_label_set_angle(labelPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-current-uri">
	 *     gtk_label_get_current_uri</a>
	 */
	val currentUri: String
		get() = gtk_label_get_current_uri(labelPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-track-visited-links">
	 *     gtk_label_get_line_wrap</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-track-visited-links">
	 *     gtk_label_set_line_wrap</a>
	 */
	var trackVisitedLinks: Boolean
		get() = gtk_label_get_line_wrap(labelPointer).bool
		set(value) = gtk_label_set_line_wrap(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-get-selectable">
	 *     gtk_label_get_selectable</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-selectable">
	 *     gtk_label_set_selectable</a>
	 */
	var selectable: Boolean
		get() = gtk_label_get_selectable(labelPointer).bool
		set(value) = gtk_label_set_selectable(labelPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#GtkLabel-activate-current-link">
	 *     activate-current-link</a>
	 */
	fun addOnActivateCurrentLinkCallback(action: () -> Unit): SignalManager =
		signalManager(labelPointer, Signals.ACTIVATE_CURRENT_LINK, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#GtkLabel-activate-link">activate-link</a>
	 */
	fun addOnActivateLinkCallback(action: ActivateLinkFunction): SignalManager =
		activateLinkSignalManager(labelPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#GtkLabel-copy-clipboard">copy-clipboard</a>
	 */
	fun addOnCopyClipboardCallback(action: () -> Unit): SignalManager =
		signalManager(labelPointer, Signals.COPY_CLIPBOARD, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#GtkLabel-move-cursor">move-cursor</a>
	 */
	fun addOnMoveCursorCallback(action: ExtendedMoveCursorFunction): SignalManager =
		signalManager(labelPointer, Signals.MOVE_CURSOR, staticExtendedMoveCursorFunction)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#GtkLabel-populate-popup">populate-popup</a>
	 */
	fun addOnPopulatePopupCallback(action: PopulatePopupFunction): SignalManager =
		populatePopupSignalManager(labelPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-new">gtk_label_new</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-new-with-mnemonic">
	 *     gtk_label_new_with_mnemonic</a>
	 */
	constructor(
		label: String?, isMnemonic: Boolean = false
	) : this(
		(if (!isMnemonic) gtk_label_new(label) else gtk_label_new_with_mnemonic(
			label
		))!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-markup">
	 *     gtk_label_set_markup</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-markup-with-mnemonic">
	 *     gtk_label_set_markup_with_mnemonic</a>
	 */
	fun setMarkup(string: String, isMnemonic: Boolean = false) {
		if (!isMnemonic)
			gtk_label_set_markup(labelPointer, string)
		else gtk_label_set_markup_with_mnemonic(labelPointer, string)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-pattern">
	 *     gtk_label_set_pattern</a>
	 */
	fun setPattern(pattern: String) {
		gtk_label_set_pattern(labelPointer, pattern)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-set-text-with-mnemonic">
	 *     gtk_label_set_text_with_mnemonic</a>
	 */
	fun setTextMnemonic(text: String) {
		gtk_label_set_text_with_mnemonic(labelPointer, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLabel.html#gtk-label-select-region">
	 *     gtk_label_select_region</a>
	 */
	fun selectRegion(startOffset: Int, endOffset: Int) {
		gtk_label_select_region(labelPointer, startOffset, endOffset)
	}
}