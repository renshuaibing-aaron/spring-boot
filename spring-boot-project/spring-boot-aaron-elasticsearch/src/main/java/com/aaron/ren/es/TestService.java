package com.aaron.ren.es;

import java.util.List;

public interface TestService {
	Iterable<TestBean> findAll();

	void save(List<TestBean> list);

	TestBean save(TestBean bean);

	List<TestBean> findByName(String text);

	List<TestBean> findByNameOrDesc(String name,String desc);
}
