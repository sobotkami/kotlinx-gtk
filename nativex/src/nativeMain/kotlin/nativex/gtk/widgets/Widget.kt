package nativex.gtk.widgets

import glib.GList
import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import gtk.GtkTextDirection.*
import kotlinx.cinterop.*
import nativex.cairo.FontOptions
import nativex.cairo.FontOptions.Companion.wrap
import nativex.gdk.Clipboard
import nativex.gdk.Clipboard.Companion.wrap
import nativex.gdk.Cursor
import nativex.gdk.Cursor.Companion.wrap
import nativex.gdk.Display
import nativex.gdk.Display.Companion.wrap
import nativex.gdk.FrameClock
import nativex.gdk.FrameClock.Companion.wrap
import nativex.gio.ActionGroup
import nativex.glib.*
import nativex.glib.Variant.Companion.toCArray
import nativex.gio.KGBytes
import nativex.glib.MutableWrappedKList
import nativex.glib.MutableWrappedKList.Companion.asMutableList
import nativex.gobject.*
import nativex.graphene.Matrix
import nativex.graphene.Matrix.Companion.wrap
import nativex.graphene.Point
import nativex.graphene.Point.Companion.wrap
import nativex.graphene.Rect
import nativex.graphene.Rect.Companion.wrap
import nativex.gsk.Transform
import nativex.gtk.*
import nativex.gtk.ImplNative.Companion.wrap
import nativex.gtk.ImplRoot.Companion.wrap
import nativex.gtk.LayoutManager.Companion.wrap
import nativex.gtk.Settings.Companion.wrap
import nativex.gtk.StyleContext.Companion.wrap
import nativex.gtk.Tooltip.Companion.wrap
import nativex.gtk.common.data.Requisition
import nativex.gtk.common.data.Requisition.Companion.wrap
import nativex.gtk.common.enums.*
import nativex.pango.Context
import nativex.pango.Context.Companion.wrap
import nativex.pango.FontMap
import nativex.pango.FontMap.Companion.wrap
import nativex.pango.Layout
import nativex.pango.Layout.Companion.wrap

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.Widget.html">GtkWidget</a>
 */
