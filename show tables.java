private void setupTree(Connection c)throws SQLException{
        DefaultMutableTreeNode tablenode; // stores a tree node
        String sql = "show tables"; // this SQL will only show the tables for the user logged in
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ResultSet rsCols ;
        DatabaseMetaData metadata = c.getMetaData();
        String tablename = "";
        while(rs.next()){
            tablename = rs.getString(1); // get the table name
            System.out.println("Table name: " + tablename);
            tablenode = new DefaultMutableTreeNode(tablename); // add table to the node
            treenode.add(tablenode); // add the tablenode to the tree
            rsCols = metadata.getColumns(null, null, tablename, null); // get Columns info for the table
            while(rsCols.next()){
                String colname = rsCols.getString("COLUMN_NAME"); // get the Column Name
                System.out.println("\t\tcolname: " + colname);
                tablenode.add(new DefaultMutableTreeNode(rsCols.getString("COLUMN_NAME") )); // column to the Table Node
            } 
        }
        
        dbtreemodel.reload();
    }
