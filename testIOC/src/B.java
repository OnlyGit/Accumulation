
public class B extends A{

	String c;  
	  
    public String getC() {  
        return c;  
    }  
  
    public void setC(String c) {  
        this.c = c;  
    }  
  
    String fn() {  
        return this.getA() + this.getB() + c;  
    }  
}
