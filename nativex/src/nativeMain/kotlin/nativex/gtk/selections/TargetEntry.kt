package nativex.gtk.selections

import gtk.GtkTargetEntry
import kotlinx.cinterop.*
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Selections.html#GtkTargetEntry">GtkTargetEntry</a>
 */
class TargetEntry(val struct: CPointer<GtkTargetEntry>) {

	/**
	 * A string representation of the target type
	 */
	var target: String
		get() = struct.pointed.target?.toKString() ?: ""
		set(value) {
			memScoped {
				struct.pointed.target = value.cstr.ptr
			}
		}

	/**
	 * GtkTargetFlags for DND
	 */
	var flags: UInt
		get() = struct.pointed.flags
		set(value) {
			struct.pointed.flags = value
		}

	/**
	 * An application-assigned integer ID which will get passed as a parameter to e.g the “selection-get” signal.
	 * It allows the application to identify the target type without extensive string compares.
	 */
	var info: UInt
		get() = struct.pointed.info
		set(value) {
			struct.pointed.info = value
		}

	companion object {
		internal inline fun CPointer<GtkTargetEntry>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkTargetEntry>.wrap() =
			TargetEntry(this)
	}
}