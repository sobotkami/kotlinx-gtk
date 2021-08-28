package org.gtk.gtk.widgets
import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.connectSignal

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class Switch(val switchPointer: CPointer<GtkSwitch>) : Widget(switchPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#gtk-switch-get-active">
	 *     gtk_switch_get_active</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#gtk-switch-set-active">
	 *     gtk_switch_set_active</a>
	 */
	var isActive: Boolean
		get() = gtk_switch_get_active(switchPointer).bool
		set(value) = gtk_switch_set_active(switchPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#gtk-switch-get-state">
	 *     gtk_switch_get_state</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#gtk-switch-set-state">
	 *     gtk_switch_set_active</a>
	 */
	var switchState: Boolean
		get() = gtk_switch_get_state(switchPointer).bool
		set(value) = gtk_switch_set_active(switchPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#gtk-switch-new">
	 *     gtk_switch_new</a>
	 */
	constructor() : this(gtk_switch_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#GtkSwitch-activate">activate</a>
	 */
	fun addOnActivateCallback(action: () -> Unit): SignalManager =
		SignalManager(
			switchPointer,
			switchPointer.connectSignal(
				Signals.ACTIVATE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#GtkSwitch-state-set">state-set</a>
	 */
	fun addOnStateSetCallback(action: SwitchStateSetFunction): SignalManager =
		SignalManager(
			switchPointer,
			switchPointer.connectSignal(
				Signals.ACTIVATE,
				StableRef.create(action).asCPointer(),
				staticStateSetFunction
			)
		)

	companion object {
		private val staticStateSetFunction: GCallback =
			staticCFunction { _: gpointer?,
			                  arg1: gboolean,
			                  data: gpointer? ->
				data?.asStableRef<SwitchStateSetFunction>()?.get()
					?.invoke(arg1.bool).gtk
			}.reinterpret()

		inline fun CPointer<GtkSwitch>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkSwitch>.wrap() =
			Switch(this)
	}
}


/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSwitch.html#GtkSwitch-state-set">state-set</a>
 */
typealias SwitchStateSetFunction = (Boolean) -> Boolean