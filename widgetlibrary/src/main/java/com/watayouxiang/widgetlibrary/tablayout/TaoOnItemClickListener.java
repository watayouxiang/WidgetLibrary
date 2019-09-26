package com.watayouxiang.widgetlibrary.tablayout;

public interface TaoOnItemClickListener {
    /**
     * itemView 点击事件
     *
     * @param viewHolder TaoViewHolder
     * @return 是否选中
     */
    boolean onItemClick(TaoViewHolder viewHolder);
}
