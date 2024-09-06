package cn.ebaize.service;

import cn.ebaize.model.vo.QueryColumnVo;
import cn.ebaize.model.vo.TableColumnVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: WEI
 */
public interface OperationBasicService {

    /**
     * 动态表的字段信息
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<TableColumnVo> getDynamicTableInfo(String tableName) throws SQLException;

    /**
     * 动态的查询信息
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<QueryColumnVo> getDynamicSearchInfo(String tableName);

    /**
     * 动态表列表数据【分页】
     *
     * @param current
     * @param size
     * @param tableName
     * @param paramsSqlList
     * @return
     */
    IPage<Map<String, Object>> getListByPage(Integer current, Integer size, String tableName, List<String> paramsSqlList);

    /**
     * 动态表列表数据【分页、排序】
     *
     * @param current
     * @param size
     * @param tableName
     * @param paramsSqlList
     * @return
     */
    IPage<Map<String, Object>> getListByPage(Integer current, Integer size, String tableName, List<String> paramsSqlList, String sortStr);

    /**
     * 动态删除表的数据
     *
     * @param primaryKeyId
     * @param tableName
     */
    void deleteById(String primaryKeyId, String tableName);

    /**
     * 动态更新表的数据
     *
     * @param primaryKeyId
     * @param tableName
     * @param dataInfo
     * @return
     */
    Boolean updateById(String primaryKeyId, String tableName, Map<String, Object> dataInfo);

}
