package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "overcharges")
public class Overcharge
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "reason")
    private String reason;
}
