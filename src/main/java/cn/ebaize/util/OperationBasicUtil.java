package cn.ebaize.util;

import cn.ebaize.model.dto.QueryColumnSearch;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: WEI
 */
public class OperationBasicUtil {

    /**
     * 获取动态查询Sql
     *
     * @param paramsList
     * @return
     */
    public List<String> transParamsSqlLit(List<QueryColumnSearch> paramsList) {
        List<String> paramsSqlList = new ArrayList<>();
        for (QueryColumnSearch queryColumnSearch : paramsList) {
            if ("text".equals(queryColumnSearch.getQueryType())) {
                if (StringUtils.isNotEmpty(queryColumnSearch.getQueryValue().toString())) {
                    paramsSqlList.add(queryColumnSearch.getQueryField() + " = '" + queryColumnSearch.getQueryValue().toString() + "'");
                }
            }
            if ("daterange".equals(queryColumnSearch.getQueryType())) {
                if (queryColumnSearch.getQueryValue() != null) {
                    List<String> dateList = (List<String>) queryColumnSearch.getQueryValue();
                    paramsSqlList.add(queryColumnSearch.getQueryField() + " >= '" + dateList.get(0) + " 00:00:00'");
                    paramsSqlList.add("'" + dateList.get(1) + " 23:59:59' >= " + queryColumnSearch.getQueryField());
                }
            }
        }
        return paramsSqlList;
    }

}
