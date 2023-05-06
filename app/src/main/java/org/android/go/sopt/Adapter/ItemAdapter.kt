package org.android.go.sopt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.ItemData
import org.android.go.sopt.databinding.LayoutItemBinding

class ItemAdapter(context: Context): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var itemList: List<ItemData> = emptyList()

    class ItemViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ItemData) {
            binding.ivItem.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvName.text = data.name
            binding.tvTeam.text = data.team
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setItemList(itemList: List<ItemData>){
        this.itemList = itemList.toList()
        notifyDataSetChanged()
    }
}