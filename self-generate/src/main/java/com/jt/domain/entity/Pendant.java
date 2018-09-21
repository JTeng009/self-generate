package com.jt.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "质量问题")
public class Pendant implements Serializable {

 	private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ApiModelProperty("主键")   
    private Integer id;
    /**
    * 挂件标题
    */
    @ApiModelProperty("挂件标题")   
    private String title;
    /**
    * 挂件封面
    */
    @ApiModelProperty("挂件封面")   
    private String cover;

    /**
     *    
     */
    @ApiModelProperty("挂件分类")   
    private String type;
    /**
    * 挂件位置
    */
    @ApiModelProperty("挂件位置")   
    private String position;
    /**
    * 挂件需求
    */
    @ApiModelProperty("挂件需求")   
    private String need;
    /**
    * 权重
    */
    @ApiModelProperty("权重")   
    private Integer sort;
    /**
    * 启用状态：t/启用，f/停用
    */
    @ApiModelProperty("启用状态：t/启用，f/停用")   
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
    
    
    public String getTitle(){
    	return title;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getCover(){
    	return cover;
    }
    
    public void setCover(String cover){
    	this.cover = cover;
    }
    
    public String getType(){
    	return type;
    }
    
    public void setType(String type){
    	this.type = type;
    }
    
    public String getPosition(){
    	return position;
    }
    
    public void setPosition(String position){
    	this.position = position;
    }
    
    public String getNeed(){
    	return need;
    }
    
    public void setNeed(String need){
    	this.need = need;
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
        sb.append(", title=").append(title);
        sb.append(", cover=").append(cover);
        sb.append(", type=").append(type);
        sb.append(", position=").append(position);
        sb.append(", need=").append(need);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}