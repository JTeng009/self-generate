package ${entity_name};

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

<#-- 排除为空属性 -->
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "${table_annotation!}")
public class ${class_name} implements Serializable {

	<#-- 序列化 -->
 	private static final long serialVersionUID = 1L;
    <#if model_column?exists>
        <#list model_column as model>
    /**
    * ${model.columnComment!}
    */
    @ApiModelProperty("${model.columnComment!}")   
    <#if (model.columnType = 'INTEGER' )>
    private Integer ${model.changeColumnName?uncap_first};
    <#elseif (model.columnType = 'VARCHAR' || model.columnType = 'TEXT' || model.columnType = 'CHAR')>
    private String ${model.changeColumnName?uncap_first};
    <#elseif model.columnType = 'TIMESTAMP'>
    <#-- 格式化时间 -->
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ${model.changeColumnName?uncap_first};
    </#if>
        </#list>
    <#list model_column as model>
    
    <#-- 生成get、set方法 -->
    <#if (model.columnType = 'INT' )>
    public Integer ${"get"+model.changeColumnName?cap_first}(){
    	return ${model.changeColumnName?uncap_first};
    }
    
    public void ${"set"+model.changeColumnName?cap_first}(Integer ${model.changeColumnName?uncap_first}){
    	this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    <#elseif (model.columnType = 'VARCHAR' || model.columnType = 'TEXT' || model.columnType = 'CHAR')>
    public String ${"get"+model.changeColumnName?cap_first}(){
    	return ${model.changeColumnName?uncap_first};
    }
    
    public void ${"set"+model.changeColumnName?cap_first}(String ${model.changeColumnName?uncap_first}){
    	this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    <#elseif model.columnType = 'TIMESTAMP'|| model.columnType = 'DATETIME' >
    public Date ${"get"+model.changeColumnName?cap_first}(){
    	return ${model.changeColumnName?uncap_first};
    }
    
    public void ${"set"+model.changeColumnName?cap_first}(Date ${model.changeColumnName?uncap_first}){
    	this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    </#if>
    	</#list>
    
    <#-- 生成toString方法 -->
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        <#list model_column as model>
        sb.append(", ${model.changeColumnName?uncap_first}=").append(${model.changeColumnName?uncap_first});
        </#list>
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    </#if> 
}