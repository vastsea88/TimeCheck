package com.hus.timecheck.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding>(
    private val setupViewBinding: (LayoutInflater) -> VB
) : ComponentActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setupViewBinding(layoutInflater)
        setContentView(binding.root)

        setUpObserver()
        setUpView()
        addEventListener()
        loadData()
    }

    open fun setUpObserver() {}
    open fun loadData() {}
    open fun setUpView() {}
    open fun addEventListener() {}
}