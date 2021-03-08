package kotlin.gtk

import gtk.*
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKString
import kotlin.gdk.GWindow
import kotlin.gdk.Visual
import kotlin.gtk.data.Requisition
import kotlin.gtk.enums.Alignment
import kotlin.gtk.enums.Orientation
import kotlin.gtk.enums.StateType


/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */

open class Widget(internal open val pointer: WidgetPointer) {

	val isInDestruction: Boolean
		get() = Boolean.from(gtk_widget_in_destruction(pointer))

	var isAppPaintable: Boolean
		get() = Boolean.from(gtk_widget_get_app_paintable(pointer))
		set(value) = gtk_widget_set_app_paintable(pointer, value.gtkValue)

	var canDefault: Boolean
		get() = Boolean.from(gtk_widget_get_can_default(pointer))
		set(value) = gtk_widget_set_can_default(pointer, value.gtkValue)

	var canFocus: Boolean
		get() = Boolean.from(gtk_widget_get_can_focus(pointer))
		set(value) = gtk_widget_set_can_focus(pointer, value.gtkValue)


	val isCompositeChild: Boolean
		get() = TODO("composite-child")


	var isDoubleBuffered: Boolean
		get() = Boolean.from(gtk_widget_get_double_buffered(pointer))
		set(value) = gtk_widget_set_double_buffered(pointer, value.gtkValue)


	var expand: Boolean
		get() = TODO("expand")
		set(value) = TODO("expand")


	var focusOnClick: Boolean
		get() = Boolean.from(gtk_widget_get_focus_on_click(pointer))
		set(value) = gtk_widget_set_focus_on_click(pointer, value.gtkValue)

	var horizontalAlignment: Alignment
		get() = Alignment.valueOf(gtk_widget_get_halign(pointer))!!
		set(value) = gtk_widget_set_halign(pointer, value.gtk)


	var hasDefault: Boolean
		get() = TODO("has-default")
		set(value) = TODO("has-default")

	var hasFocus: Boolean
		get() = TODO("has-focus")
		set(value) = TODO("has-focus")


	var hasTooltip: Boolean
		get() = Boolean.from(gtk_widget_get_has_tooltip(pointer))
		set(value) = gtk_widget_set_has_tooltip(pointer, value.gtkValue)


	var heightRequest: Int
		get() = TODO("height-request")
		set(value) = TODO("height-request")

	var horizontalExpand: Boolean
		get() = Boolean.from(gtk_widget_get_hexpand(pointer))
		set(value) =
			gtk_widget_set_hexpand(pointer, value.gtkValue)

	var horizontalExpandSet: Boolean
		get() = Boolean.from(gtk_widget_get_hexpand_set(pointer))
		set(value) = gtk_widget_set_hexpand_set(pointer, value.gtkValue)

	/**
	 * TODO Figure out set?
	 */
	val isFocus: Boolean
		get() = Boolean.from(gtk_widget_is_focus(pointer))

	var marginStart: Int
		get() = gtk_widget_get_margin_start(pointer)
		set(value) = gtk_widget_set_margin_start(pointer, value)

	var marginEnd: Int
		get() = gtk_widget_get_margin_end(pointer)
		set(value) = gtk_widget_set_margin_end(pointer, value)

	var marginTop: Int
		get() = gtk_widget_get_margin_top(pointer)
		set(value) = gtk_widget_set_margin_top(pointer, value)

	var marginBottom: Int
		get() = gtk_widget_get_margin_bottom(pointer)
		set(value) = gtk_widget_set_margin_bottom(pointer, value)

	var name: String?
		get() = gtk_widget_get_name(pointer)?.toKString()
		set(value) = gtk_widget_set_name(pointer, value)

	val scaleFactor: Int
		get() = gtk_widget_get_scale_factor(pointer)

