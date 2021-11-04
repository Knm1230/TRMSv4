package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "event_types")
public class EventType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_name", columnDefinition = "varchar(30)")
    private String name;

    @Column(name = "coverage", columnDefinition = "numeric(5,2)")
    private double coverage;
}
