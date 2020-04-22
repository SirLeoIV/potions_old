package engine.dto;

import engine.enums.EventType;

public class Event {

    EventType type;
    String content;
    int value;

    public Event(EventType type, String content, int value) {
        this.type = type;
        this.content = content;
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
