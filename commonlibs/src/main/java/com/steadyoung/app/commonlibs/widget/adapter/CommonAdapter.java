package com.steadyoung.app.commonlibs.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/7/30 09:57
 * @email steadyoung@foxmail.com
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
        if(this.mDatas == null){
            this.mDatas = new ArrayList<>();
        }
    }

    @Override
    public int getCount()
    {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public T getItem(int position)
    {
        return mDatas != null && mDatas .size() > position ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent)
    {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    /**
     * 刷新数据
     * @param mDatas
     */
    public void setNewDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param mData
     */
    public void addData(T mData) {
        if(this.mDatas == null){
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.add(mData);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     * @param position
     */
    public void removeData(int position) {
        if(this.mDatas != null && this.mDatas.size() > position){
            this.mDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据
     * @return
     */
    public List<T> getDatas() {
        return mDatas;
    }
}

