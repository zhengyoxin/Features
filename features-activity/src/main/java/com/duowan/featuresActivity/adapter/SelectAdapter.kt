package com.duowan.featuresActivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper
import com.duowan.featuresActivity.R
import com.duowan.featuresActivity.bean.SelectInfo
import com.duowan.featuresActivity.util.SettingStorageUtil
import com.yoxin.multivlayout.MultiAdapter
import kotlinx.android.synthetic.main.item_setting_select.view.*

/**
 * Created by zhengyongxin on 2018/5/9.
 */
class SelectAdapter : MultiAdapter<SelectInfo, RecyclerView.ViewHolder>(), OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(context, "[onNothingSelected]", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(context, "[onItemSelected] position = $position, id = $id", Toast.LENGTH_SHORT).show()
        SettingStorageUtil.setSelectWithKey(getData().wrapper.storageKey(), position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SelectVH) {
            holder.itemView.setting_select_nick.text = getData().wrapper.alias()
            holder.spinnerAdapter.clear()
            val spinnerData: MutableList<String> = mutableListOf()
            getData().wrapper.allFeaturesInstance.mapTo(spinnerData) { it.name }
            holder.spinnerAdapter.add("default")
            holder.spinnerAdapter.addAll(spinnerData)
            holder.spinnerAdapter.notifyDataSetChanged()
            holder.itemView.setting_select_spinner.onItemSelectedListener = this
            holder.itemView.setting_select_spinner.setSelection(SettingStorageUtil.getSelectIndex(key = getData().wrapper.storageKey()))
        }
    }

    override fun setItemViewType(position: Int): Int {
        return getData().type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_setting_select, null)
        return SelectVH(view)
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return ColumnLayoutHelper().apply {
            this.setMargin(0, 2, 0, 2)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    class SelectVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item)

        init {
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            itemView.setting_select_spinner.adapter = spinnerAdapter
        }
    }
}