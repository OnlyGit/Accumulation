package com.validation.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Validations(requiredStrings={
        @RequiredStringValidator(fieldName="userName",message="用户名不能为空！"),
        @RequiredStringValidator(fieldName="userName",message="密码不能为空！")
    }
)
public class SimpleAnnotationAction extends ActionSupport {

	@Validations(requiredFields={
            @RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName="userName",message="用户名不能为null！！")
        }
    )
	public String execute() throws Exception {
		return super.execute();
	}

	public String login(){
        return "success";
    }
	
	public String login2(){
        return "success";
    }
	
	public String login3(){
        return "success";
    }
	
	public String login5(){
        return "success";
    }
	
	
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
