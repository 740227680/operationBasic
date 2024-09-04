package cn.ebaize.service;

import cn.ebaize.model.vo.BasicSearchVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: WEI
 */
public interface OperationBasicService {

    /**
     * Get information about dynamic tables
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    BasicSearchVo getDynamicTableInfo(String tableName) throws SQLException;

    /**
     * Get list of dynamic tables based on parameters
     *
     * @param current
     * @param size
     * @param tableName
     * @param paramsSqlList
     * @return
     */
    IPage<Map<String, Object>> getListByPage(Integer current, Integer size, String tableName, List<String> paramsSqlList);

    /**
     * Delete data from dynamic table
     *
     * @param primaryKeyId
     * @param tableName
     */
    void deleteById(String primaryKeyId, String tableName);

    /**
     * Update the dynamic table
     *
     * @param primaryKeyId
     * @param tableName
     * @param dataInfo
     * @return
     */
    Boolean updateById(String primaryKeyId, String tableName, Map<String, Object> dataInfo);

}
