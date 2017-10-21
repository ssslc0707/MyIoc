package ioc.test;

import com.slc.MyIoc.Application.anno.Ioc;

@Ioc
public class House {

	private String name = "ÌìÇÅµ×";

	@Override
	public String toString() {
		return "House [name=" + name + "]";
	}
	
	
}
