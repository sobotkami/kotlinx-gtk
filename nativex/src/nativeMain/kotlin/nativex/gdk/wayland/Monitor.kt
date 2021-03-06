package nativex.gdk.wayland

import gtk.*
import gtk.GdkSubpixelLayout.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.gdk.Display
import nativex.gdk.Display.Companion.wrap
import nativex.gdk.Rectangle
import nativex.gobject.KObject
import nativex.glib.bool
import nativex.gobject.Signals

/**
 * Question why such details are needed by a program, ever?
 */
class Monitor(
	 val monitorPointer: CPointer<GdkMonitor>
) : KObject(monitorPointer.reinterpret()) {
	val display: Display
		get() = gdk_monitor_get_display(monitorPointer)!!.wrap()

	val geometry: Any
		get() = TODO("gdk_monitor_get_geometry")

	val workarea: Rectangle
		get() = memScoped {
			val cWorkArea = cValue<GdkRectangle>()
			gdk_monitor_get_workarea(monitorPointer, cWorkArea)
			Rectangle(cWorkArea.ptr)
		}

	val widthMM: Int
		get() = gdk_monitor_get_width_mm(monitorPointer)

	val heightMM: Int
		get() = gdk_monitor_get_height_mm(monitorPointer)

	val manufacturer: String?
		get() = gdk_monitor_get_manufacturer(monitorPointer)?.toKString()

	val model: String?
		get() = gdk_monitor_get_model(monitorPointer)?.toKString()

	val scaleFactor: Int
		get() = gdk_monitor_get_scale_factor(monitorPointer)

	val refreshRate: Int
		get() = gdk_monitor_get_refresh_rate(monitorPointer)

	val subpixelLayout: SubpixelLayout
		get() = SubpixelLayout.valueOf(gdk_monitor_get_subpixel_layout(monitorPointer))

	val isPrimary: Boolean
		get() = gdk_monitor_is_primary(monitorPointer).bool

	@ExperimentalCoroutinesApi
	val invalidateSignal: Flow<Unit> by signalFlow(Signals.INVALIDATE)

	enum class SubpixelLayout(val key: Int,  val gdk: GdkSubpixelLayout) {
		UNKNOWN(0, GDK_SUBPIXEL_LAYOUT_UNKNOWN),
		NONE(1, GDK_SUBPIXEL_LAYOUT_NONE),
		HORIZONTAL_RGB(2, GDK_SUBPIXEL_LAYOUT_HORIZONTAL_RGB),
		HORIZONTAL_BGR(3, GDK_SUBPIXEL_LAYOUT_HORIZONTAL_BGR),
		VERTICAL_RGB(4, GDK_SUBPIXEL_LAYOUT_VERTICAL_RGB),
		VERTICAL_BGR(5, GDK_SUBPIXEL_LAYOUT_VERTICAL_BGR);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key } ?: UNKNOWN
			fun valueOf(gdk: GdkSubpixelLayout) = values().find { it.gdk == gdk } ?: UNKNOWN
		}
	}

	companion object{

		 inline fun CPointer<GdkMonitor>?.wrap() =
			this?.let { Monitor(it) }

		 inline fun CPointer<GdkMonitor>.wrap() =
			Monitor(this)
	}

}