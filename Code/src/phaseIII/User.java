/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phaseIII;

/**
 *
 * @author WYF
 */
public class User {
    private String u_Username;
    private String u_Email;
    private String u_Password;
    private String u_Usertype;
    public User(String username, String email, String password, String usertype){
		u_Username = username;
		u_Email= email;
		u_Password = password;
		u_Usertype = usertype;	
	}
    public void setUsername(String username){u_Username = username;}
    public void setEmail(String email){u_Email = email;}
    public void setPassword(String password){u_Password = password;}
    public void setUsertype(String usertype){u_Usertype = usertype;}
    public String getUsername(){return u_Username;}
    public String getEmail(){return u_Email;}
    public String getPassword(){return u_Password;}
    public String getUsertype(){return u_Usertype;}
}