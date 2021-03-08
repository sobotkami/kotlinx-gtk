package kotlin.gtk.widgets

import gtk.*
import kotlinx.cinterop.*
import kotlin.g.KotlinGObject
import kotlin.gdk.GWindow
import kotlin.gdk.Visual
import kotlin.gtk.WidgetPointer
import kotlin.gtk.common.data.Requisition
import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.from
import kotlin.gtk.gtkValue


/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */

open class Widget(
	internal open val widgetPointer: WidgetPointer
) : KotlinGObject(
	widgetPointer.reinterpret()
) {

	val isInDestruction: Boolean
		get() = Boolean.from(gtk_widget_in_destruction(widgetPointer))

	var isAppPaintable: Boolean
		get() = Boolean.from(gtk_widget_get_app_paintable(widgetPointer))
		set(value) = gtk_widget_set_app_paintable(widgetPointer, value.gtkValue)

	var canDefault: Boolean
		get() = Boolean.from(gtk_widget_get_can_default(widgetPointer))
		set(value) = gtk_widget_set_can_default(widgetPointer, value.gtkValue)

	var canFocus: Boolean
		get() = Boolean.from(gtk_widget_get_can_focus(widgetPointer))
		set(value) = gtk_widget_set_can_focus(widgetPointer, value.gtkValue)


	val isCompositeChild: Boolean
		get() = TODO("composite-child")


	var isDoubleBuffered: Boolean
		get() = Boolean.from(gtk_widget_get_double_buffered(widgetPointer))
		set(value) = gtk_widget_set_double_buffered(
			widgetPointer,
			value.gtkValue
		)


	var expand: Boolean
		get() = TODO("expand")
		set(_) = TODO("expand")


	var focusOnClick: Boolean
		get() = Boolean.from(gtk_widget_get_focus_on_click(widgetPointer))
		set(value) = gtk_widget_set_focus_on_click(
			widgetPointer,
			value.gtkValue
		)

	var horizontalAlignment: Alignment
		get() = Alignment.valueOf(gtk_widget_get_halign(widgetPointer))!!
		set(value) = gtk_widget_set_halign(widgetPointer, value.gtk)


	var hasDefault: Boolean
		get() = TODO("has-default")
		set(_) = TODO("has-default")

	var hasFocus: Boolean
		get() = TODO("has-focus")
		set(_) = TODO("has-focus")


	var hasTooltip: Boolean
		get() = Boolean.from(gtk_widget_get_has_tooltip(widgetPointer))
		set(value) = gtk_widget_set_has_tooltip(widgetPointer, value.gtkValue)


	var heightRequest: Int
		get() = TODO("height-request")
		set(_) = TODO("height-request")

	var horizontalExpand: Boolean
		get() = Boolean.from(gtk_widget_get_hexpand(widgetPointer))
		set(value) =
			gtk_widget_set_hexpand(widgetPointer, value.gtkValue)

	var horizontalExpandSet: Boolean
		get() = Boolean.from(gtk_widget_get_hexpand_set(widgetPointer))
		set(value) = gtk_widget_set_hexpand_set(widgetPointer, value.gtkValue)

	/**
	 * TODO Figure out set?
	 */
	val isFocus: Boolean
		get() = Boolean.from(gtk_widget_is_focus(widgetPointer))

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
		get() = Boolean.from(gtk_widget_get_sensitive(widgetPointer))
		set(value) = gtk_widget_set_sensitive(widgetPointer, value.gtkValue)
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
	var verticalAlignment: Alignment
		get() = Alignment.valueOf(gtk_widget_get_valign(widgetPointer))!!
		set(value) = gtk_widget_set_valign(widgetPointer, value.gtk)


	var verticalExpand: Boolean
		get() = Boolean.from(gtk_widget_get_vexpand(widgetPointer))
		set(value) = gtk_widget_set_vexpand(widgetPointer, value.gtkValue)
	var verticalExpandSet: Boolean
		get() = Boolean.from(gtk_widget_get_vexpand_set(widgetPointer))
		set(value) = gtk_widget_set_vexpand_set(widgetPointer, value.gtkValue)

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
		gtk_widget_destroy(widgetPointer)
	}

	fun unParent() {
		gtk_widget_unparent(widgetPointer)
	}

	fun show() {
		gtk_widget_show(widgetPointer);
	}

	fun showNow() {
		gtk_widget_show_now(widgetPointer)
	}

	fun hide() {
		gtk_widget_hide(widgetPointer)
	}

	fun showAll() {
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
		Boolean.from(gtk_widget_can_activate_accel(widgetPointer, signalID))

	fun event() {
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

	fun grabDefault() {
		gtk_widget_grab_default(widgetPointer)
	}

	fun addEvents(events: Int) {
		gtk_widget_add_events(widgetPointer, events)
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
		gtk_widget_get_ancestor(widgetPointer, widgetType)?.let { Widget(it) }

	fun getVerticalAlignmentWithBaseLine(): Alignment =
		Alignment.valueOf(gtk_widget_get_valign_with_baseline(widgetPointer))!!

	fun queueComputeExpand() {
		gtk_widget_queue_compute_expand(widgetPointer)
	}

	fun computeExpand(orientation: Orientation): Boolean =
		Boolean.from(gtk_widget_compute_expand(widgetPointer, orientation.gtk))

	enum class Alignment(val key: Int, internal val gtk: GtkAlign) {
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

		;

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			fun valueOf(gtk: GtkStateType) = values().find { it.gtk == gtk }
		}
	}
}