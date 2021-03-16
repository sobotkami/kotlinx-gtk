package kotlin.gtk.container.bin.scrolledwindow

import gtk.*
import gtk.GtkCornerType.*
import gtk.GtkPolicyType.*
import kotlinx.cinterop.*
import kotlin.gtk.Adjustment
import kotlin.gtk.common.enums.ShadowType
import kotlin.gtk.container.bin.Bin
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

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
			val hP = cValue<GtkPolicyType.Var>()
			val vP = cValue<GtkPolicyType.Var>()

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
		get() = Boolean.from(
			gtk_scrolled_window_get_kinetic_scrolling(
				scrolledWindowPointer
			)
		)
		set(value) = gtk_scrolled_window_set_kinetic_scrolling(
			scrolledWindowPointer,
			value.gtkValue
		)

	var captureButtonPress: Boolean
		get() = Boolean.from(
			gtk_scrolled_window_get_capture_button_press(
				scrolledWindowPointer
			)
		)
		set(value) = gtk_scrolled_window_set_capture_button_press(
			scrolledWindowPointer,
			value.gtkValue
		)

	var overlayScrolling: Boolean
		get() = Boolean.from(
			gtk_scrolled_window_get_overlay_scrolling(
				scrolledWindowPointer
			)
		)
		set(value) = gtk_scrolled_window_set_overlay_scrolling(
			scrolledWindowPointer,
			value.gtkValue
		)

	var minContentWidth: Int
		get() = gtk_scrolled_window_get_min_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_width(scrolledWindowPointer,value)

	var minContentHeight: Int
		get() = gtk_scrolled_window_get_min_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_height(scrolledWindowPointer,value)

	var maxContentWidth: Int
		get() = gtk_scrolled_window_get_max_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_width(scrolledWindowPointer,value)

	var maxContentHeight: Int
		get() = gtk_scrolled_window_get_max_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_height(scrolledWindowPointer,value)

	var propagateNaturalWidth: Boolean
		get() = Boolean.from(
			gtk_scrolled_window_get_propagate_natural_width(
				scrolledWindowPointer
			)
		)
		set(value) = gtk_scrolled_window_set_propagate_natural_width(
			scrolledWindowPointer,
			value.gtkValue
		)

	var propagateNaturalHeight: Boolean
		get() = Boolean.from(
			gtk_scrolled_window_get_propagate_natural_height(
				scrolledWindowPointer
			)
		)
		set(value) = gtk_scrolled_window_set_propagate_natural_height(
			scrolledWindowPointer,
			value.gtkValue
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