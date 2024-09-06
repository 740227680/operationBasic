package cn.ebaize.model.dto;


import java.io.Serializable;

/**
 * @author WEI
 */
public class QueryColumnSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private String queryField;

    private String queryType;

    private Object queryValue;

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Object getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(Object queryValue) {
        this.queryValue = queryValue;
    }
}