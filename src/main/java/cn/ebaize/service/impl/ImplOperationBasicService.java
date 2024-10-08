package cn.ebaize.service.impl;

import cn.ebaize.mapper.OperationBasicMapper;
import cn.ebaize.model.vo.QueryColumnVo;
import cn.ebaize.model.vo.TableColumnVo;
import cn.ebaize.service.ColumnService;
import cn.ebaize.service.OperationBasicService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: WEI
 */
@Service
public class ImplOperationBasicService implements OperationBasicService {

    @Resource
    private OperationBasicMapper operationBasicMapper;

    @Resource
    private ColumnService columnService;

    @Override
    public List<TableColumnVo> getDynamicTableInfo(String tableName) throws SQLException {
        return columnService.getColumnNamesAndComments(tableName);
    }

    @Override
    public List<QueryColumnVo> getDynamicSearchInfo(String tableName) {
        return operationBasicMapper.getQueryColumnInfoList(tableName);
    }

    @Override
    public IPage<Map<String, Object>> getListByPage(Integer current, Integer size, String tableName, List<String> paramsSqlList) {
        IPage<Map<String, Object>> page = new Page<>(current, size);
        List<Map<String, Object>> resultList = operationBasicMapper.getListByPageByTableName(page, tableName, paramsSqlList);
        return page.setRecords(resultList);
    }

    @Override
    public void deleteById(String primaryKeyId, String tableName) {
        operationBasicMapper.delete(primaryKeyId, tableName);
    }

    @Override
    public Boolean updateById(String primaryKeyId, String tableName, Map<String, Object> dataInfo) {
        return columnService.updateDataByDynamic(primaryKeyId, tableName, dataInfo);
    }

}
