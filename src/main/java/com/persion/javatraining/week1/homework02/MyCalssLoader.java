package com.persion.javatraining.week1.homework02;

import java.io.IOException;
import java.io.InputStream;

public class MyCalssLoader extends ClassLoader{

    public void main(){

        try {
            Class clazz = new MyCalssLoader().findClass("Hello");
            Object instance = clazz.newInstance();

            clazz.getMethod("hello").invoke(instance);
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes=new byte[1024];
        int len=0;
        InputStream input=null;
        try {
            input = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
            len=input.read(bytes);
            for(int i=0;i<len;i++){
                bytes[i]=(byte)(255-bytes[i]);
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            input = null;
        }

        return defineClass(name,bytes,0,len);
    }
}
