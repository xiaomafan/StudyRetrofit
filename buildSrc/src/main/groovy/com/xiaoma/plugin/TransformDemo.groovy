package com.xiaoma.plugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

class TransformDemo extends Transform {

    TransformDemo() {
    }

    @Override
    String getName() {
        return "xiaoma"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocationxi)
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        inputs.each {
            it.jarInputs.each {transformInvocationxi
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes
                        , it.scopes, Format.JAR)
                println "dest:${dest}"
                FileUtils.copyFile(it.file, dest)
            }

            it.directoryInputs.each {
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes
                        , it.scopes, Format.DIRECTORY)
                println "Dest: ${it.file}"
                FileUtils.copyDirectory(it.file, dest)
            }
        }
    }

}
