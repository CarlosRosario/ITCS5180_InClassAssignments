package com.group26.myapplication;

import java.io.Serializable;

/**
 * Created by crosario on 2/22/2016.
 */
public class News implements Serializable{

    String newsItemUrl = "null value";
    String newsImageUrl = "null value";
    String newsTitle = "null value";
    String newsDescription = "null value";
    String publicationDate = "null value";

    public String getNewsItemUrl() {
        return newsItemUrl;
    }

    public void setNewsItemUrl(String newsItemUrl) {

        if(newsItemUrl == null){
            this.newsItemUrl = "null value";
        }
        else {
            this.newsItemUrl = newsItemUrl;
        }

    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public void setNewsImageUrl(String newsImageUrl) {

        if(newsImageUrl == null){
            this.newsImageUrl = "null value";
        }
        else {
            this.newsImageUrl = newsImageUrl;
        }
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {

        if(newsTitle == null){
            this.newsTitle = "null value";
        }
        else {
            this.newsTitle = newsTitle;
        }

    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        if(newsDescription == null){
            this.newsDescription = "null value";
        }
        else {
            this.newsDescription = newsDescription;
        }

    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        if(publicationDate == null){
            this.publicationDate = "null value";
        }
        else {
            this.publicationDate = publicationDate;
        }

    }
}
