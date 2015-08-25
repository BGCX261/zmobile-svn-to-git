package net.zmobile.util;

public class Utility {
	/**
	 * Convert integer to byte array.
	 * @param value
	 * @return
	 */
	public static final byte[] int2Byte(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}

	/**
	 * Convert byte array to integer.
	 * @param b
	 * @return
	 */
	public static final int byte2Int(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
				+ (b[3] & 0xFF);
	}
}
