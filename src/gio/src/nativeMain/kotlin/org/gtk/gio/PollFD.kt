package org.gtk.gio

import glib.GPollFD
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class PollFD(val pollFDPointer: CPointer<GPollFD>) {

	constructor() : this(memScoped { alloc<GPollFD>().ptr })

	constructor(nativePlacement: NativePlacement) : this(with(nativePlacement) { alloc<GPollFD>().ptr })
}