/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phaseIII;
import java.util.Date;
/**
 *
 * @author WYF
 */
public class POI {
    private String p_Name;
    private Boolean p_Flag;
    private String p_DateFlagged; /*or another date type instead of string */
    private String p_Zipcode;
    private String p_City;
    private String p_State;
   
    public POI (String name, Boolean flag, String DateFlagged, String Zipcode, String City, String State){
        p_Name = name;
        p_Flag = flag;
        p_DateFlagged = DateFlagged;
        p_Zipcode = Zipcode;
        p_City = City;
        p_State = State;
    }
    public void setName(String name){p_Name=name;}
    public void setFlag(boolean flag){p_Flag = flag;}
    public void setDateFlagged(String dateflagged){p_DateFlagged=dateflagged;}
    public void setZipcode(String zipcode){p_Zipcode=zipcode;}
    public void setCity(String city){p_City=city;}
    public void setState(String state){p_State = state;}
    public String getName(){return p_Name;}
    public Boolean getFlag(){return p_Flag;}
    public String getDateFlagged(){return p_DateFlagged;}
    public String getZipcode(){return p_Zipcode;}
    public String getCity(){return p_City;}
    public String getState(){return p_State;}
    
}
