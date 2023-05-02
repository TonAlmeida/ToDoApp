package model;

import java.util.Date;

/**
 * @author ton618
 */
public class Project {

    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private int projectUser;

    public Project(int id, String name, String description, Date createdAt, Date updatedAt, int projectUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.projectUser = projectUser;
    }

    public Project() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.projectUser = 1;
    }

    public int getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(int projectUser) {
        this.projectUser = projectUser;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null) this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null) this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        if(updatedAt == null) {
            updatedAt = new Date();
        }
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    

}
