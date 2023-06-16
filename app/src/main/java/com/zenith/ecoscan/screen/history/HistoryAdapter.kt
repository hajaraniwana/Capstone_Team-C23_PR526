package com.zenith.ecoscan.screen.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenith.ecoscan.data.api.response.ItemInfo
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.databinding.ItemHistoryBinding

class HistoryAdapter (private val onClickItem: (data: ItemInfo) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
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
            tvDate.text = item.date

            Glide.with(holder.itemView.context)
                .load(item.image)
                .centerCrop()
                .into(ivPreview)

            root.setOnClickListener {
                val data = ItemInfo(
                    item.lokasi,
                    item.averageEnergy?.toDouble(),
                    item.dampakDisposal,
                    item.linkSumber,
                    item.dampakProduksi,
                    item.name,
                    item.dampakKonsumsi,
                    item.image,
                    item.recommendations
                )
                onClickItem(data)
            }
        }
    }

    fun setData(data: List<DataEntity>) {
        val diffUtil = HistoryCallback(listHistory, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listHistory = data
        diffResult.dispatchUpdatesTo(this)
    }

}

class HistoryCallback(private val oldList: List<DataEntity>, private val newList: List<DataEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            else -> true
        }
    }

}