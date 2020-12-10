package com.aaron.ren;

import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ActivitiTest {

	/**
	 * 部署流程
	 */
	@Test
	public void deploy(){
		//创建ProcessEngineConfiguration对象
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		//创建ProcessEngine对象
		ProcessEngine processEngine = configuration.buildProcessEngine();
		//得到RepositoryService实例
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//进行部署
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("processes/pro1.bpmn")  //添加bpmn资源
				.addClasspathResource("processes/pro1.png")
				.name("请假申请单流程")
				.deploy();

		System.out.println(deployment.getName());
		System.out.println(deployment.getId());
	}

	/**
	 * 启动一个流程实例
	 */
	@Test
	public void startProcessInstance(){
		ProcessEngine processEngine = getProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("process");

		System.out.println("流程实例ID:"+instance.getId());
		System.out.println("流程定义ID:"+instance.getProcessDefinitionId());
	}

	public ProcessEngine getProcessEngine(){
		//创建ProcessEngineConfiguration对象
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		//创建ProcessEngine对象
		return configuration.buildProcessEngine();
	}


	/**
	 * 查询用户的任务列表
	 */
	@Test
	public void taskQuery() {
		ProcessEngine processEngine = getProcessEngine();

		TaskService taskService = processEngine.getTaskService();

		//根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
		List<Task> list = taskService.createTaskQuery()
				.processDefinitionKey("process")
				.taskAssignee("tom")
				.list();

		if(list!=null && list.size()>0){
			for(Task task:list){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("getOwner:"+task.getOwner());
				System.out.println("getCategory:"+task.getCategory());
				System.out.println("getDescription:"+task.getDescription());
				System.out.println("getFormKey:"+task.getFormKey());
				Map<String, Object> map = task.getProcessVariables();
				for (Map.Entry<String, Object> m : map.entrySet()) {
					System.out.println("key:" + m.getKey() + " value:" + m.getValue());
				}
				for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
					System.out.println("key:" + m.getKey() + " value:" + m.getValue());
				}

			}
		}
	}


	/**
	 * 完成任务
	 */
	@Test
	public void completeTask(){
		//任务ID
		String taskId = "7502";
		ProcessEngine processEngine = getProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		taskService.complete(taskId);
		System.out.println("完成任务：任务ID："+taskId);
	}

}