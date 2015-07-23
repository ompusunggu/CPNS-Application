package project.pam.cpns_formasi1;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerFormasi1CPNS {
	//DATABASE FIELDS
	
		private DBHelper dbHelper;
		private SQLiteDatabase database;
		
		public static final String TABLE_NAME = "formasi1";
		public static final String IDFORMASILEMBAGA ="idFormasiLembaga";
		public static final String IDFORMASI = "idFormasi";
		public static final String LEMBAGA = "lembaga";
		public static final String DETAIL = "detail";
		
		public static final String CREATE_FORMASI1 = "CREATE TABLE " + TABLE_NAME + " " + 
				"(" + IDFORMASILEMBAGA + " INTEGER PRIMARY KEY, " + 
				IDFORMASI + " INTEGER, " +
				LEMBAGA + " TEXT, " +
				DETAIL + " VARCHAR(50)) ";
		
		private String[] TABLES_COLUMNS = { IDFORMASILEMBAGA, IDFORMASI, LEMBAGA, DETAIL };
		
		public ControllerFormasi1CPNS(Context context){
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
		
		public void insertData (int idFormasiLembaga, int idFormasi, String lembaga, String detail){
			ContentValues contentValues = new ContentValues();
			
			contentValues.put(IDFORMASILEMBAGA, idFormasiLembaga);
			contentValues.put(IDFORMASI, idFormasi);
			contentValues.put(LEMBAGA, lembaga);
			contentValues.put(DETAIL, detail);
			
			database.insert(TABLE_NAME, null, contentValues);
		}
		
		public ArrayList<ModelFormasi1CPNS> getData(){
			ArrayList<ModelFormasi1CPNS> allData = new ArrayList<ModelFormasi1CPNS>();
			
			Cursor cursor = null;
			
			cursor = database.query(TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASILEMBAGA);
			cursor.moveToFirst();
			
			while (!cursor.isAfterLast()) {
				allData.add(parseData(cursor));
				cursor.moveToNext();
			}
			
			cursor.close();
			return allData;
		}
		
		private ModelFormasi1CPNS parseData (Cursor cursor){
			
			ModelFormasi1CPNS curData = new ModelFormasi1CPNS();
			
			curData.setIdFormasiLembaga(cursor.getInt(0));
			curData.setIdFormasi(cursor.getInt(1));
			curData.setLembaga(cursor.getString(2));
			curData.setDetail(cursor.getString(3));
			
			return curData;
		}
}
