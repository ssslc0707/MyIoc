package ioc.test;

import com.slc.MyIoc.Application.anno.Ioc;

@Ioc
public class House {

	private String name = "���ŵ�";

	@Override
	public String toString() {
		return "House [name=" + name + "]";
	}
	
	
}
