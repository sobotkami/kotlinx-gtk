package nativex.gtk.widgets.container.bin.button

import gtk.GtkLockButton
import gtk.gtk_lock_button_get_permission
import gtk.gtk_lock_button_new
import gtk.gtk_lock_button_set_permission
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.g.Permission

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class LockButton internal constructor(
	internal val lockButtonPointer: CPointer<GtkLockButton>
) : Button(lockButtonPointer.reinterpret()) {

	constructor(permission: Permission) : this(gtk_lock_button_new(permission.permissionPointer)!!.reinterpret())

	var permission: Permission?
		get() = gtk_lock_button_get_permission(lockButtonPointer)?.let {
			Permission(
				it
			)
		}
		set(value) = gtk_lock_button_set_permission(
			lockButtonPointer,
			value?.permissionPointer
		)

}