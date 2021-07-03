package nativex.gtk.widgets

import gtk.*
import gtk.GtkLevelBarMode.GTK_LEVEL_BAR_MODE_CONTINUOUS
import gtk.GtkLevelBarMode.GTK_LEVEL_BAR_MODE_DISCRETE
import kotlinx.cinterop.*
import kotlinx.coroutines.flow.Flow
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 25 / 03 / 2021
 */
class LevelBar(
	 val levelBarPointer: CPointer<GtkLevelBar>
) : Widget(levelBarPointer.reinterpret()) {
	constructor() : this(gtk_level_bar_new()!!.reinterpret())
	constructor(
		min: Double,
		max: Double
	) : this(gtk_level_bar_new_for_interval(min, max)!!.reinterpret())

	var mode: Mode
		get() = Mode.valueOf(
			gtk_level_bar_get_mode(levelBarPointer)
		)!!
		set(value) = gtk_level_bar_set_mode(levelBarPointer, value.gtk)

	var value: Double
		get() = gtk_level_bar_get_value(levelBarPointer)
		set(value) = gtk_level_bar_set_value(levelBarPointer, value)

	var min: Double
		get() = gtk_level_bar_get_min_value(levelBarPointer)
		set(value) = gtk_level_bar_set_min_value(levelBarPointer, value)

	var max: Double
		get() = gtk_level_bar_get_max_value(levelBarPointer)
		set(value) = gtk_level_bar_set_max_value(levelBarPointer, value)

	var inverted: Boolean
		get() = gtk_level_bar_get_inverted(levelBarPointer).bool
		set(value) = gtk_level_bar_set_inverted(
			levelBarPointer,
			value.gtk
		)

	fun addOffsetValue(
		@Offset
		name: String, value: Double
	) {
		gtk_level_bar_add_offset_value(levelBarPointer, name, value)
	}

	fun removeOffsetValue(
		@Offset
		name: String
	) {
		gtk_level_bar_remove_offset_value(levelBarPointer, name)
	}

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


	enum class Mode(val key: Int,  val gtk: GtkLevelBarMode) {
		CONTINUOUS(0, GTK_LEVEL_BAR_MODE_CONTINUOUS),
		DISCRETE(1, GTK_LEVEL_BAR_MODE_DISCRETE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gtk: GtkLevelBarMode) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * TODO Figure out char signals
	 */
	val offsetChangedSignal: Flow<Char>
		get() {
			TODO()
		}

	companion object {
		@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
		annotation class Offset

		@Offset
		const val OFFSET_LOW = "low"

		@Offset
		const val OFFSET_HIGH = "high"

		@Offset
		const val OFFSET_FULL = "full"
	}
}