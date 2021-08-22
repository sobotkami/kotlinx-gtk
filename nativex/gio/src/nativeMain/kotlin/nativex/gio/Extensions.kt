import nativex.glib.KList
import nativex.glib.plus
import nativex.glib.set
import nativex.gobject.KGObject

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