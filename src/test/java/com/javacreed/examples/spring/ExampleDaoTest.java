/*
 * #%L
 * How to get Auto-Generated Key with JdbcTemplate
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
