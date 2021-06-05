package nativex.gtk.widgets

import gtk.*
import gtk.GtkTextDirection.*
import kotlinx.cinterop.*
import nativex.async.staticDestroyStableRefFunction
import nativex.atk.KAtkObject
import nativex.atk.KAtkObject.Companion.wrap
import nativex.cairo.FontOptionsT
import nativex.cairo.FontOptionsT.Companion.wrap
import nativex.cairo.CairoT
import nativex.cairo.RegionT
import nativex.gdk.*
import nativex.gdk.Display.Companion.wrap
import nativex.gdk.FrameClock.Companion.wrap
import nativex.gdk.Screen.Companion.wrap
import nativex.gdk.Visual.Companion.wrap
import nativex.gdk.Window.Companion.wrap
import nativex.gio.KObject
import nativex.glib.KGValue
import nativex.gmodule.KGBytes
import nativex.gtk.*
import nativex.gtk.Settings.Companion.wrap
import nativex.gtk.StyleContext.Companion.wrap
import nativex.gtk.bool
import nativex.gtk.common.data.Requisition
import nativex.gtk.common.data.Requisition.Companion.wrap
import nativex.gtk.common.enums.DirectionType
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.StateFlags
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget.Path.Companion.wrap
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.Window.Companion.wrap
import nativex.pango.Context
import nativex.pango.FontMap
import nativex.pango.FontMap.Companion.wrap
import nativex.pango.Layout

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html">GtkWidget</a>
 */
