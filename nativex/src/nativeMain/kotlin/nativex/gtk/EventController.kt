package nativex.gtk

import gtk.*
import gtk.GtkPropagationLimit.GTK_LIMIT_NONE
import gtk.GtkPropagationLimit.GTK_LIMIT_SAME_NATIVE
import gtk.GtkPropagationPhase.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gdk.Device
import nativex.gdk.Device.Companion.wrap
import nativex.gdk.Event
import nativex.gdk.Event.Companion.wrap
import nativex.gobject.KGObject
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Widget.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 26 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#GtkEventController-struct">
 *     GtkEventController</a>
 */
class EventController(val eventControllerPointer: CPointer<GtkEventController>) :
	KGObject(eventControllerPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-propagation-phase">
	 *     gtk_event_controller_get_propagation_phase</a>
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-set-propagation-phase">
	 *     gtk_event_controller_set_propagation_phase</a>
	 */
	var propagationPhase: PropagationPhase
		get() = PropagationPhase.valueOf(gtk_event_controller_get_propagation_phase(eventControllerPointer))!!
		set(value) = gtk_event_controller_set_propagation_phase(eventControllerPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-propagation-limit">
	 *     gtk_event_controller_get_propagation_limit</a>
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-set-propagation-limit">
	 *     gtk_event_controller_set_propagation_limit</a>
	 */
	var propagationLimit: PropagationLimit
		get() = PropagationLimit.valueOf(gtk_event_controller_get_propagation_limit(eventControllerPointer))!!
		set(value) = gtk_event_controller_set_propagation_limit(eventControllerPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-widget">
	 *     gtk_event_controller_get_widget</a>
	 */
	val widget: Widget
		get() = gtk_event_controller_get_widget(eventControllerPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-reset">
	 *     gtk_event_controller_reset</a>
	 */
	fun reset() {
		gtk_event_controller_reset(eventControllerPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-name">
	 *     gtk_event_controller_get_name</a>
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-set-name">
	 *     gtk_event_controller_set_name</a>
	 */
	var name: String
		get() = gtk_event_controller_get_name(eventControllerPointer)!!.toKString()
		set(value) = gtk_event_controller_set_name(eventControllerPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-current-event">
	 *     gtk_event_controller_get_current_event</a>
	 */
	val currentEvent: Event?
		get() = gtk_event_controller_get_current_event(eventControllerPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-current-event-device">
	 *     gtk_event_controller_get_current_event_device</a>
	 */
	val currentEventDevice: Device?
		get() = gtk_event_controller_get_current_event_device(eventControllerPointer).wrap()

	//	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-current-event-state"></a>
	// val currentEventState: ModifierType TODO Find documentation

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#gtk-event-controller-get-current-event-time">
	 *     gtk_event_controller_get_current_event_time</a>
	 */
	val currentEventTime: UInt
		get() = gtk_event_controller_get_current_event_time(eventControllerPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#GtkPropagationPhase">
	 *     GtkPropagationPhase</a>
	 */
	enum class PropagationPhase(val gtk: GtkPropagationPhase) {
		/**
		 * Events are not delivered.
		 */
		NONE(GTK_PHASE_NONE),

		/**
		 * Events are delivered in the capture phase.
		 *
		 * The capture phase happens before the bubble phase, runs from the toplevel down to the event widget.
		 *
		 * This option should only be used on containers that might possibly handle events before their children do.
		 */
		CAPTURE(GTK_PHASE_CAPTURE),

		/**
		 * Events are delivered in the bubble phase.
		 *
		 * The bubble phase happens after the capture phase, and before the default handlers are run.
		 *
		 * This phase runs from the event widget, up to the toplevel.
		 */
		BUBBLE(GTK_PHASE_BUBBLE),

		/**
		 * Events are delivered in the default widget event handlers,
		 * note that widget implementations must chain up on button,
		 * motion, touch and grab broken handlers for controllers in this phase to be run.
		 */
		TARGET(GTK_PHASE_TARGET);

		companion object {
			fun valueOf(gtk: GtkPropagationPhase) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkEventController.html#GtkPropagationLimit">
	 *     GtkPropagationLimit</a>
	 */
	enum class PropagationLimit(val gtk: GtkPropagationLimit) {
		/**
		 * Events are handled regardless of what their target is.
		 */
		NONE(GTK_LIMIT_NONE),

		/**
		 * Events are only handled if their target is in the same [nativex.gtk.Native] as the event controllers widget.
		 * Note that some event types have two targets (origin and destination).
		 */
		SAME_NATIVE(GTK_LIMIT_SAME_NATIVE);

		companion object {
			fun valueOf(gtk: GtkPropagationLimit) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {

		inline fun CPointer<GtkEventController>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkEventController>.wrap() =
			EventController(this)
	}

}