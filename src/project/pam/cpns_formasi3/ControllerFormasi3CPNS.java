package project.pam.cpns_formasi3;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerFormasi3CPNS {
	//DATABASE FIELDS
	
	private DBHelper dbHelper;
	private SQLiteDatabase database;
	
	public static final String TABLE_NAME = "formasi3";
	public static final String IDFORMASILEMBAGAMOREDET ="idFormasiLembagaMoreDet";
	public static final String IDFORMASILEMBAGADET ="idFormasiLembagaDet";
	public static final String LEMBAGA = "lembaga";
	public static final String DETAIL = "detail";
	
	public static final String CREATE_FORMASI3 = "CREATE TABLE " + TABLE_NAME + " " + 
			"(" + IDFORMASILEMBAGAMOREDET + " INTEGER PRIMARY KEY, " + 
			IDFORMASILEMBAGADET + " INTEGER, " +
			LEMBAGA + " TEXT, " +
			DETAIL + " TEXT) ";
	
	private String[] TABLES_COLUMNS = { IDFORMASILEMBAGAMOREDET, IDFORMASILEMBAGADET, LEMBAGA, DETAIL };
	
	public ControllerFormasi3CPNS(Context context){
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
	
	public void insertData (int idFormasiLembagaMoreDet, int idFormasiLembagaDet, String lembaga, String detail){
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(IDFORMASILEMBAGAMOREDET, idFormasiLembagaMoreDet);
		contentValues.put(IDFORMASILEMBAGADET, idFormasiLembagaDet);
		contentValues.put(LEMBAGA, lembaga);
		contentValues.put(DETAIL, detail);
		
		database.insert(TABLE_NAME, null, contentValues);
	}
	
	public ArrayList<ModelFormasi3CPNS> getData(){
		ArrayList<ModelFormasi3CPNS> allData = new ArrayList<ModelFormasi3CPNS>();
		
		Cursor cursor = null;
		
		cursor = database.query(TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASILEMBAGAMOREDET );
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			allData.add(parseData(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		return allData;
	}
	
	private ModelFormasi3CPNS parseData (Cursor cursor){
		ModelFormasi3CPNS curData = new ModelFormasi3CPNS();
		
		curData.setIdFormasiLembagaMoreDet(cursor.getInt(0));
		curData.setIdFormasiLembagaDet(cursor.getInt(1));
		curData.setLembaga(cursor.getString(2));
		curData.setDetail(cursor.getString(3));
		
		return curData;
	}
}
