package kr.or.ddit.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.emp.dao.EmployeeDAO;
import kr.or.ddit.emp.dao.EmployeeDAOImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.EmployeeWrapper;

public class EmployeeServiceImpl implements EmployeeService {
	
	//객체의 상태가 없어서 싱글톤 사용 가능 ==> 전역 변수의 상태가 변하지 않아서 싱글톤 사용 가능
	
	private EmployeeServiceImpl() {}

	private static EmployeeServiceImpl self;
	
	public static  EmployeeServiceImpl getInstance() {
		if(self==null)
			 self = new EmployeeServiceImpl();
		return self;
	}
	
	private EmployeeDAO empDAO = EmployeeDAOImpl.getInstanse();
	
	@Override
	public ServiceResult createEmloyee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeWrapper> retrieveEmployeeList(Map<String, Object> pMap) {
		List<EmployeeVO> empList = 	empDAO.selectEmplyeeList(pMap);
		List<EmployeeWrapper> wrapperList = new ArrayList<>();
		empList.forEach((employee)->{wrapperList.add(new EmployeeWrapper(employee));}); //forEach 하나씩 접근하겠다
		return  wrapperList;
	}

	@Override
	public EmployeeVO retrieveEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

}
