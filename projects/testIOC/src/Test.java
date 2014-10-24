import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		B bclass = (B)ac.getBean("bean2");  
        String res = bclass.fn();  
        System.out.println(res); 
	}
}
