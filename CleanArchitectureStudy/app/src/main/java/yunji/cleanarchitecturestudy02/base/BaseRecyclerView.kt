package yunji.cleanarchitecturestudy02.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import yunji.cleanarchitecturestudy02.listener.OnItemClickListener

/*
 * Created by yunji on 09/03/2020
 */
abstract class BaseRecyclerView<B : ViewDataBinding, T : Any>(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<BaseViewHolder<B, T>>() {
    protected val items = mutableListOf<T>()

    var onItemClickListener = object : OnItemClickListener<T> {
        override fun onClick(item: T) {
            // do nothing
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B, T>(layoutResId, parent) {}

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items[position]

    fun updateItems(newItems: List<T>) {
        val diffUtil = BaseDiffUtil(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}