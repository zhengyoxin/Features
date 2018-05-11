package com.duowan.featuresActivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper
import com.duowan.featuresActivity.R
import com.duowan.featuresActivity.bean.TitleInfo
import com.yoxin.multivlayout.MultiAdapter
import kotlinx.android.synthetic.main.item_setting_title.view.*

/**
 * Created by zhengyongxin on 2018/5/10.
 */
class TitleAdapter : MultiAdapter<TitleInfo, RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setting_title_name.text = getData().titleName
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_setting_title, null)
        return TitleVH(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun setItemViewType(position: Int): Int {
        return getData().type
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return ColumnLayoutHelper()
    }

    class TitleVH(itemView: View): RecyclerView.ViewHolder(itemView)
}