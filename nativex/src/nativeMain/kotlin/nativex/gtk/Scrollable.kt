package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
interface Scrollable {
	val scrollablePointer: CPointer<GtkScrollable>

	var scrollableHorizontalAdjustment: Adjustment
		get() = Adjustment(gtk_scrollable_get_hadjustment(scrollablePointer)!!)
		set(value) {
			gtk_scrollable_set_hadjustment(scrollablePointer, value.adjustmentPointer)
		}

	var scrollableVerticalAdjustment: Adjustment
		get() = Adjustment(gtk_scrollable_get_vadjustment(scrollablePointer)!!)
		set(value) {
			gtk_scrollable_set_vadjustment(scrollablePointer, value.adjustmentPointer)
		}

	var horizontalScrollPolicy: Policy
		get() =
			Policy.valueOf(
				gtk_scrollable_get_hscroll_policy(scrollablePointer)
			)!!
		set(value) =
			gtk_scrollable_set_hscroll_policy(scrollablePointer, value.gtk)


	var verticalScrollPolicy: Policy
		get() =
			Policy.valueOf(
				gtk_scrollable_get_vscroll_policy(scrollablePointer)
			)!!
		set(value) =
			gtk_scrollable_set_hscroll_policy(scrollablePointer, value.gtk)

	val border: StyleContext.Border
		get() {
			val borderPointer = cValue<GtkBorder>()
			gtk_scrollable_get_border(scrollablePointer, borderPointer)
			return memScoped {
				StyleContext.Border(borderPointer.ptr)
			}
		}


	enum class Policy(val key: Int,  val gtk: GtkScrollablePolicy) {
		MINIMUM(0, GTK_SCROLL_MINIMUM),
		NATURAL(1, GTK_SCROLL_NATURAL);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			 fun valueOf(gtk: GtkScrollablePolicy) =
				values().find { it.gtk == gtk }
		}

	}
}