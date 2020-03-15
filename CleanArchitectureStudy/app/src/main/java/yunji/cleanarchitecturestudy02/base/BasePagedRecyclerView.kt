package yunji.cleanarchitecturestudy02.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import yunji.cleanarchitecturestudy02.listener.OnItemClickListener

/*
 * Created by yunji on 09/03/2020
 */
abstract class BasePagedRecyclerView<B : ViewDataBinding, T : Any>(
    itemDiffUtilCallback: BaseDiffUtilCallback<T> = BaseDiffUtilCallback(),
    @LayoutRes private val layoutResId: Int
) : PagedListAdapter<T, BaseViewHolder<B, T>>(itemDiffUtilCallback) {

    var onItemClickListener = object : OnItemClickListener<T> {
        override fun onClick(item: T) {
            // do nothing
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B, T>(layoutResId, parent) {}

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem, onItemClickListener)
        }
    }
}