open class Widget(
	internal open val widgetPointer: WidgetPointer
) : KObject(
	widgetPointer.reinterpret()
) {
	val isInDestruction: Boolean
		get() = gtk_widget_in_destruction(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var isAppPaintable: Boolean
		get() = gtk_widget_get_app_paintable(widgetPointer).bool
		set(value) = gtk_widget_set_app_paintable(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var canDefault: Boolean
		get() = gtk_widget_get_can_default(widgetPointer).bool
		set(value) = gtk_widget_set_can_default(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var canFocus: Boolean
		get() = gtk_widget_get_can_focus(widgetPointer).bool
		set(value) = gtk_widget_set_can_focus(widgetPointer, value.gtk)


	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var isDoubleBuffered: Boolean
		get() = gtk_widget_get_double_buffered(widgetPointer).bool
		set(value) = gtk_widget_set_double_buffered(
			widgetPointer,
			value.gtk
		)


	var expand: Boolean
		get() = horizontalExpand && verticalExpand
		set(value) {
			horizontalExpand = value
			verticalExpand = value
		}


	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var focusOnClick: Boolean
		get() = gtk_widget_get_focus_on_click(widgetPointer).bool
		set(value) = gtk_widget_set_focus_on_click(
			widgetPointer,
			value.gtk
		)

	data class PreferredSize(
		val minimmum: Requisition,
		val maximum: Requisition
	)

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
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var horizontalAlign: Align
		get() = Align.valueOf(gtk_widget_get_halign(widgetPointer))!!
		set(value) = gtk_widget_set_halign(widgetPointer, value.gtk)


	val hasDefault: Boolean
		get() = gtk_widget_has_default(widgetPointer).bool

	val hasFocus: Boolean
		get() = gtk_widget_has_focus(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var hasTooltip: Boolean
		get() = gtk_widget_get_has_tooltip(widgetPointer).bool
		set(value) = gtk_widget_set_has_tooltip(widgetPointer, value.gtk)


	var heightRequest: Int
		get() = TODO("height-request")
		set(_) = TODO("height-request")

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var horizontalExpand: Boolean
		get() = gtk_widget_get_hexpand(widgetPointer).bool
		set(value) =
			gtk_widget_set_hexpand(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var horizontalExpandSet: Boolean
		get() = gtk_widget_get_hexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_hexpand_set(widgetPointer, value.gtk)

	/**
	 * TODO Figure out set?
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-is-focus">gtk_widget_is_focus</a>
	 */
	val isFocus: Boolean
		get() = gtk_widget_is_focus(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var marginStart: Int
		get() = gtk_widget_get_margin_start(widgetPointer)
		set(value) = gtk_widget_set_margin_start(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var marginEnd: Int
		get() = gtk_widget_get_margin_end(widgetPointer)
		set(value) = gtk_widget_set_margin_end(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var marginTop: Int
		get() = gtk_widget_get_margin_top(widgetPointer)
		set(value) = gtk_widget_set_margin_top(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var marginBottom: Int
		get() = gtk_widget_get_margin_bottom(widgetPointer)
		set(value) = gtk_widget_set_margin_bottom(widgetPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-name">gtk_widget_get_name</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-name">gtk_widget_set_name</a>
	 */
	var name: String?
		get() = gtk_widget_get_name(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_name(widgetPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-scale-factor"></a>
	 */
	val scaleFactor: Int
		get() = gtk_widget_get_scale_factor(widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-sensitive">gtk_widget_set_sensitive</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-sensitive">gtk_widget_get_sensitive</a>
	 */
	var sensitive: Boolean
		get() = gtk_widget_get_sensitive(widgetPointer).bool
		set(value) = gtk_widget_set_sensitive(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-parent">gtk_widget_get_parent</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-parent">gtk_widget_set_parent</a>
	 */
	var parent: Widget?
		get() = gtk_widget_get_parent(widgetPointer)?.let { Widget(it) }
		set(value) = gtk_widget_set_parent(widgetPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-parent-window">gtk_widget_set_parent_window</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-parent-window">gtk_widget_get_parent_window</a>
	 */
	var parentWindow: nativex.gdk.Window?
		get() = gtk_widget_get_parent_window(widgetPointer)?.wrap()
		set(value) = gtk_widget_set_parent_window(widgetPointer, value?.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-events">gtk_widget_get_events</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-events">gtk_widget_set_events</a>
	 */
	var events: Int
		get() = gtk_widget_get_events(widgetPointer)
		set(value) = gtk_widget_set_events(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var state: StateType
		get() = StateType.valueOf(gtk_widget_get_state(widgetPointer))!!
		set(value) = gtk_widget_set_state(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-toplevel"gtk_widget_get_toplevel></a>
	 */
	val topLevel: Widget
		get() = gtk_widget_get_toplevel(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-visual">gtk_widget_get_visual</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-visual">gtk_widget_set_visual</a>
	 */
	var visual: Visual?
		get() = gtk_widget_get_visual(widgetPointer).wrap()
		set(value) = gtk_widget_set_visual(widgetPointer, value?.pointer)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var verticalAlign: Align
		get() = Align.valueOf(gtk_widget_get_valign(widgetPointer))!!
		set(value) = gtk_widget_set_valign(widgetPointer, value.gtk)


	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var verticalExpand: Boolean
		get() = gtk_widget_get_vexpand(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var verticalExpandSet: Boolean
		get() = gtk_widget_get_vexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand_set(widgetPointer, value.gtk)

	/*
	TODO Figure out constructor
	
	constructor(
		type: ULong,
		vararg properties: Properties
	) : this(
		gtk_widget_new(
			type,
			properties[0]!!.first,
			*(arrayOf<Any?>(properties.flatMapIndexed<Properties, Any?> { index, pair ->
				pair?.let {
					mutableListOf(it.second).apply {
						if (index != 0)
							add(it.first)
					}
				} ?: listOf(null)
			}.toTypedArray()))
		)!!
	)

	 */

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-destroy">gtk_widget_destroy</a>
	 */
	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
	open fun destroy() {
		gtk_widget_destroy(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-unparent"></a>
	 */
	fun unParent() {
		gtk_widget_unparent(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-show"></a>
	 */
	fun show() {
		gtk_widget_show(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-show-now"></a>
	 */
	fun showNow() {
		gtk_widget_show_now(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-hide"></a>
	 */
	fun hide() {
		gtk_widget_hide(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-show-all"></a>
	 */
	@Suppress("DeprecatedCallableAddReplaceWith")
	fun showAll() {
		//@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
		gtk_widget_show_all(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-map"></a>
	 */
	fun map() {
		gtk_widget_map(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-unmap"></a>
	 */
	fun unmap() {
		gtk_widget_unmap(widgetPointer)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-realize"></a>
	 */
	fun realize() {
		gtk_widget_realize(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-unrealize"></a>
	 */
	fun unrealize() {
		gtk_widget_unrealize(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-draw"></a>
	 */
	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
	fun draw(cairoT: CairoT) {
		gtk_widget_draw(widgetPointer, cairoT.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-draw"></a>
	 */
	fun queueDraw() {
		gtk_widget_queue_draw(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-resize"></a>
	 */
	fun queueResize() {
		gtk_widget_queue_resize(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-resize-no-redraw"></a>
	 */
	fun queueResizeNoDraw() {
		gtk_widget_queue_resize_no_redraw(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-allocate"></a>
	 */
	fun queueAllocate() {
		gtk_widget_queue_allocate(widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	val frameClock: FrameClock?
		get() = gtk_widget_get_frame_clock(widgetPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-add-tick-callback">gtk_widget_add_tick_callback</a>
	 */
	fun addTickCallback(callback: (FrameClock) -> Boolean): UInt =
		gtk_widget_add_tick_callback(
			widgetPointer,
			staticTickCallback,
			StableRef.create(callback).asCPointer(),
			staticDestroyStableRefFunction
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-remove-tick-callback">gtk_widget_remove_tick_callback</a>
	 */
	fun removeTickCallback(id: UInt) {
		gtk_widget_remove_tick_callback(widgetPointer, id)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun sizeRequest(): Requisition {
		val gtkRequisition = cValue<GtkRequisition>()
		gtk_widget_size_request(widgetPointer, gtkRequisition)
		return memScoped { gtkRequisition.ptr.wrap() }
	}

	/**
	 * TODO GtkAllocation
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-size-allocate">gtk_widget_size_allocate</a>
	 */
	fun sizeAllocate() {
		TODO("gtk_widget_size_allocate")
	}

	/**
	 * TODO GtkAllocation
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-size-allocate-with-baseline">gtk_widget_size_allocate_with_baseline</a>
	 */
	fun sizeAllocateWithBaseline() {
		TODO("gtk_widget_size_allocate_with_baseline")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-add-accelerator">gtk_widget_add_accelerator</a>
	 */
	fun addAccelerator() {
		TODO("gtk_widget_add_accelerator")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-remove-accelerator">gtk_widget_remove_accelerator</a>
	 */
	fun removeAccelerator() {
		TODO("gtk_widget_remove_accelerator")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-accel-path">gtk_widget_set_accel_path</a>
	 */
	fun setAcceleratorPath() {
		TODO("gtk_widget_set_accel_path")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-list-accel-closures">gtk_widget_list_accel_closures</a>
	 */
	fun listAcceleratorClosures() {
		TODO("gtk_widget_list_accel_closures")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-can-activate-accel">gtk_widget_can_activate_accel</a>
	 */

	fun canActivateAccelerator(signalID: UInt) =
		gtk_widget_can_activate_accel(widgetPointer, signalID).bool

	/**
	 * TODO Event
	 * @see Event
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-event">gtk_widget_event</a>
	 */
	fun event(event: Event) {
		TODO("gtk_widget_event")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-activate">gtk_widget_activate</a>
	 */
	fun activate(): Boolean =
		gtk_widget_activate(widgetPointer).bool

	/**
	 * TODO gtk_widget_intersect
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-intersect">gtk_widget_intersect</a>
	 */
	fun intersect() {
		TODO("gtk_widget_intersect")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-grab-focus">gtk_widget_grab_focus</a>
	 */
	fun grabFocus() {
		gtk_widget_grab_focus(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-grab-default">gtk_widget_grab_default</a>
	 */
	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
	fun grabDefault() {
		gtk_widget_grab_default(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-add-events">gtk_widget_add_events</a>
	 */
	fun addEvents(events: Int) {
		gtk_widget_add_events(widgetPointer, events)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-device-events">gtk_widget_get_device_events</a>
	 */
	fun setDeviceEvents(device: Device) {
		gtk_widget_get_device_events(widgetPointer, device.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-device-events">gtk_widget_set_device_events</a>
	 */
	fun getDeviceEvents(device: Device, events: Event.Mask) {
		gtk_widget_set_device_events(widgetPointer, device.pointer, events.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-add-device-events">gtk_widget_add_device_events</a>
	 */
	fun addDeviceEvents(device: Device, events: Event.Mask) {
		gtk_widget_add_device_events(widgetPointer, device.pointer, events.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-device-enabled">gtk_widget_set_device_enabled</a>
	 */
	fun setDeviceEnabled(device: Device, boolean: Boolean) {
		gtk_widget_set_device_enabled(
			widgetPointer,
			device.pointer,
			boolean.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-device-enabled">gtk_widget_get_device_enabled</a>
	 */
	fun getDeviceEnabled(device: Device): Boolean =
		gtk_widget_get_device_enabled(widgetPointer, device.pointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-ancestor">gtk_widget_get_ancestor</a>
	 */

	fun getAncestor(widgetType: ULong) =
		gtk_widget_get_ancestor(widgetPointer, widgetType)?.let { Widget(it) }

	/**
	 * @see <a href=""></a>
	 */
	fun getVerticalAlignmentWithBaseLine(): Align =
		Align.valueOf(gtk_widget_get_valign_with_baseline(widgetPointer))!!

	/**
	 * @see <a href=""></a>
	 */
	fun queueComputeExpand() {
		gtk_widget_queue_compute_expand(widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun computeExpand(orientation: Orientation): Boolean =
		gtk_widget_compute_expand(widgetPointer, orientation.gtk).bool

	/**
	 * @see <a href=""></a>
	 */
	fun initTemplate() {
		gtk_widget_init_template(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-is-ancestor">gtk_widget_is_ancestor</a>
	 */
	fun isAncestor(widget: Widget): Boolean =
		gtk_widget_is_ancestor(widgetPointer, widget.widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-translate-coordinates">gtk_widget_translate_coordinates</a>
	 */
	fun translateCoordinates(destination: Widget): Pair<Int, Int> = TODO("")


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-hide-on-delete">gtk_widget_hide_on_delete</a>
	 */
	fun hideOnDelete() {
		gtk_widget_hide_on_delete(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-direction">
	 *     gtk_widget_set_direction
	 *     </a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-direction">
	 *     gtk_widget_get_direction
	 *     </a>
	 */
	var direction: TextDirection
		get() = TextDirection.valueOf(gtk_widget_get_direction(widgetPointer))!!
		set(value) = gtk_widget_set_direction(widgetPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-shape-combine-region">gtk_widget_shape_combine_region</a>
	 */
	fun shapeCombineRegion(region: RegionT): Unit =
		gtk_widget_shape_combine_region(widgetPointer, region.pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-input-shape-combine-region">gtk_widget_input_shape_combine_region</a>
	 */
	fun inputShapeCombineRegion(region: RegionT): Unit =
		gtk_widget_input_shape_combine_region(widgetPointer, region.pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-create-pango-context">gtk_widget_create_pango_context</a>
	 */
	fun createPangoContext(): Context =
		Context(gtk_widget_create_pango_context(widgetPointer)!!)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-pango-context">gtk_widget_get_pango_context</a>
	 */
	val pangoContext: Context
		get() = Context(gtk_widget_get_pango_context(widgetPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-font-options">gtk_widget_get_font_options</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-font-options">gtk_widget_set_font_options</a>
	 */
	var fontOptions: FontOptionsT?
		get() = gtk_widget_get_font_options(widgetPointer).wrap()
		set(value) = gtk_widget_set_font_options(widgetPointer, value?.pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-font-map">gtk_widget_get_font_map</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-font-map">gtk_widget_set_font_map</a>
	 */
	var fontMap: FontMap?
		get() = gtk_widget_get_font_map(widgetPointer).wrap()
		set(value) = gtk_widget_set_font_map(widgetPointer, value?.pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-create-pango-layout">gtk_widget_create_pango_layout</a>
	 */
	fun createPangoLayout(text: String? = null): Layout =
		Layout(gtk_widget_create_pango_layout(widgetPointer, text)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-draw-area">
	 *     gtk_widget_queue_draw_area</a>
	 */
	fun queueDrawArea(x: Int, y: Int, width: Int, height: Int) {
		gtk_widget_queue_draw_area(widgetPointer, x, y, width, height)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-queue-draw-region">gtk_widget_queue_draw_region</a>
	 */
	fun queueDrawRegion(region: RegionT) {
		gtk_widget_queue_draw_region(widgetPointer, region.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-app-paintable">gtk_widget_set_app_paintable</a>
	 */
	fun setPaintable(isPaintable: Boolean): Unit =
		gtk_widget_set_app_paintable(widgetPointer, isPaintable.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-redraw-on-allocate">gtk_widget_set_redraw_on_allocate</a>
	 */
	fun setRedrawOnAllocate(redrawOnAllocate: Boolean): Unit =
		gtk_widget_set_redraw_on_allocate(widgetPointer, redrawOnAllocate.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-mnemonic-activate">gtk_widget_mnemonic_activate</a>
	 */
	fun mnemonicActivate(groupCycling: Boolean): Boolean =
		gtk_widget_mnemonic_activate(widgetPointer, groupCycling.gtk).bool

	/**
	 * @see <a href=""></a>
	 */
	fun installStyleProperty(): Unit = TODO("gtk_widget_class_install_style_property")

	/**
	 * @see <a href=""></a>
	 */
	fun installStylePropertyParser(): Unit = TODO("gtk_widget_class_install_style_property")

	/**
	 * @see <a href=""></a>
	 */
	fun findStyleProperty(): Unit = TODO("gtk_widget_class_find_style_property")

	/**
	 * @see <a href=""></a>
	 */
	fun listStyleProperties(): Unit = TODO("gtk_widget_class_list_style_properties")

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-send-focus-change">gtk_widget_send_focus_change</a>
	 */
	fun sendFocusChange(): Boolean = TODO("gtk_widget_send_focus_change")

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-style-get-property">
	 *     gtk_widget_style_get_property</a>
	 */
	fun getStyleProperty(propertyName: String): KGValue = TODO("gtk_widget_style_get_property")

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-style-get-valist">
	 *     gtk_widget_style_get_valist</a>
	 */
	fun getStyle(values: Array<String>): Map<String, KGValue> = TODO("gtk_widget_style_get_valist")

	/**
	 * @see <a href=""></a>
	 */
	fun setAccessibleType(): Unit = TODO("gtk_widget_class_set_accessible_type")

	/**
	 * @see <a href=""></a>
	 */
	fun setAccessibleRole(): Unit = TODO("gtk_widget_class_set_accessible_role")

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-accessible">
	 *     gtk_widget_get_accessible</a>
	 */
	val accessible: KAtkObject
		get() = gtk_widget_get_accessible(widgetPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-child-focus">
	 *     gtk_widget_child_focus</a>
	 */
	fun childFocus(direction: DirectionType) {
		gtk_widget_child_focus(widgetPointer, direction.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-child-notify">
	 *     gtk_widget_child_notify</a>
	 */
	fun childNotify(property: String) {
		gtk_widget_child_notify(widgetPointer, property)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-freeze-child-notify">
	 *     gtk_widget_freeze_child_notify</a>
	 */
	fun freezeChildNotify() {
		gtk_widget_freeze_child_notify(widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-child-visible">gtk_widget_get_child_visible</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-child-visible">gtk_widget_set_child_visible</a>
	 */
	var isChildVisible: Boolean
		get() = gtk_widget_get_child_visible(widgetPointer).bool
		set(value) = gtk_widget_set_child_visible(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	val settings: Settings
		get() = gtk_widget_get_settings(widgetPointer)!!.wrap()

	/**
	 * @see <a href="">gtk_widget_get_clipboard</a>
	 */
	val clipboard: Clipboard
		get() = TODO("gtk_widget_get_clipboard")

	/**
	 * @see <a href="">gtk_widget_get_display</a>
	 */
	val display: Display
		get() = gtk_widget_get_display(widgetPointer)!!.wrap()

	/**
	 * @see <a href=""></a>
	 */
	val screen: Screen
		get() = gtk_widget_get_screen(widgetPointer)!!.wrap()

	/**
	 * @see <a href="">gtk_widget_has_screen</a>
	 */
	val hasScreen: Boolean
		get() = gtk_widget_has_screen(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	var sizeRequest: Pair<Int, Int>
		get() = memScoped {
			val w = cValue<IntVar>()
			val h = cValue<IntVar>()
			gtk_widget_get_size_request(widgetPointer, w, h)
			w.ptr.pointed.value to h.ptr.pointed.value
		}
		set(value) = gtk_widget_set_size_request(widgetPointer, value.first, value.second)

	fun setChildVisible(isVisible: Boolean) {
		gtk_widget_set_child_visible(widgetPointer, isVisible.gtk)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun thawChildNotify() {
		gtk_widget_thaw_child_notify(widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	var noShowAll: Boolean
		get() = gtk_widget_get_no_show_all(widgetPointer).bool
		set(value) = gtk_widget_set_no_show_all(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	val mnemonicLabels: Sequence<Widget>
		get() = gtk_widget_list_mnemonic_labels(widgetPointer).asKSequence<GtkWidget, Widget> { it.wrap() }

	/**
	 * @see <a href=""></a>
	 */
	fun addMnemonicLabel(label: Widget) {
		gtk_widget_add_mnemonic_label(widgetPointer, label.widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun removeMnemonicLabel(label: Widget) {
		gtk_widget_remove_mnemonic_label(widgetPointer, label.widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun errorBell(widget: Widget) {
		gtk_widget_error_bell(widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun keynavFailed(direction: DirectionType): Boolean =
		gtk_widget_keynav_failed(widgetPointer, direction.gtk).bool

	/**
	 * @see <a href=""></a>
	 */
	var tooltipMarkup: String?
		get() = gtk_widget_get_tooltip_markup(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_tooltip_markup(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 */
	var tooltipText: String?
		get() = gtk_widget_get_tooltip_text(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_tooltip_text(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 */
	var tooltipWindow: Window
		get() = gtk_widget_get_tooltip_window(widgetPointer)!!.wrap()
		set(value) = gtk_widget_set_tooltip_window(widgetPointer, value.windowPointer)

	/**
	 * @see <a href=""></a>
	 */
	fun triggerTooltipQuery() {
		gtk_widget_trigger_tooltip_query(widgetPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	val window: nativex.gdk.Window?
		get() = gtk_widget_get_window(widgetPointer)?.wrap()

	/**
	 * @see <a href=""></a>
	 */
	fun registerWindow(window: nativex.gdk.Window) {
		gtk_widget_register_window(widgetPointer, window.windowPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun unregisterWindow(window: nativex.gdk.Window) {
		gtk_widget_unregister_window(widgetPointer, window.windowPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	val allocatedWidth: Int
		get() = gtk_widget_get_allocated_width(widgetPointer)

	/**
	 * @see <a href=""></a>
	 */
	val allocatedHeight: Int
		get() = gtk_widget_get_allocated_height(widgetPointer)

	/**
	 * @see <a href=""></a>
	 */
	var allocation: Any
		get() = TODO("gtk_widget_get_allocation")
		set(value) = TODO("gtk_widget_set_allocation")

	/**
	 * @see <a href=""></a>
	 */
	val allocatedBaseline: Int
		get() = gtk_widget_get_allocated_baseline(widgetPointer)

	/**
	 * @see <a href=""></a>
	 */
	var hasWindow: Boolean
		get() = gtk_widget_get_has_window(widgetPointer).bool
		set(value) = gtk_widget_set_has_window(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	val isSensitive: Boolean
		get() = gtk_widget_is_sensitive(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */

	val isVisible: Boolean
		get() = gtk_widget_is_visible(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */

	var visible: Boolean
		get() = gtk_widget_get_visible(widgetPointer).bool
		set(value) = gtk_widget_set_visible(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	fun setStateFlags(flags: StateFlags, clear: Boolean) {
		gtk_widget_set_state_flags(widgetPointer, flags.gtk, clear.gtk)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun unsetStateFlags(flags: StateFlags) {
		gtk_widget_unset_state_flags(widgetPointer, flags.gtk)
	}

	/**
	 * @see <a href=""></a>
	 */
	val hasVisibleFocus: Boolean
		get() = gtk_widget_has_visible_focus(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	val hasGrab: Boolean
		get() = gtk_widget_has_grab(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	val isDrawable: Boolean
		get() = gtk_widget_is_drawable(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	val isToplevel: Boolean
		get() = gtk_widget_is_toplevel(widgetPointer).bool

	/**
	 * @see <a href=""></a>
	 */
	fun setWindow(window: nativex.gdk.Window) {
		gtk_widget_set_window(widgetPointer, window.windowPointer)
	}

	/**
	 * @see <a href=""></a>
	 */
	var receivesDefault: Boolean
		get() = gtk_widget_get_receives_default(widgetPointer).bool
		set(value) = gtk_widget_set_receives_default(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	var supportMultidevice: Boolean
		get() = gtk_widget_get_support_multidevice(widgetPointer).bool
		set(value) = gtk_widget_set_support_multidevice(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	var realized: Boolean
		get() = gtk_widget_get_realized(widgetPointer).bool
		set(value) = gtk_widget_set_realized(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	var mapped: Boolean
		get() = gtk_widget_get_mapped(widgetPointer).bool
		set(value) = gtk_widget_set_mapped(widgetPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	fun isDeviceShadowed(device: Device): Boolean =
		gtk_widget_device_is_shadowed(widgetPointer, device.pointer).bool

	/**
	 * @see <a href=""></a>
	 */
	fun getModifierMask() {
		TODO("gtk_widget_get_modifier_mask")
	}

	/**
	 * @see <a href=""></a>
	 */
	fun insertActionGroup() {
		TODO("gtk_widget_insert_action_group")
	}

	/**
	 * @see <a href=""></a>
	 */
	var opacity: Double
		get() = gtk_widget_get_opacity(widgetPointer)
		set(value) = gtk_widget_set_opacity(widgetPointer, value)

	/**
	 * @see <a href=""></a>
	 */
	val actionPrefixes: Sequence<String>
		get() = gtk_widget_list_action_prefixes(widgetPointer).asKSequence()

	/**
	 * @see <a href=""></a>
	 */
	fun getActionGroup() {
		TODO("gtk_widget_get_action_group")
	}

	/**
	 * @see <a href=""></a>
	 */
	val path: Path
		get() = gtk_widget_get_path(widgetPointer)!!.wrap()


	val styleContext: StyleContext
		get() = gtk_widget_get_style_context(widgetPointer)!!.wrap()

	fun resetStyle() {
		gtk_widget_reset_style(widgetPointer)
	}

	/**
	 * @see <a href="">GtkWidgetPath</a>
	 */
	class Path internal constructor(
		internal val pointer: CPointer<GtkWidgetPath>
	) {
		companion object {
			internal inline fun CPointer<GtkWidgetPath>?.wrap() =
				this?.wrap()

			internal inline fun CPointer<GtkWidgetPath>.wrap() =
				Path(this)
		}
	}

	/**
	 * @see <a href=""></a>
	 */
	enum class Align(val key: Int, internal val gtk: GtkAlign) {
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#GtkTextDirection">GtkTextDirection</a>
	 */
	enum class TextDirection(val key: Int, internal val gtk: GtkTextDirection) {
		NONE(0, GTK_TEXT_DIR_NONE),
		LTR(1, GTK_TEXT_DIR_LTR),
		RTL(2, GTK_TEXT_DIR_RTL);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			internal fun valueOf(gtk: GtkTextDirection) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href=""></a>
	 */
	@Deprecated("Replace with StateType")
	enum class StateType(val key: Int, internal val gtk: GtkStateType) {
		NORMAL(0, GtkStateType.GTK_STATE_NORMAL),

		ACTIVE(1, GtkStateType.GTK_STATE_ACTIVE),

		PRELIGHT(2, GtkStateType.GTK_STATE_PRELIGHT),

		SELECTED(3, GtkStateType.GTK_STATE_SELECTED),

		INSENSITIVE(4, GtkStateType.GTK_STATE_INSENSITIVE),

		INCONSISTENT(5, GtkStateType.GTK_STATE_INCONSISTENT),

		FOCUSED(6, GtkStateType.GTK_STATE_FOCUSED);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			fun valueOf(gtk: GtkStateType) = values().find { it.gtk == gtk }
		}
	}


	class Class internal constructor(
		internal val classPointer: CPointer<GtkWidgetClass>
	) {
		fun setTemplate(templateBytes: KGBytes) {
			gtk_widget_class_set_template(classPointer, templateBytes.pointer)
		}
	}


	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-set-default-direction">gtk_widget_get_default_direction</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-widget-get-default-direction">gtk_widget_set_default_direction</a>
		 */
		var defaultDirection: TextDirection
			get() = TextDirection.valueOf(gtk_widget_get_default_direction())!!
			set(value) = gtk_widget_set_default_direction(value.gtk)

		/**
		 * @see <a href=""></a>
		 */
		fun shouldDrawWindow(cairoT: CairoT, window: nativex.gdk.Window): Boolean = TODO("gtk_cairo_should_draw_window")

		/**
		 * @see <a href=""></a>
		 */
		fun cairoTransformToWindow(): Unit = TODO("gtk_cairo_transform_to_window")

		internal val staticTickCallback: GtkTickCallback =
			staticCFunction { _: gpointer?, frameClock: CPointer<GdkFrameClock>, data: gpointer ->
				data.asStableRef<(FrameClock) -> Boolean>().get()
					.invoke(FrameClock(frameClock)).gtk
			}.reinterpret()

		internal inline fun CPointer<GtkWidget>?.wrap() =
			this?.let { Widget(it) }

		internal inline fun CPointer<GtkWidget>.wrap() =
			Widget(this)
	}
}