package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "balance", columnDefinition = "numeric(6,2)")
    private double balance;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass")
    private String passWord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "special_role", referencedColumnName = "id")
    private EmpRole specialRole;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "supervisor", referencedColumnName = "id")
//    private Employee supervisor;
    @Column(name = "supervisor")
    private int supervisor;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dep_head", referencedColumnName = "id")
//    private Employee depHead;
    @Column(name = "dep_head")
    private int depHead;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ben_co", referencedColumnName = "id")
//    private Employee benCo;
    @Column(name = "ben_co")
    private int benCo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipient")
    private List<Message> messages;

    public Employee()
    {
    }

    public Employee(int id, String firstName, String lastName, double balance, String userName, String passWord, EmpRole specialRole, int supervisor, int depHead, int benCo, List<Message> messages)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.userName = userName;
        this.passWord = passWord;
        this.specialRole = specialRole;
        this.supervisor = supervisor;
        this.depHead = depHead;
        this.benCo = benCo;
        this.messages = messages;
    }

    public Employee(int id, String firstName, String lastName, double balance, String userName, String passWord, EmpRole specialRole, int supervisor, int depHead, int benCo)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.userName = userName;
        this.passWord = passWord;
        this.specialRole = specialRole;
        this.supervisor = supervisor;
        this.depHead = depHead;
    }

    public Employee(int id, String firstName, String lastName, double balance, String userName, String passWord, EmpRole specialRole)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.userName = userName;
        this.passWord = passWord;
        this.specialRole = specialRole;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public EmpRole getSpecialRole()
    {
        return specialRole;
    }

    public void setSpecialRole(EmpRole specialRole)
    {
        this.specialRole = specialRole;
    }

    public int getSupervisor()
    {
        return supervisor;
    }

    public void setSupervisor(int supervisor)
    {
        this.supervisor = supervisor;
    }

    public int getDepHead()
    {
        return depHead;
    }

    public void setDepHead(int depHead)
    {
        this.depHead = depHead;
    }

    public int getBenCo()
    {
        return benCo;
    }

    public void setBenCo(int benCo)
    {
        this.benCo = benCo;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", specialRole=" + specialRole +
                ", supervisor=" + supervisor +
                ", depHead=" + depHead +
                ", benCo=" + benCo +
                ", messages=" + messages +
                '}';
    }
}
