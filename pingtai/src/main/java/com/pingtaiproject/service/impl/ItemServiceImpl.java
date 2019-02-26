package com.pingtaiproject.service.impl;

import com.pingtaiproject.dao.ItemDOMapper;
import com.pingtaiproject.dao.ItemStockDOMapper;
import com.pingtaiproject.dataobject.ItemDO;
import com.pingtaiproject.dataobject.ItemStockDO;
import com.pingtaiproject.error.BusinessException;
import com.pingtaiproject.error.EmBusinessError;
import com.pingtaiproject.service.ItemService;
import com.pingtaiproject.service.model.ItemModel;
import com.pingtaiproject.validator.ValidationResult;
import com.pingtaiproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel==null) {
            return null;
        }
        ItemDO itemDO=new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());

        return itemDO;
    }

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
        if(itemModel==null) {
            return null;
        }
        ItemStockDO itemStockDO=new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;

    }

    @Override
    @Transactional
    public ItemModel creatItem(ItemModel itemModel) throws BusinessException{

        //校验入参
        ValidationResult result =validator.validate(itemModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }


        //转化itemmodel->dataobject

        ItemDO itemDO=this.convertItemDOFromItemModel(itemModel);
        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO=this.convertItemStockDOFromItemModel(itemModel);

        itemStockDOMapper.insertSelective(itemStockDO);
        //返回创建完成的对象
        return this.genItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel genItemById(Integer id) {

        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }
        //获得库存数量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        return null;
    }
}
