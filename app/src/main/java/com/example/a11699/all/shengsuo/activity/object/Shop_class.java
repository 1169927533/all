package com.example.a11699.all.shengsuo.activity.object;

/**
 * 作者：余智强
 * 2019/4/17
 */
public class Shop_class {
    private String shop_image_url;//图片链接
    private String shop_brand;//商品品牌
    private String shop_season;//商品季节

    public Shop_class() {
    }

    public Shop_class(String shop_image_url, String shop_brand, String shop_season) {
        this.shop_image_url = shop_image_url;
        this.shop_brand = shop_brand;
        this.shop_season = shop_season;
    }

    public String getShop_image_url() {
        return shop_image_url;
    }

    public void setShop_image_url(String shop_image_url) {
        this.shop_image_url = shop_image_url;
    }

    public String getShop_brand() {
        return shop_brand;
    }

    public void setShop_brand(String shop_brand) {
        this.shop_brand = shop_brand;
    }

    public String getShop_season() {
        return shop_season;
    }

    public void setShop_season(String shop_season) {
        this.shop_season = shop_season;
    }
}
