package nativex.gtk.widgets

import gtk.*
import kotlinx.cinterop.*
import nativex.gdk.Device
import nativex.gdk.Event
import nativex.gdk.GWindow
import nativex.gdk.Visual
import nativex.gio.KObject
import nativex.gtk.WidgetPointer
import nativex.gtk.bool
import nativex.gtk.common.data.Requisition
import nativex.gtk.common.enums.Orientation
import nativex.gtk.gtk


/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */

open class Widget(
	internal open val widgetPointer: WidgetPointer
) : KObject(
	widgetPointer.reinterpret()
) {

	val isInDestruction: Boolean
		get() = gtk_widget_in_destruction(widgetPointer).bool

	var isAppPaintable: Boolean
		get() = gtk_widget_get_app_paintable(widgetPointer).bool
		set(value) = gtk_widget_set_app_paintable(widgetPointer, value.gtk)

	var canDefault: Boolean
		get() = gtk_widget_get_can_default(widgetPointer).bool
		set(value) = gtk_widget_set_can_default(widgetPointer, value.gtk)

	var canFocus: Boolean
		get() = gtk_widget_get_can_focus(widgetPointer).bool
		set(value) = gtk_widget_set_can_focus(widgetPointer, value.gtk)


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


	var focusOnClick: Boolean
		get() = gtk_widget_get_focus_on_click(widgetPointer).bool
		set(value) = gtk_widget_set_focus_on_click(
			widgetPointer,
			value.gtk
		)

	var horizontalAlign: Align
		get() = Align.valueOf(gtk_widget_get_halign(widgetPointer))!!
		set(value) = gtk_widget_set_halign(widgetPointer, value.gtk)


	val hasDefault: Boolean
		get() = gtk_widget_has_default(widgetPointer).bool

	val hasFocus: Boolean
		get() = gtk_widget_has_focus(widgetPointer).bool

	var hasTooltip: Boolean
		get() = gtk_widget_get_has_tooltip(widgetPointer).bool
		set(value) = gtk_widget_set_has_tooltip(widgetPointer, value.gtk)


	var heightRequest: Int
		get() = TODO("height-request")
		set(_) = TODO("height-request")

	var horizontalExpand: Boolean
		get() = gtk_widget_get_hexpand(widgetPointer).bool
		set(value) =
			gtk_widget_set_hexpand(widgetPointer, value.gtk)

	var horizontalExpandSet: Boolean
		get() = gtk_widget_get_hexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_hexpand_set(widgetPointer, value.gtk)

	/**
	 * TODO Figure out set?
	 */
	val isFocus: Boolean
		get() = gtk_widget_is_focus(widgetPointer).bool

	var marginStart: Int
		get() = gtk_widget_get_margin_start(widgetPointer)
		set(value) = gtk_widget_set_margin_start(widgetPointer, value)

	var marginEnd: Int
		get() = gtk_widget_get_margin_end(widgetPointer)
		set(value) = gtk_widget_set_margin_end(widgetPointer, value)

	var marginTop: Int
		get() = gtk_widget_get_margin_top(widgetPointer)
		set(value) = gtk_widget_set_margin_top(widgetPointer, value)

	var marginBottom: Int
		get() = gtk_widget_get_margin_bottom(widgetPointer)
		set(value) = gtk_widget_set_margin_bottom(widgetPointer, value)

	var name: String?
		get() = gtk_widget_get_name(widgetPointer)?.toKString()
		set(value) = gtk_widget_set_name(widgetPointer, value)

	val scaleFactor: Int
		get() = gtk_widget_get_scale_factor(widgetPointer)

	var sensitive: Boolean
		get() = gtk_widget_get_sensitive(widgetPointer).bool
		set(value) = gtk_widget_set_sensitive(widgetPointer, value.gtk)
	var parent: Widget?
		get() = gtk_widget_get_parent(widgetPointer)?.let { Widget(it) }
		set(value) = gtk_widget_set_parent(widgetPointer, value?.widgetPointer)
	var parentWindow: GWindow?
		get() = gtk_widget_get_parent_window(widgetPointer)?.let { GWindow(it) }
		set(value) = gtk_widget_set_parent_window(widgetPointer, value?.pointer)
	var events: Int
		get() = gtk_widget_get_events(widgetPointer)
		set(value) = gtk_widget_set_events(widgetPointer, value)
	var state: StateType
		get() = StateType.valueOf(gtk_widget_get_state(widgetPointer))!!
		set(value) = gtk_widget_set_state(widgetPointer, value.gtk)
	val topLevel: Widget?
		get() = gtk_widget_get_toplevel(widgetPointer)?.let { Widget(it) }
	var visual: Visual?
		get() = gtk_widget_get_visual(widgetPointer)?.let { Visual(it) }
		set(value) = gtk_widget_set_visual(widgetPointer, value?.pointer)
	var verticalAlign: Align
		get() = Align.valueOf(gtk_widget_get_valign(widgetPointer))!!
		set(value) = gtk_widget_set_valign(widgetPointer, value.gtk)


	var verticalExpand: Boolean
		get() = gtk_widget_get_vexpand(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand(widgetPointer, value.gtk)
	var verticalExpandSet: Boolean
		get() = gtk_widget_get_vexpand_set(widgetPointer).bool
		set(value) = gtk_widget_set_vexpand_set(widgetPointer, value.gtk)

	/*
	TODO Figure out constructor
	@ExperimentalUnsignedTypes
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

	@Deprecated("Removed in GTK4", level = DeprecationLevel.HIDDEN)
	open fun destroy() {
		gtk_widget_destroy(widgetPointer)
	}

	fun unParent() {
		gtk_widget_unparent(widgetPointer)
	}

	fun show() {
		gtk_widget_show(widgetPointer)
	}

	fun showNow() {
		gtk_widget_show_now(widgetPointer)
	}

	fun hide() {
		gtk_widget_hide(widgetPointer)
	}

	@Suppress("DeprecatedCallableAddReplaceWith")
	fun showAll() {
		//@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
		gtk_widget_show_all(widgetPointer)
	}

	fun map() {
		gtk_widget_map(widgetPointer)
	}

	fun realize() {
		gtk_widget_realize(widgetPointer)
	}

	fun unrealize() {
		gtk_widget_unrealize(widgetPointer)
	}

	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
	fun draw() {
		TODO("cario_t")
	}

	fun queueDraw() {
		gtk_widget_queue_draw(widgetPointer)
	}

	fun queueResize() {
		gtk_widget_queue_resize(widgetPointer)
	}

	fun queueResizeNoDraw() {
		gtk_widget_queue_resize_no_redraw(widgetPointer)
	}

	fun queueAllocate() {
		gtk_widget_queue_allocate(widgetPointer)
	}

	fun getFrameClock() {
		TODO("gtk_widget_get_frame_clock")
	}

	fun addTickCallback() {
		TODO("gtk_widget_add_tick_callback")
	}

	fun removeTickCallback() {
		TODO("gtk_widget_remove_tick_callback")
	}

	fun sizeRequest(): Requisition {
		val gtkRequisition = cValue<GtkRequisition>()
		gtk_widget_size_request(widgetPointer, gtkRequisition)
		return memScoped {
			gtkRequisition.ptr.pointed.let {
				Requisition(it.width, it.height)
			}
		}
	}

	fun sizeAllocate() {
		TODO("gtk_widget_size_allocate")
	}

	fun sizeAllocateWithBaseline() {
		TODO("gtk_widget_size_allocate_with_baseline")
	}

	fun addAccelerator() {
		TODO("gtk_widget_add_accelerator")
	}

	fun removeAccelerator() {
		TODO("gtk_widget_remove_accelerator")
	}

	fun setAcceleratorPath() {
		TODO("gtk_widget_set_accel_path")
	}

	fun listAcceleratorClosures() {
		TODO("gtk_widget_list_accel_closures")
	}

	@ExperimentalUnsignedTypes
	fun canActivateAccelerator(signalID: UInt) =
		gtk_widget_can_activate_accel(widgetPointer, signalID).bool

	fun event(event: Event) {
		TODO("gtk_widget_event")
	}

	fun reparent(widget: Widget) {
		gtk_widget_reparent(widgetPointer, widget.widgetPointer)
	}

	fun intersect() {
		TODO("gtk_widget_intersect")
	}

	fun grabFocus() {
		gtk_widget_grab_focus(widgetPointer)
	}

	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
	fun grabDefault() {
		gtk_widget_grab_default(widgetPointer)
	}

	fun addEvents(events: Int) {
		gtk_widget_add_events(widgetPointer, events)
	}

	fun setDeviceEvents(device: Device) {
		TODO("gtk_widget_get_device_events")
	}

	fun getDeviceEvents(device: Device) {
		TODO("gtk_widget_set_device_events")
	}

	fun addDeviceEvents(device: Device) {
		TODO("gtk_widget_add_device_events")
	}

	fun setDeviceEnabled(device: Device, boolean: Boolean) {
		TODO("gtk_widget_set_device_enabled")
	}

	fun getDeviceEnabled(device: Device): Boolean {
		TODO("gtk_widget_get_device_enabled")
	}


	@ExperimentalUnsignedTypes
	fun getAncestor(widgetType: ULong) =
		gtk_widget_get_ancestor(widgetPointer, widgetType)?.let { Widget(it) }

	fun getVerticalAlignmentWithBaseLine(): Align =
		Align.valueOf(gtk_widget_get_valign_with_baseline(widgetPointer))!!

	fun queueComputeExpand() {
		gtk_widget_queue_compute_expand(widgetPointer)
	}

	fun computeExpand(orientation: Orientation): Boolean =
		gtk_widget_compute_expand(widgetPointer, orientation.gtk).bool

	fun initTemplate() {
		gtk_widget_init_template(widgetPointer)
	}


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
}