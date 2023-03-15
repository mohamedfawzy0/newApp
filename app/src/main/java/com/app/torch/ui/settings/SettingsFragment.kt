package com.app.torch.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.torch.R
import com.app.torch.base.BaseFragment
import com.app.torch.databinding.FragmentSettingsBinding


class SettingsFragment : BaseFragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        init()
        setUpObservers()
        setUpListeners()

        return binding.root
    }

    private fun init() {
    }

    private fun setUpObservers() {
    }

    private fun setUpListeners() {
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}
