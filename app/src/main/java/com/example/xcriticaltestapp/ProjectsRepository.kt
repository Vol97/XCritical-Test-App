package com.example.xcriticaltestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class ProjectsRepository @Inject constructor() {

    private val _arrayList: ArrayList<ProjectListItem> = arrayListOf(
        ProjectListItem(
            R.drawable.ic_mobile,
            "name1",
            "text1",
            "12.12.2012"
        ),
        ProjectListItem(
            R.drawable.ic_mobile,
            "name2",
            "text2",
            "12.12.2012"
        ),
        ProjectListItem(
            R.drawable.ic_mobile,
            "name3",
            "text3",
            "12.12.2012"
        ),
        ProjectListItem(
            R.drawable.ic_mobile,
            "name4",
            "text4",
            "12.12.2012"
        ),
        ProjectListItem(
            R.drawable.ic_mobile,
            "name5",
            "text5",
            "12.12.2012"
        ),
        ProjectListItem(
            R.drawable.ic_mobile,
            "name6",
            "text6",
            "12.12.2012"
        )
    )

    fun addProject(project: ProjectListItem) {
        _arrayList.add(project)
    }

    fun removeProject(project: ProjectListItem) {
        _arrayList.remove(project)
    }

    fun getProjectsList() = _arrayList
}