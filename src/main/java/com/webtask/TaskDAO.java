package com.webtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.infytask.Task;

@Repository
public class TaskDAO {
	
	@Autowired
	protected JdbcTemplate jdbc;
	
	@Autowired
	Task allTask;
	
	public List<Task> getAll()
	{
		String sql = "select * from task_master;";
		System.out.println("Connected to databse");
		System.out.println("Fetching all the tasks");
		List<Task> allTasks = new ArrayList<Task>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);
		
		for(Map row : rows)
		{
			Task allTask = new Task();
          allTask.setTaskid((Integer)row.get("task_id"));
          allTask.setDuedate((String)row.get("due_date"));
          allTask.setStartdate((String)row.get("start_date"));
          allTask.setRemainder((String)row.get("remainder"));
          allTask.setPriority((Integer)row.get("priority"));
          allTasks.add(allTask);
		}
		
		return allTasks;
	}
	
	public int createTask(Task taskobj)
	{
		String sql = "INSERT INTO TASK_MASTER(task_id, start_date, due_date, priority, remainder) VALUES(?, ?, ?, ?, ?)";
		System.out.println("Connected to databse");
		
		return jdbc.update(sql, taskobj.getTaskid(),taskobj.getStartdate(),taskobj.getDuedate(),taskobj.getPriority(),taskobj.getRemainder());
		
	}
	
	
	public int deleteTask(int taskId)
	{
		String sql = "DELETE from TASK_MASTER where task_id = ?";
		System.out.println("Connected to databse");
		
		return jdbc.update(sql, taskId);
		
	}
	
	public int updateTask(Task taskobj)
	{
		String sql = "UPDATE TASK_MASTER set remainder =  ? where task_id = ?";
		System.out.println("Connected to databse");
		
		return jdbc.update(sql, taskobj.getRemainder(),taskobj.getTaskid());
		
	}
	
	public Task getTask(int taskId)
	{
		
		String Sql = "select * from TASK_MASTER where task_id = ?";
		System.out.println("Fetching task details");
		
		List<Map<String, Object>> rows =  jdbc.queryForList(Sql,taskId);
		
		for(Map row : rows)
		{
          allTask.setTaskid((Integer)row.get("task_id"));
          allTask.setDuedate((String)row.get("due_date"));
          allTask.setStartdate((String)row.get("start_date"));
          allTask.setRemainder((String)row.get("remainder"));
          allTask.setPriority((Integer)row.get("priority"));
          System.out.println((Integer)row.get("task_id") + (String)row.get("due_date") + row.get("start_date") + row.get("remainder") + (Integer)row.get("priority"));
		}
		
		return allTask;
		
	}
	

}
