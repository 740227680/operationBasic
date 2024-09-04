package cn.ebaize.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author WEI
 */

public class BasicSearchVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * tableName
     */
    private String tableName;

    /**
     * List the set of fields that need to be displayed
     */
    private List<TableColumnVo> tableColumnVoList;

    /**
     * Query parameter set
     */
    private List<QueryColumnVo> queryColumnVoList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableColumnVo> getTableColumnVoList() {
        return tableColumnVoList;
    }

    public void setTableColumnVoList(List<TableColumnVo> tableColumnVoList) {
        this.tableColumnVoList = tableColumnVoList;
    }

    public List<QueryColumnVo> getQueryColumnVoList() {
        return queryColumnVoList;
    }

    public void setQueryColumnVoList(List<QueryColumnVo> queryColumnVoList) {
        this.queryColumnVoList = queryColumnVoList;
    }
}
