package com.duowan.featuresActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.duowan.featuresActivity.adapter.SelectAdapter
import com.duowan.featuresActivity.adapter.TitleAdapter
import com.duowan.featuresActivity.bean.SelectInfo
import com.duowan.featuresActivity.bean.TitleInfo
import com.duowan.mobile.main.feature.Features
import com.yoxin.multivlayout.MultiDelegateAdapter
import kotlinx.android.synthetic.main.activity_setting_feature.*

class SettingFeatureActivity : AppCompatActivity() {

    private lateinit var delegateAdapter: MultiDelegateAdapter
    private val data: MutableList<Any> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_feature)

        //todo 做SettingActivityStorage的校验，判断Features的Storage有没有使用SettingActivityStorage的做修饰类
        initData()
        initView()
    }

    private fun initData() {
        val featuresWrappers = Features.getFeaturesWrapper()
        var group = ""
        featuresWrappers.forEach {
            if (group != it.group()) {
                data.add(TitleInfo(it.group(), 2))
                group = it.group()
            }
            data.add(SelectInfo(it, 1))
        }
    }

    private fun initView() {
        setting_feature_recycler.apply {
            val virtualLayoutManager = VirtualLayoutManager(context)
            delegateAdapter = MultiDelegateAdapter(context, virtualLayoutManager).apply {
                register(SelectInfo::class.java, SelectAdapter::class.java)
                register(TitleInfo::class.java, TitleAdapter::class.java)
            }
            this.layoutManager = virtualLayoutManager
            this.adapter = delegateAdapter
        }
        delegateAdapter.setData(data)
        setting_feature_back.setOnClickListener {
            finish()
        }
    }

    companion object {
        val TAG = "SettingFeatureActivity"
    }
}
