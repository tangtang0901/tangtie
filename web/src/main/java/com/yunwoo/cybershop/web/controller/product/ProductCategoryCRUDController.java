package com.yunwoo.cybershop.web.controller.product;

import com.alibaba.fastjson.JSONArray;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.utils.PictureUtils;
import com.yunwoo.cybershop.web.controller.base.WebBaseController;
import com.yunwoo.cybershop.web.service.product.ProductCategoryService;
import com.yunwoo.cybershop.web.vo.product.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/1000/productCategory")
public class ProductCategoryCRUDController extends WebBaseController{

      @Autowired
      private ProductCategoryService productCategoryService;




    //跳转到展示商品列表页面
    @RequestMapping("/productCategoryList")
    public  String productCategoryList() {
        return "/module/productCategory/productCategoryList";
    }

    //查询商品列表节点(采用递归方式)
    @RequestMapping("/productCategoryqueryList")
    @ResponseBody
    public  Object getCategoryTree() {
        ProductCategoryDTO root = new ProductCategoryDTO();
        root.setId(-1);
        queryChildTree(root);
        JSONArray jsonArray = null ;
        jsonArray = (JSONArray) JSONArray.toJSON(root.getChildren());
        return   jsonArray;
    }
    private void queryChildTree(ProductCategoryDTO tree) {
        // 查询子节点
        List<ProductCategoryDTO> children = productCategoryService.getNodesById(tree.getId());
        if(children !=null){
            for ( ProductCategoryDTO childtree : children ) {
                queryChildTree(childtree);
            }
            // 组合父子节点的关系
            tree.setChildren(children);
        }
    }

    //增加商品列表功能需要获取的参数
    @RequestMapping("/productCategoryAdd")
    public String productCategoryAdd(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "level",required = false) Integer level,
                             @RequestParam(value = "status",required = false) Integer status, Map map){
           map.put("pid",id);
           map.put("level",level);
           map.put("status",status);
        return "module/productCategory/productCategoryAdd";
    }


    //获取含有商品列表信息的对象
    @RequestMapping("/productCategoryAddForm")
    @ResponseBody
    public String productCategoryAddForm(@RequestBody ProductCategoryVO productCategoryVO){
        if(productCategoryVO==null){
             return  String.valueOf(false);
          }
        return String.valueOf(  productCategoryService.productCategoryAddList(BeanConverter.to(productCategoryVO,ProductCategoryDTO.class)));
    }


    //修改商品列表功能需要获取的参数传入到页面
    @RequestMapping("/productCategoryEdit")
    public String productCategoryEdit(@RequestParam(value = "id",required = false) Integer id,
                                      @RequestParam(value = "name",required = false) String name,
                                      @RequestParam(value = "pid",required = false)  Integer pid,
                                      @RequestParam(value = "level",required = false) Integer level,
                                      @RequestParam(value = "status",required = false)  Integer status,
                                      Map map){
        map.put("id",id);
        map.put("name",name);
        map.put("pid",pid);
        map.put("level",level);
        map.put("status",status);
        return "module/productCategory/productCategoryEdit";
    }

    //修改商品列表功能根据查询ID列表信息回显到页面
    @RequestMapping("/getById")
    @ResponseBody
    public ProductCategoryVO getById(Integer id){
        ProductCategoryDTO productCategoryDTO = productCategoryService.getById(id);
       return dtoTvo(productCategoryDTO);
    }

    //商品删除功能，获取ID
    @RequestMapping("/productCategoryDelete")
    @ResponseBody
    public String   productCategoryDelete(@RequestParam(value = "id",required = false) Integer id,Map map){
         //根据ID查询子节点
        List<ProductCategoryDTO> children = productCategoryService.getNodesById(id);

            if(children.size()!=0){
                return String.valueOf(false);
            }
            productCategoryService.deleteCategoryById(id);
           return String.valueOf(true);
    }

    //商品列表修改方法
    @RequestMapping("/CategoryEdit")
    @ResponseBody
    public  String productCategoryUpdate(@RequestBody ProductCategoryVO productCategoryVO ){
        if(productCategoryVO==null){
            return  String.valueOf(false);
        }
        return String.valueOf(productCategoryService.productCategoryUpdate(BeanConverter.to(productCategoryVO,ProductCategoryDTO.class)));
    }



    private ProductCategoryVO dtoTvo(ProductCategoryDTO productCategoryDTO){
        ProductCategoryVO productCategoryVO = null;
        if(productCategoryDTO != null){
            productCategoryVO = BeanConverter.to(productCategoryDTO,ProductCategoryVO.class);
            productCategoryVO.setPicIdUrl(PictureUtils.assemblePicUrl(productCategoryDTO.getPicIdUrl()));
        }
        return productCategoryVO;
    }
}
