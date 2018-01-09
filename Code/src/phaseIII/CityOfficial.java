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
public class CityOfficial extends User {
    private String c_Title;
    private Boolean c_Approved;
    private String c_City;
    private String c_State;
    public CityOfficial(String Username, String Email, String Password, String Usertype,String title,Boolean approved,String city,String state) {
        super(Username, Email, Password, Usertype);
        c_Title = title;
        c_Approved = approved;
        c_City = city;
        c_State = state;  
    }
    public void setTitle(String Title){c_Title = Title;}
    public void setEmail(Boolean approved){c_Approved = approved;}
    public void setCity(String city){c_City = city;}
    public void setState(String state){c_State = state;}
    public String getTitle(){return c_Title;}
    public Boolean getApproved(){return c_Approved;}
    public String getCity(){return c_City;}
    public String getState(){return c_State;}
}
