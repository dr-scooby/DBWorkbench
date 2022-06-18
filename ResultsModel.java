
package gui;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

/**
 * ResultsModel extends AbstractTableModel
 * This class can be set to any JTable.
 * 
 * @author Jahangir
 */
public class ResultsModel extends AbstractTableModel{
    
    // Empty array of names
    private String[] columnNames = new String[0];
    
    private Vector dataRows = new Vector();
    
    
    /**
     * 
     * @param row int
     * @param column int
     * @return Object - the value in the data row
     */
    public Object getValueAt(int row, int column){
        // return value at row, column
        return  ((String[]) (dataRows.elementAt(row)) )[column] ;
    }
    
    /**
     * 
     * @return int - Column Names length
     */
    public int getColumnCount(){
        // return number of columns..
        return columnNames.length;
    }
    
    /**
     * 
     * @return int - number of Rows
     */
    public int getRowCount(){
        // return row count, number of rows
        if(dataRows == null)
            return 0;
        else
            return dataRows.size();
    }
    
    /**
     * Get the column name from the column number
     * @param col int
     * @return String
     */
    public String getColumnName(int col){
        return columnNames[col] == null ? "No Name" : columnNames[col];
    }
    
    /**
     * provide any SQL query, and data is added to the JTable model
     * @param rs ResultSet
     * @throws SQLException 
     */
    public void setResultSet(ResultSet rs)throws SQLException{
        if(rs == null){
                columnNames = new String[0]; // reset column names
                if(dataRows != null)
                        dataRows.clear();//remove all entries in vector
                fireTableChanged(null); // notify table of changes
                return;
        }
        
       
            // get the MetaData from ResultSet
            ResultSetMetaData metada = rs.getMetaData();
            
            // get how many columns
            int numCols = metada.getColumnCount();
            System.out.println("number of columns:" + numCols); // testing only
            columnNames = new String[numCols]; // init array with number of cols
            // get column names
            for(int i = 0; i < numCols; i++){
                columnNames[i] = metada.getColumnLabel(i+1); // i + 1 because the getColumnLable starts at 1 
                System.out.println(columnNames[i]); // testing only
            }
            
            // get all the rows
            dataRows = new Vector(); // new Vector to store data
            String[] rowdata; // Stores one row
            while(rs.next()){
                rowdata = new String[numCols]; // create array to hold data
                for(int i=0; i < numCols; i++){ // for each column
                    rowdata[i] = rs.getString(i+1); // retrieve the data item
                    System.out.println(rowdata[i]); // testing only
                    
                }
                
                dataRows.addElement(rowdata); // add the row to the Vector
                
            }
            
            fireTableChanged(null); // send signal to the table there is a new model data, refresh the JTable
           
       
    }
}
