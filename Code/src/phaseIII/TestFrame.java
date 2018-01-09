/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phaseIII;
/**
 *
 * @author shuangliang
 */
import javax.swing.*;

public class TestFrame extends JFrame{
    public TestFrame(){
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login p = new Login(this);     
        add(p);
        pack();
        setVisible(true);
    }   

    public static void main(String[] args){
        new TestFrame();
    }     
}
