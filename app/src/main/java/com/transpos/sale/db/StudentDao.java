package com.transpos.sale.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    /**
     * 插入操作,若已存在相同的主键的数据则直接覆盖
     *  返回值也可以设为void
     * @param student
     * @return 插入的row id
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Student student);

    /**
     * 同上
     * @param student
     * @return 插入的row id 集合，也可以设置为Long[]返回值
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(Student ... student);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<Student> list);

    /**
     * 更新操作
     * @param student
     * @return 更新成功的数量
     */
    @Update
    int update(Student student);

    /**
     * 同上
     * @param student
     * @return 更新成功的数量
     */
    @Update
    int update(Student... student);

    /**
     * 查询指定名字的数据
     * @param name
     * @return
     */
    @Query("SELECT * FROM student WHERE name=:name")
    Student getByName(String name);

    /**
     * 查询所有数据
     * @return
     */
    @Query("SELECT * FROM student")
    List<Student> loadAll();

    /**
     * 根据主键进行删除
     * @param student
     * @return 删除成功的数量
     */
    @Delete
    int delete(Student student);

    @Delete
    int delete(Student... student);

    @Query("DELETE FROM student")
    int deleteAll();

    /**
     * 删除指定名字的数据
     * @param name
     * @return
     */
    @Query("DELETE FROM student where name=:name")
    int deleteByName(String name);
}
