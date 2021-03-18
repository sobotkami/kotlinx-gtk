package nativex.gtk.widgets.container.bin.scrolledwindow

import gtk.*
import gtk.GtkCornerType.*
import gtk.GtkPolicyType.*
import gtk.GtkPolicyType.Var
import kotlinx.cinterop.*
import nativex.gtk.Adjustment
import nativex.gtk.bool
import nativex.gtk.common.enums.ShadowType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ScrolledWindow internal constructor(
	internal val scrolledWindowPointer: CPointer<GtkScrolledWindow>
) : Bin(scrolledWindowPointer.reinterpret()) {
	constructor(
		verticalAdjustment: Adjustment? = null,
		horizontalAdjustment: Adjustment? = null
	) : this(
		gtk_scrolled_window_new(
			horizontalAdjustment?.pointer,
			verticalAdjustment?.pointer
		)!!.reinterpret()
	)

	var scrollbarVerticalAdjustment: Adjustment?
		get() = gtk_scrolled_window_get_vadjustment(scrolledWindowPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scrolled_window_set_vadjustment(
			scrolledWindowPointer,
			value?.pointer
		)

	var scrollbarHorizontalAdjustment: Adjustment?
		get() = gtk_scrolled_window_get_hadjustment(scrolledWindowPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scrolled_window_set_hadjustment(
			scrolledWindowPointer,
			value?.pointer
		)


	val horizontalScrollBar: Widget
		get() = Widget(gtk_scrolled_window_get_hscrollbar(scrolledWindowPointer)!!)

	val verticalScrollBar: Widget
		get() = Widget(gtk_scrolled_window_get_vscrollbar(scrolledWindowPointer)!!)

	var policy: Pair<PolicyType, PolicyType>
		get() {
			val hP = cValue<Var>()
			val vP = cValue<Var>()

			gtk_scrolled_window_get_policy(scrolledWindowPointer, hP, vP)

			return memScoped {
				PolicyType.valueOf(hP.ptr.pointed.value)!! to PolicyType.valueOf(
					vP.ptr.pointed.value
				)!!
			}
		}
		set(value) {
			gtk_scrolled_window_set_policy(
				scrolledWindowPointer,
				value.first.gtk,
				value.second.gtk
			)
		}

	var placement: CornerType
		get() = CornerType.valueOf(
			gtk_scrolled_window_get_placement(
				scrolledWindowPointer
			)
		)!!
		set(value) = gtk_scrolled_window_set_placement(
			scrolledWindowPointer,
			value.gtk
		)

	fun unsetPlacement() {
		gtk_scrolled_window_unset_placement(scrolledWindowPointer)
	}

	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_scrolled_window_get_shadow_type(
				scrolledWindowPointer
			)
		)!!
		set(value) = gtk_scrolled_window_set_shadow_type(
			scrolledWindowPointer,
			value.gtk
		)

	var kineticScrolling: Boolean
		get() = gtk_scrolled_window_get_kinetic_scrolling(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_kinetic_scrolling(
			scrolledWindowPointer,
			value.gtk
		)

	var captureButtonPress: Boolean
		get() = gtk_scrolled_window_get_capture_button_press(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_capture_button_press(
			scrolledWindowPointer,
			value.gtk
		)

	var overlayScrolling: Boolean
		get() = gtk_scrolled_window_get_overlay_scrolling(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_overlay_scrolling(
			scrolledWindowPointer,
			value.gtk
		)

	var minContentWidth: Int
		get() = gtk_scrolled_window_get_min_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_width(
			scrolledWindowPointer,
			value
		)

	var minContentHeight: Int
		get() = gtk_scrolled_window_get_min_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_height(
			scrolledWindowPointer,
			value
		)

	var maxContentWidth: Int
		get() = gtk_scrolled_window_get_max_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_width(
			scrolledWindowPointer,
			value
		)

	var maxContentHeight: Int
		get() = gtk_scrolled_window_get_max_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_height(
			scrolledWindowPointer,
			value
		)

	var propagateNaturalWidth: Boolean
		get() = gtk_scrolled_window_get_propagate_natural_width(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_propagate_natural_width(
			scrolledWindowPointer,
			value.gtk
		)

	var propagateNaturalHeight: Boolean
		get() = gtk_scrolled_window_get_propagate_natural_height(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_propagate_natural_height(
			scrolledWindowPointer,
			value.gtk
		)


	enum class PolicyType(val key: Int, val gtk: GtkPolicyType) {
		ALWAYS(0, GTK_POLICY_ALWAYS),
		AUTOMATIC(1, GTK_POLICY_AUTOMATIC),
		NEVER(2, GTK_POLICY_NEVER),
		EXTERNAL(3, GTK_POLICY_EXTERNAL);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkPolicyType) =
				values().find { it.gtk == gtk }
		}
	}

	enum class CornerType(val key: Int, val gtk: GtkCornerType) {
		TOP_LEFT(0, GTK_CORNER_TOP_LEFT),
		BOTTOM_LEFT(1, GTK_CORNER_BOTTOM_LEFT),
		TOP_RIGHT(2, GTK_CORNER_TOP_RIGHT),
		BOTTOM_RIGHT(3, GTK_CORNER_BOTTOM_RIGHT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkCornerType) =
				values().find { it.gtk == gtk }
		}
	}

}