package com.steadyoung.app.commonlibs.widget.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * File description.
 *
 * @author wayne
 * @date 2018/5/15
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;
    int orientation = LinearLayoutManager.VERTICAL;

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (orientation){
            //列表竖向时，只有第一项设置上边距
            case LinearLayoutManager.VERTICAL:
                outRect.left = mSpace;
                outRect.right = mSpace;
                outRect.bottom = mSpace;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = mSpace;
                }
                break;
            //列表横向时，只有第一项设置左边距
            case LinearLayoutManager.HORIZONTAL:
                outRect.right = mSpace;
                outRect.top = mSpace;
                outRect.bottom = mSpace;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = mSpace;
                }
                break;
            default:
                break;
        }


    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    public SpaceItemDecoration(int mSpace, int orientation) {
        this.mSpace = mSpace;
        this.orientation = orientation;
    }
}
