package com.example.xcriticaltestapp

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import kotlin.collections.ArrayList

class ProjectsRepository @Inject constructor() {

    private val _testList = MutableLiveData<ArrayList<ProjectListItem>>()

    init {
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name1",
                "text1",
                "12.12.2012"
            )
        )
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name2",
                "text2",
                "12.12.2012"
            )
        )
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name3",
                "text3",
                "12.12.2012"
            )
        )
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name4",
                "text4",
                "12.12.2012"
            )
        )
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name5",
                "text5",
                "12.12.2012"
            )
        )
        _testList.value?.add(
            ProjectListItem(
                R.drawable.ic_mobile,
                "name6",
                "text6",
                "12.12.2012"
            )
        )
    }

    fun addProject(project: ProjectListItem) {
        _testList.value?.add(project)
    }

    fun removeProject(project: ProjectListItem) {
        _testList.value?.remove(project)
    }

    fun getProjectsList() = _testList
}