package com.ethan.mall.common.domain;

import lombok.Data;

/**
 * @author zhenghui
 * @name Oauth2Token
 * @Description oauth2 token实体
 * @Date 2022/3/12
 */
@Data
public class Oauth2Token {
    private String tokenHead;
    private String token;
    private String refreshToken;
    private Integer expiresIn;
}
