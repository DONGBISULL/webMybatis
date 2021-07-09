package kr.or.ddit.vo;

import java.io.File;

public class FancyTreeNodeAdapter extends File implements FancyTreeNode {
	
	//private File adaptee; //file이 내가 원하는 속성이 없어서 생성하는 adaptee
	//s나 자체가 File이면 굳이 file 객체 만들 필요없음 
	public FancyTreeNodeAdapter(File adaptee ,String relativePath) {
		super(adaptee.getAbsolutePath());
		this.key = relativePath;
	} 
	private String key ;
	
	@Override
	public String getTitle() {
		return getName();
	}

	@Override
	public boolean isFolder() {
		return isDirectory();
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public int compareTo(File pathname) {
		boolean mine = isDirectory();
		boolean other = pathname.isDirectory();
		if(mine ^ other ) {
			return mine?  -1 :1;
		}else {
			return super.compareTo(pathname);
			
		}
	}

	@Override
	public boolean isLazy() {
		
		return isFolder();
	}
	
	
}
