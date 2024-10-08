package cn.ebaize.mapper;

import cn.ebaize.model.vo.QueryColumnVo;
import cn.ebaize.sql.SqlProvider;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author WEI
 */
public interface OperationBasicMapper {

    /**
     * Get table dynamic query conditions
     *
     * @param tableName
     * @return
     */
    @Select("select query_field,query_name,query_type,query_sort from std_table_config where table_name = #{tableName} and valid = 'Y' order by query_sort asc,create_time desc")
    List<QueryColumnVo> getQueryColumnInfoList(@Param("tableName") String tableName);

    /**
     * Delete data from dynamic table
     *
     * @param tableName
     * @param id
     */
    @Delete("delete from ${tableName} where id=#{id}")
    void delete(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * Query Data from Dynamic Tables
     *
     * @param page
     * @param tableName
     * @param paramsSqlList
     * @return
     */
    @SelectProvider(type = SqlProvider.class, method = "selectFromTableWithConditions")
    List<Map<String, Object>> getListByPageByTableName(@Param("page") IPage page,
                                                       @Param("tableName") String tableName,
                                                       @Param("paramsSqlList") List<String> paramsSqlList);

}
