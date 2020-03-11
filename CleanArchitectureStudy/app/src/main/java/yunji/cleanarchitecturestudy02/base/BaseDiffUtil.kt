package yunji.cleanarchitecturestudy02.base

import androidx.recyclerview.widget.DiffUtil

/*
 * Created by yunji on 10/03/2020
 */
class BaseDiffUtil<T>(private val newList: List<T>, private val oldList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}