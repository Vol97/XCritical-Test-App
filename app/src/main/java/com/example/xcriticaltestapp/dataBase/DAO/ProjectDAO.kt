package com.example.xcriticaltestapp.dataBase.DAO

import androidx.room.*
import com.example.xcriticaltestapp.dataBase.entities.ProjectEntity

@Dao
interface ProjectDAO {
    @Query("SELECT * FROM projectDb")
    fun getAll(): List<ProjectEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: ProjectEntity): Long

    @Query("SELECT * FROM projectDb WHERE id = :projectId")
    fun getProject(projectId: Long): ProjectEntity

    @Update
    fun updateProject(projectEntity: ProjectEntity)

    @Query("DELETE FROM projectDb WHERE id = :projectId")
    fun deleteProject(projectId: Long)
}