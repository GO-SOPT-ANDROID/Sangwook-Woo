package org.android.go.sopt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.ResponseListUsersDto
import org.android.go.sopt.databinding.LayoutItemBinding
import org.android.go.sopt.util.ItemDiffCallback

class ItemAdapter(context: Context): ListAdapter<ResponseListUsersDto.UserData, ItemAdapter.ItemViewHolder>(
    ItemDiffCallback<ResponseListUsersDto.UserData>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<ResponseListUsersDto.UserData> = emptyList()

    class ItemViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseListUsersDto.UserData) {
            with(binding) {
                Glide.with(binding.root.context).load(data.avatar).into(binding.ivItem)
                binding.tvName.text = data.first_name
                binding.tvEmail.text = data.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(userList[position])
    }
}