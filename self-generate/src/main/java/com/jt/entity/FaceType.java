package com.jt.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "头像挂件")
public class FaceType implements Serializable {

 	private static final long serialVersionUID = 1L;
    /**
    * 
    */
    @ApiModelProperty("")   
    private Integer id;
    /**
    * 分类名称
    */
    @ApiModelProperty("分类名称")   
    private String name;
    /**
    * 0/表示通用 1/表示微信 2/表示百度
    */
    @ApiModelProperty("0/表示通用 1/表示微信 2/表示百度")   
    private Integer type;
    /**
    * 
    */
    @ApiModelProperty("")   
    private Integer sort;
    /**
    * 启用状态 t/启用 f/停用
    */
    @ApiModelProperty("启用状态 t/启用 f/停用")   
    private String status;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")   
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")   
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;
    
    
    public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    
    
    public String getStatus(){
    	return status;
    }
    
    public void setStatus(String status){
    	this.status = status;
    }
    
    public Date getCreateAt(){
    	return createAt;
    }
    
    public void setCreateAt(Date createAt){
    	this.createAt = createAt;
    }
    
    public Date getUpdateAt(){
    	return updateAt;
    }
    
    public void setUpdateAt(Date updateAt){
    	this.updateAt = updateAt;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}