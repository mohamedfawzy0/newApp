package com.app.torch.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.torch.R
import com.app.torch.base.BaseFragment
import com.app.torch.databinding.FragmentHomeBinding
import com.app.torch.utils.Constants
import com.app.torch.utils.callbacks.OnUpdateDrawerNavigationCallback


class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var typeFor: String
    private var isObserve = false
    private var callback: OnUpdateDrawerNavigationCallback? = null

    override fun onAttach(context: Context) {
        component.inject(this)
        if (context is OnUpdateDrawerNavigationCallback) {
            callback = context
        }
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        callback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        isObserve = false
        init()
        setUpObservers()
        setUpListeners()

        typeFor = Constants.Keys.HOME_FOR_WOMEN
        savedInstanceState?.let {
            if (it.containsKey(Constants.Keys.KEY_OBJECT)) {
                typeFor = it.getString(Constants.Keys.KEY_OBJECT, Constants.Keys.HOME_FOR_WOMEN)
            }
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Constants.Keys.KEY_OBJECT, typeFor)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}
