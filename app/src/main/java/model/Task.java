
package model;
import java.util.Date;
/**
 * @author ton618
 */
public class Task {
    private int id;
    private int myproject;
    private String name;
    private String description;
    private String notes;
    private Date deadline;
    private Date createdAt;
    private Date updatedAt;
    private boolean compleated;
    
    public Task(int id, int myproject, String name, String description, String notes, Date deadline, Date createdAt, Date updatedAt, boolean compleated) {
        this.id = id;
        this.myproject = myproject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.compleated = compleated;
    }
    
    public Task() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.compleated = false;
    }

    @Override
    public String toString() {
        return "Tesk{" + "id=" + id + ", myproject=" + myproject + ", name=" + name + ", description=" + description + ", notes=" + notes + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", compleated=" + compleated + '}';
    }
    
    public boolean isCompleated() {
        return compleated;
    }

    public void setCompleated(boolean compleated) {
        this.compleated = compleated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyproject() {
        return myproject;
    }

    public void setMyproject(int myproject) {
        this.myproject = myproject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
        this.updatedAt = updatedAt;
    }
    
    
}
