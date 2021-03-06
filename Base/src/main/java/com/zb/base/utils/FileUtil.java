package com.zb.base.utils;

import com.zb.base.exception.BaseRuntimeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {

    /**
     * 如果文件所属路径不存在则创建,如果文件已存在则删除
     * @param path
     */
    public static void createNewFile(Path path){
        if(path==null){
            return;
        }
        try {
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent());
        Files.createFile(path);
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    /**
     * 如果文件已存在则忽视,否则创建
     * @param path
     */
    public static void createFileIfNotExists(Path path){
        if(path==null){
            return;
        }
        try {
            if (Files.exists(path)) {
                return;
            }
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    /**
     * 如果文件夹已存在则不创建
     * @param path
     */
    public static void createDirectories(Path path){
        if(path==null){
            return;
        }
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    /**
     * 从InputStream读取流数据写入OutputStream中
     * @param is
     * @param os
     */
    public static void write(InputStream is, OutputStream os){
        try {
            int len;
            byte[] content=new byte[1024];
            while((len=is.read(content))!=-1){
                os.write(content,0,len);
                os.flush();
            }
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    /**
     * 从InputStream读取流数据重新生成Path
     * @param is
     * @param outPath
     */
    public static void write(InputStream is, Path outPath){
        createFileIfNotExists(outPath);
        try(OutputStream os= Files.newOutputStream(outPath)) {
            int len;
            byte[] content=new byte[1024];
            while((len=is.read(content))!=-1){
                os.write(content,0,len);
                os.flush();
            }
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    /**
     * 从InputStream读取流数据添加到Path中
     * @param is
     * @param outPath
     */
    public static void append(InputStream is, Path outPath){
        createFileIfNotExists(outPath);
        try(OutputStream os= Files.newOutputStream(outPath, StandardOpenOption.APPEND)) {
            int len;
            byte[] content=new byte[1024];
            while((len=is.read(content))!=-1){
                os.write(content,0,len);
                os.flush();
            }
        } catch (IOException e) {
            throw BaseRuntimeException.getException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        File file  = new File("/Users/fanchen/IdeaProjects/incar/saicmotor-neevcns-platform/NE/src/main/resources/db/migration");
        File[] files = file.listFiles();
        for(File f : files){
            String newName = f.getName().replace("__xny","__ne" );
            write(new FileInputStream(f), Paths.get("/Users/fanchen/IdeaProjects/incar/saicmotor-neevcns-platform/NE/src/main/resources/db/migration/"+newName));
        }

    }
}
