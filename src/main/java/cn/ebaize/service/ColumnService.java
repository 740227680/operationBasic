package cn.ebaize.service;

import cn.ebaize.model.vo.TableColumnVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WEI
 */

@Service
public class ColumnService {

    private final JdbcTemplate jdbcTemplate;

    public ColumnService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TableColumnVo> getColumnNamesAndComments(String tableName) throws SQLException {
        List<TableColumnVo> tableColumnVoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            String databaseProductName = connection.getMetaData().getDatabaseProductName();
            String query;
            if ("DM DBMS".equalsIgnoreCase(databaseProductName)) {
                query = "select aa.COLUMN_NAME as COLUMN_NAME,ss.comments as COLUMN_COMMENT,aa.data_type as DATA_TYPE from all_tab_columns aa inner join all_col_comments ss on ss.COLUMN_NAME = aa.COLUMN_NAME and ss.Table_Name = aa.Table_Name where aa.Table_Name = ? group by aa.column_id order by aa.column_id";
            } else {
                query = "SELECT COLUMN_NAME, COLUMN_COMMENT, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
            }
            pstmt = connection.prepareStatement(query);

            if ("DM DBMS".equalsIgnoreCase(databaseProductName)) {
                pstmt.setString(1, tableName);
            } else {
                String catalog = connection.getCatalog();
                pstmt.setString(1, catalog);
                pstmt.setString(2, tableName);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TableColumnVo tableColumnVo = new TableColumnVo();
                if (!"id".equals(rs.getString("COLUMN_NAME"))) {
                    tableColumnVo.setColumnName(this.toCamelCase(rs.getString("COLUMN_NAME")));
                    tableColumnVo.setColumnDesc(rs.getString("COLUMN_COMMENT"));
                    tableColumnVo.setColumnType(this.convertDataType(rs.getString("DATA_TYPE")));
                    tableColumnVoList.add(tableColumnVo);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return tableColumnVoList;
    }

    public Boolean updateDataByDynamic(String id, String tableName, Map<String, Object> updates) {
        if (updates.isEmpty()) {
            return true;
        }

        StringBuilder query = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        List<Object> params = new ArrayList<>();

        // 构建SQL语句
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            if ("id".equals(entry.getKey())) {
                continue;
            }
            if (query.length() > ("UPDATE " + tableName + " SET ").length()) {
                query.append(", ");
            }
            String entryKey = this.camelToSnake(entry.getKey());
            query.append(entryKey).append(" = ?");
            params.add(entry.getValue());
        }

        query.append(" WHERE id = ?");
        params.add(id);

        try (Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
             PreparedStatement pstmt = connection.prepareStatement(query.toString())) {

            // 设置参数
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            // The resources will be automatically closed by try-with-resources
        }
    }

    /**
     * 将xx_dd 转换为 xxDD统一方法
     *
     * @param underscoreSeparatedString
     * @return
     */
    public String toCamelCase(String underscoreSeparatedString) {
        StringBuilder camelCaseString = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : underscoreSeparatedString.toCharArray()) {
            if (c == '_') {
                // 下一个字符应该大写
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    camelCaseString.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    camelCaseString.append(camelCaseString.length() == 0 ? Character.toLowerCase(c) : c);
                }
            }
        }
        return camelCaseString.toString();
    }

    /**
     * 将xxDD 转换为 xx_dd统一方法
     *
     * @param camelCaseString
     * @return
     */
    public String camelToSnake(String camelCaseString) {
        StringBuilder snakeCaseBuilder = new StringBuilder();
        boolean nextIsUpper = false;

        for (char c : camelCaseString.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (nextIsUpper) {
                    snakeCaseBuilder.append(Character.toLowerCase(c));
                } else {
                    snakeCaseBuilder.append('_').append(Character.toLowerCase(c));
                    nextIsUpper = true;
                }
            } else {
                snakeCaseBuilder.append(c);
                nextIsUpper = false;
            }
        }
        return snakeCaseBuilder.toString();
    }

    public String convertDataType(String dataType) {
        switch (dataType) {
            case "text":
                return "textarea";
            case "datetime":
                return "datetime";
            default:
                return "text";
        }
    }

}