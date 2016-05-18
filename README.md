Spring provides <code>GeneratedKeyHolder</code> (<a href="http://docs.spring.io/spring/docs/3.2.8.RELEASE/javadoc-api/org/springframework/jdbc/support/GeneratedKeyHolder.html" target="_blank">Java Doc</a>) class which can be used to retrieve the auto generated values.


Most of the examples will not contain the whole code and may omit fragments which are not relevant to the example being discussed. The readers can download or view all code from the above link.


The following class shows how to retrieve the auto generated key after a new value is added to the table.

<pre>
package com.javacreed.examples.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ExampleDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public long addNew(final String name) {
    final PreparedStatementCreator psc = new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("INSERT INTO `names` (`name`) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        return ps;
      }
    };

    <span class="comments">// The newly generated key will be saved in this object</span>
    <span class="highlight">final KeyHolder holder = new GeneratedKeyHolder();</span>

    jdbcTemplate.update(psc, <span class="highlight">holder</span>);

    <span class="highlight">final long newNameId = holder.getKey().longValue();
    return newNameId;</span>
  }
}
</pre>


