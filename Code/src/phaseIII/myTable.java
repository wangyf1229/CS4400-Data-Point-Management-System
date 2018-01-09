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
import javax.swing.table.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultListSelectionModel;
public class myTable {
    public JTable table;
    private Object[][] content;
    private DefaultListSelectionModel selectionModel;
    public myTable(Object[][] result,Object[] column,JPanel bottom,int[] position) { 
        content = result;
        DefaultTableModel model = new DefaultTableModel(content, column){       
            public Class getColumnClass(int c) {  
              if ((c >= 0) && (c < getColumnCount())) {
                return getValueAt(0, c).getClass();
              } else {
                  return Object.class;
             }
         }
        };
        table = new JTable(model){};
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoCreateRowSorter(true);  
        table.setRowSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
     
        //selectionModel = (DefaultListSelectionModel) table.getSelectionModel();
        this.adjust_width();
        //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scroll = new JScrollPane(table);
        //scroll.repaint();    
        scroll.setBounds(position[0],position[1],position[2],position[3]);
        bottom.add(scroll);
    }
    
     public myTable(Object[][] result,Object[] column,FS_POI bottom,int[] position) { 
        content = result;
        DefaultTableModel model = new DefaultTableModel(content, column);     
        table = new JTable(model) {
          
            public Class getColumnClass(int c) {
                return content[0][c].getClass();
            }         
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoCreateRowSorter(true);
        table.setRowSelectionAllowed(true);
        //table.setCellSelectionEnabled(true);
       
        table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println("the selected row is "+ table.getSelectedRow());               
                    String selected_location = getLocname(table.getSelectedRow());
                    bottom.update_FS_POI_GUI(selected_location);                
                }
        });
       
        this.adjust_width();
        JScrollPane scroll = new JScrollPane(table);   
        scroll.setBounds(position[0],position[1],position[2],position[3]);
        bottom.add(scroll);
    }
     
     
    public int[] getRow(){
        int[] selectedRow = table.getSelectedRows();
        for(int i = 0;i<selectedRow.length;i++){
            System.out.println("The selected index is");
            System.out.println(selectedRow[i]);
        }
        return selectedRow;
    }
    public String getLocname(int i){
        Object[] temp = this.get_row_object(i);
        String locname = (String)temp[0];    
        return locname;
    }
    
    public void print_table(){
       for(int i = 0 ; i<content.length;i++){
         for(int j = 0;j < content[i].length;j++){
            System.out.println(content[i][j]);
        }
       }
    }
    
    public Object[] get_row_object(int i){
        int converted_row = table.convertRowIndexToModel(i);
        Object[] row_object = new Object[content[converted_row].length];
        for(int j = 0;j < content[i].length;j++){
            //System.out.println(content[i][j]);       
            row_object[j] = table.getModel().getValueAt(converted_row,j);
        }
        return row_object;
    }
    
    
   
    public void delete_row(int[] row){
         DefaultTableModel model = (DefaultTableModel)table.getModel();
         for(int i = 0 ;i<row.length;i++){
            int converted_row = table.convertRowIndexToModel(row[i]);
            model.removeRow(converted_row);
         }
         
    }
    public void adjust_width(){
        for (int j = 0; j < table.getColumnCount(); j++)
            {
                TableColumn col = table.getColumnModel().getColumn(j);
                int min = col.getMinWidth();
                int max = col.getMaxWidth();
        for (int i = 0; i < table.getRowCount(); i++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(i, j);
                Component c = table.prepareRenderer(cellRenderer, i, j);
                int temp = c.getPreferredSize().width + table.getIntercellSpacing().width;
                min = Math.max(min, temp); 
         if (min >= max)
            {
                min = max;
            break;
                }
            }
              col.setPreferredWidth(min);
        }
    }
  }