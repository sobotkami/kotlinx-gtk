package nativex.gdk

import gtk.*
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 15 / 03 / 2021
 * TODO
 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-Event-Structures.html#GdkEvent">GdkEvent union</a>
 */
class Event internal constructor(
	internal val eventPointer: CPointer<GdkEvent>
) {
	companion object {

		internal inline fun CPointer<GdkEvent>?.wrap() =
			this?.let { Event(it) }

		internal inline fun CPointer<GdkEvent>.wrap() =
			Event(this)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-Events.html#GdkEventMask">GdkEventMask</a>
	 */
	enum class Mask(val key: Int, internal val gdk: GdkEventMask) {
		EXPOSURE_MASK(0, GDK_EXPOSURE_MASK),
		POINTER_MOTION_MASK(1, GDK_POINTER_MOTION_MASK),
		POINTER_MOTION_HINT_MASK(2, GDK_POINTER_MOTION_HINT_MASK),
		BUTTON_MOTION_MASK(3, GDK_BUTTON_MOTION_MASK),
		BUTTON1_MOTION_MASK(4, GDK_BUTTON1_MOTION_MASK),
		BUTTON2_MOTION_MASK(5, GDK_BUTTON2_MOTION_MASK),
		BUTTON3_MOTION_MASK(6, GDK_BUTTON3_MOTION_MASK),
		BUTTON_PRESS_MASK(7, GDK_BUTTON_PRESS_MASK),
		BUTTON_RELEASE_MASK(8, GDK_BUTTON_RELEASE_MASK),
		KEY_PRESS_MASK(9, GDK_KEY_PRESS_MASK),
		KEY_RELEASE_MASK(10, GDK_RELEASE_MASK),
		ENTER_NOTIFY_MASK(11, GDK_ENTER_NOTIFY_MASK),
		LEAVE_NOTIFY_MASK(12, GDK_LEAVE_NOTIFY_MASK),
		FOCUS_CHANGE_MASK(13, GDK_FOCUS_CHANGE_MASK),
		STRUCTURE_MASK(14, GDK_STRUCTURE_MASK),
		PROPERTY_CHANGE_MASK(15, GDK_PROPERTY_CHANGE_MASK),
		VISIBILITY_NOTIFY_MASK(16, GDK_VISIBILITY_NOTIFY_MASK),
		PROXIMITY_IN_MASK(17, GDK_PROXIMITY_IN_MASK),
		PROXIMITY_OUT_MASK(18, GDK_PROXIMITY_OUT_MASK),
		SUBSTRUCTURE_MASK(19, GDK_SUBSTRUCTURE_MASK),
		SCROLL_MASK(20, GDK_SCROLL_MASK),
		TOUCH_MASK(21, GDK_TOUCH_MASK),
		SMOOTH_SCROLL_MASK(22, GDK_SMOOTH_SCROLL_MASK),
		TOUCHPAD_GESTURE_MASK(23, GDK_TOUCHPAD_GESTURE_MASK),
		TABLET_PAD_MASK(24, GDK_TABLET_PAD_MASK),
		ALL_EVENTS_MASK(25, GDK_ALL_EVENTS_MASK);

		companion object {
			internal fun valueOf(gdk: GdkEventMask) =
				values().find { it.gdk == gdk }
		}

	}
}