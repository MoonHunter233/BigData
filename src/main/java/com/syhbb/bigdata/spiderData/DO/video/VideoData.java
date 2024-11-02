package com.syhbb.bigdata.spiderData.DO.video;

public class VideoData extends RightData {
    private long aid;           //avid 主键
    private int videos;         //视频分p数
    private int tid;            //分区id
    private String tname;       //分区名称
    private int coopyright;     //复制权限？？
    private String pic;         //封面url
    private String title;       //标题
    private int pubdate;        //发布时间
    private int ctime;          //审核时间
    private String desc;        //描述
    private int duration;      //视频时长
    private RightData rights;
    private OwnerData owner;
    private StatData stat;
    private String dynamic;     //动态引用视频内容
    private long cid;            //1p的cid
    private DimensionData dimension;
    private String short_link_v2;   //链接
    private String first_frame;     //1p视频第一帧
    private String pub_location;    //发布地点
    private RecommendReason rcmd_reason;

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getCoopyright() {
        return coopyright;
    }

    public void setCoopyright(int coopyright) {
        this.coopyright = coopyright;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
        this.pubdate = pubdate;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public RightData getRights() {
        return rights;
    }

    public void setRights(RightData rights) {
        this.rights = rights;
    }

    public OwnerData getOwner() {
        return owner;
    }

    public void setOwner(OwnerData owner) {
        this.owner = owner;
    }

    public StatData getStat() {
        return stat;
    }

    public void setStat(StatData stat) {
        this.stat = stat;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public DimensionData getDimension() {
        return dimension;
    }

    public void setDimension(DimensionData dimension) {
        this.dimension = dimension;
    }

    public String getShort_link_v2() {
        return short_link_v2;
    }

    public void setShort_link_v2(String short_link_v2) {
        this.short_link_v2 = short_link_v2;
    }

    public String getFirst_frame() {
        return first_frame;
    }

    public void setFirst_frame(String first_frame) {
        this.first_frame = first_frame;
    }

    public String getPub_location() {
        return pub_location;
    }

    public void setPub_location(String pub_location) {
        this.pub_location = pub_location;
    }

    public RecommendReason getRcmd_reason() {
        return rcmd_reason;
    }

    public void setRcmd_reason(RecommendReason rcmd_reason) {
        this.rcmd_reason = rcmd_reason;
    }
}
