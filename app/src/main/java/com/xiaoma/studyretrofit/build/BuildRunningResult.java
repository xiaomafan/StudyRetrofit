package com.xiaoma.studyretrofit.build;

public class BuildRunningResult {

    public static void main(String args[]) {

        Student student = new Student.Builder()
                .setName("小马哥")
                .setAge(18)
                .build();
        System.out.println("Build模式结果是="+student.toString());

        Student noNameStudent = new Student.Builder().setAge(20).build();
        System.out.println("Build模式无名字结果是="+noNameStudent.toString());
    }
}
