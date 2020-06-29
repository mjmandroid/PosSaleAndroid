package com.transpos.sale.utils

import android.content.Intent
import android.os.CountDownTimer
import android.widget.TextView
import com.transpos.sale.R
import com.transpos.sale.base.BaseActivity
import com.transpos.sale.ui.food.FoodActivity
import java.lang.ref.WeakReference

class CountTimer(millisInFuture : Long, countDownInterval : Long,activity: BaseActivity,var tv : TextView): CountDownTimer(millisInFuture,countDownInterval) {
    private var mWeakReference : WeakReference<BaseActivity> ?= null
    init {
        mWeakReference = WeakReference(activity)
    }
    override fun onFinish() {
        var context = mWeakReference?.get()
        context?.startActivity(FoodActivity::class.java)
    }
    override fun onTick(millisUntilFinished: Long) {
        var context = mWeakReference?.get()
        val sec = (millisUntilFinished / 1000).toInt()
        tv.text = context?.getString(R.string.txt_actionbar_content,sec.toString())
    }


}