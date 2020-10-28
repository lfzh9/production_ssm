package com.megagao.production.ssm.controller;

import static com.megagao.production.ssm.common.Constants.VALIDATE_CODE;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.common.CommonController;
import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.LoginLogService;
import com.megagao.production.ssm.util.CollectionsFactory;

/**
  * created on 2016年9月6日 
  *
  * @author  megagao
  */
@Controller
public class LoginController extends CommonController{
	public static String a;
	@Autowired
	private LoginLogService loginLogService;
	
	@RequestMapping("/loginLog/find")
	public String find() throws Exception{
		return "loginLog_list";                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping("/loginLog/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = loginLogService.getList(page, rows);
		return result;
	}
	
	
	
	
	@RequestMapping(value="/loginLog/delete")
	@ResponseBody
	private CustomResult delete(int id) throws Exception {
		CustomResult result = loginLogService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/loginLog/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(int[] ids) throws Exception {
		CustomResult result = loginLogService.deleteBatch(ids);
		log("删除登录日志：{id:"+ids+"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/loginLog/search_loginLog_by_id")
	@ResponseBody
	public EUDataGridResult searchLoginLogById(Integer page, Integer rows, int searchValue) throws Exception{
		EUDataGridResult result = loginLogService.searchLoginLogById(page, rows, searchValue);
		log("查询登录日志：{id:"+searchValue+"}");
		return result;
	}
	//根据日期查找
	@RequestMapping("/loginLog/search_loginLog_by_date")
	@ResponseBody
	public EUDataGridResult searchLoginLogByDate(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = loginLogService.searchLoginLogByDate(page, rows, searchValue);
		log("查询登录日志：{日期:"+searchValue+"}");
		return result;
	}
	//根据登录用户查找
	@RequestMapping("/loginLog/search_loginLog_by_name")
	@ResponseBody
	public EUDataGridResult searchLoginLogByName(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8"); 
		EUDataGridResult result = loginLogService.searchLoginLogByName(page, rows, searchValue);
		log("查询登录日志：{登录用户:"+searchValue+"}");
		return result;
	}
	
	
	
	
	
	
	
	

	/**
	 * shiro ajax登录 
	 */
	@RequestMapping(value = "/ajaxLogin")
	@ResponseBody
	public Map<String,Object> ajaxLogin(@RequestParam String username,
													  @RequestParam String password,
													  @RequestParam(required=false) String randomcode,
													  HttpSession session,HttpServletRequest request) throws Exception{
	    
		Map<String,Object> map = CollectionsFactory.newHashMap();
		
		if(randomcode !=null && !randomcode.equals("")){
	    	//取出session的验证码（正确的验证码）
	    	String validateCode = (String)session.getAttribute(VALIDATE_CODE);
			//页面中输入的验证和session中的验证进行对比 
			if(validateCode!=null && !randomcode.equals(validateCode)){
				//如果校验失败，将验证码错误失败信息放入map中
				map.put("msg", "randomcode_error");
				//直接返回，不再校验账号和密码 
				return map; 
			}
	    }
		Subject currentUser = SecurityUtils.getSubject();
	    if (!currentUser.isAuthenticated()) {
	    	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	        try{
	        	session.setAttribute("login_user", username);
	            currentUser.login(token);
	            
	            //数据库添加登录日志
	            LoginLog loginLog = new LoginLog();
	            loginLog.setDate(new Date());
	            loginLog.setIp(request.getRemoteAddr());
	            loginLog.setName(username);
	            loginLogService.insert(loginLog);
	            
	            a=username;
	            
	        }catch(UnknownAccountException ex){
	        	map.put("msg", "account_error");
	        }catch(IncorrectCredentialsException ex){
	        	map.put("msg", "password_error");
	        }catch(AuthenticationException ex){
	        	map.put("msg", "authentication_error");
	        }
	    }
	    //返回json数据
	    return map;
	}
}
