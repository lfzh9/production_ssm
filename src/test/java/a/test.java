package a;

import java.lang.reflect.Method;

import org.junit.Test;

import com.megagao.production.ssm.controller.BranchController;
import com.megagao.production.ssm.domain.Branch;

public class test {
	@Test
	public void testLog() throws Exception{
		BranchController branchController = new BranchController();
		Class c = branchController.getClass();
		Method method = c.getDeclaredMethod("insert", Branch.class);
		method.setAccessible(true);
		Object object = c.newInstance();
		Object result = method.invoke(object, new Branch());
		
		System.out.println(result);	
			
	}
	
}
