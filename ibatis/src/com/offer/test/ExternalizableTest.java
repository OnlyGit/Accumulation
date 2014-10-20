package com.offer.test;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Alexia
 * @date 2013-10-15
 *
 */
public class ExternalizableTest implements Serializable {

	public static void main(String[] args) {
	        
        User user = new User();
        user.setUsername("Alexia");
	    user.setPasswd("123456");
	    user.setId(1);
	    
	    System.out.println("read before Serializable: ");
	    System.out.println("id: " + user.getId());
	    System.out.println("username: " + user.getUsername());
	    System.err.println("password: " + user.getPasswd());
	    
	    try {
	        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("E:/user.txt"));
	        os.writeObject(user); // 将User对象写进文件
	        os.flush();
	        os.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	        ObjectInputStream is = new ObjectInputStream(new FileInputStream("E:/user.txt"));
	        user = (User) is.readObject(); // 从流中读取User的数据
	        is.close();
	        
	        System.out.println("\nread after Serializable: ");
	        System.out.println("id: " + user.getId());
	        System.out.println("username: " + user.getUsername());
	        System.err.println("password: " + user.getPasswd());
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
    }
}

class Parent {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

class User extends Parent implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;  
    
    private String username;
    private transient String passwd;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}