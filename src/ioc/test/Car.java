package ioc.test;

import com.slc.MyIoc.Application.anno.Ioc;

@Ioc
public class Car implements Super{

	private String name = "��";

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
}
