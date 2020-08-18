package main.java;

public class Event extends Task {
    protected String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}