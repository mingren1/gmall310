package com.athome.gmall.gmallgenerator.dao;

import com.athome.gmall.gmallgenerator.domain.BaseAttrInfo;
import com.athome.gmall.gmallgenerator.domain.BaseAttrInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseAttrInfoMapper {
    int countByExample(BaseAttrInfoExample example);

    int deleteByExample(BaseAttrInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseAttrInfo record);

    int insertSelective(BaseAttrInfo record);

    List<BaseAttrInfo> selectByExample(BaseAttrInfoExample example);

    BaseAttrInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseAttrInfo record, @Param("example") BaseAttrInfoExample example);

    int updateByExample(@Param("record") BaseAttrInfo record, @Param("example") BaseAttrInfoExample example);

    int updateByPrimaryKeySelective(BaseAttrInfo record);

    int updateByPrimaryKey(BaseAttrInfo record);
}