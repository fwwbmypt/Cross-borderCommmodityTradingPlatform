package com.pingtaiproject.controller;

import com.pingtaiproject.error.BusinessException;
import com.pingtaiproject.response.CommonReturnType;
import com.pingtaiproject.service.ItemService;
import com.pingtaiproject.service.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/item")
@CrossOrigin(origins ={"*"},allowCredentials = "true")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    //创建商品的controller
                          public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        //封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelForReturn = itemService.creatItem(itemModel);

        if(itemModel==null){
            return null;
        }
        return CommonReturnType.create(itemModel);
    }

    //商品详情页浏览
    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){

        ItemModel itemModel=itemService.genItemById(id);
        return CommonReturnType.create(itemModel);

    }


}
