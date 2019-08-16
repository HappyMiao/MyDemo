package com.miao.aidl.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用于AIDL的实体类
 */
public class Dog implements Parcelable {

    private String name;
    private String color;
    private int age;

    public Dog(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


   //Parcelable需要实现的一些方法

    /**
     * 参数读取顺序和写顺序一致
     */
    private Dog(Parcel in) {
        name = in.readString();
        color = in.readString();
        age = in.readInt();
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将数据写到Parcel中用于传递
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
        dest.writeInt(age);
    }
}
