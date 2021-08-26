package org.gtk.gtk

import gtk.GtkATContext
import gtk.gtk_at_context_create
import gtk.gtk_at_context_get_accessible
import gtk.gtk_at_context_get_accessible_role
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gdk.Display
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * gtk-kt
 *
 * 25 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.ATContext.html">GtkATContext</a>
 */
class ATContext(val atContextPointer: CPointer<GtkATContext>) : KGObject(atContextPointer.reinterpret()) {


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.ATContext.create.html">
	 *     gtk_at_context_create</a>
	 */
	@Throws(ATContextCreateException::class)
	constructor(role: Accessible.Role, accessible: Accessible, display: Display) : this(
		gtk_at_context_create(
			role.gtk,
			accessible.accessiblePointer,
			display.displayPointer
		) ?: throw ATContextCreateException("gtk_at_context_create returned null")
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.ATContext.get_accessible.html">
	 *     gtk_at_context_get_accessible</a>
	 */
	val accessible: Accessible
		get() = ImplAccessible(gtk_at_context_get_accessible(atContextPointer)!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.ATContext.get_accessible_role.html">
	 *     gtk_at_context_get_accessible_role</a>
	 */
	val role: Accessible.Role
		get() = Accessible.Role.valueOf(gtk_at_context_get_accessible_role(atContextPointer))!!

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.ATContext.state-change.html">state-change</a>
	 */
	fun addOnStateChangeCallback(action: () -> Unit) =
		addSignalCallback(Signals.STATE_CHANGE, action)

	/**
	 * Thrown when [gtk_at_context_create] returns null in the constructor of [ATContext]
	 */
	class ATContextCreateException(message: String?, cause: Throwable? = null) : Exception(message, cause)
}