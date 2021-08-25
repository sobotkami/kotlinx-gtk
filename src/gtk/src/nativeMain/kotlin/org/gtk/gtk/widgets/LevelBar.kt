package org.gtk.gtk.widgets

import gtk.*
import gtk.GtkLevelBarMode.GTK_LEVEL_BAR_MODE_CONTINUOUS
import gtk.GtkLevelBarMode.GTK_LEVEL_BAR_MODE_DISCRETE
import kotlinx.cinterop.*
import org.gtk.async.staticCStringCallback
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * kotlinx-gtk
 *
 * 25 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html">GtkLevelBar</a>
 */
class LevelBar(
	val levelBarPointer: CPointer<GtkLevelBar>
) : Widget(levelBarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-new">gtk_level_bar_new</a>
	 */
	constructor() : this(gtk_level_bar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-new-for-interval">
	 *     gtk_level_bar_new_for_interval</a>
	 */
	constructor(
		min: Double,
		max: Double
	) : this(gtk_level_bar_new_for_interval(min, max)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-mode">gtk_level_bar_get_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-set-mode">gtk_level_bar_set_mode</a>
	 */
	var mode: Mode
		get() = Mode.valueOf(
			gtk_level_bar_get_mode(levelBarPointer)
		)!!
		set(value) = gtk_level_bar_set_mode(levelBarPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-value">gtk_level_bar_get_value</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-set-value">gtk_level_bar_set_value</a>
	 */
	var value: Double
		get() = gtk_level_bar_get_value(levelBarPointer)
		set(value) = gtk_level_bar_set_value(levelBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-min-value"gtk_level_bar_get_min_value></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-set-min-value">gtk_level_bar_set_min_value</a>
	 */
	var min: Double
		get() = gtk_level_bar_get_min_value(levelBarPointer)
		set(value) = gtk_level_bar_set_min_value(levelBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-max-value">gtk_level_bar_get_max_value</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-set-max-value">gtk_level_bar_set_max_value</a>
	 */
	var max: Double
		get() = gtk_level_bar_get_max_value(levelBarPointer)
		set(value) = gtk_level_bar_set_max_value(levelBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-inverted">gtk_level_bar_get_inverted</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-set-inverted">gtk_level_bar_set_inverted</a>
	 */
	var inverted: Boolean
		get() = gtk_level_bar_get_inverted(levelBarPointer).bool
		set(value) = gtk_level_bar_set_inverted(
			levelBarPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-add-offset-value">
	 *     gtk_level_bar_add_offset_value</a>
	 */
	fun addOffsetValue(
		@Offset
		name: String, value: Double
	) {
		gtk_level_bar_add_offset_value(levelBarPointer, name, value)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-remove-offset-value">
	 *     gtk_level_bar_remove_offset_value</a>
	 */
	fun removeOffsetValue(
		@Offset
		name: String
	) {
		gtk_level_bar_remove_offset_value(levelBarPointer, name)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#gtk-level-bar-get-offset-value">
	 *     gtk_level_bar_get_offset_value</a>
	 */
	fun getOffsetValue(
		@Offset
		name: String
	): Double? = memScoped {
		val value = cValue<DoubleVar>()
		if (gtk_level_bar_get_offset_value(
				levelBarPointer,
				name,
				value.ptr
			).bool
		) value.ptr.pointed.value
		else null
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#GtkLevelBarMode">GtkLevelBarMode</a>
	 */
	enum class Mode(val gtk: GtkLevelBarMode) {
		/**
		 * The bar has a continuous mode
		 */
		CONTINUOUS(GTK_LEVEL_BAR_MODE_CONTINUOUS),

		/**
		 * The bar has a discrete mode
		 */
		DISCRETE(GTK_LEVEL_BAR_MODE_DISCRETE);

		companion object {
			fun valueOf(gtk: GtkLevelBarMode) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#GtkLevelBar-offset-changed">
	 *     offset-changed</a>
	 */
	fun addOnOffsetChangedCallback(action: (String) -> Unit): SignalManager =
		addSignalCallback(
			Signals.OFFSET_CHANGED,
			action,
			staticCStringCallback,
		)

	companion object {
		@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
		annotation class Offset

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#GTK-LEVEL-BAR-OFFSET-LOW:CAPS">
		 *     GTK_LEVEL_BAR_OFFSET_LOW</a>
		 */
		@Offset
		const val OFFSET_LOW = "low"

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#GTK-LEVEL-BAR-OFFSET-HIGH:CAPS">
		 *     GTK_LEVEL_BAR_OFFSET_HIGH</a>
		 */
		@Offset
		const val OFFSET_HIGH = "high"

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLevelBar.html#GTK-LEVEL-BAR-OFFSET-FULL:CAPS">
		 *     GTK_LEVEL_BAR_OFFSET_FULL</a>
		 */
		@Offset
		const val OFFSET_FULL = "full"
	}
}