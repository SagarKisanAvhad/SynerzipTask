package com.sagar.synerzip.ui.home.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sagar.synerzip.R
import com.sagar.synerzip.databinding.WeatherFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class WeatherFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val factory: WeatherViewModelFactory by instance()
    private val viewModel: WeatherViewModel by viewModels { factory }
    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
    }

    private fun bindUi() {
        binding.btnSubmit.setOnClickListener {
            Log.d("on submit click", binding.searchView.query.toString())
        }
    }

}