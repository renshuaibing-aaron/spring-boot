package com.aaron.ren.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/testes")
public class TestController {

	@Autowired
	TestService testService;

	@RequestMapping("findAll")
	public Iterable<TestBean> findAll() {

		return testService.findAll();
	}

	@RequestMapping("list")
	public String save() {
		List<TestBean> list = null;
		testService.save(list);

		return "success";
	}

	@RequestMapping("save")
	public void save(TestBean bean) {
		System.out.println("==========="+bean);
		long unboundedLong = new Random().nextLong();
		TestBean testBean=new TestBean(unboundedLong,"测试",12,"男","藐视是看得见肯德基");
		TestBean save = testService.save(testBean);

		System.out.println("****save******"+save);
	}

	@RequestMapping("findByName")
	public List<TestBean> findByName(String name) {
		return testService.findByName(name);
	}

	@RequestMapping("findByNameOrDesc")
	public List<TestBean> findByNameOrDesc(String text,String  desc) {
		return testService.findByNameOrDesc(text,desc);
	}

}