open class Widget(
	open val widgetPointer: WidgetPointer
) : KGObject(
	widgetPointer.reinterpret()
) {

	/*
	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.activate_action.html"></a>
	 */
	fun activateAction(name: String, formatString: String) {} // Ignored due to vararg
	*/


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.in_destruction.html"></a>
	 */
	val isInDestruction: Boolean
		get() = gtk_widget_in_destruction(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_can_focus.html"></a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_can_focus.html"></a>
	 */
	var canFocus: Boolean
		get() = gtk_widget_get_can_focus(widgetPointer).bool
		set(value) = gtk_widget_set_can_focus(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_can_target.html"></a>
	 */
	val canTarget: Boolean
		get() = gtk_widget_get_can_target(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_focus_on_click.html">gtk_widget_get_focus_on_click</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_focus_on_click.html"></a>
	 */
	var focusOnClick: Boolean
		get() = gtk_widget_get_focus_on_click(widgetPointer).bool
		set(value) = gtk_widget_set_focus_on_click(
			widgetPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_preferred_size.html"></a>
	 */
	val preferredSize: PreferredSize
		get() = memScoped {
			val min = cValue<GtkRequisition>()
			val nat = cValue<GtkRequisition>()

			gtk_widget_get_preferred_size(widgetPointer, min, nat)

			PreferredSize(
				min.ptr.wrap(),
				nat.ptr.wrap()
			)
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_halign.html">gtk_widget_get_halign</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_halign.html"></a>
	 */
	var horizontalAlign: Align
		get() = Align.valueOf(gtk_widget_get_halign(widgetPointer))!!
		set(value) = gtk_widget_set_halign(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.has_default.html"></a>
	 */
	val hasDefault: Boolean
		get() = gtk_widget_has_default(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.has_focus.html"></a>
	 */
	val hasFocus: Boolean
		get() = gtk_widget_has_focus(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_focusable.html">gtk_widget_get_focusable</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_focusable.html">gtk_widget_set_focusable</a>
	 */
	var focusable: Boolean
		get() = gtk_widget_get_focusable(widgetPointer).bool
		set(value) = gtk_widget_set_focusable(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_has_tooltip.html">
	 *     gtk_widget_get_has_tooltip</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_has_tooltip.html">
	 *     gtk_widget_set_has_tooltip</a>
	 */
	var hasTooltip: Boolean
		get() = gtk_widget_get_has_tooltip(widgetPointer).bool
		set(value) = gtk_widget_set_has_tooltip(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_hexpand.html">gtk_widget_get_hexpand</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_hexpand.html"></a>
	 */
	var horizontalExpand: Boolean
		get() = gtk_widget_get_hexpand(widgetPointer).bool
		set(value) =
			gtk_widget_set_hexpand(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_hexpand_set.html">gtk_widget_get_hexpand_set</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_hexpand_set.html"></a>
	 */
	var horizontalExpandSet: Boolean
		get() = gtk_widget_get_hexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_hexpand_set(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.is_focus.html">gtk_widget_is_focus</a>
	 */
	val isFocus: Boolean
		get() = gtk_widget_is_focus(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_margin_start.html">gtk_widget_get_margin_start</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_margin_start.html"></a>
	 */
	var marginStart: Int
		get() = gtk_widget_get_margin_start(widgetPointer)
		set(value) = gtk_widget_set_margin_start(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_margin_end.html">gtk_widget_get_margin_end</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_margin_end.html"></a>
	 */
	var marginEnd: Int
		get() = gtk_widget_get_margin_end(widgetPointer)
		set(value) = gtk_widget_set_margin_end(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_margin_top.html">gtk_widget_get_margin_top</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_margin_top.html"></a>
	 */
	var marginTop: Int
		get() = gtk_widget_get_margin_top(widgetPointer)
		set(value) = gtk_widget_set_margin_top(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_margin_bottom.html">gtk_widget_get_margin_bottom</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_margin_bottom.html">gtk_widget_set_margin_bottom</a>
	 */
	var marginBottom: Int
		get() = gtk_widget_get_margin_bottom(widgetPointer)
		set(value) = gtk_widget_set_margin_bottom(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_name.html">gtk_widget_get_name</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_name.html">gtk_widget_set_name</a>
	 */
	var name: String?
		get() = gtk_widget_get_name(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_name(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_scale_factor.html">gtk_widget_get_scale_factor</a>
	 */
	val scaleFactor: Int
		get() = gtk_widget_get_scale_factor(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_sensitive.html">gtk_widget_set_sensitive</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_sensitive.html">gtk_widget_get_sensitive</a>
	 */
	var sensitive: Boolean
		get() = gtk_widget_get_sensitive(widgetPointer).bool
		set(value) = gtk_widget_set_sensitive(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_parent.html">gtk_widget_get_parent</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_parent.html">gtk_widget_set_parent</a>
	 */
	var parent: Widget?
		get() = gtk_widget_get_parent(widgetPointer)?.let { Widget(it) }
		set(value) = gtk_widget_set_parent(widgetPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_valign.html"></a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_valign.html"></a>
	 */
	var verticalAlign: Align
		get() = Align.valueOf(gtk_widget_get_valign(widgetPointer))!!
		set(value) = gtk_widget_set_valign(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_vexpand.html"></a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_vexpand.html"></a>
	 */
	var verticalExpand: Boolean
		get() = gtk_widget_get_vexpand(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_vexpand_set.html"></a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_vexpand_set.html"></a>
	 */
	var verticalExpandSet: Boolean
		get() = gtk_widget_get_vexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand_set(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_root.html">gtk_widget_get_root</a>
	 */
	val root: Root?
		get() = gtk_widget_get_root(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_native.html">gtk_widget_get_native</a>
	 */
	val native: Native?
		get() = gtk_widget_get_native(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_frame_clock.html"></a>
	 */
	val frameClock: FrameClock?
		get() = gtk_widget_get_frame_clock(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_direction.html">
	 *     gtk_widget_set_direction
	 *     </a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_direction.html">
	 *     gtk_widget_get_direction
	 *     </a>
	 */
	var direction: TextDirection
		get() = TextDirection.valueOf(gtk_widget_get_direction(widgetPointer))!!
		set(value) = gtk_widget_set_direction(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_pango_context.html">gtk_widget_get_pango_context</a>
	 */
	val pangoContext: Context
		get() = gtk_widget_get_pango_context(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_font_options.html">gtk_widget_get_font_options</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_font_options.html">gtk_widget_set_font_options</a>
	 */
	var fontOptions: FontOptions?
		get() = gtk_widget_get_font_options(widgetPointer).wrap()
		set(value) = gtk_widget_set_font_options(widgetPointer, value?.pointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_font_map.html">gtk_widget_get_font_map</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_font_map.html">gtk_widget_set_font_map</a>
	 */
	var fontMap: FontMap?
		get() = gtk_widget_get_font_map(widgetPointer).wrap()
		set(value) = gtk_widget_set_font_map(widgetPointer, value?.fontMapPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_cursor.html">gtk_widget_get_cursor</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_cursor.html">gtk_widget_set_cursor</a>
	 */
	var cursor: Cursor?
		get() = gtk_widget_get_cursor(widgetPointer).wrap()
		set(value) = gtk_widget_set_cursor(widgetPointer, value?.cursorPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_child_visible.html">gtk_widget_get_child_visible</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_child_visible.html">gtk_widget_set_child_visible</a>
	 */
	var isChildVisible: Boolean
		get() = gtk_widget_get_child_visible(widgetPointer).bool
		set(value) = gtk_widget_set_child_visible(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_settings.html">
	 *     gtk_widget_get_settings</a>
	 */
	val settings: Settings
		get() = gtk_widget_get_settings(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_clipboard.html">
	 *     gtk_widget_get_clipboard</a>
	 */
	val clipboard: Clipboard
		get() = gtk_widget_get_clipboard(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_css_classes.html">
	 *     gtk_widget_get_css_classes</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_css_classes.html">
	 *     gtk_widget_set_css_classes</a>
	 */
	var cssClasses: List<String>
		get() = stringListFromNullTerminatedCStringListAndFree(gtk_widget_get_css_classes(widgetPointer))
		set(value) = gtk_widget_set_css_classes(widgetPointer, value.toNullTermCStringArray())

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_css_name.html">
	 *     gtk_widget_get_css_name</a>
	 */
	val cssName: String
		get() = gtk_widget_get_css_name(widgetPointer)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_primary_clipboard.html">
	 *     gtk_widget_get_primary_clipboard</a>
	 */
	val primaryClipboard: Clipboard
		get() = gtk_widget_get_primary_clipboard(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_display.html">gtk_widget_get_display</a>
	 */
	val display: Display
		get() = gtk_widget_get_display(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_size_request.html">
	 *     gtk_widget_get_size_request</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_size_request.html">
	 *     gtk_widget_set_size_request</a>
	 */
	var sizeRequest: Pair<Int, Int>
		get() = memScoped {
			val w = cValue<IntVar>()
			val h = cValue<IntVar>()
			gtk_widget_get_size_request(widgetPointer, w, h)
			w.ptr.pointed.value to h.ptr.pointed.value
		}
		set(value) = gtk_widget_set_size_request(widgetPointer, value.first, value.second)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.list_mnemonic_labels.html">
	 *     gtk_widget_list_mnemonic_labels</a>
	 */
	val mnemonicLabels: MutableWrappedKList<Widget>
		get() = gtk_widget_list_mnemonic_labels(widgetPointer)!!.asMutableWidgetList()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_tooltip_markup.html">
	 *     gtk_widget_get_tooltip_markup</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_tooltip_markup.html">
	 *     gtk_widget_set_tooltip_markup</a>
	 */
	var tooltipMarkup: String?
		get() = gtk_widget_get_tooltip_markup(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_tooltip_markup(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_tooltip_text.html">
	 *     gtk_widget_get_tooltip_text</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_tooltip_markup.html">
	 *     gtk_widget_set_tooltip_text</a>
	 */
	var tooltipText: String?
		get() = gtk_widget_get_tooltip_text(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_tooltip_text(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_allocated_width.html"></a>
	 */
	val allocatedWidth: Int
		get() = gtk_widget_get_allocated_width(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_allocated_height.html"></a>
	 */
	val allocatedHeight: Int
		get() = gtk_widget_get_allocated_height(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_allocation.html">gtk_widget_get_allocation</a>
	 */
	val allocation: Allocation
		get() = memScoped {
			val p = cValue<GtkAllocation>()
			gtk_widget_get_allocation(widgetPointer, p)
			Allocation(p.ptr)
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_width.html"></a>
	 */
	val width: Int
		get() = gtk_widget_get_width(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_height.html">gtk_widget_get_height</a>
	 */
	val height: Int
		get() = gtk_widget_get_height(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_allocated_baseline.html"></a>
	 */
	val allocatedBaseline: Int
		get() = gtk_widget_get_allocated_baseline(widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.is_sensitive.html"></a>
	 */
	val isSensitive: Boolean
		get() = gtk_widget_is_sensitive(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.is_visible.html"></a>
	 */
	val isVisible: Boolean
		get() = gtk_widget_is_visible(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_visible.html"></a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_visible.html"></a>
	 */
	var visible: Boolean
		get() = gtk_widget_get_visible(widgetPointer).bool
		set(value) = gtk_widget_set_visible(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.has_visible_focus.html"></a>
	 */
	val hasVisibleFocus: Boolean
		get() = gtk_widget_has_visible_focus(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.is_drawable.html"></a>
	 */
	val isDrawable: Boolean
		get() = gtk_widget_is_drawable(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_receives_default.html">gtk_widget_get_receives_default</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_receives_default.html">gtk_widget_set_receives_default</a>
	 */
	var receivesDefault: Boolean
		get() = gtk_widget_get_receives_default(widgetPointer).bool
		set(value) = gtk_widget_set_receives_default(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_realized.html">gtk_widget_get_realized</a>
	 */
	val realized: Boolean
		get() = gtk_widget_get_realized(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_mapped.html">gtk_widget_get_mapped</a>
	 */
	val mapped: Boolean
		get() = gtk_widget_get_mapped(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_opacity.html">gtk_widget_get_opacity</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_opacity.html">gtk_widget_set_opacity</a>
	 */
	var opacity: Double
		get() = gtk_widget_get_opacity(widgetPointer)
		set(value) = gtk_widget_set_opacity(widgetPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_style_context.html">gtk_widget_get_style_context</a>
	 */
	val styleContext: StyleContext
		get() = gtk_widget_get_style_context(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_first_child.html">gtk_widget_get_first_child</a>
	 */
	val firstChild: Widget?
		get() = gtk_widget_get_first_child(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_focus_child.html">gtk_widget_get_focus_child</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_focus_child.html">gtk_widget_set_focus_child</a>
	 */
	var focusChild: Widget?
		get() = gtk_widget_get_focus_child(widgetPointer).wrap()
		set(value) = gtk_widget_set_focus_child(widgetPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_last_child.html">gtk_widget_get_last_child</a>
	 */
	val lastChild: Widget?
		get() = gtk_widget_get_last_child(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_layout_manager.html">gtk_widget_get_layout_manager</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_layout_manager.html">gtk_widget_set_layout_manager</a>
	 */
	var layoutManager: LayoutManager?
		get() = gtk_widget_get_layout_manager(widgetPointer).wrap()
		set(value) = gtk_widget_set_layout_manager(widgetPointer, value?.layoutManagerPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_next_sibling.html">gtk_widget_get_next_sibling</a>
	 */
	val nextSibling: Widget?
		get() = gtk_widget_get_next_sibling(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_prev_sibling.html">gtk_widget_get_prev_sibling</a>
	 */
	val prevSibling: Widget?
		get() = gtk_widget_get_prev_sibling(widgetPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_overflow.html">gtk_widget_get_overflow</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_overflow.html">gtk_widget_set_overflow</a>
	 */
	var overflow: Overflow
		get() = Overflow.valueOf(gtk_widget_get_overflow(widgetPointer))!!
		set(value) = gtk_widget_set_overflow(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_request_mode.html">gtk_widget_get_request_mode</a>
	 */
	val requestMode: SizeRequestMode
		get() = SizeRequestMode.valueOf(gtk_widget_get_request_mode(widgetPointer))!!

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_state_flags.html">gtk_widget_get_state_flags</a>
	 */
	val stateFlags: StateFlags
		get() = StateFlags.valueOf(gtk_widget_get_state_flags(widgetPointer))!!

	/**
	 * @see <a h ref="https://docs.gtk.org/gtk4/method.Widget.should_layout.html">gtk_widget_should_layout</a
	 */
	val shouldLayout: Boolean
		get() = gtk_widget_should_layout(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.unparent.html"></a>
	 */
	fun unparent() {
		gtk_widget_unparent(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_ancestor.html"></a>
	 */
	fun getAncestor(type: KGType): Widget? =
		gtk_widget_get_ancestor(widgetPointer, type.glib).wrap()

	/**
	 * @see <a href=""></a>
	 */
	fun isInAncestor(ancestor: Widget): Boolean =
		gtk_widget_is_ancestor(widgetPointer, ancestor.widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	fun unParent() {
		gtk_widget_unparent(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.contains.html"></a>
	 */
	fun contains(x: Double, y: Double): Boolean =
		gtk_widget_contains(widgetPointer, x, y).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.show.html">gtk_widget_show</a>
	 */
	fun show() {
		gtk_widget_show(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.map.html">gtk_widget_map</a>
	 */
	fun map() {
		gtk_widget_map(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.unmap.html">gtk_widget_unmap</a>
	 */
	fun unmap() {
		gtk_widget_unmap(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.realize.html">gtk_widget_realize</a>
	 */
	fun realize() {
		gtk_widget_realize(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.unrealize.html">gtk_widget_unrealize</a>
	 */
	fun unrealize() {
		gtk_widget_unrealize(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.queue_draw.html">
	 *     gtk_widget_queue_draw</a>
	 */
	fun queueDraw() {
		gtk_widget_queue_draw(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.queue_resize.html">
	 *     gtk_widget_queue_resize</a>
	 */
	fun queueResize() {
		gtk_widget_queue_resize(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.queue_allocate.html">
	 *     gtk_widget_queue_allocate</a>
	 */
	fun queueAllocate() {
		gtk_widget_queue_allocate(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.add_tick_callback.html">gtk_widget_add_tick_callback</a>
	 */
	fun addTickCallback(callback: TickCallback): UInt =
		gtk_widget_add_tick_callback(
			widgetPointer,
			staticTickCallback,
			StableRef.create(callback).asCPointer(),
			staticDestroyStableRefFunction
		)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.remove_tick_callback.html">gtk_widget_remove_tick_callback</a>
	 */
	fun removeTickCallback(id: UInt) {
		gtk_widget_remove_tick_callback(widgetPointer, id)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.size_allocate.html">gtk_widget_size_allocate</a>
	 */
	fun sizeAllocate(allocation: Allocation, baseline: Int) {
		gtk_widget_size_allocate(widgetPointer, allocation.allocationPointer, baseline)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.allocate.html">gtk_widget_size_allocate</a>
	 */
	fun allocate(width: Int, height: Int, baseline: Int, transform: Transform) {
		gtk_widget_allocate(widgetPointer, width, height, baseline, transform.transformPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.activate.html">gtk_widget_activate</a>
	 */
	fun activate(): Boolean =
		gtk_widget_activate(widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.grab_focus.html">gtk_widget_grab_focus</a>
	 */
	fun grabFocus(): Boolean =
		gtk_widget_grab_focus(widgetPointer).bool

	/**
	 * @see <a href="">gtk_widget_get_ancestor</a>
	 */
	fun getAncestor(widgetType: ULong) =
		gtk_widget_get_ancestor(widgetPointer, widgetType)?.let { Widget(it) }

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.compute_expand.html"></a>
	 */
	fun computeExpand(orientation: Orientation): Boolean =
		gtk_widget_compute_expand(widgetPointer, orientation.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.compute_bounds.html"></a>
	 */
	fun computeBounds(target: Widget): Rect? = memScoped {
		val rect = cValue<graphene_rect_t>()

		return if (gtk_widget_compute_bounds(widgetPointer, target.widgetPointer, rect).bool)
			rect.ptr.wrap()
		else null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.compute_point.html"></a>
	 */
	fun computePoint(target: Widget, point: Point): Point? = memScoped {
		val outPoint = cValue<graphene_point_t>()

		return if (gtk_widget_compute_point(widgetPointer, target.widgetPointer, point.pointPointer, outPoint).bool)
			outPoint.ptr.wrap()
		else null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.compute_transform.html"></a>
	 */
	fun computeTransform(target: Widget): Matrix? = memScoped {
		val matrix = cValue<graphene_matrix_t>()

		return if (gtk_widget_compute_transform(widgetPointer, target.widgetPointer, matrix).bool)
			matrix.ptr.wrap()
		else null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.init_template.html"></a>
	 */
	fun initTemplate() {
		gtk_widget_init_template(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.is_ancestor.html">gtk_widget_is_ancestor</a>
	 */
	fun isAncestor(widget: Widget): Boolean =
		gtk_widget_is_ancestor(widgetPointer, widget.widgetPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.translate_coordinates.html">gtk_widget_translate_coordinates</a>
	 */
	fun translateCoordinates(destination: Widget, srcX: Double, srcY: Double): Pair<Double, Double>? =
		memScoped {
			val destY = cValue<DoubleVar>()
			val destX = cValue<DoubleVar>()
			if (gtk_widget_translate_coordinates(
					widgetPointer,
					destination.widgetPointer,
					srcX,
					srcY,
					destX,
					destY
				).bool
			)
				destX.ptr.pointed.value to destY.ptr.pointed.value
			else
				null
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.add_controller.html">
	 *     gtk_widget_add_controller</a>
	 */
	fun addController(controller: EventController) {
		gtk_widget_add_controller(widgetPointer, controller.eventControllerPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.remove_controller.html">
	 *     gtk_widget_remove_controller</a>
	 */
	fun removeController(controller: EventController) {
		gtk_widget_remove_controller(widgetPointer, controller.eventControllerPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.remove_css_class.html">
	 *     gtk_widget_remove_css_class</a>
	 */
	fun removeCssClass(cssClass: String) {
		gtk_widget_remove_css_class(widgetPointer, cssClass)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.create_pango_context.html">
	 *     gtk_widget_create_pango_context</a>
	 */
	fun createPangoContext(): Context =
		gtk_widget_create_pango_context(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.create_pango_layout.html">
	 *     gtk_widget_create_pango_layout</a>
	 */
	fun createPangoLayout(text: String? = null): Layout =
		gtk_widget_create_pango_layout(widgetPointer, text)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_cursor_from_name.html">
	 *     gtk_widget_set_cursor_from_name</a>
	 */
	fun setCursorFromName(name: String) {
		gtk_widget_set_cursor_from_name(widgetPointer, name)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.mnemonic_activate.html">gtk_widget_mnemonic_activate</a>
	 */
	fun mnemonicActivate(groupCycling: Boolean): Boolean =
		gtk_widget_mnemonic_activate(widgetPointer, groupCycling.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.activate_action_variant.html"></a>
	 */
	fun activateActionVariant(name: String, vararg args: Variant): Boolean =
		gtk_widget_activate_action_variant(widgetPointer, name, args.toCArray().pointed.value).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.activate_default.html"></a>
	 */
	fun activateDefault() {
		gtk_widget_activate_default(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.add_css_class.html"></a>
	 */
	fun addCSSClass(cssClass: String) {
		gtk_widget_add_css_class(widgetPointer, cssClass)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.child_focus.html">
	 *     gtk_widget_child_focus</a>
	 */
	fun childFocus(direction: DirectionType) {
		gtk_widget_child_focus(widgetPointer, direction.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.add_mnemonic_label.html">
	 *     gtk_widget_add_mnemonic_label</a>
	 */
	fun addMnemonicLabel(label: Widget) {
		gtk_widget_add_mnemonic_label(widgetPointer, label.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.remove_mnemonic_label.html">
	 *     gtk_widget_remove_mnemonic_label</a>
	 */
	fun removeMnemonicLabel(label: Widget) {
		gtk_widget_remove_mnemonic_label(widgetPointer, label.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.error_bell.html">
	 *     gtk_widget_error_bell</a>
	 */
	fun errorBell() {
		gtk_widget_error_bell(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.keynav_failed.html">
	 *     gtk_widget_keynav_failed</a>
	 */
	fun keynavFailed(direction: DirectionType): Boolean =
		gtk_widget_keynav_failed(widgetPointer, direction.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.trigger_tooltip_query.html">
	 *     gtk_widget_trigger_tooltip_query</a>
	 */
	fun triggerTooltipQuery() {
		gtk_widget_trigger_tooltip_query(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_size.html">gtk_widget_get_size</a>
	 */
	fun getSize(orientation: Orientation) =
		gtk_widget_get_size(widgetPointer, orientation.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.set_state_flags.html"></a>
	 */
	fun setStateFlags(flags: StateFlags, clear: Boolean) {
		gtk_widget_set_state_flags(widgetPointer, flags.gtk, clear.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.unset_state_flags.html"></a>
	 */
	fun unsetStateFlags(flags: StateFlags) {
		gtk_widget_unset_state_flags(widgetPointer, flags.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.drag_check_threshold.html"></a>
	 */
	fun dragCheckThreshold(startX: Int, startY: Int, currentX: Int, currentY: Int): Boolean =
		gtk_drag_check_threshold(widgetPointer, startX, startY, currentX, currentY).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.init_template.html"></a>
	 */
	fun insertActionGroup(name: String, group: ActionGroup) {
		gtk_widget_insert_action_group(widgetPointer, name, group.actionGroupPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.insert_after.html"></a>
	 */
	fun insertAfter(widget: Widget, previousSibling: Widget) {
		gtk_widget_insert_after(widget.widgetPointer, widgetPointer, previousSibling.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.insert_before.html"></a>
	 */
	fun insertBefore(widget: Widget, nextSibling: Widget) {
		gtk_widget_insert_before(widget.widgetPointer, widgetPointer, nextSibling.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.action_set_enabled.html">gtk_widget_action_set_enabled</a>
	 */
	fun actionSetEnabled(action: String, enabled: Boolean) {
		gtk_widget_action_set_enabled(widgetPointer, action, enabled.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.destroy.html"></a>
	 */
	fun addOnDestroyCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.DESTROY, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.direction-changed.html"></a>
	 */
	fun addOnDirectionChangedCallback(action: (TextDirection) -> Unit) =
		addSignalCallback(Signals.DIRECTION_CHANGED, action, staticDirectionChangedCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.hide.html"></a>
	 */
	fun addOnHideCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.HIDE, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.keynav-failed.html"></a>
	 */
	fun setKeynavFailedCallback(action: KeynavFailedFunction) =
		addSignalCallback(Signals.KEYNAV_FAILED, action, staticKeynavFailedFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.map.html"></a>
	 */
	fun setMapCallback(action: MapFunction) =
		addSignalCallback(Signals.MAP, action, staticMapFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.mnemonic-activate.html"></a>
	 */
	fun setMnemonicActivateCallback(action: MnemonicActivateFunction) =
		addSignalCallback(Signals.MNEMONIC_ACTIVATE, action, staticMnemonicActivateFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.move-focus.html"></a>
	 */
	fun addOnMoveFocusCallback(action: (DirectionType) -> Unit) =
		addSignalCallback(Signals.MOVE_FOCUS, action, staticMoveFocusFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.query-tooltip.html"></a>
	 */
	fun setQueryTooltipCallback(action: QueryTooltipFunction) =
		addSignalCallback(Signals.QUERY_TOOLTIP, action, staticQueryTooltipFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.realize.html"></a>
	 */
	fun addOnRealizeCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.REALIZE, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.show.html"></a>
	 */
	fun addOnShowCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.SHOW, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.state-flags-changed.html"></a>
	 */
	fun addOnStateFlagsChangedCallback(action: (StateFlags) -> Unit) =
		addSignalCallback(Signals.STATE_FLAGS_CHANGED, action, staticStateFlagsChangedCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.unmap.html"></a>
	 */
	fun addOnUnmapCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.UNMAP, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.Widget.unrealize.html"></a>
	 */
	fun addOnUnrealizeCallback(action: (Unit) -> Unit) =
		addSignalCallback(Signals.UNREALIZE, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.get_template_child.html">gtk_widget_get_template_child</a>
	 */
	fun getTemplateChild(widget: KGType, name: String): KGObject =
		gtk_widget_get_template_child(widgetPointer, widget.glib, name).wrap()!!

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.has_css_class.html"></a>
	 */
	fun hasCssClass(cssClass: String): Boolean =
		gtk_widget_has_css_class(widgetPointer, cssClass).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.hide.html"></a>
	 */
	fun hide() {
		gtk_widget_hide(widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.measure.html"></a>
	 */
	fun measure(orientation: Orientation, forSize: Int): LayoutManager.MeasureResult =
		memScoped {
			val minimum = cValue<IntVar>()
			val natural = cValue<IntVar>()
			val minimumBaseline = cValue<IntVar>()
			val naturalBaseline = cValue<IntVar>()

			gtk_widget_measure(
				widgetPointer,
				orientation.gtk,
				forSize,
				minimum,
				natural,
				minimumBaseline,
				naturalBaseline
			)

			LayoutManager.MeasureResult(
				minimum.ptr.pointed.value,
				natural.ptr.pointed.value,
				minimumBaseline.ptr.pointed.value,
				naturalBaseline.ptr.pointed.value,
			)
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.observe_children.html"></a>
	 */
	fun observeChildren() {
		TODO("gtk_widget_observe_children")
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.observe_controllers.html"></a>
	 */
	fun observeControllers() {
		TODO("gtk_widget_observe_controllers")
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.pick.html"></a>
	 */
	fun pick(x: Double, y: Double, pickFlags: @PickFlags UInt): Widget? =
		gtk_widget_pick(widgetPointer, x, y, pickFlags).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Widget.snapshot_child.html"></a>
	 */
	fun snapshotChild(child: Widget, snapshot: Snapshot) {
		gtk_widget_snapshot_child(widgetPointer, child.widgetPointer, snapshot.snapshotPointer)
	}

	data class PreferredSize(
		val minimmum: Requisition,
		val maximum: Requisition
	)

	/**
	 * @see <a href=""></a>
	 */
	enum class Align(val key: Int, val gtk: GtkAlign) {
		FILL(0, GtkAlign.GTK_ALIGN_FILL),
		START(1, GtkAlign.GTK_ALIGN_START),
		END(2, GtkAlign.GTK_ALIGN_END),
		CENTER(3, GtkAlign.GTK_ALIGN_CENTER),
		BASELINE(4, GtkAlign.GTK_ALIGN_BASELINE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gtkAlign: GtkAlign) =
				values().find { it.gtk == gtkAlign }
		}
	}

	/**
	 * @see <a href="">GtkTextDirection</a>
	 */
	enum class TextDirection(val key: Int, val gtk: GtkTextDirection) {
		NONE(0, GTK_TEXT_DIR_NONE),
		LTR(1, GTK_TEXT_DIR_LTR),
		RTL(2, GTK_TEXT_DIR_RTL);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gtk: GtkTextDirection) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/class.Widget.html#class-methods">Class methods</a>
	 * @see <a href="https://docs.gtk.org/gtk4/class.Widget.html#virtual-methods">Virtual methods</a>
	 */
	class Class(
		val classPointer: CPointer<GtkWidgetClass>
	) {

		/**
		 * @see <a href="">
		 *     gtk_widget_class_get_accessible_role</a>
		 * @see <a href="">
		 *     gtk_widget_class_set_accessible_role</a>
		 */
		var accessibleRole: Accessible.Role
			get() = Accessible.Role.valueOf(gtk_widget_class_get_accessible_role(classPointer))!!
			set(value) = gtk_widget_class_set_accessible_role(classPointer, value.gtk)

		/**
		 * @see <a href=""></a>
		 * @see <a href=""></a>
		 */
		fun setTemplate(templateBytes: KGBytes) {
			gtk_widget_class_set_template(classPointer, templateBytes.pointer)
		}


	}

	companion object {
		/**
		 * @see <a href="">gtk_widget_get_default_direction</a>
		 * @see <a href="">gtk_widget_set_default_direction</a>
		 */
		var defaultDirection: TextDirection
			get() = TextDirection.valueOf(gtk_widget_get_default_direction())!!
			set(value) = gtk_widget_set_default_direction(value.gtk)

		val staticTickCallback: GtkTickCallback =
			staticCFunction { _: gpointer?, frameClock: CPointer<GdkFrameClock>, data: gpointer ->
				data.asStableRef<TickCallback>().get()
					.invoke(FrameClock(frameClock)).gtk
			}.reinterpret()

		inline fun CPointer<GtkWidget>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkWidget>.wrap() =
			Widget(this)

		inline fun CPointer<GList>.asMutableWidgetList(): MutableWrappedKList<Widget> =
			asMutableList({ reinterpret<GtkWidget>().wrap() }, { widgetPointer })
	}
}

/**
 * @see <a href="">GtkTickCallback</a>
 */
typealias TickCallback = (FrameClock) -> Boolean

val staticDirectionChangedCallback: GCallback =
	staticCFunction { _: WidgetPointer, previous: GtkTextDirection, data: gpointer ->
		data.asStableRef<(Widget.TextDirection) -> Unit>().get().invoke(Widget.TextDirection.valueOf(previous)!!)
		@Suppress("RedundantUnitExpression")
		Unit
	}.reinterpret()

val staticStateFlagsChangedCallback: GCallback =
	staticCFunction { _: WidgetPointer, flags: GtkStateFlags, data: gpointer ->
		data.asStableRef<(StateFlags) -> Unit>().get().invoke(StateFlags.valueOf(flags)!!)
		@Suppress("RedundantUnitExpression")
		Unit
	}.reinterpret()

/**
 * @see <a href=""></a>
 */
typealias QueryTooltipFunction = (@ParameterName("x") Int, @ParameterName("y") Int, Boolean, Tooltip) -> Boolean

val staticQueryTooltipFunction: GCallback = staticCFunction { _: WidgetPointer,
                                                              x: Int,
                                                              y: Int,
                                                              key: gboolean,
                                                              tooltip: CPointer<GtkTooltip>,
                                                              data: gpointer ->
	data.asStableRef<QueryTooltipFunction>().get().invoke(x, y, key.bool, tooltip.wrap()).gtk
}.reinterpret()

/**
 * @see <a href=""></a>
 */
typealias MnemonicActivateFunction = (@ParameterName("groupCycling") Boolean) -> Boolean

val staticMnemonicActivateFunction: GCallback =
	staticCFunction { _: WidgetPointer, groupCycling: gboolean, data: gpointer ->
		data.asStableRef<MnemonicActivateFunction>().get().invoke(groupCycling.bool).gtk
	}.reinterpret()
/**
 * @see <a href=""></a>
 */
typealias KeynavFailedFunction = (DirectionType) -> Boolean

val staticKeynavFailedFunction: GCallback = staticCFunction { _: WidgetPointer,
                                                              direction: GtkDirectionType,
                                                              data: gpointer ->
	data.asStableRef<KeynavFailedFunction>().get().invoke(DirectionType.valueOf(direction)!!).gtk
}.reinterpret()

val staticMoveFocusFunction: GCallback = staticCFunction { _: WidgetPointer,
                                                           direction: GtkDirectionType,
                                                           data: gpointer ->
	data.asStableRef<(DirectionType) -> Unit>().get().invoke(DirectionType.valueOf(direction)!!)
	@Suppress("RedundantUnitExpression")
	Unit
}.reinterpret()

/**
 * @see <a href=""></a>
 */
typealias MapFunction = () -> Unit

val staticMapFunction: GCallback = staticCFunction { _: WidgetPointer, data: gpointer ->
	data.asStableRef<MapFunction>().get().invoke()
	@Suppress("RedundantUnitExpression")
	Unit
}.reinterpret()
