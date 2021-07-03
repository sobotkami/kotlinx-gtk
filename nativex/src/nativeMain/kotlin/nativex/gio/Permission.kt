package nativex.gio

import gtk.GPermission
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Permission(
	 val permissionPointer: CPointer<GPermission>
)