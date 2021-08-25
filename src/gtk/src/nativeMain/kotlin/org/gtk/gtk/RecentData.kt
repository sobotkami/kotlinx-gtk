package org.gtk.gtk

import gtk.GtkRecentData
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.glib.toList
import org.gtk.glib.toNullTermCStringArray

/**
 * kotlinx-gtk
 *
 * 25 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class RecentData(val pointer: CPointer<GtkRecentData>) {
	var displayName: String?
		get() = pointer.pointed.display_name?.toKString()
		set(value) {
			memScoped { pointer.pointed.display_name = value?.cstr?.ptr }
		}

	var description: String?
		get() = pointer.pointed.description?.toKString()
		set(value) {
			memScoped { pointer.pointed.description = value?.cstr?.ptr }
		}

	var mimeType: String
		get() = pointer.pointed.mime_type!!.toKString()
		set(value) {
			memScoped { pointer.pointed.mime_type = value.cstr.ptr }
		}

	var appName: String
		get() = pointer.pointed.app_name!!.toKString()
		set(value) {
			memScoped { pointer.pointed.app_name = value.cstr.ptr }
		}

	var appExec: String
		get() = pointer.pointed.app_exec!!.toKString()
		set(value) {
			memScoped { pointer.pointed.app_exec = value.cstr.ptr }
		}

	var groups: List<String>
		get() = pointer.pointed.groups!!.toList()
		set(value) {
			pointer.pointed.groups = value.toNullTermCStringArray()
		}

	var isPrivate: Boolean
		get() = pointer.pointed.is_private.bool
		set(value) {
			memScoped { pointer.pointed.is_private = value.gtk }
		}

	constructor() : this(memScoped { alloc<GtkRecentData>().ptr })

	constructor(
		data: KRecentData
	) : this() {
		this.displayName = data.displayName
		this.description = data.description
		this.mimeType = data.mimeType
		this.appName = data.appName
		this.appExec = data.appExec
		this.groups = data.groups
		this.isPrivate = data.isPrivate
	}

	fun toKotlin() = KRecentData(displayName, description, mimeType, appName, appExec, groups, isPrivate)
}

/**
 * Kotlin version of [RecentData]
 */
data class KRecentData(
	val displayName: String?,
	val description: String?,
	val mimeType: String,
	val appName: String,
	val appExec: String,
	val groups: List<String>,
	val isPrivate: Boolean
)