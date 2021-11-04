package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "emp_roles")
public class EmpRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    public EmpRole()
    {
    }

    public EmpRole(int id, String roleName)
    {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public String toString()
    {
        return "EmpRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
