package com.example.test.hutool;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hawk
 * @package com.example.test.hutool
 * @desc
 * @date 2021/1/12
 */
public class Test {
    public static void main(String[] args) {
        List<Vo> list = new ArrayList<>();
        Vo o = new Vo();
        o.setSkuAttr("attr");
        o.setCid("cid");
        o.setQuantity(15);
        o.setProductId("productId");
        list.add(o);
        list.add(o);
        System.out.println(
                HttpRequest.post("127.0.0.1:39616/mallv2/cart/redis_test")
                        .header("Content-Type","application/json")
                        .body(JSONUtil.toJsonStr(list))
                        .execute()
                        .body()
        );
        // Map<String, Object> param = new HashMap<>();
        // param.put("skuId", "2018-10-10 09:42:02");
        // System.out.println(HttpUtil.post("127.0.0.1:39616/mallv2/cart/redis_test", param));
    }
}
class Vo{
    private String skuId;
    /**
     * 商品类目id
     */
    private String cid;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品规格属性
     */
    private String skuAttr;
    /**
     * 商品id，即spuId
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer quantity;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

