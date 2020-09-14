package com.example.organizer.Database;

public class EventModel {
    int eventId;
    String eventName,date,time,location_event;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation_event() {
        return location_event;
    }

    public void setLocation_event(String location_event) {
        this.location_event = location_event;
    }
}
