package kotlin.gtk.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.Adjustment
import kotlin.gtk.Properties
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Container internal constructor(
	internal val containerPointer: CPointer<GtkContainer>
) : Widget(containerPointer.reinterpret()) {


	fun add(widget: Widget, vararg properties: Properties) {
		if (properties.isEmpty())
			gtk_container_add(containerPointer, widget.widgetPointer)
		else {
			TODO("Convert varag properties into C representation")
			//gtk_container_add_with_properties(containerPointer, widget.pointer)
		}
	}

	fun remove(widget: Widget) {
		gtk_container_remove(containerPointer, widget.widgetPointer)
	}

	var resizeMode: ResizeMode
		get() = ResizeMode.valueOf(
			gtk_container_get_resize_mode(
				containerPointer
			)
		)!!
		set(value) = gtk_container_set_resize_mode(containerPointer, value.gtk)

	fun checkResize() {
		gtk_container_check_resize(containerPointer)
	}

	fun foreach() {
		TODO("gtk_container_foreach")
		//gtk_container_foreach(containerPointer,,)
	}

	fun getChildren() {
		gtk_container_get_children(containerPointer)
		TODO("gtk_container_get_children")
	}


	fun getPathForChild() {
		TODO("gtk_container_get_path_for_child")
	}

	fun setReallocateRedraws(needsRedraw: Boolean) {
		gtk_container_set_reallocate_redraws(
			containerPointer,
			needsRedraw.gtkValue
		)
	}

	var focusChild: Widget?
		get() =
			gtk_container_get_focus_child(containerPointer)?.let { Widget(it) }
		set(value) =
			gtk_container_set_focus_child(
				containerPointer,
				value?.widgetPointer
			)

	var verticalAdjustment: Adjustment?
		get() =
			gtk_container_get_focus_vadjustment(containerPointer)?.let {
				Adjustment(it)
			}
		set(value) = gtk_container_set_focus_vadjustment(
			containerPointer,
			value?.pointer
		)

	var horizontalAdjustment: Adjustment?
		get() =
			gtk_container_get_focus_hadjustment(containerPointer)?.let {
				Adjustment(it)
			}
		set(value) = gtk_container_set_focus_hadjustment(
			containerPointer,
			value?.pointer
		)

	fun resizeChildren() {
		gtk_container_resize_children(containerPointer)
	}

	@ExperimentalUnsignedTypes
	val childType: ULong
		get() = gtk_container_child_type(containerPointer)

	fun childGet() {
		TODO("gtk_container_child_get")
	}

	fun childSet() {
		TODO("gtk_container_child_set")
	}

	fun childGetProperty() {
		TODO("gtk_container_child_get_property")
	}

	fun childSetProperty() {
		TODO("gtk_container_child_set_property")
	}

	fun childGetValist() {
		TODO("gtk_container_child_get_valist")
	}

	fun childSetValist() {
		TODO("gtk_container_child_set_valist")
	}

	fun childNotify() {
		TODO("gtk_container_child_notify")
	}

	fun childNotifyByPspec() {
		TODO("gtk_container_child_notify_by_pspec")
	}

	fun forall() {
		TODO("gtk_container_forall")
	}

	var borderWidth: UInt
		get() = gtk_container_get_border_width(containerPointer)
		set(value) = gtk_container_set_border_width(containerPointer, value)

	fun propogateDraw() {
		TODO("gtk_container_propogate_draw")
	}

	fun getFocusChain() {
		TODO("gtk_container_get_focus_chain")
	}

	fun setFocusChain() {
		TODO("gtk_container_get_focus_chain")
	}

	fun unsetFocusChain() {
		gtk_container_unset_focus_chain(containerPointer)
	}

	enum class ResizeMode(val key: Int, internal val gtk: GtkResizeMode) {
		PARENT(0, GtkResizeMode.GTK_RESIZE_PARENT),
		QUEUE(1, GtkResizeMode.GTK_RESIZE_QUEUE),
		IMMEDIATE(2, GtkResizeMode.GTK_RESIZE_IMMEDIATE);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkResizeMode) =
				values().find { it.gtk == gtk }
		}
	}
}