package com.duowan.zhengyongxin.abtestfeatures.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper
import com.duowan.mobile.main.feature.Features
import com.duowan.zhengyongxin.abtestfeatures.R
import com.duowan.zhengyongxin.abtestfeatures.bean.IntInfo
import com.yoxin.multivlayout.MultiAdapter
import kotlinx.android.synthetic.main.item_int.view.*

/**
 * Created by zhengyongxin on 2018/4/27.
 */
class IntAdapter : MultiAdapter<IntInfo, RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.item_int_txt.text = Features.of(getData().clazz).getText()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_int, null)
        return IntVH(view)
    }

    override fun setItemViewType(position: Int): Int {
        return 2
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return ColumnLayoutHelper()
    }

    override fun getItemCount(): Int {
        return 1
    }

    class IntVH(view: View) : RecyclerView.ViewHolder(view)
}