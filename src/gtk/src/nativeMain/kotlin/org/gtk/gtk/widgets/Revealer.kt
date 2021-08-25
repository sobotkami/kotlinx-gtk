package org.gtk.gtk.widgets

import gtk.*
import gtk.GtkRevealerTransitionType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.glib.bool
import org.gtk.glib.gtk

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html">GtkRevealer</a>
 */
class Revealer(
	 val revealerPointer: CPointer<GtkRevealer>
) : Widget(revealerPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-new">GtkRevealer Constructor</a>
	 */
	constructor() : this(gtk_revealer_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-get-reveal-child">gtk_revealer_get_reveal_child</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-set-reveal-child">gtk_revealer_set_reveal_child</a>
	 */
	var revealChild: Boolean
		get() = gtk_revealer_get_reveal_child(revealerPointer).bool
		set(value) = gtk_revealer_set_reveal_child(
			revealerPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-get-child-revealed">gtk_revealer_get_child_revealed</a>
	 */
	val isChildRevealed: Boolean
		get() = gtk_revealer_get_child_revealed(revealerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-get-transition-duration">gtk_revealer_get_transition_duration</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-set-transition-duration">gtk_revealer_set_transition_duration</a>
	 */
	var transitionDuration: UInt
		get() = gtk_revealer_get_transition_duration(revealerPointer)
		set(value) = gtk_revealer_set_transition_duration(
			revealerPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-get-transition-type">gtk_revealer_get_transition_type</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#gtk-revealer-set-transition-type">gtk_revealer_set_transition_type</a>
	 */
	var transitionType: TransitionType
		get() = TransitionType.valueOf(
			gtk_revealer_get_transition_type(
				revealerPointer
			)
		)!!
		set(value) = gtk_revealer_set_transition_type(
			revealerPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRevealer.html#GtkRevealerTransitionType">GtkRevealerTransitionType</a>
	 */
	enum class TransitionType(
		val key: Int,
		 val gtk: GtkRevealerTransitionType
	) {
		NONE(0, GTK_REVEALER_TRANSITION_TYPE_NONE),
		CROSSFADE(1, GTK_REVEALER_TRANSITION_TYPE_CROSSFADE),
		SLIDE_RIGHT(2, GTK_REVEALER_TRANSITION_TYPE_SLIDE_RIGHT),
		SLIDE_LEFT(3, GTK_REVEALER_TRANSITION_TYPE_SLIDE_LEFT),
		SLIDE_UP(4, GTK_REVEALER_TRANSITION_TYPE_SLIDE_UP),
		SLIDE_DOWN(5, GTK_REVEALER_TRANSITION_TYPE_SLIDE_DOWN);


		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			 fun valueOf(gtk: GtkRevealerTransitionType) =
				values().find { it.gtk == gtk }
		}
	}
}