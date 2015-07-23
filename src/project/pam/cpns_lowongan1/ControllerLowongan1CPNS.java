package project.pam.cpns_lowongan1;

import java.util.ArrayList;

import project.pam.cpns_2014.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class ControllerLowongan1CPNS {
	//DATABASE FIELDS
	
			private DBHelper dbHelper;
			private SQLiteDatabase database;
			
			public static final String TABLE_NAME = "lowongan1";
			public static final String IDFORMASILOWONGANMOREDET = "idFormasiLowonganMoreDet";
			public static final String IDFORMASILEMBAGAMOREDET = "idFormasiLembagaMoreDet";
			public static final String TAHUN = "tahun";
			public static final String LEMBAGA = "lembaga";
			public static final String JABATAN = "jabatan";
			public static final String KUALIFIKASI = "kualifikasi";
			public static final String GOLRUANG = "golRuang";
			public static final String ALOKASI = "alokasi";
			public static final String PENEMPATAN = "penempatan";
			
			public static final String CREATE_LOWONGAN1 = "CREATE TABLE " + TABLE_NAME + " " + 
					"(" + IDFORMASILOWONGANMOREDET + " INTEGER PRIMARY KEY, " + 
					IDFORMASILEMBAGAMOREDET + " INTEGER, " +
					TAHUN + " TEXT, " +
					LEMBAGA + " TEXT, " +
					JABATAN + " TEXT, " +
					KUALIFIKASI + " TEXT, " +
					GOLRUANG + " TEXT, " +
					ALOKASI + " INTEGER, " +
					PENEMPATAN +  " TEXT )";
			
			private String[] TABLES_COLUMNS = { IDFORMASILOWONGANMOREDET, IDFORMASILEMBAGAMOREDET, TAHUN, LEMBAGA, JABATAN, KUALIFIKASI, GOLRUANG, ALOKASI, PENEMPATAN }; 
			
			public ControllerLowongan1CPNS(Context context){
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
			
			public void inserData(int idFormasiLowonganMoreDet, int idFormasiLembagaMoreDet, String tahun, String lembaga, String jabatan, String kualifikasi, String golRuang, int alokasi, String penempatan){
				ContentValues contentValues = new ContentValues();
				
				contentValues.put(IDFORMASILOWONGANMOREDET, idFormasiLowonganMoreDet);
				contentValues.put(IDFORMASILEMBAGAMOREDET, idFormasiLembagaMoreDet);
				contentValues.put(TAHUN, tahun);
				contentValues.put(LEMBAGA, lembaga);
				contentValues.put(JABATAN, jabatan);
				contentValues.put(KUALIFIKASI, kualifikasi);
				contentValues.put(GOLRUANG, golRuang);
				contentValues.put(ALOKASI, alokasi);
				contentValues.put(PENEMPATAN, penempatan);
				
				database.insert( TABLE_NAME, null, contentValues);
			}
			
			public ArrayList<ModelLowongan1CPNS> getData() {
				ArrayList<ModelLowongan1CPNS> allData = new ArrayList<ModelLowongan1CPNS>();
				
				Cursor cursor = null;
				
				cursor = database.query( TABLE_NAME, TABLES_COLUMNS, null, null, null, null, IDFORMASILOWONGANMOREDET);
				cursor.moveToFirst();
				
				while (!cursor.isAfterLast()) {
					allData.add(parseData(cursor));
					cursor.moveToNext();
				}
				
				cursor.close();
				return allData;
			}
			
			private ModelLowongan1CPNS parseData(Cursor cursor){
				ModelLowongan1CPNS curData = new ModelLowongan1CPNS();
				
				curData.setIdFormasiLowonganMoreDet(cursor.getInt(0));
				curData.setIdFormasiLembagaMoreDet(cursor.getInt(1));
				curData.setTahun(cursor.getString(2));
				curData.setJabatan(cursor.getString(3));
				curData.setKualifikasi(cursor.getString(4));
				curData.setGolRuang(cursor.getString(5));
				curData.setAlokasi(cursor.getInt(6));
				curData.setPenempatan(cursor.getString(7));
				
				return curData;
			}

}
