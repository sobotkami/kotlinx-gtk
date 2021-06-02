package nativex.gdk

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gdk.FrameTimings.Companion.wrap
import nativex.gio.KObject
import nativex.gtk.Signals

/**
 * kotlinx-gtk
 * 16 / 04 / 2021
 *
 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html">GdkFrameClock</a>
 */
class FrameClock internal constructor(
	val frameClockPointer: CPointer<GdkFrameClock>
) : KObject(frameClockPointer.reinterpret()) {
	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-frame-time">gdk_frame_clock_get_frame_time</a>
	 */
	val frameTime: Long
		get() = gdk_frame_clock_get_frame_time(frameClockPointer)

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-frame-counter">gdk_frame_clock_get_frame_counter</a>
	 */
	val frameCounter: Long
		get() = gdk_frame_clock_get_frame_counter(frameClockPointer)

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-history-start">gdk_frame_clock_get_history_start</a>
	 */
	val historyStart: Long
		get() = gdk_frame_clock_get_history_start(frameClockPointer)

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-current-timings">gdk_frame_clock_get_current_timings</a>
	 */
	val currentTimings: FrameTimings?
		get() = gdk_frame_clock_get_current_timings(frameClockPointer).wrap()

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-after-paint">after-paint</a>
	 */
	@ExperimentalCoroutinesApi
	val afterPaintSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.AFTER_PAINT)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-before-paint">before-paint</a>
	 */
	@ExperimentalCoroutinesApi
	val beforePaintSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.BEFORE_PAINT)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-flush-events">flush-events</a>
	 */
	@ExperimentalCoroutinesApi
	val flushEvents: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.FLUSH_EVENTS)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-layout">layout</a>
	 */
	@ExperimentalCoroutinesApi
	val layoutSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.LAYOUT)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-paint">paint</a>
	 */
	@ExperimentalCoroutinesApi
	val paintSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.PAINT)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-resume-events">resume-events</a>
	 */
	@ExperimentalCoroutinesApi
	val resumeEventsSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.RESUME_EVENTS)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClock-update">update</a>
	 */
	@ExperimentalCoroutinesApi
	val updateSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.UPDATE)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-request-phase">gdk_frame_clock_request_phase</a>
	 */
	fun requestPhase(phase: Phase) {
		gdk_frame_clock_request_phase(frameClockPointer, phase.gdk)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-begin-updating">gdk_frame_clock_begin_updating</a>
	 */
	fun beginUpdating() {
		gdk_frame_clock_begin_updating(frameClockPointer)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-end-updating">gdk_frame_clock_end_updating</a>
	 */
	fun endUpdating() {
		gdk_frame_clock_end_updating(frameClockPointer)
	}

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-timings">gdk_frame_clock_get_timings</a>
	 */
	fun getTimings(frameCounter: Long): FrameTimings? =
		gdk_frame_clock_get_timings(frameClockPointer, frameCounter).wrap()

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#gdk-frame-clock-get-refresh-info">gdk_frame_clock_get_refresh_info</a>
	 */
	fun getRefreshInfo(baseTime: Long): RefreshInfo =
		memScoped {
			val cRI = cValue<LongVar>()
			val cPT = cValue<LongVar>()
			gdk_frame_clock_get_refresh_info(
				frameClockPointer,
				baseTime,
				cRI,
				cPT
			)
			RefreshInfo(
				cRI.ptr.pointed.value,
				cPT.ptr.pointed.value
			)
		}

	/**
	 * Data class for the return of [getRefreshInfo]
	 * @see getRefreshInfo
	 */
	data class RefreshInfo(
		val refreshInterval: Long,
		val presentationTimeReturn: Long
	)

	/**
	 * <a href="https://developer.gnome.org/gdk3/stable/GdkFrameClock.html#GdkFrameClockPhase">GdkFrameClockPhase</a>
	 */
	enum class Phase(val key: Int, val gdk: GdkFrameClockPhase) {
		NONE(1, GDK_FRAME_CLOCK_PHASE_NONE),
		FLUSH_EVENTS(2, GDK_FRAME_CLOCK_PHASE_FLUSH_EVENTS),
		BEFORE_PAINT(3, GDK_FRAME_CLOCK_PHASE_BEFORE_PAINT),
		UPDATE(4, GDK_FRAME_CLOCK_PHASE_UPDATE),
		LAYOUT(5, GDK_FRAME_CLOCK_PHASE_LAYOUT),
		PAINT(6, GDK_FRAME_CLOCK_PHASE_PAINT),
		RESUME_EVENTS(7, GDK_FRAME_CLOCK_PHASE_RESUME_EVENTS),
		AFTER_PAINT(8, GDK_FRAME_CLOCK_PHASE_AFTER_PAINT);

		companion object {

			internal fun valueOf(gdk: GdkFrameClockPhase) =
				values().find { it.gdk == gdk }!!
		}
	}

	companion object{
		internal inline fun CPointer<GdkFrameClock>?.wrap() =
			this?.let { FrameClock(it) }

		internal inline fun CPointer<GdkFrameClock>.wrap() =
			FrameClock(this)
	}
}