package com.megagao.production.ssm.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.megagao.production.ssm.controller.LoginController;
import com.megagao.production.ssm.domain.OperationLog;
import com.megagao.production.ssm.service.OperationLogService;
@Controller
public class CommonController {
	@Autowired
	private OperationLogService operationLogService ;
	
	public  void log(String desc){
		try {
			OperationLog operationLog = new OperationLog();
			operationLog.setDate(new Date());
			operationLog.setDesc(desc);
			operationLog.setName(LoginController.a);
			operationLogService.insert(operationLog);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
