package com.example.onlineretailers.Bean;

import java.util.List;

public class DetailData {


    private int goodsId;
    private String name;
    private Specification Specification;
    private String content;
    private String imageUrl;
    private String origin_price;
    private String present_price;
    private List<String> banners;
    public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }
        public int getGoodsId() {
            return goodsId;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setSpecification(Specification Specification) {
            this.Specification = Specification;
        }
        public Specification getSpecification() {
            return Specification;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public void setOrigin_price(String origin_price) {
            this.origin_price = origin_price;
        }
        public String getOrigin_price() {
            return origin_price;
        }

        public void setPresent_price(String present_price) {
            this.present_price = present_price;
        }
        public String getPresent_price() {
            return present_price;
        }

        public void setBanners(List<String> banners) {
            this.banners = banners;
        }
        public List<String> getBanners() {
            return banners;
        }

}
