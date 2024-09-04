package cn.ebaize.service.impl;

import cn.ebaize.mapper.BasicMapper;
import cn.ebaize.model.vo.BasicSearchVo;
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
    private BasicMapper basicMapper;

    @Resource
    private ColumnService columnService;

    @Override
    public BasicSearchVo getDynamicTableInfo(String tableName) throws SQLException {
        BasicSearchVo basicSearchVo = new BasicSearchVo();
        basicSearchVo.setTableName(tableName);
        basicSearchVo.setQueryColumnVoList(basicMapper.getQueryColumnInfoList(tableName));
        basicSearchVo.setTableColumnVoList(columnService.getColumnNamesAndComments(tableName));
        return basicSearchVo;
    }

    @Override
    public IPage<Map<String, Object>> getListByPage(Integer current, Integer size, String tableName, List<String> paramsSqlList) {
        IPage<Map<String, Object>> page = new Page<>(current, size);
        List<Map<String, Object>> resultList = basicMapper.getListByPageByTableName(page, tableName, paramsSqlList);
        return page.setRecords(resultList);
    }

    @Override
    public void deleteById(String primaryKeyId, String tableName) {
        basicMapper.delete(primaryKeyId, tableName);
    }

    @Override
    public Boolean updateById(String primaryKeyId, String tableName, Map<String, Object> dataInfo) {
        return columnService.updateDataByDynamic(tableName, primaryKeyId, dataInfo);
    }

}
