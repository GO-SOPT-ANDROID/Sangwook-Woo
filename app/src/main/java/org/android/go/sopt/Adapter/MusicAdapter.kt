package org.android.go.sopt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.ResponseMusicListDto
import org.android.go.sopt.databinding.LayoutItemBinding
import org.android.go.sopt.util.ItemDiffCallback

class MusicAdapter(context: Context): ListAdapter<ResponseMusicListDto.Data.Music, MusicAdapter.MusicViewHolder>(
    ItemDiffCallback<ResponseMusicListDto.Data.Music>(
        onItemsTheSame = { old, new -> old.title == new.title },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var musicList: List<ResponseMusicListDto.Data.Music> = emptyList()

    class MusicViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMusicListDto.Data.Music) {
            with(binding) {
                Glide.with(binding.root.context).load(data.url).into(binding.ivItem)
                binding.tvName.text = data.title
                binding.tvEmail.text = data.singer
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}