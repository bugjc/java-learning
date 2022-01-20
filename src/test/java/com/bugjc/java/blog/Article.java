package com.bugjc.java.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String _id;
    private String _about;
    private String _detail_url;
    private String _openid;
    private String _title;
}
