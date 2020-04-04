package com.template.nanamare.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * 생성자
 *
 * @param spanCount
 * @param hSpacing
 * @param vSpacing
 * @param includeEdge
 */
class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val hSpacing: Int,
    private val vSpacing: Int,
    private val includeEdge: Boolean
) : ItemDecoration() {

    /**
     * 생성자
     *
     * @param spanCount
     * @param spacing
     * @param includeEdge
     */
    constructor(spanCount: Int, spacing: Int, includeEdge: Boolean) : this(
        spanCount,
        spacing,
        spacing,
        includeEdge
    ) {
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left =
                hSpacing - column * hSpacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * hSpacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = vSpacing
            }
            outRect.bottom = vSpacing // item bottom
        } else {
            outRect.left = column * hSpacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                hSpacing - (column + 1) * hSpacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = vSpacing // item top
            }
        }
    }

}