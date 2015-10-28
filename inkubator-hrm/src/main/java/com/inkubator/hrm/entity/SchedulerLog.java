package com.inkubator.hrm.entity;
// Generated Oct 1, 2015 11:21:14 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SchedulerLog generated by hbm2java
 */
@Entity
@Table(name = "scheduler_log", catalog = "hrm"
)
public class SchedulerLog implements java.io.Serializable {

    private long id;
    private SchedulerConfig schedulerConfig;
    private Date startExecution;
    private Date endExecution;
    private String statusMessages;

    public SchedulerLog() {
    }

    public SchedulerLog(long id) {
        this.id = id;
    }

    public SchedulerLog(long id, SchedulerConfig schedulerConfig, Date startExecution, Date endExecution, String statusMessages) {
        this.id = id;
        this.schedulerConfig = schedulerConfig;
        this.startExecution = startExecution;
        this.endExecution = endExecution;
        this.statusMessages = statusMessages;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduler_config_id")
    public SchedulerConfig getSchedulerConfig() {
        return this.schedulerConfig;
    }

    public void setSchedulerConfig(SchedulerConfig schedulerConfig) {
        this.schedulerConfig = schedulerConfig;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_execution", length = 19)
    public Date getStartExecution() {
        return this.startExecution;
    }

    public void setStartExecution(Date startExecution) {
        this.startExecution = startExecution;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_execution", length = 19)
    public Date getEndExecution() {
        return this.endExecution;
    }

    public void setEndExecution(Date endExecution) {
        this.endExecution = endExecution;
    }

    @Column(name = "status_messages", length = 500)
    public String getStatusMessages() {
        return this.statusMessages;
    }

    public void setStatusMessages(String statusMessages) {
        this.statusMessages = statusMessages;
    }

}