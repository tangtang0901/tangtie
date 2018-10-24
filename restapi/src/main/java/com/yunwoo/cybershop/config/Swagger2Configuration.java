package com.yunwoo.cybershop.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	@Value("${swagger.api.title}")
	private String API_TITLE;
	@Value("${swagger.api.termsOfServiceUrl}")
	private String TERMS_OF_SERVICE_URL;
	@Value("${swagger.api.contact.name}")
	private String CONTACT_NAME;
	@Value("${swagger.api.contact.url}")
	private String CONTACT_URL;
	@Value("${swagger.api.contact.email}")
	private String CONTACT_EMAIL;

	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket accessToken() {
		//可以添加多个header或参数
		ParameterBuilder cusSession = new ParameterBuilder();
		cusSession
				//参数类型支持header, cookie, body, query etc
				.parameterType("header")
				//参数名
				.name("cusSession")
				//默认值
				.defaultValue("")
				.description("会话标识")
				//指定参数值的类型
				.modelRef(new ModelRef("string"))
				//非必需，这里是全局配置，然而在登陆的时候是不用验证的
				.required(false).build();
		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(cusSession.build());

		ParameterBuilder os = new ParameterBuilder();
		os
				//参数类型支持header, cookie, body, query etc
				.parameterType("header")
				//参数名
				.name("os")
				//默认值
				.defaultValue("")
				.description("设备类型，如 IOS、ANDROID")
				//指定参数值的类型
				.modelRef(new ModelRef("string"))
				//非必需，这里是全局配置，然而在登陆的时候是不用验证的
				.required(false).build();
		aParameters.add(os.build());

		ParameterBuilder unique = new ParameterBuilder();
		unique
				//参数类型支持header, cookie, body, query etc
				.parameterType("header")
				//参数名
				.name("unique")
				//默认值
				.defaultValue("")
				.description("设备码")
				//指定参数值的类型
				.modelRef(new ModelRef("string"))
				//非必需，这里是全局配置，然而在登陆的时候是不用验证的
				.required(false).build();
		aParameters.add(unique.build());

		ParameterBuilder memberId = new ParameterBuilder();
		memberId
				//参数类型支持header, cookie, body, query etc
				.parameterType("header")
				//参数名
				.name("memberId")
				//默认值
				.defaultValue("")
				.description("用户ID")
				//指定参数值的类型
				.modelRef(new ModelRef("int"))
				//非必需，这里是全局配置，然而在登陆的时候是不用验证的
				.required(false).build();
		aParameters.add(memberId.build());

		return new Docket(DocumentationType.SWAGGER_2)
				// 定义组
				.groupName("api")
				// 选择那些路径和api会生成document
				.select()
				// 拦截的包路径
				.apis(RequestHandlerSelectors.basePackage("com.yunwoo.cybershop.controller"))
				// 拦截的接口路径
				.paths(regex("/api/.*"))
				// 创建
				.build()
				// 配置说明
				.apiInfo(apiInfo()).globalOperationParameters(aParameters);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 标题
				.title("云乌科技")
				// 描述
				.description("接口文档 \n\n\n\n" +
						"公共返回码 code ：\n\n" +
						"00:成功\n\n" +
						"01:异常\n\n"+
						"02:不合法\n\n"+
						"03:未登录\n\n"+
						"提示信息 msg；\n\n"+
						"具体数据 data；\n\n"+
						"os为系统，如 ANDROID IOS WAP，每次必须在header中携带\n\n"+
						"unique为设备号，没有则自定义生成，每次必须在header中携带\n\n"+
						"cusSession为会话ID，登录后返回，以后每个请求header中都携带\n\n"+
						"cusSession为会话ID，登录后返回，以后每个请求header中都携带\n\n"+
						"新增数据用 POST请求\n\n"+
						"修改数据用 PUT请求\n\n"+
						"删除数据用 DELETE请求\n\n"+
						"获取数据用 GET请求\n\n")
				.termsOfServiceUrl("wwwh.bjyunwu.com")
				// 联系
				.contact(new Contact("云乌科技", "www.bjyunwu.com", "bjyunwu@bjyunwu.com"))
				// 版本
				.version("1.0")
				.build();
	}
}
