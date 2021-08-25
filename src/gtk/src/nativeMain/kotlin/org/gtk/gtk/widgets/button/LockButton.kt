package org.gtk.gtk.widgets.button

import gtk.GtkLockButton
import gtk.gtk_lock_button_get_permission
import gtk.gtk_lock_button_new
import gtk.gtk_lock_button_set_permission
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gio.Permission
import org.gtk.gio.Permission.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLockButton.html">GtkLockButton</a>
 */
class LockButton(
	val lockButtonPointer: CPointer<GtkLockButton>
) : Button(lockButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLockButton.html#gtk-lock-button-new">
	 *     gtk_lock_button_new</a>
	 */
	constructor(permission: Permission) : this(gtk_lock_button_new(permission.permissionPointer)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLockButton.html#gtk-lock-button-get-permission">
	 *     gtk_lock_button_get_permission</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLockButton.html#gtk-lock-button-set-permission">
	 *     gtk_lock_button_set_permission</a>
	 */
	var permission: Permission?
		get() = gtk_lock_button_get_permission(lockButtonPointer).wrap()
		set(value) = gtk_lock_button_set_permission(lockButtonPointer, value?.permissionPointer)
}