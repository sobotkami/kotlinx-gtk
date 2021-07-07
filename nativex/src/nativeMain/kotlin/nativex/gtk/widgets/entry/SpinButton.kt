package nativex.gtk.widgets.entry

import glib.gpointer
import gobject.GCallback
import gtk.*
import gtk.GtkSpinButtonUpdatePolicy.GTK_UPDATE_ALWAYS
import gtk.GtkSpinButtonUpdatePolicy.GTK_UPDATE_IF_VALID
import gtk.GtkSpinType.*
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.Signals
import nativex.gtk.Adjustment
import nativex.gtk.common.enums.ScrollType
import nativex.gobject.connectSignal
import nativex.gobject.SignalManager

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html">GtkSpinButton</a>
 */
class SpinButton(
	val spinButtonPointer: CPointer<GtkSpinButton>
) : Entry(spinButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-adjustment">gtk_spin_button_get_adjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-adjustment">gtk_spin_button_set_adjustment</a>
	 */
	var adjustment: Adjustment?
		get() = gtk_spin_button_get_adjustment(spinButtonPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_spin_button_set_adjustment(
			spinButtonPointer,
			value?.adjustmentPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-digits">gtk_spin_button_get_digits</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-digits">gtk_spin_button_set_digits</a>
	 */
	var digits: UInt
		get() = gtk_spin_button_get_digits(spinButtonPointer)
		set(value) = gtk_spin_button_set_digits(spinButtonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-numeric">gtk_spin_button_get_numeric</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-numeric">gtk_spin_button_set_numeric</a>
	 */
	var isNumeric: Boolean
		get() = gtk_spin_button_get_numeric(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_numeric(
			spinButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-range">gtk_spin_button_get_range</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-range">gtk_spin_button_set_range</a>
	 */
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-increments">gtk_spin_button_get_increments</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-increments">gtk_spin_button_set_increments</a>
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-snap-to-ticks">gtk_spin_button_get_snap_to_ticks</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-snap-to-ticks">gtk_spin_button_set_snap_to_ticks</a>
	 */
	var snapToTicks: Boolean
		get() = gtk_spin_button_get_snap_to_ticks(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_snap_to_ticks(
			spinButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-update-policy">gtk_spin_button_get_update_policy</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-update-policy">gtk_spin_button_set_update_policy</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-value-as-int">gtk_spin_button_get_value_as_int</a>
	 */
	val valueAsInt: Int
		get() = gtk_spin_button_get_value_as_int(spinButtonPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-value">gtk_spin_button_get_value</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-value">gtk_spin_button_set_value</a>
	 */
	var value: Double
		get() = gtk_spin_button_get_value(spinButtonPointer)
		set(value) = gtk_spin_button_set_value(spinButtonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-get-wrap">gtk_spin_button_get_wrap</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-set-wrap">gtk_spin_button_set_wrap</a>
	 */
	var wrap: Boolean
		get() = gtk_spin_button_get_wrap(spinButtonPointer).bool
		set(value) = gtk_spin_button_set_wrap(
			spinButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-new">gtk_spin_button_new</a>
	 */
	constructor(
		adjustment: Adjustment? = null,
		climbRate: Double,
		digits: UInt
	) : this(
		gtk_spin_button_new(
			adjustment?.adjustmentPointer,
			climbRate,
			digits
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-new-with-range">gtk_spin_button_new_with_range</a>
	 */
	constructor(
		min: Double,
		max: Double,
		step: Double
	) : this(gtk_spin_button_new_with_range(min, max, step)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-configure">gtk_spin_button_configure</a>
	 */
	fun configure(
		adjustment: Adjustment? = null,
		climbRate: Double,
		digits: UInt
	) {
		gtk_spin_button_configure(
			spinButtonPointer,
			adjustment?.adjustmentPointer,
			climbRate,
			digits
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-spin">gtk_spin_button_spin</a>
	 */
	fun spin(direction: SpinType, increment: Double) {
		gtk_spin_button_spin(spinButtonPointer, direction.gtk, increment)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#gtk-spin-button-update">gtk_spin_button_update</a>
	 */
	fun update() {
		gtk_spin_button_update(spinButtonPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-change-value">change-value</a>
	 */
	fun addOnChangeValueCallback(action: ChangeValueFunction): SignalManager =
		SignalManager(
			spinButtonPointer,
			spinButtonPointer.connectSignal(
				Signals.CHANGE_VALUE,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticChangeValueFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-input">input</a>
	 */
	fun addOnInputCallback(action: SpinButtonInputFunction): SignalManager =
		SignalManager(
			spinButtonPointer,
			spinButtonPointer.connectSignal(
				Signals.INPUT,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticInputFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-output">output</a>
	 */
	fun addOnOutputCallback(action: SpinButtonOutputFunction): SignalManager =
		SignalManager(
			spinButtonPointer,
			spinButtonPointer.connectSignal(
				Signals.OUTPUT,
				StableRef.create(action).asCPointer(),
				staticOutputFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-value-changed">value-changed</a>
	 */
	fun addOnValueChangedCallback(action: () -> Unit): SignalManager =
		SignalManager(
			spinButtonPointer,
			spinButtonPointer.connectSignal(
				Signals.VALUE_CHANGED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-wrapped">wrapped</a>
	 */
	fun addOnWrappedCallback(action: () -> Unit): SignalManager =
		SignalManager(
			spinButtonPointer,
			spinButtonPointer.connectSignal(
				Signals.WRAPPED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * Easy way to handle the conversion states
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-change-value">change-value</a>
	 */
	sealed class ConversionResult {
		class Success(val data: Double) : ConversionResult()
		object Unhandled : ConversionResult()
		object Error : ConversionResult()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButtonUpdatePolicy">GtkSpinButtonUpdatePolicy</a>
	 */
	enum class UpdatePolicy(val gtk: GtkSpinButtonUpdatePolicy) {

		/**
		 * When refreshing your [SpinButton], the value is always displayed
		 */
		ALWAYS(GTK_UPDATE_ALWAYS),

		/**
		 * When refreshing your [SpinButton],
		 * the value is only displayed if it is valid within the bounds of the spin button's adjustment
		 */
		IF_VALID(GTK_UPDATE_IF_VALID);

		companion object {
			fun valueOf(gtk: GtkSpinButtonUpdatePolicy) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinType">GtkSpinType</a>
	 */
	enum class SpinType(val gtk: GtkSpinType) {

		/**
		 * Increment by the adjustments step increment.
		 */
		STEP_FORWARD(GTK_SPIN_STEP_FORWARD),

		/**
		 * Decrement by the adjustments step increment.
		 */
		STEP_BACKWARD(GTK_SPIN_STEP_BACKWARD),

		/**
		 * Increment by the adjustments page increment.
		 */
		PAGE_FORWARD(GTK_SPIN_PAGE_FORWARD),

		/**
		 * Decrement by the adjustments page increment.
		 */
		PAGE_BACKWARD(GTK_SPIN_PAGE_BACKWARD),

		/**
		 * Go to the adjustments lower bound.
		 */
		HOME(GTK_SPIN_HOME),

		/**
		 * Go to the adjustments upper bound.
		 */
		END(GTK_SPIN_END),

		/**
		 * Change by a specified amount.
		 */
		USER_DEFINED(GTK_SPIN_USER_DEFINED);

		companion object {
			fun valueOf(gtk: GtkSpinType) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-change-value">change-value</a>
		 */
		private val staticChangeValueFunction: GCallback =
			staticCFunction { _: gpointer?, scrollType: GtkScrollType, data: gpointer? ->
				data?.asStableRef<ChangeValueFunction>()
					?.get()
					?.invoke(ScrollType.valueOf(scrollType)!!)
				Unit
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-input">input</a>
		 */
		private val staticInputFunction: GCallback =
			staticCFunction { _: gpointer?, newValue: CPointer<DoubleVar>, data: gpointer? ->
				val convertedValue = data?.asStableRef<SpinButtonInputFunction>()
					?.get()
					?.invoke() ?: ConversionResult.Unhandled

				return@staticCFunction when (convertedValue) {
					ConversionResult.Error -> GTK_INPUT_ERROR
					is ConversionResult.Success -> {
						newValue.pointed.value = convertedValue.data
						true.gtk
					}
					ConversionResult.Unhandled -> false.gtk
				}
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-output">output</a>
		 */
		private val staticOutputFunction: GCallback =
			staticCFunction { _: gpointer?, data: gpointer? ->
				data?.asStableRef<SpinButtonOutputFunction>()
					?.get()
					?.invoke().gtk
			}.reinterpret()
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-change-value">change-value</a>
 */
typealias ChangeValueFunction = (ScrollType) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-input">input</a>
 */
typealias SpinButtonInputFunction = () -> SpinButton.ConversionResult

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSpinButton.html#GtkSpinButton-output">output</a>
 */
typealias SpinButtonOutputFunction = () -> Boolean