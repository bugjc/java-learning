package com.bugjc.java.blog;

import com.alibaba.fastjson.JSON;

import java.util.UUID;

public class ArticleToJson {

    public static void main(String[] args) {
        Article article = new Article();
        article.set_id(UUID.randomUUID().toString());
        article.set_title("过度拟合");
        article.set_about("数学家告诉你为什么难得糊涂。");
        article.set_openid("oGGcc42M4ZHKacZc3GfTYj9DOmz0");
        article.set_detail_url("https://www.bugjc.com/blog/%E8%BF%87%E6%8B%9F%E5%90%88-over-fitting/");
        System.out.println(JSON.toJSONString(article));
    }
}
