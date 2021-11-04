package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request
{
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private int status;

    @Column(name = "request_location")
    private String requestLocation;

    @Column(name = "request_cost", columnDefinition = "numeric(6,2)")
    private double requestCost;

    @Column(name = "file_path", columnDefinition = "text")
    private String filePath;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "work_time", columnDefinition = "int")
    private int workTime;

    @Column(name = "last_update_time", columnDefinition = "bigint")
    private long lastUpdateTime;

    @Column(name = "start_time", columnDefinition = "bigint")
    private long startTime;

    @Column(name = "grade", columnDefinition = "varchar(20)")
    private String grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_format", referencedColumnName = "id")
    private GradeFormat gradeFormat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type", referencedColumnName = "id")
    private EventType eventType;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "emp_id", referencedColumnName = "id")
//    private int empId;
    
    @Column(name = "emp_id")
    private int empId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "current_desk", referencedColumnName = "id")
//    private int currentDesk;
    
    @Column(name = "current_desk")
    private int currentDesk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "overcharge", referencedColumnName = "id")
    private Overcharge overcharge;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "request")
    private List<Message> messageList;

    public Request()
    {
    }

    public Request(int id, int status, String requestLocation, double requestCost, String filePath, long lastUpdateTime, long startTime, String grade, GradeFormat gradeFormat, EventType eventType, int empId, int currentDesk, Overcharge overcharge)
    {
        this.id = id;
        this.status = status;
        this.requestLocation = requestLocation;
        this.requestCost = requestCost;
        this.filePath = filePath;
        this.lastUpdateTime = lastUpdateTime;
        this.startTime = startTime;
        this.grade = grade;
        this.gradeFormat = gradeFormat;
        this.eventType = eventType;
        this.empId = empId;
        this.currentDesk = currentDesk;
        this.overcharge = overcharge;
    }

    public Request(int id, int status, String requestLocation, double requestCost, String filePath, long lastUpdateTime, long startTime, String grade, GradeFormat gradeFormat, EventType eventType, int empId, int currentDesk, Overcharge overcharge, List<Message> messageList)
    {
        this.id = id;
        this.status = status;
        this.requestLocation = requestLocation;
        this.requestCost = requestCost;
        this.filePath = filePath;
        this.lastUpdateTime = lastUpdateTime;
        this.startTime = startTime;
        this.grade = grade;
        this.gradeFormat = gradeFormat;
        this.eventType = eventType;
        this.empId = empId;
        this.currentDesk = currentDesk;
        this.overcharge = overcharge;
        this.messageList = messageList;
    }

    public Request(int id, int status, String requestLocation, double requestCost, String filePath, long lastUpdateTime, long startTime, String grade, String description, int workTime, GradeFormat gradeFormat, EventType eventType, int empId, int currentDesk, Overcharge overcharge)
    {
        this.id = id;
        this.status = status;
        this.requestLocation = requestLocation;
        this.requestCost = requestCost;
        this.filePath = filePath;
        this.lastUpdateTime = lastUpdateTime;
        this.startTime = startTime;
        this.grade = grade;
        this.description = description;
        this.workTime = workTime;
        this.gradeFormat = gradeFormat;
        this.eventType = eventType;
        this.empId = empId;
        this.currentDesk = currentDesk;
        this.overcharge = overcharge;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getRequestLocation()
    {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation)
    {
        this.requestLocation = requestLocation;
    }

    public double getRequestCost()
    {
        return requestCost;
    }

    public void setRequestCost(double requestCost)
    {
        this.requestCost = requestCost;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public long getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public GradeFormat getGradeFormat()
    {
        return gradeFormat;
    }

    public void setGradeFormat(GradeFormat gradeFormat)
    {
        this.gradeFormat = gradeFormat;
    }

    public EventType getEventType()
    {
        return eventType;
    }

    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    public int getEmpId()
    {
        return empId;
    }

    public void setEmpId(int empId)
    {
        this.empId = empId;
    }

    public int getCurrentDesk()
    {
        return currentDesk;
    }

    public void setCurrentDesk(int currentDesk)
    {
        this.currentDesk = currentDesk;
    }

    public Overcharge getOvercharge()
    {
        return overcharge;
    }

    public void setOvercharge(Overcharge overcharge)
    {
        this.overcharge = overcharge;
    }

    public List<Message> getMessageList()
    {
        return messageList;
    }

    public void setMessageList(List<Message> messageList)
    {
        this.messageList = messageList;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getWorkTime()
    {
        return workTime;
    }

    public void setWorkTime(int workTime)
    {
        this.workTime = workTime;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "id=" + id +
                ", status=" + status +
                ", requestLocation='" + requestLocation + '\'' +
                ", requestCost=" + requestCost +
                ", filePath='" + filePath + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", startTime=" + startTime +
                ", grade='" + grade + '\'' +
                ", description='" + description + '\'' +
                ", workTime=" + workTime +
                ", gradeFormat=" + gradeFormat +
                ", eventType=" + eventType +
                ", empId=" + empId +
                ", currentDesk=" + currentDesk +
                ", overcharge=" + overcharge +
                ", messageList=" + messageList +
                '}';
    }
}
