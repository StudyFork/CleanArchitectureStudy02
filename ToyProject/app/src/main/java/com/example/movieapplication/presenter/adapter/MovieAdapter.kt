package com.example.movieapplication.presenter.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.base.BaseDiffUtilCallback
import com.example.movieapplication.base.BaseViewHolder
import com.example.movieapplication.databinding.ItemLoadingBinding
import com.example.movieapplication.databinding.ItemMovieBinding
import com.example.movieapplication.presenter.model.MovieItem

class MovieAdapter : RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, MovieItem>>() {

    private val items = mutableListOf<MovieItem>()

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(data: MovieItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, MovieItem> {
        return when (ViewType.getViewType(
            viewType
        )) {
            ViewType.MOVIE -> MovieViewHolder(
                parent
            )
            ViewType.LOADING -> LoadingViewHolder(
                parent
            )
        }.apply {
            if (this is MovieViewHolder) {
                itemView.setOnClickListener {
                    onItemClickListener?.let { listener ->
                        listener.onItemClick(
                            items[adapterPosition]
                        )
                    }
                }
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType.ordinal
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, MovieItem>,
        position: Int
    ) {
        if (holder is MovieViewHolder) holder.bind(items[position])
    }

    override fun onViewRecycled(holder: BaseViewHolder<ViewDataBinding, MovieItem>) {
        holder.recycled()
        super.onViewRecycled(holder)
    }

    fun replaceAll(items: List<MovieItem>, useDiffCallback: Boolean = false) {
        val diffCallback = BaseDiffUtilCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items.run {
            clear()
            addAll(items)
        }

        if (useDiffCallback) {
            diffResult.dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    }

    fun addItems(items: List<MovieItem>) {
        val preItemCount = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(preItemCount, items.count())
    }

    fun addBottomLoading() {
        if (itemCount > 0) {
            items.add(
                MovieItem(viewType = ViewType.LOADING)
            )
            notifyItemInserted(itemCount - 1)
        }
    }

    fun removeBottomLoading() {
        if (itemCount > 0) {
            for (i in (itemCount - 1) downTo 0) {
                val loadingHolder = items[i]
                if (loadingHolder.viewType == ViewType.LOADING) {
                    items.removeAt(i)
                    notifyItemChanged(i)
                    break
                }
            }
        }
    }

    class MovieViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemMovieBinding, MovieItem>(parent, R.layout.item_movie) {

        override fun bind(data: MovieItem) {
            binding.movie = data
        }

        override fun recycled() {}
    }

    class LoadingViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemLoadingBinding, MovieItem>(parent, R.layout.item_loading) {

        override fun bind(data: MovieItem) {

        }
    }

    enum class ViewType(val spanCount: Int) {
        MOVIE(1), LOADING(spanCount);

        companion object {
            fun getViewType(value: Int) = values()[value]
        }
    }

    companion object {
        const val spanCount = 2
    }

}