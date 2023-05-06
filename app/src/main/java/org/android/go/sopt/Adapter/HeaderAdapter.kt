package org.android.go.sopt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.HeaderData
import org.android.go.sopt.data.ItemData
import org.android.go.sopt.databinding.LayoutHeaderBinding


class HeaderAdapter(context: Context): RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var Header: String = "전세계의 축구선수들"

    class HeaderViewHolder(private val binding: LayoutHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(string: String) {
            binding.tvHead.text = string
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = LayoutHeaderBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun getItemCount() = 1

    fun setHeaderText(string: String){
        this.Header = string
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.onBind(Header)
    }
}