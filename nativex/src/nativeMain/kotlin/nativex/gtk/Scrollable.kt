package nativex.gtk

import gtk.*
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
interface Scrollable {
	val scrollablePointer: nativex.PointerHolder<GtkScrollable>

	var scrollableHorizontalAdjustment: Adjustment
		get() = Adjustment(gtk_scrollable_get_hadjustment(scrollablePointer.ptr)!!)
		set(value) {
			gtk_scrollable_set_hadjustment(scrollablePointer.ptr, value.adjustmentPointer)
		}

	var scrollableVerticalAdjustment: Adjustment
		get() = Adjustment(gtk_scrollable_get_vadjustment(scrollablePointer.ptr)!!)
		set(value) {
			gtk_scrollable_set_vadjustment(scrollablePointer.ptr, value.adjustmentPointer)
		}

	var horizontalScrollPolicy: Policy
		get() =
			Policy.valueOf(
				gtk_scrollable_get_hscroll_policy(scrollablePointer.ptr)
			)!!
		set(value) =
			gtk_scrollable_set_hscroll_policy(scrollablePointer.ptr, value.gtk)


	var verticalScrollPolicy: Policy
		get() =
			Policy.valueOf(
				gtk_scrollable_get_vscroll_policy(scrollablePointer.ptr)
			)!!
		set(value) =
			gtk_scrollable_set_hscroll_policy(scrollablePointer.ptr, value.gtk)

	val border: StyleContext.Border
		get() {
			val borderPointer = cValue<GtkBorder>()
			gtk_scrollable_get_border(scrollablePointer.ptr, borderPointer)
			return memScoped {
				StyleContext.Border(borderPointer.ptr)
			}
		}


	enum class Policy(val key: Int, internal val gtk: GtkScrollablePolicy) {
		MINIMUM(0, GTK_SCROLL_MINIMUM),
		NATURAL(1, GTK_SCROLL_NATURAL);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkScrollablePolicy) =
				values().find { it.gtk == gtk }
		}

	}
}