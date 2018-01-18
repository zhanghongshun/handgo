package com.stackfing.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue
    private Long id;   //id

    @NotNull
    private String name; //产品名

    @NotNull
    private String photo;    //产品图片地址

//    private ProductCategory category;    //产品类型

    @NotNull
    private BigDecimal price; //价格

    @NotNull
    private int quantity;   //供货数量

    private int sold;   //已经出售数量

    @NotNull
    private int status; //产品状态 1 上架 0 下架

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

}
