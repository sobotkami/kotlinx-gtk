package nativex.gio

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.PointerHolder
import nativex.async.callbackSignalFlow
import nativex.gio.DBusConnection.Companion.wrap
import nativex.gio.File.Companion.toCArray
import nativex.glib.OptionArg
import nativex.glib.OptionFlags
import nativex.gtk.Signals
import nativex.gtk.bool
import nativex.gtk.common.ext.unwrap
import nativex.gtk.connectSignal
import nativex.gtk.staticNoArgGCallback

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val gApplicationPointer: CPointer<GApplication>
) : KObject(gApplicationPointer.reinterpret()), ActionMap {

	constructor(applicationID: String?, flags: Flags) : this(
		g_application_new(
			applicationID,
			flags.gtk
		)!!.reinterpret()
	)

	var applicationID: String?
		get() = g_application_get_application_id(gApplicationPointer)?.toKString()
		set(value) = g_application_set_application_id(
			gApplicationPointer,
			value
		)

	@ExperimentalUnsignedTypes
	var inactivityTimeout: UInt
		get() = g_application_get_inactivity_timeout(gApplicationPointer)
		set(value) = g_application_set_inactivity_timeout(
			gApplicationPointer,
			value
		)

	@ExperimentalUnsignedTypes
	var flags: Flags
		get() = Flags.valueOf(g_application_get_flags(gApplicationPointer))!!
		set(value) = g_application_set_flags(gApplicationPointer, value.gtk)

	var resourceBasePath: String?
		get() = g_application_get_resource_base_path(gApplicationPointer)?.toKString()
		set(value) = g_application_set_resource_base_path(
			gApplicationPointer,
			value
		)

	val dbusConnection: DBusConnection?
		get() = g_application_get_dbus_connection(gApplicationPointer).wrap()


	val dbusObjectPath: String?
		get() = g_application_get_dbus_object_path(gApplicationPointer)?.toKString()

	val isRegistered: Boolean
		get() = g_application_get_is_registered(gApplicationPointer).bool

	val isRemote: Boolean
		get() = g_application_get_is_remote(gApplicationPointer).bool

	fun register(cancellable: KCancellable): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = g_application_register(
			gApplicationPointer,
			cancellable.cancellablePointer,
			err
		)
		err.unwrap()
		r.bool
	}

	fun hold() {
		g_application_hold(gApplicationPointer)
	}

	fun release() {
		g_application_release(gApplicationPointer)
	}

	fun quit() {
		g_application_quit(gApplicationPointer)
	}

	fun activate() {
		g_application_activate(gApplicationPointer)
	}

	fun open(files: Array<File>, hint: String = "") {
		g_application_open(
			gApplicationPointer,
			files.toCArray(),
			files.size,
			hint
		)
	}

	fun sendNotification(notification: Notification, id: String? = null) {
		g_application_send_notification(
			gApplicationPointer,
			id,
			notification.notificationPointer
		)
	}

	fun withdrawNotification(id: String) {
		g_application_withdraw_notification(gApplicationPointer, id)
	}


	fun run(argc: Int = 0, argv: Array<String> = arrayOf()): Int = memScoped {
		g_application_run(
			gApplicationPointer,
			argc,
			memScoped { argv.toCStringArray(this) })
	}

	fun addMainOption(
		longName: String,
		shortName: Char,
		description: String,
		flags: OptionFlags = OptionFlags.NONE,
		arg: OptionArg = OptionArg.NONE,
		argDescription: String? = null
	) {
		g_application_add_main_option(
			gApplicationPointer,
			longName,
			shortName.toByte(),
			flags.gtk,
			arg.gtk,
			description,
			argDescription
		)
	}

	/**
	 * Direct connection to [activateSignal] source
	 *
	 * This should not be used by developers
	 */
	@ExperimentalUnsignedTypes
	fun onActivate(onActive: () -> Unit) {
		// Has to be a direct event, to prevent application from shutting down
		gApplicationPointer.connectSignal(
			Signals.ACTIVATE,
			handler = staticNoArgGCallback,
			callbackWrapper = StableRef.create {
				onActive()
			}.asCPointer()
		)
	}


	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val activateSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.ACTIVATE)
	}

	val commandLineSignal: Flow<GApplicationCommandLine>
		get() = TODO()

	val handleLocalOptions: Flow<GVariantDict>
		get() = TODO()

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val nameLostSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.NAME_LOST)
	}

	data class OpenEvent(
		val files: Sequence<File>,
		val hint: String,
	)

	val openSignal: Flow<OpenEvent>
		get() = TODO()


	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val shutdownSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.SHUTDOWN)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val startupSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.STARTUP)
	}

	enum class Flags constructor(
		val key: Int,
		internal val gtk: GApplicationFlags
	) {
		NONE(0, G_APPLICATION_FLAGS_NONE),
		IS_SERVICE(1, G_APPLICATION_IS_SERVICE),
		IS_LAUNCHER(2, G_APPLICATION_IS_LAUNCHER),
		HANDLES_OPEN(3, G_APPLICATION_HANDLES_OPEN),
		HANDLES_COMMAND_LINE(4, G_APPLICATION_HANDLES_COMMAND_LINE),
		SEND_ENVIRONMENT(5, G_APPLICATION_SEND_ENVIRONMENT),
		NON_UNIQUE(6, G_APPLICATION_NON_UNIQUE),
		CAN_OVERRIDE_APP_ID(7, G_APPLICATION_CAN_OVERRIDE_APP_ID),
		ALLOW_REPLACEMENT(8, G_APPLICATION_ALLOW_REPLACEMENT),
		REPLACE(9, G_APPLICATION_REPLACE);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GApplicationFlags) =
				values().find { it.gtk == gtk }
		}
	}

	override val actionMapPointer: PointerHolder<GActionMap>
		get() = PointerHolder(gApplicationPointer.reinterpret())

	companion object {
		fun isIdValid(applicationID: String): Boolean =
			g_application_id_is_valid(applicationID).bool
	}
}