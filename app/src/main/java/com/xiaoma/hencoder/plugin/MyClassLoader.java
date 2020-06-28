package com.xiaoma.hencoder.plugin;


public class MyClassLoader extends ClassLoader {


    /**
     *  ClassLoader   双亲委托模式  先向上委托流程检查类是否加载，如果没有加载，则向下执行查找
     *      loadClass  查看类是否已经加载 ，没有的话调用findClass去寻找流程，
     *
     *     BaseDexClassLoader  findClass调用时
     *      DexPathList  内循环调用Element的findClass
     *     DexFile.loadClassBinaryName    然后调用defineClass,然后调用的是Native方法
     *
     *     BootClassLoader  ZygoteInit的main方法实例
     *     PathClassLoader
     *
     */

}

