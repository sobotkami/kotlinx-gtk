package nativex.gtk.widgets.entry

import gtk.*
import gtk.GtkEntryIconPosition.GTK_ENTRY_ICON_PRIMARY
import gtk.GtkEntryIconPosition.GTK_ENTRY_ICON_SECONDARY
import gtk.GtkInputPurpose.*
import kotlinx.cinterop.*
import nativex.async.SignalManager
import nativex.async.staticCStringCallback
import nativex.gdk.Event
import nativex.gdk.Event.Companion.wrap
import nativex.gdk.Pixbuf
import nativex.gdk.Pixbuf.Companion.wrap
import nativex.gdk.Rectangle
import nativex.gdk.Rectangle.Companion.wrap
import nativex.gdk.dragndrop.DragAction
import nativex.gio.Icon
import nativex.gio.ImplIcon.Companion.wrap
import nativex.gtk.*
import nativex.gtk.Adjustment.Companion.wrap
import nativex.gtk.EntryBuffer.Companion.wrap
import nativex.gtk.EntryCompletion.Companion.wrap
import nativex.gtk.common.enums.DeleteType
import nativex.gtk.common.events.ExtendedMoveCursorFunction
import nativex.gtk.common.events.staticExtendedMoveCursorFunction
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Widget.Companion.wrap
import nativex.gtk.widgets.misc.Image
import nativex.pango.AttrList
import nativex.pango.AttrList.Companion.wrap
import nativex.pango.Layout
import nativex.pango.Layout.Companion.wrap
import nativex.pango.TabArray
import nativex.pango.TabArray.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html">GtkEntry</a>
 */
