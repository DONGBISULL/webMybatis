package kr.or.ddit.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static EmployeeDAOImpl self;
	private EmployeeDAOImpl() {
		super();
	}
	
	public static EmployeeDAOImpl getInstanse() {
		if(self==null)
			self= new EmployeeDAOImpl();
		return self;
	}
	
	@Override
	public int insertEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeVO> selectEmplyeeList(Map<String, Object> pMap) {
		StringBuffer sql = new StringBuffer();
	sql.append("	SELECT                                                        ");
	sql.append("    EMPNO,    ENAME,    JOB,                                      ");
	sql.append("    MGR,   HIREDATE,    SAL,   COMM,                              ");
	sql.append("    A.DEPTNO,    B.DNAME                                          ");
	sql.append("    , (SELECT DECODE(COUNT(*) ,0,'1' ,'0') FROM EMP C WHERE C.MGR=A.EMPNO )  AS LEAF                                        ");
	sql.append("    FROM   EMP A LEFT OUTER JOIN DEPT B  ON(A.DEPTNO = B.DEPTNO)  "); 
	if(pMap!=null && pMap.containsKey("mgr")) {
		sql.append("    WHERE MGR = ?                                          ");
	}else {
		sql.append("    WHERE MGR IS NULL                                             ");
	}
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				//statement 쿼리가 언제 컴파일 되는지 차이 
				//preparedStatement 쿼리를 변경할 수 없음
				){
			if(pMap!=null && pMap.containsKey("mgr")) {
				pstmt.setInt(1,(Integer)pMap.get("mgr"));
			}
			
			ResultSet rs = pstmt.executeQuery();
			List<EmployeeVO> empList = new ArrayList<>();
			while(rs.next()) {
				EmployeeVO empVO = EmployeeVO.builder()
						.empno(rs.getInt("EMPNO"))
						.ename(rs.getString("ENAME"))
						.job(rs.getString("JOB"))
						.mgr(rs.getInt("MGR"))
						.hiredate(rs.getString("HIREDATE"))
						.sal(rs.getInt("SAL"))
						.comm(rs.getInt("COMM"))
						.deptno(rs.getInt("DEPTNO"))
						.dname(rs.getString("DNAME"))
						.leaf(rs.getBoolean("LEAF"))
						.build();
				
				empList.add(empVO);
				
			}
			
			rs.close();
			return empList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		
		}
	}

	@Override
	public EmployeeVO selectEmplyee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmplyee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmplyee(int empno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
