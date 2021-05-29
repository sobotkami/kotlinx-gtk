package nativex.gdk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.coroutines.flow.Flow
import nativex.gdk.Device.Companion.wrap
import nativex.gdk.Display.Companion.wrap
import nativex.gtk.asKSequence

/**
 * @see <a href=""></a>
 */
/**
 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkSeat.html">
 *     GdkSeat</a>
 */
class Seat internal constructor(
	internal val seatPointer: CPointer<GdkSeat>
) {

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkSeat.html#gdk-seat-get-display">
	 *     gdk_seat_get_display</a>
	 */
	val display: Display
		get() = gdk_seat_get_display(seatPointer)!!.wrap()


	fun grab(): Unit = TODO("gdk_seat_grab")

	fun ungrab() {
		gdk_seat_ungrab(seatPointer)
	}

	val pointer: Device?
		get() = gdk_seat_get_pointer(seatPointer).wrap()

	val keyboard: Device?
		get() = gdk_seat_get_keyboard(seatPointer).wrap()

	fun getSubordinates(capabilities: Capabilities): Sequence<Device> =
		gdk_seat_get_slaves(seatPointer,capabilities.gdk).asKSequence<GdkDevice,Device> {
			it.wrap()
		}

	val deviceAddedSignal:Flow<Unit> = TODO("device-added")
	val deviceRemovedSignal:Flow<Unit> = TODO("device-removed")
	val toolAddedSignal:Flow<Unit> = TODO("tool-added")
	val toolRemovedSignal:Flow<Unit> = TODO("tool-removed")


	enum class Capabilities(val key: Int, internal val gdk: GdkSeatCapabilities) {
		NONE(0, GDK_SEAT_CAPABILITY_NONE),
		POINTER(1, GDK_SEAT_CAPABILITY_POINTER),
		TOUCH(2, GDK_SEAT_CAPABILITY_TOUCH),
		TABLET_STYLUS(3, GDK_SEAT_CAPABILITY_TABLET_STYLUS),
		KEYBOARD(4, GDK_SEAT_CAPABILITY_KEYBOARD),
		ALL_POINTING(5, GDK_SEAT_CAPABILITY_ALL_POINTING),
		ALL(6, GDK_SEAT_CAPABILITY_ALL);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			internal fun valueOf(gdk: GdkSeatCapabilities) = values().find { it.gdk == gdk }
		}
	}

	companion object {
		internal inline fun CPointer<GdkSeat>?.wrap() =
			this?.let { Seat(it) }

		internal inline fun CPointer<GdkSeat>.wrap() =
			Seat(this)
	}
}