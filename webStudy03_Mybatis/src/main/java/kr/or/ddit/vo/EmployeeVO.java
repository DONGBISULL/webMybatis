package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 직원 관리 Domain Layer
 *
 */
/*
@Getter
@Setter 
//@ToString(exclude="test")//exclude 제외할 조건
*/
 
@Builder //builder 패턴
@EqualsAndHashCode(of="empno")//이것만 사용해서 비교한다는 조건
@Data//기본적으로 만든다 ==> 모든 프로퍼티를 같이 비교
//컴파일까지 자기가 해줌
public class EmployeeVO implements Serializable {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private String hiredate;
	private Integer sal;
	private Integer comm;
	private Integer deptno;
	private String dname;
	private boolean leaf;
	
	
 
	
}
