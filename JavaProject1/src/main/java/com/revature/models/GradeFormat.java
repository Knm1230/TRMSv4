package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "grade_formats")
public class GradeFormat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "threshold")
    private String threshold;
}
