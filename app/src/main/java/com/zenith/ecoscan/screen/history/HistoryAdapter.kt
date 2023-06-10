package com.zenith.ecoscan.screen.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.databinding.ItemHistoryBinding

class HistoryAdapter (private val onClickItem: (id: String) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var listHistory = emptyList<DataEntity>()

    class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listHistory[position]
        with(holder.binding) {
            tvName.text = item.name

            Glide.with(holder.itemView.context)
                .load(item.image)
                .centerCrop()
                .into(ivPreview)
        }
    }

}