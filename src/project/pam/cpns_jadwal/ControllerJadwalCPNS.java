package project.pam.cpns_jadwal;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerJadwalCPNS {
	//DATABASE FIELDS
	
	private DBHelper dbHelper;
	private SQLiteDatabase database;
	public static final String TABLE_NAME = "jadwalcpns";
	public static final String ID_INSTANSI = "idInstansi";
	public static final String  INSTANSI = "instansi";
	public static final String STATUS = "status";
	public static final String JADWAL_DAFTAR = "jadwalDaftar";
	public static final String TOTAL_PELAMAR = "totalPelamar";
	
	public static final String CREATE_JADWAL = "CREATE TABLE " + TABLE_NAME + " " + 
			"(" + ID_INSTANSI + " INTEGER PRIMARY KEY, " + 
			INSTANSI + " TEXT, " +
			STATUS + " TEXT, " +
			JADWAL_DAFTAR + " TEXT, " +
			TOTAL_PELAMAR +  " INTEGER )";
	
	private String[] TABLES_COLUMNS = { ID_INSTANSI, INSTANSI, STATUS, JADWAL_DAFTAR, TOTAL_PELAMAR }; 
	
	public ControllerJadwalCPNS(Context context){
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
	
	public void inserData(int idInstansi, String instansi, String status, String jadwalDaftar, int totalPelamar){
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(ID_INSTANSI, idInstansi);
		contentValues.put(INSTANSI, instansi);
		contentValues.put(STATUS, status);
		contentValues.put(JADWAL_DAFTAR, jadwalDaftar);
		contentValues.put(TOTAL_PELAMAR, totalPelamar);
		
		database.insert( TABLE_NAME, null, contentValues);
	}
	
	public ArrayList<ModelJadwalCPNS> getData() {
		ArrayList<ModelJadwalCPNS> allData = new ArrayList<ModelJadwalCPNS>();
		
		Cursor cursor = null;
		
		cursor = database.query( TABLE_NAME, TABLES_COLUMNS, null, null, null, null, ID_INSTANSI );
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			allData.add(parseData(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		return allData;
	}
	
	private ModelJadwalCPNS parseData(Cursor cursor){
		ModelJadwalCPNS curData = new ModelJadwalCPNS();
		
		curData.setIdInstansi(cursor.getInt(0));
		curData.setInstansi(cursor.getString(1));
		curData.setStatus(cursor.getString(2));
		curData.setJadwalDaftar(cursor.getString(3));
		curData.setTotalPelamar(cursor.getInt(4));
		
		return curData;
	}
}
