package nativex.gio

import gio.*
import glib.GError
import glib.GVariantDict
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.*
import nativex.gio.ApplicationCommandLine.Companion.wrap
import nativex.gio.DBusConnection.Companion.wrap
import nativex.gio.File.Companion.toCArray
import nativex.glib.*
import nativex.gobject.KObject
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val gApplicationPointer: CPointer<GApplication>
) : KObject(gApplicationPointer.reinterpret()), ActionMap {
	// TODO g_application_bind_busy_property
	// TODO g_application_unbind_busy_property

	var applicationID: String?
		get() = g_application_get_application_id(gApplicationPointer)?.toKString()
		set(value) = g_application_set_application_id(
			gApplicationPointer,
			value
		)

	var inactivityTimeout: UInt
		get() = g_application_get_inactivity_timeout(gApplicationPointer)
		set(value) = g_application_set_inactivity_timeout(
			gApplicationPointer,
			value
		)

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
	val isBusy: Boolean
		get() = g_application_get_is_busy(gApplicationPointer).bool


	fun addOnActivateCallback(action: () -> Unit): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.ACTIVATE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	fun addOnCommandLineCallback(action: ApplicationCommandLineFunction): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.COMMAND_LINE,
				staticCommandLineFunction,
				StableRef.create(action).asCPointer()
			)
		)


	fun addOnHandleLocalOptionsCallback(action: ApplicationHandleLocalOptionsFunction): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.HANDLE_LOCAL_OPTIONS,
				staticHandleLocalOptionsFunction,
				StableRef.create(action).asCPointer()
			)
		)


	fun addOnNameLostCallback(action: () -> Unit): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.NAME_LOST,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	//TODO open signal

	fun addOnShutdownCallback(action: () -> Unit): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.SHUTDOWN,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	fun addOnStartupCallback(action: () -> Unit): SignalManager =
		SignalManager(
			gApplicationPointer,
			gApplicationPointer.connectSignal(
				Signals.STARTUP,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	override val actionMapPointer: CPointer<GActionMap>
		get() = gApplicationPointer.reinterpret()

	constructor(applicationID: String?, flags: Flags) : this(
		g_application_new(
			applicationID,
			flags.gtk
		)!!.reinterpret()
	)

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

	fun addMainOptionEntries(entries: Array<OptionEntry>) {
		TODO("g_application_add_main_option_entries")
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
			shortName.code.toByte(),
			flags.gtk,
			arg.gio,
			description,
			argDescription
		)
	}

	fun addOptionGroup(optionGroup: OptionGroup) {
		g_application_add_option_group(
			gApplicationPointer,
			optionGroup.optionPointer
		)
	}

	fun setOptionContextParameterString(parameterString: String?) {
		g_application_set_option_context_parameter_string(
			gApplicationPointer,
			parameterString
		)
	}

	fun setOptionContextSummary(summary: String?) {
		g_application_set_option_context_summary(
			gApplicationPointer,
			summary
		)
	}

	fun setOptionContextDescription(description: String?) {
		g_application_set_option_context_description(
			gApplicationPointer,
			description
		)
	}

	fun setDefault() {
		g_application_set_default(gApplicationPointer)
	}

	fun markBusy() =
		g_application_mark_busy(gApplicationPointer)

	fun markNotBusy() =
		g_application_unmark_busy(gApplicationPointer)

	/**
	 * Direct connection to [activateSignal] source
	 *
	 * This should not be used by developers
	 */
	fun onActivate(onActive: () -> Unit) {
		// Has to be a direct event, to prevent application from shutting down
		gApplicationPointer.connectSignal(
			Signals.ACTIVATE,
			callbackWrapper = StableRef.create {
				onActive()
			}.asCPointer()
		)
	}

	data class OpenEvent(
		val files: Sequence<File>,
		val hint: String,
	)

	enum class Flags constructor(
		val key: Int,
		val gtk: GApplicationFlags
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

			fun valueOf(gtk: GApplicationFlags) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		private val staticCommandLineFunction: GCallback =
			staticCFunction { _: gpointer, commandLine: CPointer<GApplicationCommandLine>, data: gpointer ->
				data.asStableRef<ApplicationCommandLineFunction>().get().invoke(commandLine.wrap())
			}.reinterpret()

		private val staticHandleLocalOptionsFunction: GCallback =
			staticCFunction { _: gpointer, dict: CPointer<GVariantDict>, data: gpointer ->
				data.asStableRef<ApplicationHandleLocalOptionsFunction>().get().invoke(Variant.Dict.Impl(dict))
			}.reinterpret()

		val default: Application?
			get() = g_application_get_default().wrap()

		fun isIdValid(applicationID: String): Boolean =
			g_application_id_is_valid(applicationID).bool

		inline fun CPointer<GApplication>?.wrap() =
			this?.let { Application(it) }

		inline fun CPointer<GApplication>.wrap() =
			Application(this)
	}
}

typealias ApplicationCommandLineFunction = (ApplicationCommandLine) -> Int

typealias ApplicationHandleLocalOptionsFunction = (Variant.Dict) -> Int