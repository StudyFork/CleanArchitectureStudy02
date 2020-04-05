package com.example.movieapplication.presenter.adapter.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.presenter.adapter.MovieAdapter

class MovieItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val lp = parent.layoutManager as GridLayoutManager
        val spanIndex = lp.spanSizeLookup.getSpanIndex(position, MovieAdapter.spanCount)
        val margin = view.resources.getDimension(R.dimen.item_movie_horizontal_margin)

        setOffset(outRect, spanIndex, margin.toInt())
    }

    private fun setOffset(outRect: Rect, spanIndex: Int, margin: Int) {
        outRect.bottom = margin

        if (spanIndex == 0) {
            outRect.right = margin / 2
            outRect.left = margin
        } else if (spanIndex == 1) {
            outRect.left = margin / 2
            outRect.right = margin
        }
    }
}