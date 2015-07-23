package project.pam.cpns_2014;

import project.pam.cpns_formasi0.ControllerFormasi0CPNS;
import project.pam.cpns_formasi1.ControllerFormasi1CPNS;
import project.pam.cpns_formasi2.ControllerFormasi2CPNS;
import project.pam.cpns_formasi3.ControllerFormasi3CPNS;
import project.pam.cpns_jadwal.ControllerJadwalCPNS;
import project.pam.cpns_lowongan0.ControllerLowongan0CPNS;
import project.pam.cpns_lowongan1.ControllerLowongan1CPNS;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;;

public class DBHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "cpns2014";
	
	public DBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate (SQLiteDatabase db){
		db.execSQL(ControllerJadwalCPNS.CREATE_JADWAL);
		db.execSQL(ControllerFormasi0CPNS.CREATE_FORMASI);
		db.execSQL(ControllerFormasi1CPNS.CREATE_FORMASI1);
		db.execSQL(ControllerFormasi2CPNS.CREATE_FORMASI2);
		db.execSQL(ControllerFormasi3CPNS.CREATE_FORMASI3);
		db.execSQL(ControllerLowongan0CPNS.CREATE_LOWONGAN0);
		db.execSQL(ControllerLowongan1CPNS.CREATE_LOWONGAN1);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//TODO Auto-generated method stub
	}
}
