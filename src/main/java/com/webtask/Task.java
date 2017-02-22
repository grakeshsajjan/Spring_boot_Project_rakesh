package com.webtask;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

//@Component("task")
@Component
public class Task {
	
	
	private Integer taskid;
	private String startdate;
	private String duedate;
	private Integer priority;
	@NotNull
	@Size(min = 2, max = 100)
	private String remainder;
	
	public Task()
	{
		
	}
	
	public Task(Integer taskid,String startdate,String duedate,Integer priority,String remainder)
	{
		this.taskid = taskid;
		this.startdate = startdate;
		this.duedate = duedate;
		this.priority = priority;
		this.remainder = remainder;
	}
	
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getRemainder() {
		return remainder;
	}
	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}
	

}
