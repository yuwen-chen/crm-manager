package com.crm.manager.send.mail.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.crm.manager.send.mail.domain.MailTemplate;

@Mapper
public interface IMailTemplateDao{
	
	/**
     * 插入邮件模板
     *
     * @param mailTemplate
     * @return
     */
	@Insert("<script>" +
		"INSERT INTO t_mail_template (" +
		"	id," +
		"	template_id, " + 
		"	description, "+
		"	template_subject, " +
		"	template_content, " + 
		"	status, "+
		"	create_time, " +
		"	update_time " + 
		" ) values ( " +
		"	#{id}, " + 
		"	#{templateId}, " +
		"	#{description}, "+
		"	#{templateSubject}, " +
		"	#{templateContent}, " +
		"	#{status}, " +
		"	now(), " +
		"	now()" +
		" )" +
		"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMailTemplate(MailTemplate mailTemplate);
    
    /**
     * 查询邮件模板信息
     */
    @Select("<script>" +
	    "SELECT * FROM t_mail_template " +
	    "where template_id = #{templateId} " +
        "and status = '1' "+
	    "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "templateId", column = "template_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "templateSubject", column = "template_subject"),
            @Result(property = "templateContent", column = "template_content"),
            @Result(property = "status", column = "status")
    })
    public MailTemplate queryMailTemplateByTemplateId(@Param("templateId")String templateId);

}
