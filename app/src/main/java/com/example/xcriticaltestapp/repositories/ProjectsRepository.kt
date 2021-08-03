package com.example.xcriticaltestapp.repositories

import com.example.xcriticaltestapp.ProjectListItem
import com.example.xcriticaltestapp.R
import com.example.xcriticaltestapp.dataBase.DAO.ProjectDAO
import com.example.xcriticaltestapp.dataBase.entities.ProjectEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class ProjectsRepository @Inject constructor(private val projectDAO: ProjectDAO) {

    fun getAllProjects() = projectDAO.getAll()

    fun addProject(project: ProjectEntity): Long = projectDAO.insertProject(project)

    fun removeProject(id: Long) = projectDAO.deleteProject(id)
}