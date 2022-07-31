package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "dead_line")
    private int deadLine;
    private String task;

    public Task(String name, int deadLine, String task) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;
    }
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadLine=" + deadLine +
                ", task='" + task + '\'' +
                '}';
    }
}
