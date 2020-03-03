package com.fedorov.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.weatherapp.databinding.SearchItemRvBinding
import com.fedorov.weatherapp.ui.fragment.Search
import com.fedorov.weatherapp.ui.model.WeatherFound


class SearchWeatherAdapter : RecyclerView.Adapter<SearchWeatherAdapter.SearchViewHolder>() {

    private val _data: MutableList<WeatherFound> = ArrayList()
    var listener: Search.OnLocationSelectedListener? = null

    var data: List<WeatherFound>
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }
        get() = _data

    override fun getItemCount() = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemRvBinding.inflate(inflater, parent, false)

        return SearchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val cityWeather: WeatherFound = _data[position]
        holder.bind(cityWeather)
    }

    class SearchViewHolder(
        private val binding: SearchItemRvBinding,
        private val listener: Search.OnLocationSelectedListener?
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(weatherFound: WeatherFound) {
            binding.weatherFound = weatherFound
            binding.listener = listener
            binding.executePendingBindings()
        }
    }
}