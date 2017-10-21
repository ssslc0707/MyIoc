package ioc.test;


import com.slc.MyIoc.Application.ApplicationContext;
import com.slc.MyIoc.Application.BeanFactory.BeanFactory;
import com.slc.MyIoc.Application.anno.Ioc;
import com.slc.MyIoc.Application.anno.MyAuto;

@Ioc
public class User {
	
	@MyAuto
	private Super supe;
	@MyAuto
	private Car car;
	@MyAuto
	private House house;
	
	private int age = 10;
	private String name = "slc";
	
	
	@Override
	public String toString() {
		return "User [car=" + car + ", house=" + house + ", age=" + age + ", name=" + name + "]";
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}




	public static void main(String[] args) {
		ApplicationContext applicationContext = new ApplicationContext();
		
		ApplicationContext run = applicationContext.run(User.class);
		
		BeanFactory beanFactory = run.getBeanFactory();
		
		User bean = beanFactory.getBean(User.class);
		
		System.out.println(bean);

	}
}
