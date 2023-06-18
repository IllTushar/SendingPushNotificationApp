package com.example.pushnotificationmainapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBody {
    @SerializedName("to")
    @Expose
    private String to;

    public RequestBody(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    @SerializedName("notification")
    @Expose
    private Notification notification;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    public static class Notification{
        public Notification(String body, String title) {
            this.body = body;
            this.title = title;
        }

        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("title")
        @Expose
        private String title;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
