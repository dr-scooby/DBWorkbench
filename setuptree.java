 // this will grab all the Tables in the system
    private void setupTree(DatabaseMetaData metadata)throws SQLException{
		String[] tabletypes = {"TABLE"};
		ResultSet tables = metadata.getTables(null, null ,"%",tabletypes);

		String tablename; // stores the table name
		DefaultMutableTreeNode tablenode; // stores a tree node
		// loop through the resultset and get all tables
		while(tables.next()){
			tablename = tables.getString("TABLE_NAME");
			tablenode = new DefaultMutableTreeNode(tablename);
			treenode.add(tablenode); // add the tablenode to the tree

			// get all columns for the current table
			ResultSet columnNames = metadata.getColumns(null,null,tablename,null);
			// add nodes for the columns as children of the current table node
			while(columnNames.next()){
				tablenode.add(new DefaultMutableTreeNode(columnNames.getString("COLUMN_NAME") ));
			}
		}

                dbtreemodel.reload();
	}