open class Entry(val entryPointer: CPointer<GtkEntry>) : Widget(entryPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-new">gtk_entry_new</a>
	 */
	constructor() : this(gtk_entry_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-new-with-buffer">
	 *     gtk_entry_new_with_buffer</a>
	 */
	constructor(buffer: EntryBuffer) : this(gtk_entry_new_with_buffer(buffer.entryBufferPointer)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-buffer">gtk_entry_get_buffer</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-buffer">gtk_entry_set_buffer</a>
	 */
	var buffer: EntryBuffer
		get() = gtk_entry_get_buffer(entryPointer)!!.wrap()
		set(value) = gtk_entry_set_buffer(entryPointer, value.entryBufferPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-text">gtk_entry_get_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-text">gtk_entry_set_text</a>
	 */
	var text: String?
		get() = gtk_entry_get_text(entryPointer)?.toKString()
		set(value) = gtk_entry_set_text(entryPointer, value)
	/**
	 * @see <a href=""></a>
	 */

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-text-length">
	 *     gtk_entry_get_text_length</a>
	 */
	val textLength: UShort
		get() = gtk_entry_get_text_length(entryPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-text-area">
	 *     gtk_entry_get_text_area</a>
	 */
	val textArea: Rectangle
		get() = memScoped {
			val ta = cValue<GdkRectangle>()
			gtk_entry_get_text_area(entryPointer, ta)
			ta.ptr.wrap()
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-visibility">
	 *     gtk_entry_get_visibility</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-visibility">
	 *     gtk_entry_set_visibility</a>
	 */
	var isContentVisible: Boolean
		get() = gtk_entry_get_visibility(entryPointer).bool
		set(value) = gtk_entry_set_visibility(entryPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-invisible-char">
	 *     gtk_entry_get_invisible_char</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-invisible-char">
	 *     gtk_entry_set_invisible_char</a>
	 */
	var invisibleChar: Char
		get() = gtk_entry_get_invisible_char(entryPointer).toInt().toChar()
		set(value) = gtk_entry_set_invisible_char(entryPointer, value.code.toUInt())


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-unset-invisible-char">
	 *     gtk_entry_unset_invisible_char</a>
	 */
	fun unsetInvisibleChar() {
		gtk_entry_unset_invisible_char(entryPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-max-length">
	 *     gtk_entry_get_max_length</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-max-length">
	 *     gtk_entry_set_max_length</a>
	 */
	var maxLength: Int
		get() = gtk_entry_get_max_length(entryPointer)
		set(max) = gtk_entry_set_max_length(entryPointer, max)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-activates-default">
	 *     gtk_entry_get_activates_default</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-activates-default">
	 *     gtk_entry_set_activates_default</a>
	 */
	var activatesDefault: Boolean
		get() = gtk_entry_get_activates_default(entryPointer).bool
		set(value) = gtk_entry_set_activates_default(entryPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-has-frame">
	 *     gtk_entry_get_has_frame</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-has-frame">
	 *     gtk_entry_set_has_frame</a>
	 */
	var hasFrame: Boolean
		get() = gtk_entry_get_has_frame(entryPointer).bool
		set(value) = gtk_entry_set_has_frame(entryPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-width-chars">
	 *     gtk_entry_get_width_chars</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-width-chars">
	 *     gtk_entry_set_width_chars</a>
	 */
	var widthChars: Int
		get() = gtk_entry_get_width_chars(entryPointer)
		set(value) = gtk_entry_set_width_chars(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-max-width-chars">
	 *     gtk_entry_get_max_width_chars</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-max-width-chars">
	 *     gtk_entry_set_max_width_chars</a>
	 */
	var maxWidthChars: Int
		get() = gtk_entry_get_max_width_chars(entryPointer)
		set(value) = gtk_entry_set_max_width_chars(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-alignment">
	 *     gtk_entry_get_alignment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-alignment">
	 *     gtk_entry_set_alignment</a>
	 */
	var alignment: Float
		get() = gtk_entry_get_alignment(entryPointer)
		set(value) = gtk_entry_set_alignment(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-placeholder-text">
	 *     gtk_entry_get_placeholder_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-placeholder-text">
	 *     gtk_entry_set_placeholder_text</a>
	 */
	var placeholderText: String
		get() = gtk_entry_get_placeholder_text(entryPointer)!!.toKString()
		set(value) = gtk_entry_set_placeholder_text(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-overwrite-mode">
	 *     gtk_entry_get_overwrite_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-overwrite-mode">
	 *     gtk_entry_set_overwrite_mode</a>
	 */
	var overwriteMode: Boolean
		get() = gtk_entry_get_overwrite_mode(entryPointer).bool
		set(value) = gtk_entry_set_overwrite_mode(entryPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-layout">gtk_entry_get_layout</a>
	 */
	val layout: Layout
		get() = gtk_entry_get_layout(entryPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-layout-offsets">
	 *     gtk_entry_get_layout_offsets</a>
	 */
	val layoutOffsets: Pair<Int, Int>
		get() = memScoped {
			val x = cValue<IntVar>()
			val y = cValue<IntVar>()
			gtk_entry_get_layout_offsets(entryPointer, x, y)
			x.ptr.pointed.value to y.ptr.pointed.value
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-layout-index-to-text-index">
	 *     gtk_entry_layout_index_to_text_index</a>
	 */
	fun layoutIndexToTextIndex(layoutIndex: Int): Int =
		gtk_entry_layout_index_to_text_index(entryPointer, layoutIndex)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-text-index-to-layout-index">
	 *     gtk_entry_text_index_to_layout_index</a>
	 */
	fun textIndexToLayoutIndex(textIndex: Int): Int =
		gtk_entry_text_index_to_layout_index(entryPointer, textIndex)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-attributes">
	 *     gtk_entry_get_attributes</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-attributes">
	 *     gtk_entry_set_attributes</a>
	 */
	var attributes: AttrList?
		get() = gtk_entry_get_attributes(entryPointer).wrap()
		set(value) = gtk_entry_set_attributes(entryPointer, value?.attrListPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-completion">
	 *     gtk_entry_get_completion</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-completion">
	 *     gtk_entry_set_completion</a>
	 */
	var completion: EntryCompletion?
		get() = gtk_entry_get_completion(entryPointer).wrap()
		set(value) = gtk_entry_set_completion(entryPointer, value?.entryCompletionPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-cursor-hadjustment">
	 *     gtk_entry_get_cursor_hadjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-cursor-hadjustment">
	 *     gtk_entry_set_cursor_hadjustment</a>
	 */
	var cursorHorizontalAdjustment: Adjustment?
		get() = gtk_entry_get_cursor_hadjustment(entryPointer).wrap()
		set(value) = gtk_entry_set_cursor_hadjustment(entryPointer, value?.adjustmentPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-progress-fraction">
	 *     gtk_entry_get_progress_fraction</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-progress-fraction">
	 *     gtk_entry_set_progress_fraction</a>
	 */
	var progressFraction: Double
		get() = gtk_entry_get_progress_fraction(entryPointer)
		set(value) = gtk_entry_set_progress_fraction(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-progress-pulse-step">
	 *     gtk_entry_get_progress_pulse_step</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-progress-pulse-step">
	 *     gtk_entry_set_progress_pulse_step</a>
	 */
	var progressPulseStep: Double
		get() = gtk_entry_get_progress_pulse_step(entryPointer)
		set(value) = gtk_entry_set_progress_pulse_step(entryPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-progress-pulse">
	 *     gtk_entry_progress_pulse</a>
	 */
	fun progressPulse() {
		gtk_entry_progress_pulse(entryPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-im-context-filter-keypress">
	 *     gtk_entry_im_context_filter_keypress</a>
	 */
	fun imContextFilterKeypress(event: Event.Key): Boolean =
		gtk_entry_im_context_filter_keypress(entryPointer, event.eventKeyPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-reset-im-context">
	 *     gtk_entry_reset_im_context</a>
	 */
	fun resetImContext() {
		gtk_entry_reset_im_context(entryPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-tabs">gtk_entry_get_tabs</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-tabs">gtk_entry_set_tabs</a>
	 */
	var tabs: TabArray?
		get() = gtk_entry_get_tabs(entryPointer)?.wrap()
		set(value) = gtk_entry_set_tabs(entryPointer, value?.tabArrayPointer)

	/**
	 * @see <a href=""></a>
	 */
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-from-pixbuf">
	 *     gtk_entry_set_icon_from_pixbuf</a>
	 */
	fun setIconFromPixbuf(iconPosition: IconPosition, pixbuf: Pixbuf?) {
		gtk_entry_set_icon_from_pixbuf(entryPointer, iconPosition.gtk, pixbuf?.pixbufPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-from-icon-name">
	 *     gtk_entry_set_icon_from_icon_name</a>
	 */
	fun setIconFromIconName(iconPosition: IconPosition, iconName: String?) {
		gtk_entry_set_icon_from_icon_name(entryPointer, iconPosition.gtk, iconName)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-from-gicon">
	 *     gtk_entry_set_icon_from_gicon</a>
	 */
	fun setIconFromGIcon(iconPosition: IconPosition, icon: Icon?) {
		gtk_entry_set_icon_from_gicon(entryPointer, iconPosition.gtk, icon?.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-storage-type">
	 *     gtk_entry_get_icon_storage_type</a>
	 */
	fun getIconStorageType(iconPosition: IconPosition): Image.Type =
		Image.Type.valueOf(gtk_entry_get_icon_storage_type(entryPointer, iconPosition.gtk))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-pixbuf">
	 *     gtk_entry_get_icon_pixbuf</a>
	 */
	fun getIconPixbuf(iconPosition: IconPosition): Pixbuf? =
		gtk_entry_get_icon_pixbuf(entryPointer, iconPosition.gtk).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-name">
	 *     gtk_entry_get_icon_name</a>
	 */
	fun getIconName(iconPosition: IconPosition): String? =
		gtk_entry_get_icon_name(entryPointer, iconPosition.gtk)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-gicon">
	 *     gtk_entry_get_icon_gicon</a>
	 */
	fun getIconGIcon(iconPosition: IconPosition): Icon? =
		gtk_entry_get_icon_gicon(entryPointer, iconPosition.gtk).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-activatable">
	 *     gtk_entry_set_icon_activatable</a>
	 */
	fun setIconCanActivate(iconPosition: IconPosition, canActivate: Boolean) {
		gtk_entry_set_icon_activatable(entryPointer, iconPosition.gtk, canActivate.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-activatable">
	 *     gtk_entry_get_icon_activatable</a>
	 */
	fun getIconCanActivate(iconPosition: IconPosition): Boolean =
		gtk_entry_get_icon_activatable(entryPointer, iconPosition.gtk).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-sensitive">
	 *     gtk_entry_set_icon_sensitive</a>
	 */
	fun setIconSensitive(iconPosition: IconPosition, sensitive: Boolean) {
		gtk_entry_set_icon_sensitive(entryPointer, iconPosition.gtk, sensitive.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-sensitive">
	 *     gtk_entry_get_icon_sensitive</a>
	 */
	fun getIconSensitive(iconPosition: IconPosition): Boolean =
		gtk_entry_get_icon_sensitive(entryPointer, iconPosition.gtk).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-at-pos">
	 *     gtk_entry_get_icon_at_pos</a>
	 */
	fun getIconAtPosition(x: Int, y: Int): Int =
		gtk_entry_get_icon_at_pos(entryPointer, x, y)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-tooltip-text">
	 *     gtk_entry_set_icon_tooltip_text</a>
	 */
	fun setIconTooltipText(iconPosition: IconPosition, tooltip: String) {
		gtk_entry_set_icon_tooltip_text(entryPointer, iconPosition.gtk, tooltip)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-tooltip-text">
	 *     gtk_entry_get_icon_tooltip_text</a>
	 */
	fun getIconTooltipText(iconPosition: IconPosition): String? =
		gtk_entry_get_icon_tooltip_text(entryPointer, iconPosition.gtk)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-tooltip-markup">
	 *     gtk_entry_set_icon_tooltip_markup</a>
	 */
	fun setIconTooltipMarkup(iconPosition: IconPosition, tooltip: String) {
		gtk_entry_set_icon_tooltip_markup(entryPointer, iconPosition.gtk, tooltip)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-tooltip-markup">
	 *     gtk_entry_get_icon_tooltip_markup</a>
	 */
	fun getIconTooltipMarkup(iconPosition: IconPosition): String? =
		gtk_entry_get_icon_tooltip_markup(entryPointer, iconPosition.gtk)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-icon-drag-source">
	 *     gtk_entry_set_icon_drag_source</a>
	 */
	fun setIconDragSource(iconPosition: IconPosition, targetList: TargetList, actions: DragAction) {
		gtk_entry_set_icon_drag_source(entryPointer, iconPosition.gtk, targetList.targetListPointer, actions.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-current-icon-drag-source">
	 *     gtk_entry_get_current_icon_drag_source</a>
	 */
	val currentIconDragSource: Int
		get() = gtk_entry_get_current_icon_drag_source(entryPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-icon-area">
	 *     gtk_entry_get_icon_area</a>
	 */
	fun getIconArea(iconPosition: IconPosition): Rectangle = memScoped {
		val rectangle = cValue<GdkRectangle>()
		gtk_entry_get_icon_area(entryPointer, iconPosition.gtk, rectangle)
		rectangle.ptr.wrap()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-input-purpose">
	 *     gtk_entry_get_input_purpose</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-input-purpose">
	 *     gtk_entry_set_input_purpose</a>
	 */
	var purpose: InputPurpose?
		get() = InputPurpose.valueOf(gtk_entry_get_input_purpose(entryPointer))
		set(value) = gtk_entry_set_input_purpose(entryPointer, value!!.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-input-hints">
	 *     gtk_entry_set_input_hints</a>
	 */
	fun setInputHint(hint: InputHints) {
		gtk_entry_set_input_hints(entryPointer, hint.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-input-hints">
	 *     gtk_entry_get_input_hints</a>
	 */
	fun getInputHint(): InputHints? =
		InputHints.valueOf(gtk_entry_get_input_hints(entryPointer))

	/**
	 * This was created to allow bitwise operations
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-set-input-hints">
	 *     gtk_entry_set_input_hints</a>
	 */
	fun setInputHints(hints: @InputHints.AInputHint UInt) {
		gtk_entry_set_input_hints(entryPointer, hints)
	}

	/**
	 * This was created to allow bitwise operations
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-get-input-hints">
	 *     gtk_entry_get_input_hints</a>
	 */
	fun getInputHints(): @InputHints.AInputHint UInt =
		gtk_entry_get_input_hints(entryPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#gtk-entry-grab-focus-without-selecting">
	 *     gtk_entry_grab_focus_without_selecting</a>
	 */
	fun grabFocusWithoutSelecting() {
		gtk_entry_grab_focus_without_selecting(entryPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-activate">activate</a>
	 */
	fun addOnActivateCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.ACTIVATE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-backspace">backspace</a>
	 */
	fun addOnBackspaceCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.BACKSPACE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-copy-clipboard">copy-clipboard</a>
	 */
	fun addOnCopyClipboardCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.COPY_CLIPBOARD,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-cut-clipboard">cut-clipboard</a>
	 */
	fun addOnCutClipboardCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.CUT_CLIPBOARD,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-delete-from-cursor">
	 *     delete-from-cursor</a>
	 */
	fun addOnDeleteFromCursorCallback(action: DeleteFromCursorFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.DELETE_FROM_CURSOR,
				staticDeleteFromCursorFunction,
				StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-press">icon-press</a>
	 */
	fun addOnIconPressCallback(action: IconInteractionEventFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.ICON_PRESS,
				staticIconInteractionEventFunction,
				StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-release">icon-release</a>
	 */
	fun addOnIconReleaseCallback(action: IconInteractionEventFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.ICON_RELEASE,
				staticIconInteractionEventFunction,
				StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-insert-at-cursor">
	 *     insert-at-cursor</a>
	 */
	fun addOnInsertAtCursorCallback(action: (String) -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.PREEDIT_CHANGED,
				staticCStringCallback,
				StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-insert-emoji">insert-emoji</a>
	 */
	fun addOnInsertEmojiCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.INSERT_EMOJI,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-move-cursor">move-cursor</a>
	 */
	fun addOnMoveCursorCallback(action: ExtendedMoveCursorFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.MOVE_CURSOR,
				staticExtendedMoveCursorFunction,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-paste-clipboard">paste-clipboard</a>
	 */
	fun addOnPasteClipboardCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.PASTE_CLIPBOARD,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-populate-popup">populate-popup</a>
	 */
	fun addOnPopulatePopupCallback(action: PopulatePopupFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.POPULATE_POPUP,
				staticPopulatePopupFunction,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-preedit-changed">preedit-changed</a>
	 */
	fun addOnPreeditChangedCallback(action: PreEditChangedFunction): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.PREEDIT_CHANGED,
				staticCStringCallback,
				StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-toggle-overwrite">toggle-overwrite</a>
	 */
	fun addOnToggleOverwriteCallback(action: () -> Unit): SignalManager =
		SignalManager(
			entryPointer,
			entryPointer.connectSignal(
				Signals.TOGGLE_OVERWRITE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntryIconPosition">GtkEntryIconPosition</a>
	 */
	enum class IconPosition(val gtk: GtkEntryIconPosition) {
		/**
		 * At the beginning of the entry (depending on the text direction).
		 */
		PRIMARY(GTK_ENTRY_ICON_PRIMARY),

		/**
		 * At the end of the entry (depending on the text direction).
		 */
		SECONDARY(GTK_ENTRY_ICON_SECONDARY);

		companion object {
			fun valueOf(gtk: GtkEntryIconPosition) = values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkInputPurpose">GtkInputPurpose</a>
	 */
	enum class InputPurpose(val gtk: GtkInputPurpose) {
		/**
		 * Allow any character
		 */
		FREE_FORM(GTK_INPUT_PURPOSE_FREE_FORM),

		/**
		 * Allow only alphabetic characters
		 */
		ALPHA(GTK_INPUT_PURPOSE_ALPHA),

		/**
		 * Allow only digits
		 */
		DIGITS(GTK_INPUT_PURPOSE_DIGITS),

		/**
		 * Edited field expects numbers
		 */
		NUMBER(GTK_INPUT_PURPOSE_NUMBER),

		/**
		 * Edited field expects phone number
		 */
		PHONE(GTK_INPUT_PURPOSE_PHONE),

		/**
		 * Edited field expects URL
		 */
		URL(GTK_INPUT_PURPOSE_URL),

		/**
		 * Edited field expects email address
		 */
		EMAIL(GTK_INPUT_PURPOSE_EMAIL),

		/**
		 * Edited field expects the name of a person
		 */
		NAME(GTK_INPUT_PURPOSE_NAME),

		/**
		 * Like [FREE_FORM], but characters are hidden
		 */
		PASSWORD(GTK_INPUT_PURPOSE_PASSWORD),

		/**
		 * Like [DIGITS], but characters are hidden
		 */
		PIN(GTK_INPUT_PURPOSE_PIN),

		/**
		 * Allow any character, in addition to control codes
		 */
		TERMINAL(GTK_INPUT_PURPOSE_TERMINAL);

		companion object {
			fun valueOf(gtk: GtkInputPurpose) = values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkInputHints">GtkInputHints</a>
	 */
	enum class InputHints(val gtk: @AInputHint GtkInputHints) {
		/**
		 * No special behaviour suggested
		 */
		NONE(GTK_INPUT_HINT_NONE),

		/**
		 * Suggest checking for typos
		 */
		SPELLCHECK(GTK_INPUT_HINT_SPELLCHECK),

		/**
		 * Suggest not checking for typos
		 */
		NO_SPELLCHECK(GTK_INPUT_HINT_NO_SPELLCHECK),

		/**
		 * Suggest word completion
		 */
		WORD_COMPLETION(GTK_INPUT_HINT_WORD_COMPLETION),

		/**
		 * Suggest to convert all text to lowercase
		 */
		LOWERCASE(GTK_INPUT_HINT_LOWERCASE),

		/**
		 * Suggest to capitalize all text
		 */
		UPPERCASE_CHARS(GTK_INPUT_HINT_UPPERCASE_CHARS),

		/**
		 * Suggest to capitalize the first character of each word
		 */
		UPPERCASE_WORDS(GTK_INPUT_HINT_UPPERCASE_WORDS),

		/**
		 * Suggest to capitalize the first word of each sentence
		 */
		UPPERCASE_SENTENCES(GTK_INPUT_HINT_UPPERCASE_SENTENCES),

		/**
		 * Suggest to not show an onscreen keyboard (e.g for a calculator that already has all the keys).
		 */
		INHIBIT_OSK(GTK_INPUT_HINT_INHIBIT_OSK),

		/**
		 * The text is vertical.
		 *
		 * @since 3.18
		 */
		VERTICAL_WRITING(GTK_INPUT_HINT_VERTICAL_WRITING),

		/**
		 * Suggest offering Emoji support.
		 *
		 * @since 3.22.20
		 */
		EMOJI(GTK_INPUT_HINT_EMOJI),

		/**
		 * Suggest not offering Emoji support.
		 *
		 * @since 3.22.20
		 */
		NO_EMOJI(GTK_INPUT_HINT_NO_EMOJI);

		/**
		 * Specifies that the target is a collection of InputHints
		 */
		@Target(AnnotationTarget.TYPE)
		annotation class AInputHint


		companion object {
			fun valueOf(gtk: GtkInputHints) = values().find { it.gtk == gtk }

			/**
			 * @see [UInt.and]
			 */
			infix fun InputHints.and(hint: InputHints): @AInputHint UInt = gtk and hint.gtk

			/**
			 * @see [UInt.or]
			 */
			infix fun InputHints.or(hint: InputHints): @AInputHint UInt = gtk or hint.gtk

			/**
			 * @see [UInt.and]
			 */
			infix fun @AInputHint UInt.and(hint: InputHints): @AInputHint UInt = this and hint.gtk

			/**
			 * @see [UInt.or]
			 */
			infix fun @AInputHint UInt.or(hint: InputHints): @AInputHint UInt = this or hint.gtk

		}
	}
}

typealias PopulatePopupFunction = (Widget) -> Unit

internal val staticPopulatePopupFunction: GCallback =
	staticCFunction { _: WidgetPointer, previous: WidgetPointer, data: gpointer ->
		data.asStableRef<(Widget) -> Unit>().get().invoke(previous.wrap())
		Unit
	}.reinterpret()

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-delete-from-cursor">delete-from-cursor</a>
 */
typealias DeleteFromCursorFunction = (DeleteType, @ParameterName("count") Int) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-delete-from-cursor">delete-from-cursor</a>
 */
val staticDeleteFromCursorFunction: GCallback =
	staticCFunction { _: gpointer?, type: GtkDeleteType, count: Int, data: gpointer? ->
		data?.asStableRef<DeleteFromCursorFunction>()?.get()
			?.invoke(
				DeleteType.valueOf(type)!!,
				count
			)
		Unit
	}.reinterpret()

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-press">icon-press</a>
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-release">icon-release</a>
 */
typealias IconInteractionEventFunction = (Entry.IconPosition, Event) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-press">icon-press</a>
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-icon-release">icon-release</a>
 */
val staticIconInteractionEventFunction: GCallback =
	staticCFunction { _: gpointer?, type: GtkEntryIconPosition, event: CPointer<GdkEvent>, data: gpointer? ->
		data?.asStableRef<IconInteractionEventFunction>()?.get()
			?.invoke(Entry.IconPosition.valueOf(type)!!, event.wrap())
		Unit
	}.reinterpret()

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntry.html#GtkEntry-preedit-changed">preedit-changed</a>
 */
typealias PreEditChangedFunction = (@ParameterName("preEdit") String) -> Unit