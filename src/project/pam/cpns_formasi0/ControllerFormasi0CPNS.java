package project.pam.cpns_formasi0;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerFormasi0CPNS {
	//DATABASE FIELDS
	
		private DBHelper dbHelper;
		private SQLiteDatabase database;
		
		public static final String TABLE_NAME = "formasi0";
		public static final String IDFORMASI = "idFormasi";
		public static final String FORMASI = "formasi";
		public static final String DETAIL = "detail";
		
		public static final String CREATE_FORMASI = "CREATE TABLE " + TABLE_NAME + " " + 
				"(" + IDFORMASI + " INTEGER PRIMARY KEY, " + 
				FORMASI + " TEXT, " +
				DETAIL + " TEXT )";
		
		private String[] TABLES_COLUMNS = { IDFORMASI, FORMASI, DETAIL };
		
		public ControllerFormasi0CPNS(Context context){
			dbHelper = new DBHelper(context);
		}
		
		public void open() throws SQLException {
			database = dbHelper.getWritableDatabase();
		}
		
		public void close(){
			dbHelper.close();
		}
		
		public void deleteData() {
			database.delete(TABLE_NAME, null, null);
		}
		
		public void insertData (int idFormasi, String formasi, String detail){
			ContentValues contentValues = new ContentValues();
			
			contentValues.put(IDFORMASI, idFormasi);
			contentValues.put(FORMASI, formasi);
			contentValues.put(DETAIL, detail);
			
			database.insert( TABLE_NAME, null, contentValues);
		}
		
		public ArrayList<ModelFormasi0CPNS> getData(){
			ArrayList<ModelFormasi0CPNS> allData = new ArrayList<ModelFormasi0CPNS>();
			
			Cursor cursor = null;
			
			cursor = database.query( TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASI );
			cursor.moveToFirst();
			
			while (!cursor.isAfterLast()) {
				allData.add(parseData(cursor));
				cursor.moveToNext();
			}
			
			cursor.close();
			return allData;
		}
		
		private ModelFormasi0CPNS parseData (Cursor cursor){
			ModelFormasi0CPNS curData = new ModelFormasi0CPNS();
			
			curData.setIdFormasi(cursor.getInt(0));
			curData.setFormasi(cursor.getString(1));
			curData.setDetail(cursor.getString(2));
			
			return curData;
		}
}
