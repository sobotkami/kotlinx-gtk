package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.async.staticDestroyStableRefFunction
import nativex.gio.KObject
import nativex.gtk.PageSetup.Companion.wrap
import nativex.gtk.PrintBackend.Companion.wrap
import nativex.gtk.widgets.container.bin.windows.dialog.PrintUnixDialog

/**
 * kotlinx-gtk
 * 08 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html">GtkPrinter</a>
 */
class Printer internal constructor(
	internal val printerPointer: CPointer<GtkPrinter>
) : Comparable<Printer>, KObject(printerPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-new">gtk_printer_new</a>
	 */
	constructor(name: String, backend: PrintBackend, isVirutal: Boolean = false) : this(
		gtk_printer_new(name, backend.printBackendPointer, isVirutal.gtk)!!
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-backend">gtk_printer_get_backend</a>
	 */
	val backend: PrintBackend
		get() = gtk_printer_get_backend(printerPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-name">gtk_printer_get_name</a>
	 */
	val name: String
		get() = gtk_printer_get_name(printerPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-state-message">gtk_printer_get_state_message</a>
	 */
	val stateMessage: String
		get() = gtk_printer_get_state_message(printerPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-description">gtk_printer_get_description</a>
	 */
	val description: String
		get() = gtk_printer_get_description(printerPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-location">gtk_printer_get_location</a>
	 */
	val location: String
		get() = gtk_printer_get_location(printerPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-icon-name">gtk_printer_get_icon_name</a>
	 */
	val iconName: String
		get() = gtk_printer_get_icon_name(printerPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-job-count">gtk_printer_get_job_count</a>
	 */
	val jobCount: Int
		get() = gtk_printer_get_job_count(printerPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-is-active">gtk_printer_is_active</a>
	 */
	val isActive: Boolean
		get() = gtk_printer_is_active(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-is-paused">gtk_printer_is_paused</a>
	 */
	val isPaused: Boolean
		get() = gtk_printer_is_paused(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-is-accepting-jobs">gtk_printer_is_accepting_jobs</a>
	 */
	val isAcceptingJobs: Boolean
		get() = gtk_printer_is_accepting_jobs(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-is-virtual">gtk_printer_is_virtual</a>
	 */
	val isVirtual: Boolean
		get() = gtk_printer_is_virtual(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-is-default">gtk_printer_is_default</a>
	 */
	val isDefault: Boolean
		get() = gtk_printer_is_default(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-accepts-ps">gtk_printer_accepts_ps</a>
	 */
	val acceptsPS: Boolean
		get() = gtk_printer_accepts_ps(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-accepts-pdf">gtk_printer_accepts_pdf</a>
	 */
	val acceptsPDF: Boolean
		get() = gtk_printer_accepts_pdf(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-list-papers">gtk_printer_list_papers</a>
	 */
	val papers: Sequence<PageSetup>
		get() = gtk_printer_list_papers(printerPointer).asKSequence<GtkPageSetup, PageSetup> { it.wrap() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-compare">gtk_printer_compare</a>
	 */
	override fun compareTo(other: Printer): Int = gtk_printer_compare(printerPointer, other.printerPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-has-details"></a>
	 */
	val hasDetails: Boolean
		get() = gtk_printer_has_details(printerPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-request-details">gtk_printer_request_details</a>
	 */
	fun requestDetails() {
		gtk_printer_request_details(printerPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-capabilities">gtk_printer_get_capabilities</a>
	 */
	val capabilities: PrintUnixDialog.PrintCapabilities
		get() = PrintUnixDialog.PrintCapabilities.valueOf(gtk_printer_get_capabilities(printerPointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-default-page-size">gtk_printer_get_default_page_size</a>
	 */
	val defaultPageSize: PageSetup
		get() = gtk_printer_get_default_page_size(printerPointer)!!.wrap()

	data class HardMargins(
		val top: Double,
		val bottom: Double,
		val left: Double,
		val right: Double
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-printer-get-hard-margins">gtk_printer_get_hard_margins</a>
	 */
	val hardMargins: HardMargins
		get() = memScoped {
			val t = cValue<DoubleVar>()
			val b = cValue<DoubleVar>()
			val l = cValue<DoubleVar>()
			val r = cValue<DoubleVar>()

			gtk_printer_get_hard_margins(printerPointer, t, b, l, r)
			HardMargins(
				top = t.ptr.pointed.value,
				bottom = b.ptr.pointed.value,
				left = l.ptr.pointed.value,
				right = r.ptr.pointed.value
			)
		}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#gtk-enumerate-printers">gtk_enumerate_printers</a>
	 */
	fun enumeratePrinters(wait: Boolean = false, enumerate: PrinterFunction) {
		gtk_enumerate_printers(
			staticPrinterFunction,
			StableRef.create(enumerate).asCPointer(),
			staticDestroyStableRefFunction,
			wait.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#GtkPrinter-details-acquired">
	 *     details-acquired</a>
	 */
	@ExperimentalCoroutinesApi
	val detailsAcquiredSignal: Flow<Boolean> by signalFlow(Signals.DETAILS_ACQUIRED, staticDetailsAcquiredCallback)

	companion object {
		internal val staticPrinterFunction: GtkPrinterFunc = staticCFunction { p, d ->
			d?.asStableRef<PrinterFunction>()?.get()?.invoke(p!!.wrap()).gtk
		}

		internal val staticDetailsAcquiredCallback: GCallback =
			staticCFunction { _: gpointer, success: gboolean, data: gpointer? ->
				data?.asStableRef<(Boolean) -> Unit>()?.get()?.invoke(success.bool)
				Unit
			}.reinterpret()

		internal inline fun CPointer<GtkPrinter>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkPrinter>.wrap() =
			Printer(this)
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPrinter.html#GtkPrinterFunc">GtkPrinterFunc</a>
 * @return true to stop enumeration, false to continue
 */
typealias PrinterFunction = (Printer) -> Boolean