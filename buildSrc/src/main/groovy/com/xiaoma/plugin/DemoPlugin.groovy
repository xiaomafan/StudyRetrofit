package com.xiaoma.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class DemoPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create("xiaoma", ExtensionDemo)
        project.afterEvaluate {
            println "hello ${extension.name}"
        }

        //默认行为是不做，即无法构建apk，如果注册了
        def transform=new TransformDemo()
        def baseExtentsion=project.getExtensions().getByType(BaseExtension)
        baseExtentsion.registerTransform(transform)
    }
}
