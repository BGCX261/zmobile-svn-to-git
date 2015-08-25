package net.zmobile.io;

import javax.microedition.rms.RecordStore;

import net.zmobile.util.Logger;
import net.zmobile.util.Utility;

public class Storeage {
	public static final String RECORD_NAME = "zmobile_data";
	public static final String RECORD_SIZE = "zmobile_size";
	
	public static final String MAGIC_RMS_NAME = "zmobile_store_flag";
	public static final int RECORD_CAPACITY_FOR_STREAM_UNIT = 1024;
	
	/** Magic Number */
	public final static int MAGIC = 0x4d536601; // "MSf" 0x01
	
	public static boolean checkStore(){
		int magic = 0;
		try {
			RecordStore magicRecordStore = RecordStore.openRecordStore(MAGIC_RMS_NAME, true);
			magic = Utility.byte2Int(magicRecordStore.getRecord(magicRecordStore.getNextRecordID()-1));
			System.out.println(magic+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			magicRecordStore.closeRecordStore();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log(Logger.LOG_ERROR, e.getMessage());
		}
		return magic==MAGIC;		
	}
	public static void mark(){
		try {
			RecordStore magicRecordStore = RecordStore.openRecordStore(MAGIC_RMS_NAME, true);
			int value = magicRecordStore.addRecord(Utility.int2Byte(MAGIC), 0, 4);
			System.out.println("888888888888888888888888888888888888888888888888888888888:"+value);
			magicRecordStore.closeRecordStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void storeSize(int length)throws Exception{
		RecordStore rsize = RecordStore.openRecordStore(RECORD_SIZE, true);
		byte[] size = Utility.int2Byte(length);
		rsize.addRecord(size, 0, 4);
		rsize.closeRecordStore();
	}
	public static int readSize()throws Exception{
		RecordStore rsize = RecordStore.openRecordStore(RECORD_SIZE, true);
		int size = Utility.byte2Int(rsize.getRecord(rsize.getNextRecordID()-1));;
		rsize.closeRecordStore();
		return size;
	}
	public static void store(byte[] data) throws Exception{
		int pos = 0;
		
		storeSize(data.length);
		
		RecordStore rs = RecordStore.openRecordStore(RECORD_NAME, true);
		
		//int length = Math.min(RECORD_CAPACITY_FOR_STREAM_UNIT,data.length);
		rs.addRecord(data, 0, data.length);
//		do{
//			Logger.log(Logger.LOG_ERROR,"start Storing");
//			rs.addRecord(data, pos, length);
//			pos+=length;
//			length = Math.min(RECORD_CAPACITY_FOR_STREAM_UNIT,data.length-pos);
//		}while(pos<data.length);
		rs.closeRecordStore();
	}
	public static byte[] read() throws Exception{	
		int pos = 0;
		
		int size = readSize();
		byte[] data = new byte[size];
		RecordStore rs = RecordStore.openRecordStore(RECORD_NAME, true);
		
		int num = rs.getNumRecords();
		System.out.println("********:"+num);
//		for (int i = 0; i < num; i++) {
//			byte[] readData = rs.getRecord(i);
//			System.arraycopy(readData, readData.length, data, pos, readData.length);
//			pos+=readData.length;
//		}
		return data;
	}
}
