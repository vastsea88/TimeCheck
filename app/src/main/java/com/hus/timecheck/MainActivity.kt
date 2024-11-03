package com.hus.timecheck

import androidx.activity.viewModels
import com.hus.timecheck.base.BaseActivity
import com.hus.timecheck.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding> (ActivityMainBinding::inflate){
    private val viewModel: MainViewModel by viewModels()

}