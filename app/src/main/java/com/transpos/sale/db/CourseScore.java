package com.transpos.sale.db;

import android.arch.persistence.room.Ignore;

public class CourseScore {
    public int chinese;
    public int english;
    public int math;
    @Ignore
    public int sports;  //这个字段将被忽略，不会被映射到表中

    public CourseScore(int chinese, int english, int math) {
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }
}
