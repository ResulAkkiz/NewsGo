package com.project.newsgo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.newsgo.data.entity.Article
import com.project.newsgo.databinding.SingleNewsItemBinding
import com.project.newsgo.ui.fragments.HomeFragmentDirections

class NewsRecyclerViewAdapter(var newsList: List<Article>, var mContext: Context,val onItemClick:(article:Article)->Unit ) :
    RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(var view: SingleNewsItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Article) {

            with(view) {
                root.setOnClickListener {
                    onItemClick(item)

                }
                Glide.with(mContext).load(item.urlToImage).into(newsImageView);
                newsTitleTextView.text =item.title
                newsContentTextView.text=item.description

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = SingleNewsItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentFavorite = newsList[position]
        holder.bind(currentFavorite)
    }




}