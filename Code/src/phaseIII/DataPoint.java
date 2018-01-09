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
public class DataPoint{
    private String d_name;
    private String d_DateRecorded;
    private int d_DataValue;
    private String d_DateType;
    private Boolean d_Accepted;
    public DataPoint(String name, String DateRecorded,int DataValue, String DataType, Boolean Accepted) {
        //super(name, flag, DateFlagged, Zipcode, City, State);
        d_DateRecorded = DateRecorded;
        d_DataValue = DataValue;
        d_DateType = DataType;
        d_Accepted = Accepted;
        d_name = name;
    }
    public String getD_name(){return d_name;}
    public Boolean isD_Accepted() {return d_Accepted;}
    public int getD_DataValue() {return d_DataValue;}
    public String getD_DateRecorded() {return d_DateRecorded;}
    public String getD_DateType() {return d_DateType;}
    public void setD_Name(String name){d_name = name;}
    public void setD_Accepted(boolean d_Accepted) {this.d_Accepted = d_Accepted;}
    public void setD_DataValue(int d_DataValue) {this.d_DataValue = d_DataValue;}
    public void setD_DateRecorded(String d_DateRecorded) {this.d_DateRecorded = d_DateRecorded;}
    public void setD_DateType(String d_DateType) {this.d_DateType = d_DateType;}
    
}