package com.preetiharkanth.listview.inclass07.group26.myapplication;

/**
 * Created by crosari1 on 3/14/2016.
 */
public class StoryDBObject {

    private long _id;
    private String storyTitle;
    private String storyByline;
    private String storyAbstract;
    private String createDate;
    private String thumbUrl;
    private String normalUrl;

    public StoryDBObject(){

    }

    public StoryDBObject(String storyTitle, String storyByline, String storyAbstract, String createDate, String thumbUrl, String normalUrl) {
        this.storyTitle = storyTitle;
        this.storyByline = storyByline;
        this.storyAbstract = storyAbstract;
        this.createDate = createDate;
        this.thumbUrl = thumbUrl;
        this.normalUrl = normalUrl;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryByline() {
        return storyByline;
    }

    public void setStoryByline(String storyByline) {
        this.storyByline = storyByline;
    }

    public String getStoryAbstract() {
        return storyAbstract;
    }

    public void setStoryAbstract(String storyAbstract) {
        this.storyAbstract = storyAbstract;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public void setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
    }
}
