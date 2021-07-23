package com.example.xcriticaltestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.generated.callback.OnClickListener

class ProjectListAdapter(
    private val projectItemsList: ArrayList<ProjectListItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ProjectListAdapter.ProjectListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list_item,
            parent, false
        )

        return ProjectListHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProjectListHolder, position: Int) {
        val currentItem = projectItemsList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.projectName.text = currentItem.projectName
        holder.projectText.text = currentItem.projectText
    }

    override fun getItemCount() = projectItemsList.size

    fun deleteProjectItem(position: Int) {
        projectItemsList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addProjectItem(position: Int, projectItem: ProjectListItem) {
        projectItemsList.add(position, projectItem)
        notifyItemInserted(position)
    }

    inner class ProjectListHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewMobileIcon)
        val projectName: TextView = itemView.findViewById(R.id.textViewProjectName)
        val projectText: TextView = itemView.findViewById(R.id.textViewProjectText)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}