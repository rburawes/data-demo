package com.demo.model;

import com.demo.model.listener.TimestampListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The base class of all objects within the model.
 *
 * @author rburawes
 */
@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(value = TimestampListener.class)
public abstract class Persistable implements Serializable {

    /**
     * The unique value that identifies the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    @Field(type = FieldType.Long)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeCreated;

    /**
     * Returns this object's hashcode.
     */
    @Override
    public int hashCode() {
        return id == 0 ? super.hashCode() : getClass().hashCode() ^ new Long(id).hashCode();
    }

    /**
     * Returns true if that object equals this object.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || obj != null && obj.getClass().equals(getClass()) && ((Persistable) obj).id == id;
    }

    /**
     * Returns the id of the entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the creation time of the entity.
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets the creation time of the entity.
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
