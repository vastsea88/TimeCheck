package com.hus.timecheck

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hus.timecheck.base.BaseActivity
import com.hus.timecheck.databinding.ActivityCheckTimeBinding
import com.hus.timecheck.utils.setMaxIntLimit

class CheckTimeActivity :
    BaseActivity<ActivityCheckTimeBinding>(ActivityCheckTimeBinding::inflate) {
    private val viewModel: CheckTimeViewModel by viewModels()

    override fun setUpObserver() {
        super.setUpObserver()

        binding.apply {
            viewModel = this@CheckTimeActivity.viewModel
            lifecycleOwner = this@CheckTimeActivity
        }

        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun setUpView() {
        super.setUpView()
        binding.apply {
            startTime.setMaxIntLimit(CheckTimeViewModel.TIME_MAX_INT_LIMIT)
            endTime.setMaxIntLimit(CheckTimeViewModel.TIME_MAX_INT_LIMIT)
            checkTime.setMaxIntLimit(CheckTimeViewModel.TIME_MAX_INT_LIMIT)
        }
    }

    override fun addEventListener() {
        super.addEventListener()
        binding.checkAndSave.setOnClickListener {
            viewModel.checkTimeAndSaveResult()
        }
    }

}