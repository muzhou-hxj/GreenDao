package com.rushro2m.greendao_master.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Rushro2m on 2017/8/15.
 * 邮箱：haoxujie1993@gmail.com
 * 版本：v1.0
 */

@Entity
public class MovieBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "TITLE")
    private String title;
    @Property(nameInDb = "IMAGE")
    private String image;
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 939120649)
    public MovieBean(Long id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
    @Generated(hash = 1964823426)
    public MovieBean() {
    }
}
