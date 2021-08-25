import org.gtk.glib.KList
import org.gtk.glib.plus
import org.gtk.glib.set
import org.gtk.gobject.KGObject

fun <T : KGObject> List<T>.toGList(): KList {
	val kList = KList()
	forEachIndexed { i, it ->
		if (i == 0)
			kList[i] = it.pointer
		else
			kList + (it.pointer)
	}
	return kList
}