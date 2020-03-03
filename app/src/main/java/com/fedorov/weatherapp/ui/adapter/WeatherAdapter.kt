package com.fedorov.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.weatherapp.databinding.CityItemRvBinding
import com.fedorov.weatherapp.ui.model.CityWeather

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val _data: MutableList<CityWeather> = ArrayList()
    var data: List<CityWeather>
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }
        get() = _data

    override fun getItemCount() = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityItemRvBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cityWeather: CityWeather = _data[position]
        holder.bind(cityWeather)
    }

    class ViewHolder(private val binding: CityItemRvBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(cityWeather: CityWeather) {
            binding.cityWeather = cityWeather
            binding.executePendingBindings()
        }
    }
}