package com.example.csgoskins.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csgoskins.databinding.ListItemBinding
import com.example.csgoskins.domain.models.CharacterModel
import com.example.csgoskins.ui.views.DetailActivity

class CharactersAdapter(val context: Context) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, differCallBack)

    fun submitList(list: List<CharacterModel>) =
        differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.binding.apply {
            iv.load(item.image)
        }.mainCv.setOnClickListener(View.OnClickListener { view ->
            Log.i("clicked*************", item.id.toString())

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item_data", item)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            context.startActivity(intent)

        })

    }

    fun filterList(filteredlist: List<CharacterModel?>) {
        differ.submitList(filteredlist)
    }

}