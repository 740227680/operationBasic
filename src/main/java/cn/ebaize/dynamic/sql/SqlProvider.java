package cn.ebaize.sql;

import java.util.List;
import java.util.Map;

/**
 * @author WEI
 */
public class SqlProvider {

    public String selectFromTableWithConditions(Map<String, Object> params) {
        final String tableName = (String) params.get("tableName");
        final List<String> paramsSqlList = (List<String>) params.get("paramsSqlList");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(tableName);

        if (paramsSqlList != null && !paramsSqlList.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", paramsSqlList));
        }

        return sql.toString();
    }
}