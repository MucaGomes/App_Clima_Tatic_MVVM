package com.example.climahoje.adapter

import Root
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.climahoje.databinding.CardLayoutBinding
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var listWeather = emptyList<Root>()

    class HomeViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = listWeather[position]

        holder.binding.address.text = data.city.name + ", " + data.city.country
        holder.binding.temp.text =  data.list[0].main.temp.toString() + "ºC"
        holder.binding.tempMax.text = "temp max: " + data.list.get(0).main.tempMax.toString() + "ºC"
        holder.binding.tempMin.text = "temp min: " + data.list.get(0).main.tempMin.toString() + "ºC"
        holder.binding.humidity.text = data.list.get(0).main.humidity.toString()
        holder.binding.status.text = data.list.get(0).weather.get(0).description

        holder.binding.sunrise.text =
            SimpleDateFormat("hh:mm a",Locale.getDefault())
                .format(
                    Date(data.city.sunrise * 1000))

        holder.binding.sunset.text =
            SimpleDateFormat("hh:mm a",Locale.getDefault())
                .format(
                    Date(data.city.sunset * 1000))

        holder.binding.updatedAt.text =
            "Atualizado em: " +
                    SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault())
                        .format(
                            Date(
                            data.list.get(0).dt * 1000))

    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    fun setData(list: Root) {
        listWeather = listOf(list)
        notifyDataSetChanged()
    }
}