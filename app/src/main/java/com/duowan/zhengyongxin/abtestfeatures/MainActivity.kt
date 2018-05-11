package com.duowan.zhengyongxin.abtestfeatures

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.duowan.featuresActivity.SettingFeatureActivity
import com.duowan.zhengyongxin.abtestfeatures.adapter.BooleanAdapter
import com.duowan.zhengyongxin.abtestfeatures.adapter.IntAdapter
import com.duowan.zhengyongxin.abtestfeatures.adapter.StringAdapter
import com.duowan.zhengyongxin.abtestfeatures.bean.BooleanInfo
import com.duowan.zhengyongxin.abtestfeatures.bean.IntInfo
import com.duowan.zhengyongxin.abtestfeatures.bean.StringInfo
import com.duowan.zhengyongxin.abtestfeatures.feature.BooleanTest
import com.duowan.zhengyongxin.abtestfeatures.feature.IntTest
import com.duowan.zhengyongxin.abtestfeatures.feature.StringTest
import com.yoxin.multivlayout.MultiDelegateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var data: MutableList<Any>
    private lateinit var multiDelegateAdapter: MultiDelegateAdapter

    override fun onClick(v: View?) {
        when (v!!.id) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        data = mutableListOf()
        data.add(BooleanInfo(BooleanTest::class.java))
        data.add(StringInfo(StringTest::class.java))
        data.add(IntInfo(IntTest::class.java))
    }


    private fun initView() {
        main_refresh.setOnRefreshListener {
            initData()
            multiDelegateAdapter.setData(data)
            main_refresh.isRefreshing = false
        }
        main_recycler.apply {
            val virtualLayoutManager = VirtualLayoutManager(context)
            this.layoutManager = virtualLayoutManager
            multiDelegateAdapter = MultiDelegateAdapter(context, virtualLayoutManager).apply {
                this.register(BooleanInfo::class.java, BooleanAdapter::class.java)
                this.register(StringInfo::class.java, StringAdapter::class.java)
                this.register(IntInfo::class.java, IntAdapter::class.java)
            }
            this.adapter = multiDelegateAdapter
        }
        multiDelegateAdapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SettingFeatureActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
