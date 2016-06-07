package com.adamin.android.qdbus.domain.user;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Adam on 2016/6/4.
 */
public class BusUser extends BmobUser {
    private BmobFile useravator;
    private Boolean male;
    private String nickname;
    private Integer age;


    public BmobFile getUseravator() {
        return useravator;
    }

    public void setUseravator(BmobFile useravator) {
        this.useravator = useravator;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
