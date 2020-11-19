package com.ilan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "tweetsLike")
public class TweetsLike {

    @Id
    private String id;

    @Field
    @Size(max = 40)
    private String likeCount;

    @NotBlank
    @Size(max = 300)
    private String fId;

    @NotNull
    private Date createdAt = new Date();

    public TweetsLike() {

    }

    public TweetsLike(String likeCount, String fId) {
        this.likeCount =likeCount;
        this.fId = fId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
