package com.xiaoma.studyretrofit.build;

public class Student {

    private String name;
    private int age;
    private String location;

    public Student(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", location='").append(location).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private String name;
        private int age;
        private String location;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Student build() {
            if (location == null) {
                location = "初始化地址";
            }

            if(age==0){
                age=15;
            }

            if(name==null){
                name="无名氏";
            }
            return new Student(name, age, location);
        }
    }

}
