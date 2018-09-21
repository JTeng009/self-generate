<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${entity_name+"."+class_name}" >
  <#if model_column?exists>
  <#-- 生成BaseResultMap -->
  <resultMap id="BaseResultMap" type="${entity_name}.${class_name}">
  		 <#list model_column as model>
  	<#-- 主键输出 -->
  	<#if (model.columnName = 'id' )>
  	<id column="id" property="id" jdbcType="INTEGER" />
  	<#else>
  	<result column="${model.columnName}" property="${model.changeColumnName?uncap_first}" jdbcType="${model.columnType}"/>
 	</#if>
  		  </#list>
  </resultMap>
  
  <#-- 生成Base_Column_List -->
  <sql id="Base_Column_List">	
  	<#list model_column as model>${model.columnName}<#if (model_column?size - 1 != model_index)>, </#if></#list>
  </sql>
  
  <#-- 生成Example_Where_Clause -->
  <sql id="Example_Where_Clause">
	<where>
	<#list model_column as model>
	  <if test="${model.changeColumnName?uncap_first} != null">
		and ${model.columnName} = ${r"#{"}${model.columnName},jdbcType=${model.columnType}}
	  </if>
	</#list>
	</where>
  </sql>
  
  <select id="selectByExample" resultMap="BaseResultMap" parameterType="java.util.Map">
  	SELECT 
  	<include refid="Base_Column_List"/>
  	FROM ${table_name}
  	<include refid="Example_Where_Clause"/>
  	<if test="_BY != null" >
      ORDER BY ${r"#{"}_BY,jdbcType=VARCHAR}
    </if>
    <if test="_LM != null" >
      LIMIT ${r"#{"}_LM,jdbcType=INTEGER}
    </if>
    <if test="_ST != null" >
      OFFSET ${r"#{"}_ST,jdbcType=INTEGER}
    </if>
  </select>
  
  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Integer">
  	SELECT
  	<include refid="Base_Column_List"/>
  	FROM ${table_name}
  	WHERE id = ${r"#{"}id,jdbcType=INTEGER}
  </select>
  
  <select id="countByExample" resultMap="java.lang.Integer" parameterType="java.util.Map">
  	SELECT COUNT(*) FROM ${table_name}
  	<include refid="Example_Where_Clause"/>
  </select>
  
  <insert id="insert" parameterType="${entity_name}.${class_name}">
    <#-- 换行输出，每5个一行 -->
  	INSERT INTO ${table_name} (<#list model_column as model>${model.columnName}<#if (model_column?size - 1 != model_index)><#if (model_index == 4 || (model_index > 5 && model_index % 5 == 0) )>,${"\n"}  	<#else>, </#if></#if></#list>)
  	<#-- 换行输出，每3个一行 -->
  	VALUES (<#list model_column as model>${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}}<#if (model_column?size - 1 != model_index)><#if (model_index == 2 || (model_index > 3 && (model_index + 1) % 3 == 0) )>,${"\n"}  	<#else>, </#if></#if></#list>)
  </insert>

  <insert id="insertSelective" parameterType="${entity_name}.${class_name}">
  	INSERT INTO ${table_name}
    <trim prefix="(" suffix=")" suffixOverrides="," >
    <#list model_column as model>
      <if test="${model.changeColumnName?uncap_first} != null" >
        ${model.columnName},
      </if>
    </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
    <#list model_column as model>
      <if test="${model.changeColumnName?uncap_first} != null" >
        ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
      </if>
    </#list>
    </trim>
  </insert>
  
  <update id="updateByPrimaryKey" parameterType="${entity_name}.${class_name}">
  	UPDATE ${table_name} SET 
  	<#list model_column as model>
  	  ${model.columnName} = ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}}<#if (model_column?size - 1 != model_index)>, </#if>
  	</#list>
    WHERE id = ${r"#{"}id,jdbcType=INTEGER} 
  </update>
  
  <update id="updateByPrimaryKeySelective" parameterType="${entity_name}.${class_name}" >
    UPDATE ${table_name}
    <set>
    <#list model_column as model>
      <if test="${model.changeColumnName?uncap_first} != null" >
        ${model.columnName} = ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
      </if>
    </#list>
    </set>
    WHERE id = ${r"#{"}id,jdbcType=INTEGER} 
  </update>
  
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer" >
    DELETE FROM ${table_name}
    WHERE id = ${r"#{"}id,jdbcType=INTEGER} 
  </delete>
  </#if>
  
</mapper>