package cn.ebaize.model.vo;

import java.io.Serializable;

/**
 * @author WEI
 */
public class QueryColumnVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * queryField
     */
    private String queryField;

    /**
     * queryName
     */
    private String queryName;

    /**
     * queryType
     */
    private String queryType;

    /**
     * sort
     */
    private Integer querySort;

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Integer getQuerySort() {
        return querySort;
    }

    public void setQuerySort(Integer querySort) {
        this.querySort = querySort;
    }
}