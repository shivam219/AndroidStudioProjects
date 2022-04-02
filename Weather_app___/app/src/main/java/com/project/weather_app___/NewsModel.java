package com.project.weather_app___;

public class NewsModel {
    private  String title, icon, content, publisher, date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NewsModel(String title, String icon, String content, String publisher, String date) {
        this.title = title;
        this.icon = icon;
        this.content = content;
        this.publisher = publisher;
        this.date = date;
    }
}
