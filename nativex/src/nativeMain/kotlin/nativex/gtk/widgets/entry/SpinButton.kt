package nativex.gtk.widgets.entry

import gtk.*
import gtk.GtkSpinButtonUpdatePolicy.GTK_UPDATE_ALWAYS
import gtk.GtkSpinButtonUpdatePolicy.GTK_UPDATE_IF_VALID
import gtk.GtkSpinType.*
import kotlinx.cinterop.*
import nativex.gtk.Adjustment
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class SpinButton internal constructor(
	internal val spinButtonPointer: CPointer<GtkSpinButton>
) : Entry(spinButtonPointer.reinterpret()) {
	@ExperimentalUnsignedTypes
	fun configure(
		adjustment: Adjustment? = null,
		climbRate: Double,
		digits: UInt
	) {
		gtk_spin_button_configure(
			spinButtonPointer,
			adjustment?.pointer,
			climbRate,
			digits
		)
	}

	@ExperimentalUnsignedTypes
	constructor(
		adjustment: Adjustment? = null,
		climbRate: Double,
		digits: UInt
	) : this(
		gtk_spin_button_new(
			adjustment?.pointer,
			climbRate,
			digits
		)!!.reinterpret()
	)

	constructor(
		min: Double,
		max: Double,
		step: Double
	) : this(gtk_spin_button_new_with_range(min, max, step)!!.reinterpret())

	var adjustment: Adjustment?
		get() = gtk_spin_button_get_adjustment(spinButtonPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_spin_button_set_adjustment(
			spinButtonPointer,
			value?.pointer
		)

	@ExperimentalUnsignedTypes
	var digits: UInt
		get() = gtk_spin_button_get_digits(spinButtonPointer)
		set(value) = gtk_spin_button_set_digits(spinButtonPointer, value)

	var isNumeric: Boolean
		get() = gtk_spin_button_get_numeric(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_numeric(
			spinButtonPointer,
			value.gtk
		)

	var range: Pair<Double, Double>
		get() {
			return memScoped {
				val min = cValue<DoubleVar>()
				val max = cValue<DoubleVar>()
				gtk_spin_button_get_range(spinButtonPointer, min, max)
				min.ptr.pointed.value to max.ptr.pointed.value
			}
		}
		set(value) = gtk_spin_button_set_range(
			spinButtonPointer,
			value.first,
			value.second
		)

	/**
	 * first,  step
	 * second, page
	 */
	var increments: Pair<Double, Double>
		get() {
			return memScoped {
				val min = cValue<DoubleVar>()
				val max = cValue<DoubleVar>()
				gtk_spin_button_get_increments(spinButtonPointer, min, max)
				min.ptr.pointed.value to max.ptr.pointed.value
			}
		}
		set(value) = gtk_spin_button_set_increments(
			spinButtonPointer,
			value.first,
			value.second
		)


	var snapToTicks: Boolean
		get() = gtk_spin_button_get_snap_to_ticks(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_snap_to_ticks(
			spinButtonPointer,
			value.gtk
		)

	var updatePolicy: UpdatePolicy
		get() = UpdatePolicy.valueOf(
			gtk_spin_button_get_update_policy(
				spinButtonPointer
			)
		)!!
		set(value) = gtk_spin_button_set_update_policy(
			spinButtonPointer,
			value.gtk
		)

	val valueAsInt: Int
		get() = gtk_spin_button_get_value_as_int(spinButtonPointer)

	var value: Double
		get() = gtk_spin_button_get_value(spinButtonPointer)
		set(value) = gtk_spin_button_set_value(spinButtonPointer, value)

	var wrap: Boolean
		get() = gtk_spin_button_get_wrap(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_wrap(
			spinButtonPointer,
			value.gtk
		)

	fun spin(direction: SpinType, increment: Double) {
		gtk_spin_button_spin(spinButtonPointer, direction.gtk, increment)
	}

	fun update() {
		gtk_spin_button_update(spinButtonPointer)
	}

	enum class UpdatePolicy(
		val key: Int,
		internal val gtk: GtkSpinButtonUpdatePolicy
	) {
		ALWAYS(0, GTK_UPDATE_ALWAYS),
		IF_VALID(1, GTK_UPDATE_IF_VALID);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkSpinButtonUpdatePolicy) =
				values().find { it.gtk == gtk }
		}
	}

	enum class SpinType(
		val key: Int,
		internal val gtk: GtkSpinType
	) {
		STEP_FORWARD(0, GTK_SPIN_STEP_FORWARD),
		STEP_BACKWARD(0, GTK_SPIN_STEP_BACKWARD),
		PAGE_FORWARD(0, GTK_SPIN_PAGE_FORWARD),
		PAGE_BACKWARD(0, GTK_SPIN_PAGE_BACKWARD),
		HOME(0, GTK_SPIN_HOME),
		END(0, GTK_SPIN_END),
		USER_DEFINED(1, GTK_SPIN_USER_DEFINED);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkSpinType) =
				values().find { it.gtk == gtk }
		}
	}
}