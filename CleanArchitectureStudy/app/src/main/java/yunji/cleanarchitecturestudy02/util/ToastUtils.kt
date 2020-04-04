package yunji.cleanarchitecturestudy02.util

import android.widget.Toast
import yunji.cleanarchitecturestudy02.GlobalApplication

/*
 * Created by yunji on 09/03/2020
 */
fun showToast(text: String) {
    Toast.makeText(GlobalApplication.getContext(), text, Toast.LENGTH_SHORT).show()
}

fun showToast(resId: Int) {
    val context = GlobalApplication.getContext()
    Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
}