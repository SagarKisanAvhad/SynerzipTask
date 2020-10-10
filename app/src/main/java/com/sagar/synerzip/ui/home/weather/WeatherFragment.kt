package com.sagar.synerzip.ui.home.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sagar.synerzip.R
import com.sagar.synerzip.databinding.WeatherFragmentBinding
import com.sagar.synerzip.util.Status
import com.sagar.synerzip.util.toast
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
            viewModel.getWeatherForCity(binding.searchView.query.toString())
        }

        viewModel.getWeatherLiveData().observe(viewLifecycleOwner, Observer {
            binding.apply {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { weather ->
                            tvCityValue.text = weather.name
                            tvTempValue.text = weather.temp.toString()
                            tvMinTempValue.text = weather.tempMin.toString()
                            tvMaxTempValue.text = weather.tempMax.toString()
                            tvHumidityValue.text = weather.humidity.toString()
                        }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        //Handle Error
                        progressBar.visibility = View.GONE
                        it.message?.let { msg -> requireContext().toast(msg) }
                    }
                }


            }
        })
    }

}