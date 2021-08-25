package nativex.gtk

import glib.GDateTime
import glib.GError
import gtk.*
import gtk.GtkRecentManagerError.*
import kotlinx.cinterop.*
import nativex.gio.AppInfo
import nativex.gio.AppInfo.Companion.wrap
import nativex.gio.Icon
import nativex.gio.ImplIcon.Companion.wrap
import nativex.glib.*
import nativex.glib.DateTime.Companion.wrap
import nativex.gobject.KGObject
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback
import nativex.gtk.RecentManager.RecentInfo.Companion.wrap

/**
 * kotlinx-gtk
 * 27 / 03 / 2021
 */
class RecentManager(
	val managerPointer: CPointer<GtkRecentManager>
) : KGObject(managerPointer.reinterpret()) {

	val items: Sequence<RecentInfo>
		get() = gtk_recent_manager_get_items(managerPointer)
			.asKSequence<GtkRecentInfo, RecentInfo> { RecentInfo(it) }


	fun addOnChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.CHANGED, action)

	constructor() : this(gtk_recent_manager_new()!!)

	fun addItem(uri: String): Boolean =
		gtk_recent_manager_add_item(managerPointer, uri).bool

	fun addFull(uri: String, recentData: KRecentData): Boolean =
		gtk_recent_manager_add_full(managerPointer, uri, RecentData(recentData).pointer).bool

	fun removeItem(uri: String): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = gtk_recent_manager_remove_item(managerPointer, uri, err)
		err.unwrap()
		r.bool
	}

	fun lookupItem(uri: String): RecentInfo? = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = gtk_recent_manager_lookup_item(managerPointer, uri, err)
		err.unwrap()
		r.wrap()
	}

	fun hasItem(uri: String): Boolean =
		gtk_recent_manager_has_item(managerPointer, uri).bool

	fun moveItem(uri: String, newUri: String): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = gtk_recent_manager_move_item(managerPointer, uri, newUri, err)
		err.unwrap()
		r.bool
	}

	fun purge() {
		memScoped {
			val err = allocPointerTo<GError>().ptr
			gtk_recent_manager_purge_items(managerPointer, err)
			err.unwrap()
		}
	}

	class RecentInfo(
		val struct: CPointer<GtkRecentInfo>
	) {


		val uri: String
			get() = gtk_recent_info_get_uri(struct)!!.toKString()
		val description: String
			get() = gtk_recent_info_get_description(struct)!!.toKString()
		val mimeType: String
			get() = gtk_recent_info_get_mime_type(struct)!!.toKString()
		val added: DateTime
			get() = gtk_recent_info_get_added(struct)!!.wrap()
		val modified: DateTime
			get() = gtk_recent_info_get_modified(struct)!!.wrap()
		val visited: DateTime
			get() = gtk_recent_info_get_visited(struct)!!.wrap()
		val privateHint: Boolean
			get() = gtk_recent_info_get_private_hint(struct).bool


		val applications: List<String>
			get() = memScoped {
				val cSize = cValue<ULongVar>()
				gtk_recent_info_get_applications(
					struct,
					cSize
				).toStringListFilterNulls(
					cSize.ptr.pointed.value.toInt()
				)
			}
		val lastApplication: String
			get() = gtk_recent_info_last_application(struct)!!.toKString()


		val groups: List<String>
			get() = memScoped {
				val cSize = cValue<ULongVar>()
				gtk_recent_info_get_groups(
					struct,
					cSize
				).toStringListFilterNulls(
					cSize.ptr.pointed.value.toInt()
				)
			}
		val gicon: Icon?
			get() = gtk_recent_info_get_gicon(struct).wrap()
		val shortName: String
			get() = gtk_recent_info_get_short_name(struct)!!.toKString()
		val uriDisplay: String?
			get() = gtk_recent_info_get_uri_display(struct)?.toKString()
		val age: Int
			get() = gtk_recent_info_get_age(struct)
		val isLocal: Boolean
			get() = gtk_recent_info_is_local(struct).bool
		val exists: Boolean
			get() = gtk_recent_info_exists(struct).bool


		fun getApplicationInfo(appName: String): ApplicationInfo? =
			memScoped {
				val appExec = cValue<CPointerVar<ByteVarOf<Byte>>>()
				val count = cValue<UIntVar>()
				val time = cValue<CPointerVar<GDateTime>>()
				val b = gtk_recent_info_get_application_info(
					struct,
					appName,
					appExec,
					count,
					time
				).bool
				if (b)
					ApplicationInfo(
						appExec.ptr.pointed.value!!.toKString(),
						count.ptr.pointed.value,
						time.ptr.pointed.value!!.wrap()
					)
				else null
			}

		fun hasApplication(appName: String): Boolean =
			gtk_recent_info_has_application(struct, appName).bool

		fun createAppInfo(appName: String): AppInfo? = memScoped {
			val err = allocPointerTo<GError>().ptr
			val pointer =
				gtk_recent_info_create_app_info(struct, appName, err)
			err.unwrap()
			pointer.wrap()
		}

		fun hasGroup(groupName: String): Boolean =
			gtk_recent_info_has_group(struct, groupName).bool


		override fun equals(other: Any?): Boolean {
			if (this === other) return true
			if (other !is RecentManager) return false
			other as RecentInfo
			return gtk_recent_info_match(struct, other.struct).bool
		}

		override fun hashCode(): Int {
			return struct.hashCode()
		}

		data class ApplicationInfo(
			val appExec: String,
			val count: UInt,
			val time: DateTime
		)

		companion object {
			inline fun CPointer<GtkRecentInfo>?.wrap() =
				this?.let { RecentInfo(this) }

			inline fun CPointer<GtkRecentInfo>.wrap() =
				RecentInfo(this)
		}
	}

	class RecentData(
		val struct: CPointer<GtkRecentData>
	) {

		var displayName: String?
			get() = struct.pointed.display_name?.toKString()
			set(value) = memScoped {
				struct.pointed.display_name = value?.cstr?.ptr
			}
		var description: String?
			get() = struct.pointed.description?.toKString()
			set(value) = memScoped {
				struct.pointed.description = value?.cstr?.ptr
			}

		var mimeType: String?
			get() = struct.pointed.mime_type?.toKString()
			set(value) = memScoped {
				struct.pointed.mime_type = value?.cstr?.ptr
			}
		var appName: String?
			get() = struct.pointed.app_name?.toKString()
			set(value) = memScoped {
				struct.pointed.app_name = value?.cstr?.ptr
			}

		var appExec: String?
			get() = struct.pointed.app_exec?.toKString()
			set(value) = memScoped {
				struct.pointed.app_exec = value?.cstr?.ptr
			}//val groups:String
		var isPrivate: Boolean
			get() = struct.pointed.is_private.bool
			set(value) = memScoped {
				struct.pointed.is_private = value.gtk
			}

		constructor () : this(memScoped { cValue<GtkRecentData>().ptr })
	}

	enum class Error(val gtk: GtkRecentManagerError) {
		NOT_FOUND(GTK_RECENT_MANAGER_ERROR_NOT_FOUND),
		INVALID_URI(GTK_RECENT_MANAGER_ERROR_INVALID_URI),
		INVALID_ENCODING(GTK_RECENT_MANAGER_ERROR_INVALID_ENCODING),
		NOT_REGISTERED(GTK_RECENT_MANAGER_ERROR_NOT_REGISTERED),
		ERROR_READ(GTK_RECENT_MANAGER_ERROR_READ),
		ERROR_WRITE(GTK_RECENT_MANAGER_ERROR_WRITE),
		ERROR_UNKNOWN(GTK_RECENT_MANAGER_ERROR_UNKNOWN);

		companion object {
			fun valueOf(gtk: GtkRecentManagerError) =
				values().find { it.gtk == gtk }!!
		}
	}

	companion object {
		val default: RecentManager
			get() = RecentManager(gtk_recent_manager_get_default()!!)

	}
}