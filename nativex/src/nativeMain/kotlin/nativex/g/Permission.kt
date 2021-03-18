package nativex.g

import gtk.GPermission
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Permission internal constructor(
	internal val permissionPointer: CPointer<GPermission>
)