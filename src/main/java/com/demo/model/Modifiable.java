package com.demo.model;

import com.demo.model.listener.TimestampListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents the object that can be updated.
 */
@MappedSuperclass
@EntityListeners(value = TimestampListener.class)
public class Modifiable extends Persistable {

    /**
     * The date and time that the object was last updated. Serves as the version control of locking and concurrency.
     */
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_updated")
    private Date timeUpdated;

    /**
     * Returns the date and time of the entity's last update.
     *
     * @return timeUpdated
     */
    public Date getTimeUpdated() {
        return timeUpdated;
    }

    /**
     * Sets the date and time of the entity's last update.
     */
    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

}