	var sensitive: Boolean
		get() = Boolean.from(gtk_widget_get_sensitive(pointer))
		set(value) = gtk_widget_set_sensitive(pointer, value.gtkValue)
	var parent: Widget?
		get() = gtk_widget_get_parent(pointer)?.let { Widget(it) }
		set(value) = gtk_widget_set_parent(pointer, value?.pointer)
	var parentWindow: GWindow?
		get() = gtk_widget_get_parent_window(pointer)?.let { GWindow(it) }
		set(value) = gtk_widget_set_parent_window(pointer, value?.pointer)
	var events: Int
		get() = gtk_widget_get_events(pointer)
		set(value) = gtk_widget_set_events(pointer, value)
	var state: StateType
		get() = StateType.valueOf(gtk_widget_get_state(pointer))!!
		set(value) = gtk_widget_set_state(pointer, value.gtk)
	val topLevel: Widget?
		get() = gtk_widget_get_toplevel(pointer)?.let { Widget(it) }
	var visual: Visual?
		get() = gtk_widget_get_visual(pointer)?.let { Visual(it) }
		set(value) = gtk_widget_set_visual(pointer, value?.pointer)
	var verticalAlignment: Alignment
		get() = Alignment.valueOf(gtk_widget_get_valign(pointer))!!
		set(value) = gtk_widget_set_valign(pointer, value.gtk)


	var verticalExpand: Boolean
		get() = Boolean.from(gtk_widget_get_vexpand(pointer))
		set(value) = gtk_widget_set_vexpand(pointer, value.gtkValue)
	var verticalExpandSet: Boolean
		get() = Boolean.from(gtk_widget_get_vexpand_set(pointer))
		set(value) = gtk_widget_set_vexpand_set(pointer, value.gtkValue)

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

	open fun destroy() {
		gtk_widget_destroy(pointer)
	}

	fun unParent() {
		gtk_widget_unparent(pointer)
	}

	fun show() {
		gtk_widget_show(pointer);
	}

	fun showNow() {
		gtk_widget_show_now(pointer)
	}

	fun hide() {
		gtk_widget_hide(pointer)
	}

	fun showAll() {
		gtk_widget_show_all(pointer)
	}

	fun map() {
		gtk_widget_map(pointer)
	}

	fun realize() {
		gtk_widget_realize(pointer)
	}

	fun unrealize() {
		gtk_widget_unrealize(pointer)
	}

	fun draw() {
		TODO("cario_t")
	}

	fun queueDraw() {
		gtk_widget_queue_draw(pointer)
	}

	fun queueResize() {
		gtk_widget_queue_resize(pointer)
	}

	fun queueResizeNoDraw() {
		gtk_widget_queue_resize_no_redraw(pointer)
	}

	fun queueAllocate() {
		gtk_widget_queue_allocate(pointer)
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
		gtk_widget_size_request(pointer, gtkRequisition)
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
		Boolean.from(gtk_widget_can_activate_accel(pointer, signalID))

	fun event() {
		TODO("gtk_widget_event")
	}

	fun reparent(widget: Widget) {
		gtk_widget_reparent(pointer, widget.pointer)
	}

	fun intersect() {
		TODO("gtk_widget_intersect")
	}

	fun grabFocus() {
		gtk_widget_grab_focus(pointer)
	}

	fun grabDefault() {
		gtk_widget_grab_default(pointer)
	}

	fun addEvents(events: Int) {
		gtk_widget_add_events(pointer, events)
	}

	fun setDeviceEvents() {
		TODO("gtk_widget_get_device_events")
	}

	fun getDeviceEvents() {
		TODO("gtk_widget_set_device_events")
	}

	fun addDeviceEvents() {
		TODO("gtk_widget_add_device_events")
	}

	fun setDeviceEnabled() {
		TODO("gtk_widget_set_device_enabled")
	}

	fun getDeviceEnabled() {
		TODO("gtk_widget_get_device_enabled")
	}

	@ExperimentalUnsignedTypes
	fun getAncestor(widgetType: ULong) =
		gtk_widget_get_ancestor(pointer, widgetType)?.let { Widget(it) }

	fun getVerticalAlignmentWithBaseLine(): Alignment =
		Alignment.valueOf(gtk_widget_get_valign_with_baseline(pointer))!!

	fun queueComputeExpand() {
		gtk_widget_queue_compute_expand(pointer)
	}

	fun computeExpand(orientation: Orientation): Boolean =
		Boolean.from(gtk_widget_compute_expand(pointer, orientation.gtk))
}