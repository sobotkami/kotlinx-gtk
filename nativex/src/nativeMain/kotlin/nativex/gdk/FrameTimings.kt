package nativex.gdk

import gtk.*
import kotlinx.cinterop.CPointer
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 17 / 04 / 2021
 *
 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html">GdkFrameTimings</a>
 */
class FrameTimings internal constructor(
	internal val pointer: CPointer<GdkFrameTimings>
) {
	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-ref">gdk_frame_timings_ref</a>
	 */
	fun ref() {
		gdk_frame_timings_ref(pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-unref">gdk_frame_timings_unref</a>
	 */
	fun unref() {
		gdk_frame_timings_unref(pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-frame-counter">gdk_frame_timings_get_frame_counter</a>
	 */
	val frameCounter: Long
		get() = gdk_frame_timings_get_frame_counter(pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-complete">gdk_frame_timings_get_complete</a>
	 */
	val isComplete: Boolean
		get() = gdk_frame_timings_get_complete(pointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-frame-time">gdk_frame_timings_get_frame_time</a>
	 */
	val frameTime: Long
		get() = gdk_frame_timings_get_frame_time(pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-presentation-time">gdk_frame_timings_get_presentation_time</a>
	 */
	val presentationTime: Long
		get() = gdk_frame_timings_get_presentation_time(pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-refresh-interval">gdk_frame_timings_get_refresh_interval</a>
	 */
	val refreshInterval: Long
		get() = gdk_frame_timings_get_refresh_interval(pointer)

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-GdkFrameTimings.html#gdk-frame-timings-get-predicted-presentation-time">gdk_frame_timings_get_predicted_presentation_time</a>
	 */
	val predictedPresentationTime: Long
		get() = gdk_frame_timings_get_predicted_presentation_time(pointer)
}