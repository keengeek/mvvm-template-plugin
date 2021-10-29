package com.keenlot.plugin.services

import com.intellij.openapi.project.Project
import com.keenlot.plugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
