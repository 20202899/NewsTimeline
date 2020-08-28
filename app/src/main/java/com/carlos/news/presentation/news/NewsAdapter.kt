package com.carlos.news.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.core.domain.Article
import com.carlos.news.R
import com.carlos.news.extensions.toFormattedDateString
import com.carlos.news.scaleView


class NewsAdapter(
    val articles: List<Article>,
    val clickItem: (view: View, article: Article) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lastIndexAnimation = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TimeLineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TimeLineViewHolder) {
            holder.itemView.setOnClickListener { clickItem.invoke(it, articles[position]) }
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun getItemId(position: Int): Long {
        return articles[position].hashCode().toLong()
    }

    inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hour: TextView = itemView.findViewById(R.id.hour)
        val author: TextView = itemView.findViewById(R.id.author)
        val content_text: TextView = itemView.findViewById(R.id.content_text)
        val image: ImageView = itemView.findViewById(R.id.image)
        val cardView: CardView = itemView.findViewById(R.id.cardview)
        val time_line: ImageView = itemView.findViewById(R.id.time_line)

        fun bind(index: Int) {
            val article = articles[index]

            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_newspaper)
                .into(image)

            val context = itemView.context

            if (index > lastIndexAnimation) {

                hour.let {
                    it.text = article.publishedAt
                        .toFormattedDateString()
                    it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.from_left))
                }
                author.let {
                    it.text = article.author ?: article.source.name
                    it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_all))
                    it.visibility = View.VISIBLE
                }

                content_text.let {
                    it.text = article.content ?: article.title
                    it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_all))
                    it.visibility = View.VISIBLE
                }

                cardView.let {
                    it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.from_left))
                }

                scaleView(time_line, 0f, 1f)

                lastIndexAnimation = index
            } else {
                hour.text = article.publishedAt
                    .toFormattedDateString()

                author.let {
                    it.text = article.author ?: article.source.name
                    it.visibility = View.VISIBLE
                }

                content_text.let {
                    it.text = article.content ?: article.title
                    it.visibility = View.VISIBLE
                }

                time_line.visibility = View.VISIBLE
            }
        }
    }


}
