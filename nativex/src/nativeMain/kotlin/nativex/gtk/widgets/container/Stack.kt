package nativex.gtk.widgets.container

import gtk.*
import gtk.GtkStackTransitionType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Stack internal constructor(
	internal val stackPointer: CPointer<GtkStack>
) : Container(stackPointer.reinterpret()) {
	constructor() : this(gtk_stack_new()!!.reinterpret())

	fun addNamed(name: String, child: Widget) {
		gtk_stack_add_named(stackPointer, child.widgetPointer, name)
	}

	fun addTitled(name: String, title: String, child: Widget) {
		gtk_stack_add_titled(stackPointer, child.widgetPointer, name, title)
	}

	fun getChildByName(name: String): Widget? =
		gtk_stack_get_child_by_name(stackPointer, name).asWidgetOrNull()

	var visibleChild: Widget?
		get() = gtk_stack_get_visible_child(stackPointer).asWidgetOrNull()
		set(value) = gtk_stack_set_visible_child(
			stackPointer,
			value?.widgetPointer
		)

	fun setVisibleChildFull(name: String, transitionType: TransitionType) {
		gtk_stack_set_visible_child_full(stackPointer, name, transitionType.gtk)
	}

	var isHomogeneous: Boolean
		get() = gtk_stack_get_homogeneous(stackPointer).bool
		set(value) = gtk_stack_set_homogeneous(stackPointer, value.gtk)

	var isHorizontallyHomogeneous: Boolean
		get() = gtk_stack_get_hhomogeneous(stackPointer).bool
		set(value) = gtk_stack_set_hhomogeneous(stackPointer, value.gtk)

	var isVerticallyHomogeneous: Boolean
		get() = gtk_stack_get_vhomogeneous(stackPointer).bool
		set(value) = gtk_stack_set_vhomogeneous(stackPointer, value.gtk)

	
	var transitionDuration: UInt
		get() = gtk_stack_get_transition_duration(stackPointer)
		set(value) = gtk_stack_set_transition_duration(stackPointer, value)

	var transitionType: TransitionType
		get() = TransitionType.valueOf(
			gtk_stack_get_transition_type(
				stackPointer
			)
		)!!
		set(value) = gtk_stack_set_transition_type(stackPointer, value.gtk)

	val isTransitionRunning: Boolean
		get() = gtk_stack_get_transition_running(stackPointer).bool

	var interpolateSize: Boolean
		get() = gtk_stack_get_homogeneous(stackPointer).bool
		set(value) = gtk_stack_set_homogeneous(stackPointer, value.gtk)


	enum class TransitionType(
		val key: Int,
		internal val gtk: GtkStackTransitionType
	) {
		NONE(0, GTK_STACK_TRANSITION_TYPE_NONE),
		CROSSFADE(1, GTK_STACK_TRANSITION_TYPE_CROSSFADE),
		SLIDE_RIGHT(2, GTK_STACK_TRANSITION_TYPE_SLIDE_RIGHT),
		SLIDE_LEFT(3, GTK_STACK_TRANSITION_TYPE_SLIDE_LEFT),
		SLIDE_UP(4, GTK_STACK_TRANSITION_TYPE_SLIDE_UP),
		SLIDE_DOWN(5, GTK_STACK_TRANSITION_TYPE_SLIDE_DOWN),
		LEFT_RIGHT(6, GTK_STACK_TRANSITION_TYPE_SLIDE_LEFT_RIGHT),
		SLIDE_UP_DOWN(7, GTK_STACK_TRANSITION_TYPE_SLIDE_UP_DOWN),
		OVER_UP(8, GTK_STACK_TRANSITION_TYPE_OVER_UP),
		OVER_DOWN(9, GTK_STACK_TRANSITION_TYPE_OVER_DOWN),
		OVER_LEFT(10, GTK_STACK_TRANSITION_TYPE_OVER_LEFT),
		OVER_RIGHT(11, GTK_STACK_TRANSITION_TYPE_OVER_RIGHT),
		UNDER_UP(12, GTK_STACK_TRANSITION_TYPE_UNDER_UP),
		UNDER_DOWN(13, GTK_STACK_TRANSITION_TYPE_UNDER_DOWN),
		UNDER_LEFT(14, GTK_STACK_TRANSITION_TYPE_UNDER_LEFT),
		UNDER_RIGHT(15, GTK_STACK_TRANSITION_TYPE_UNDER_RIGHT),
		OVER_UP_DOWN(16, GTK_STACK_TRANSITION_TYPE_OVER_UP_DOWN),
		OVER_DOWN_UP(17, GTK_STACK_TRANSITION_TYPE_OVER_DOWN_UP),
		OVER_LEFT_RIGHT(18, GTK_STACK_TRANSITION_TYPE_OVER_LEFT_RIGHT),
		OVER_RIGHT_LEFT(19, GTK_STACK_TRANSITION_TYPE_OVER_RIGHT_LEFT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkStackTransitionType) =
				values().find { it.gtk == gtk }
		}
	}
}