package cn.ebaize.model.vo;

import java.io.Serializable;

/**
 * @author WEI
 */
public class TableColumnVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * columnName
     */
    private String columnName;

    /**
     * columnType
     */
    private String columnType;

    /**
     * columnDesc
     */
    private String columnDesc;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }
}