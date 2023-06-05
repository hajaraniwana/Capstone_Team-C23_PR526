package com.zenith.ecoscan.screen.history

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenith.ecoscan.databinding.ItemHistoryBinding

class HistoryAdapter (private val onClickItem: (id: String) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}