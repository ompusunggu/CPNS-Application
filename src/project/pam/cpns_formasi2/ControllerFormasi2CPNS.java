package project.pam.cpns_formasi2;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerFormasi2CPNS {
	//DATABASE FIELDS
	
			private DBHelper dbHelper;
			private SQLiteDatabase database;
			
			public static final String TABLE_NAME = "formasi2";
			public static final String IDFORMASILEMBAGADET ="idFormasiLembagaDet";
			public static final String IDFORMASILEMBAGA ="idFormasiLembaga";
			public static final String LEMBAGA = "lembaga";
			public static final String DETAIL = "detail";
			
			public static final String CREATE_FORMASI2 = "CREATE TABLE " + TABLE_NAME + " " + 
					"(" + IDFORMASILEMBAGADET + " INTEGER PRIMARY KEY, " + 
					IDFORMASILEMBAGA + " INTEGER, " +
					LEMBAGA + " TEXT, " +
					DETAIL + " VARCHAR(50)) ";
			
			private String[] TABLES_COLUMNS = { IDFORMASILEMBAGADET, IDFORMASILEMBAGA, LEMBAGA, DETAIL };
			
			public ControllerFormasi2CPNS(Context context){
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
			
			public void insertData (int idFormasiLembagaDet, int idFormasiLembaga, String lembaga, String detail){
				ContentValues contentValues = new ContentValues();
				
				contentValues.put(IDFORMASILEMBAGADET, idFormasiLembagaDet);
				contentValues.put(IDFORMASILEMBAGA, idFormasiLembaga);
				contentValues.put(LEMBAGA, lembaga);
				contentValues.put(DETAIL, detail);
				
				database.insert(TABLE_NAME, null, contentValues);
			}
			
			public ArrayList<ModelFormasi2CPNS> getData(){
				ArrayList<ModelFormasi2CPNS> allData = new ArrayList<ModelFormasi2CPNS>();
				
				Cursor cursor = null;
				
				cursor = database.query(TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASILEMBAGADET + " ASC");
				cursor.moveToFirst();
				
				while (!cursor.isAfterLast()) {
					allData.add(parseData(cursor));
					cursor.moveToNext();
				}
				
				cursor.close();
				return allData;
			}
			
			private ModelFormasi2CPNS parseData (Cursor cursor){
				ModelFormasi2CPNS curData = new ModelFormasi2CPNS();
				
				curData.setIdFormasiLembagaDet(cursor.getInt(0));
				curData.setIdFormasiLembaga(cursor.getInt(1));
				curData.setLembaga(cursor.getString(2));
				curData.setDetail(cursor.getString(3));
				
				return curData;
			}
}
