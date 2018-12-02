package com.javacreed.examples.spring;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/test-context.xml" })
public class ExampleDaoTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private ExampleDao dao;

  @Before
  @After
  public void cleanTables() {
    jdbcTemplate.execute("TRUNCATE TABLE `names`");
  }

  @Test
  public void test() {
    Assert.assertEquals(1, dao.addNew("Albert"));
    Assert.assertEquals(2, dao.addNew("Mary"));
    Assert.assertEquals(2, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `names`", Integer.class).intValue());
  }
}
