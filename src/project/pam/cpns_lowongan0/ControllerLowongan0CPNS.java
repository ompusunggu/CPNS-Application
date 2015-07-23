package project.pam.cpns_lowongan0;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ControllerLowongan0CPNS {
	//DATABASE FIELDS
	
		private DBHelper dbHelper;
		private SQLiteDatabase database;
		
		public static final String TABLE_NAME = "lowongan0";
		public static final String IDFORMASILOWONGANDET = "idFormasiLowonganDet";
		public static final String IDFORMASILEMBAGADET = "idFormasiLembagaDet";
		public static final String TAHUN = "tahun";
		public static final String LEMBAGA = "lembaga";
		public static final String JABATAN = "jabatan";
		public static final String KUALIFIKASI = "kualifikasi";
		public static final String GOLRUANG = "golRuang";
		public static final String ALOKASI = "alokasi";
		public static final String PENEMPATAN = "penempatan";
		
		public static final String CREATE_LOWONGAN0 = "CREATE TABLE " + TABLE_NAME + " " + 
				"(" + IDFORMASILOWONGANDET + " INTEGER PRIMARY KEY, " + 
				IDFORMASILEMBAGADET + " INTEGER, " +
				TAHUN + " TEXT, " +
				LEMBAGA + " TEXT, " +
				JABATAN + " TEXT, " +
				KUALIFIKASI + " TEXT, " +
				GOLRUANG + " TEXT, " +
				ALOKASI + " INTEGER, " +
				PENEMPATAN +  " TEXT )";
		
		private String[] TABLES_COLUMNS = { IDFORMASILOWONGANDET, IDFORMASILEMBAGADET, TAHUN, LEMBAGA, JABATAN, KUALIFIKASI, GOLRUANG, ALOKASI, PENEMPATAN }; 
		
		public ControllerLowongan0CPNS(Context context){
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
		
		public void inserData(int idFormasiLowonganDet, int idFormasiLembagaDet, String tahun, String lembaga, String jabatan, String kualifikasi, String golRuang, int alokasi, String penempatan){
			ContentValues contentValues = new ContentValues();
			
			contentValues.put(IDFORMASILOWONGANDET, idFormasiLowonganDet);
			contentValues.put(IDFORMASILEMBAGADET, idFormasiLembagaDet);
			contentValues.put(TAHUN, tahun);
			contentValues.put(LEMBAGA, lembaga);
			contentValues.put(JABATAN, jabatan);
			contentValues.put(KUALIFIKASI, kualifikasi);
			contentValues.put(GOLRUANG, golRuang);
			contentValues.put(ALOKASI, alokasi);
			contentValues.put(PENEMPATAN, penempatan);
			
			database.insert( TABLE_NAME, null, contentValues);
		}
		
		public ArrayList<ModelLowongan0CPNS> getData() {
			ArrayList<ModelLowongan0CPNS> allData = new ArrayList<ModelLowongan0CPNS>();
			
			Cursor cursor = null;
			
			cursor = database.query( TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASILOWONGANDET);
			cursor.moveToFirst();
			
			while (!cursor.isAfterLast()) {
				allData.add(parseData(cursor));
				cursor.moveToNext();
			}
			
			cursor.close();
			return allData;
		}
		
		private ModelLowongan0CPNS parseData(Cursor cursor){
			ModelLowongan0CPNS curData = new ModelLowongan0CPNS();
			
			curData.setIdFormasiLowonganDet(cursor.getInt(0));
			curData.setIdFormasiLembagaDet(cursor.getInt(1));
			curData.setTahun(cursor.getString(2));
			curData.setJabatan(cursor.getString(3));
			curData.setKualifikasi(cursor.getString(4));
			curData.setGolRuang(cursor.getString(5));
			curData.setAlokasi(cursor.getInt(6));
			curData.setPenempatan(cursor.getString(7));
			
			return curData;
		}

}
