package nativex.gdk

import gtk.*
import kotlinx.cinterop.CPointer
import nativex.gdk.Device.Companion.wrap
import nativex.gdk.Display.Companion.wrap
import nativex.glib.asKSequence

/**
 * @see <a href=""></a>
 */
/**
 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkSeat.html">
 *     GdkSeat</a>
 */
class Seat(
	val seatPointer: CPointer<GdkSeat>
) {

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkSeat.html#gdk-seat-get-display">
	 *     gdk_seat_get_display</a>
	 */
	val display: Display
		get() = gdk_seat_get_display(seatPointer)!!.wrap()


	fun grab(): Unit = TODO("gdk_seat_grab")

	val pointer: Device?
		get() = gdk_seat_get_pointer(seatPointer).wrap()

	val keyboard: Device?
		get() = gdk_seat_get_keyboard(seatPointer).wrap()

	fun addOnDeviceAddedCallback() {
		TODO("device-added")
	}

	fun addOnDeviceRemovedCallback() {
		TODO("device-removed")
	}

	fun addOnToolAddedCallback() {
		TODO("tool-added")
	}

	fun addOnToolRemovedCallback() {
		TODO("tool-removed")
	}


	enum class Capabilities(val key: Int, val gdk: GdkSeatCapabilities) {
		NONE(0, GDK_SEAT_CAPABILITY_NONE),
		POINTER(1, GDK_SEAT_CAPABILITY_POINTER),
		TOUCH(2, GDK_SEAT_CAPABILITY_TOUCH),
		TABLET_STYLUS(3, GDK_SEAT_CAPABILITY_TABLET_STYLUS),
		KEYBOARD(4, GDK_SEAT_CAPABILITY_KEYBOARD),
		ALL_POINTING(5, GDK_SEAT_CAPABILITY_ALL_POINTING),
		ALL(6, GDK_SEAT_CAPABILITY_ALL);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gdk: GdkSeatCapabilities) = values().find { it.gdk == gdk }
		}
	}

	companion object {
		inline fun CPointer<GdkSeat>?.wrap() =
			this?.let { Seat(it) }

		inline fun CPointer<GdkSeat>.wrap() =
			Seat(this)
	}
}