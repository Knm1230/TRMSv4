package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private Employee sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private Employee recipient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "req_id", referencedColumnName = "id")
    private Request request;

    @Column(name = "msg_body", columnDefinition = "varchar(100)")
    private String messageBody;
}
