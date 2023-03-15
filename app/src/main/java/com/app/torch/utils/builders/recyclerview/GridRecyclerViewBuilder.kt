package com.app.torch.utils.builders.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridRecyclerViewBuilder(
    recyclerView: RecyclerView,
    isDataBindingEnabled: Boolean,
    columnCount: Int,
    space: Int? = null,
    orientation: Int? = null,
    reverseLayout: Boolean? = null,
    canScrollHorizontally: Boolean? = null,
    canScrollVertically: Boolean? = null
) : RecyclerViewBuilder(
    recyclerView,
    isDataBindingEnabled,
    orientation,
    reverseLayout,
    columnCount
) {

    init {
        val layoutManager = object : GridLayoutManager(recyclerView.context, columnCount) {

            override fun canScrollHorizontally(): Boolean {
                canScrollHorizontally?.let {
                    return it
                } ?: run {
                    return super.canScrollHorizontally()
                }
            }

            override fun canScrollVertically(): Boolean {
                canScrollVertically?.let {
                    return it
                } ?: run {
                    return super.canScrollVertically()
                }
            }
        }

        orientation?.let {
            layoutManager.orientation = it
        }

        reverseLayout?.let {
            layoutManager.reverseLayout = it
        }

        recyclerView.layoutManager = layoutManager
        space?.let {
            recyclerView.addItemDecoration(SpacesItemDecoration(it))
        }
    }
}

class SpacesItemDecoration(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
    }
}