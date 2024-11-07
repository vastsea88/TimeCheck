package com.hus.timecheck.act

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hus.timecheck.adapter.CheckTimeResultRecyclerViewAdapter
import com.hus.timecheck.base.BaseActivity
import com.hus.timecheck.databinding.ActivityCheckTimeBinding
import com.hus.timecheck.utils.GlobalFunction
import com.hus.timecheck.utils.setMaxIntLimit
import com.hus.timecheck.viewmodels.CheckTimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckTimeActivity :
    BaseActivity<ActivityCheckTimeBinding>(ActivityCheckTimeBinding::inflate) {
    private val viewModel: CheckTimeViewModel by viewModels()
    private lateinit var adapter: CheckTimeResultRecyclerViewAdapter
    override fun setUpObserver() {
        super.setUpObserver()
        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun setUpView() {
        super.setUpView()
        binding.apply {
            viewModel = this@CheckTimeActivity.viewModel
            lifecycleOwner = this@CheckTimeActivity
            startTime.setMaxIntLimit(CheckTimeViewModel.Companion.TIME_MAX_INT_LIMIT)
            endTime.setMaxIntLimit(CheckTimeViewModel.Companion.TIME_MAX_INT_LIMIT)
            checkTime.setMaxIntLimit(CheckTimeViewModel.Companion.TIME_MAX_INT_LIMIT)
        }
        initRecyclerView()
    }

    override fun addEventListener() {
        super.addEventListener()
        binding.checkAndSave.setOnClickListener {
            viewModel.checkTimeAndSaveResult()
        }
    }

    private fun initRecyclerView() {
        adapter = CheckTimeResultRecyclerViewAdapter()
        binding.checkTimeResultsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.checkTimeResultsRecyclerView.adapter = adapter
        viewModel.checkResults.observe(this) {
            adapter.setList(it.asReversed())
            adapter.notifyDataSetChanged()
            GlobalFunction.hideSoftKeyboard(this)
        }
    }
}