package com.example.xcriticaltestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.ProjectListItem
import com.example.xcriticaltestapp.R
import com.example.xcriticaltestapp.dataBase.entities.ProjectEntity
import javax.inject.Inject

class ProjectListAdapter @Inject constructor(
    private var projectItemsList: ArrayList<ProjectEntity>,
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

        holder.projectName.text = currentItem.title
        holder.projectText.text = currentItem.scenario
        holder.projectDate.text = currentItem.date
    }

    override fun getItemCount() = projectItemsList.size

    fun deleteProjectItem(position: Int): ProjectEntity {
        val deletedItem = projectItemsList[position]
        projectItemsList.removeAt(position)
        notifyItemRemoved(position)
        return deletedItem
    }

    fun addProjectItem(position: Int, project: ProjectEntity) {
        projectItemsList.add(position, project)
        notifyItemInserted(position)
    }

    fun setProjects(projectList: ArrayList<ProjectEntity>){
        projectItemsList = projectList
        notifyDataSetChanged()
    }

    fun getProject(position: Int) = projectItemsList[position]

    inner class ProjectListHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewMobileIcon)
        val projectName: TextView = itemView.findViewById(R.id.textViewProjectName)
        val projectText: TextView = itemView.findViewById(R.id.textViewProjectText)
        val projectDate: TextView = itemView.findViewById(R.id.textViewProjectDate)

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