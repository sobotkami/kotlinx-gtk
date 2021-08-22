package nativex.gtk.widgets

import gtk.*
import gtk.GtkStackTransitionType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.asWidgetOrNull

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html">GtkStack</a>
 */
class Stack(
	val stackPointer: CPointer<GtkStack>
) : Widget(stackPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-new">gtk_stack_new</a>
	 */
	constructor() : this(gtk_stack_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-add-named">gtk_stack_add_named</a>
	 */
	fun addNamed(name: String, child: Widget) {
		gtk_stack_add_named(stackPointer, child.widgetPointer, name)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-add-titled">gtk_stack_add_titled</a>
	 */
	fun addTitled(name: String, title: String, child: Widget) {
		gtk_stack_add_titled(stackPointer, child.widgetPointer, name, title)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-child-by-name">gtk_stack_get_child_by_name</a>
	 */
	fun getChildByName(name: String): Widget? =
		gtk_stack_get_child_by_name(stackPointer, name).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-visible-child">gtk_stack_get_visible_child</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-visible-child">gtk_stack_set_visible_child</a>
	 */
	var visibleChild: Widget?
		get() = gtk_stack_get_visible_child(stackPointer).asWidgetOrNull()
		set(value) = gtk_stack_set_visible_child(
			stackPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-visible-child-name">gtk_stack_set_visible_child_name</a>
	 */
	fun setVisibleChildByName(name: String) {
		gtk_stack_set_visible_child_name(stackPointer, name)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-visible-child-name">gtk_stack_get_visible_child_name</a>
	 */
	val visibleChildName: String?
		get() = gtk_stack_get_visible_child_name(stackPointer)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-visible-child-full">gtk_stack_set_visible_child_full</a>
	 */
	fun setVisibleChildFull(name: String, transitionType: TransitionType) {
		gtk_stack_set_visible_child_full(stackPointer, name, transitionType.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-homogeneous">gtk_stack_get_homogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-homogeneous">gtk_stack_set_homogeneous</a>
	 */
	var isHomogeneous: Boolean
		get() = gtk_stack_get_homogeneous(stackPointer).bool
		set(value) = gtk_stack_set_homogeneous(stackPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-hhomogeneous">gtk_stack_get_hhomogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-hhomogeneous">gtk_stack_set_hhomogeneous</a>
	 */
	var isHorizontallyHomogeneous: Boolean
		get() = gtk_stack_get_hhomogeneous(stackPointer).bool
		set(value) = gtk_stack_set_hhomogeneous(stackPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-vhomogeneous">gtk_stack_get_vhomogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-vhomogeneous">gtk_stack_set_vhomogeneous</a>
	 */
	var isVerticallyHomogeneous: Boolean
		get() = gtk_stack_get_vhomogeneous(stackPointer).bool
		set(value) = gtk_stack_set_vhomogeneous(stackPointer, value.gtk)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-transition-duration">gtk_stack_get_transition_duration</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-transition-duration">gtk_stack_set_transition_duration</a>
	 */
	var transitionDuration: UInt
		get() = gtk_stack_get_transition_duration(stackPointer)
		set(value) = gtk_stack_set_transition_duration(stackPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-transition-type">gtk_stack_get_transition_type</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-transition-type">gtk_stack_set_transition_type</a>
	 */
	var transitionType: TransitionType
		get() = TransitionType.valueOf(
			gtk_stack_get_transition_type(
				stackPointer
			)
		)!!
		set(value) = gtk_stack_set_transition_type(stackPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-transition-running">gtk_stack_get_transition_running</a>
	 */
	val isTransitionRunning: Boolean
		get() = gtk_stack_get_transition_running(stackPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-get-interpolate-size">gtk_stack_get_interpolate_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#gtk-stack-set-interpolate-size">gtk_stack_set_interpolate_size</a>
	 */
	var interpolateSize: Boolean
		get() = gtk_stack_get_interpolate_size(stackPointer).bool
		set(value) = gtk_stack_set_interpolate_size(stackPointer, value.gtk)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkStack.html#GtkStackTransitionType">GtkStackTransitionType</a>
	 */
	enum class TransitionType(
		val key: Int,
		val gtk: GtkStackTransitionType
	) {
		/** No transition */
		NONE(0, GTK_STACK_TRANSITION_TYPE_NONE),

		/** A cross-fade */
		CROSSFADE(1, GTK_STACK_TRANSITION_TYPE_CROSSFADE),

		/** Slide from left to right */
		SLIDE_RIGHT(2, GTK_STACK_TRANSITION_TYPE_SLIDE_RIGHT),

		/** Slide from right to left */
		SLIDE_LEFT(3, GTK_STACK_TRANSITION_TYPE_SLIDE_LEFT),

		/** Slide from bottom up */
		SLIDE_UP(4, GTK_STACK_TRANSITION_TYPE_SLIDE_UP),

		/** Slide from top down */
		SLIDE_DOWN(5, GTK_STACK_TRANSITION_TYPE_SLIDE_DOWN),

		/** Slide from left or right according to the children order */
		LEFT_RIGHT(6, GTK_STACK_TRANSITION_TYPE_SLIDE_LEFT_RIGHT),

		/** Slide from top down or bottom up according to the order */
		SLIDE_UP_DOWN(7, GTK_STACK_TRANSITION_TYPE_SLIDE_UP_DOWN),

		/**
		 * Cover the old page by sliding up.
		 * @since 3.12
		 */
		OVER_UP(8, GTK_STACK_TRANSITION_TYPE_OVER_UP),

		/**
		 * Cover the old page by sliding down.
		 * @since 3.12
		 */
		OVER_DOWN(9, GTK_STACK_TRANSITION_TYPE_OVER_DOWN),

		/**
		 * Cover the old page by sliding to the left.
		 * @since 3.12
		 */
		OVER_LEFT(10, GTK_STACK_TRANSITION_TYPE_OVER_LEFT),

		/**
		 * Cover the old page by sliding to the right.
		 * @since 3.12
		 */
		OVER_RIGHT(11, GTK_STACK_TRANSITION_TYPE_OVER_RIGHT),

		/**
		 * Uncover the new page by sliding up.
		 * @since 3.12
		 */
		UNDER_UP(12, GTK_STACK_TRANSITION_TYPE_UNDER_UP),

		/**
		 * Uncover the new page by sliding down.
		 * @since 3.12
		 */
		UNDER_DOWN(13, GTK_STACK_TRANSITION_TYPE_UNDER_DOWN),

		/**
		 * Uncover the new page by sliding to the left.
		 * @since 3.12
		 */
		UNDER_LEFT(14, GTK_STACK_TRANSITION_TYPE_UNDER_LEFT),

		/**
		 * Uncover the new page by sliding to the right.
		 * @since 3.12
		 */
		UNDER_RIGHT(15, GTK_STACK_TRANSITION_TYPE_UNDER_RIGHT),

		/**
		 * Cover the old page sliding up or uncover the new page sliding down, according to order.
		 * @since 3.12
		 */
		OVER_UP_DOWN(16, GTK_STACK_TRANSITION_TYPE_OVER_UP_DOWN),

		/**
		 * Cover the old page sliding down or uncover the new page sliding up, according to order.
		 * @since 3.14
		 */
		OVER_DOWN_UP(17, GTK_STACK_TRANSITION_TYPE_OVER_DOWN_UP),

		/**
		 * Cover the old page sliding left or uncover the new page sliding right, according to order.
		 * @since 3.14
		 */
		OVER_LEFT_RIGHT(18, GTK_STACK_TRANSITION_TYPE_OVER_LEFT_RIGHT),

		/**
		 * Cover the old page sliding right or uncover the new page sliding left, according to order.
		 * @since 3.14
		 */
		OVER_RIGHT_LEFT(19, GTK_STACK_TRANSITION_TYPE_OVER_RIGHT_LEFT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkStackTransitionType) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		inline fun CPointer<GtkStack>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkStack>.wrap() =
			Stack(this)
	}
}