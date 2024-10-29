package com.syhbb.bigdata.dataObject;

import com.syhbb.bigdata.spiderData.DO.comment.MemberDO;
import com.syhbb.bigdata.spiderData.DO.video.OwnerData;

public class AuthorDO {
    private long id;
    private String name;
    private String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AuthorDO(OwnerData data){
        id = data.getMid();
        name = data.getName();
        avatar = data.getFace();
    }

    public AuthorDO(MemberDO memberDO){
        id = memberDO.getMid();
        name = memberDO.getUname();
        avatar = memberDO.getAvatar();
    }

}
