package com.axiel7.moelist.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axiel7.moelist.R
import com.axiel7.moelist.model.Theme

class ThemesAdapter(private val themes: MutableList<Theme>,
                    private val rowLayout: Int,
                    private val context: Context) :
    RecyclerView.Adapter<ThemesAdapter.AnimeViewHolder>() {
    private var endListReachedListener: EndListReachedListener? = null

    class AnimeViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v) {
        val themeView: TextView = v.findViewById(R.id.theme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val themeText = themes[position].text

        holder.themeView.text = themeText

        val query = themeText.replace(" ", "+", true)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/results?search_query=$query")
            context.startActivity(intent)
        }
        if (position == themes.size - 2) run {
            endListReachedListener?.onBottomReached(position)
        }
    }

    override fun getItemCount(): Int {
        return themes.size
    }

    fun setEndListReachedListener(endListReachedListener: EndListReachedListener?) {
        this.endListReachedListener = endListReachedListener
    }
}