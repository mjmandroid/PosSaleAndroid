/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trans.network.convert;

import android.os.Environment;
import android.text.TextUtils;


import com.trans.network.utils.HttpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：字符串的转换器
 * 修订历史：
 * ================================================
 */
public class FileConvert implements Converter<File> {

    public static final String DM_TARGET_FOLDER = File.separator + "download" + File.separator; //下载目标文件夹

    private String folder;                  //目标文件存储的文件夹路径
    private String fileName;                //目标文件存储的文件名

    public FileConvert() {
        this(null);
    }

    public FileConvert(String fileName) {
        this(Environment.getExternalStorageDirectory() + DM_TARGET_FOLDER, fileName);
    }

    public FileConvert(String folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;
    }



    @Override
    public File convertResponse(Response response) throws Throwable {
        return null;
    }

}
