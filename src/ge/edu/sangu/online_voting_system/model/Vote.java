package ge.edu.sangu.online_voting_system.model;

import java.time.LocalDateTime;

public class Vote {

    private Person from;
    private Person to;
    private LocalDateTime time;

    public Vote() {
    }

    public Vote(Person from, Person to, LocalDateTime time) {
        this.from = from;
        this.to = to;
        this.time = time;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
