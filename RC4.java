package com.example.SecurityTest;

public class RC4 {

	public String encryptRC4(String text) throws Exception {
		byte[] result = encrypt(text.getBytes());
		return toHex(result);
	}

	public String decryptRC4(String text) throws Exception {

		byte[] enc = toByte(text);
		byte[] result = decrypt(enc);
		return new String(result);
	}

	private final byte[] S = new byte[256];
	private final byte[] T = new byte[256];
	private final int keylen;

	public RC4(final byte[] key) {
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException();
		} else {
			keylen = key.length;
			for (int i = 0; i < 256; i++) {
				S[i] = (byte) i;
				T[i] = key[i % keylen];
			}
			int j = 0;
			for (int i = 0; i < 256; i++) {
				j = (j + S[i] + T[i]) & 0xFF;
				S[i] ^= S[j];
				S[j] ^= S[i];
				S[i] ^= S[j];
			}
		}
	}

	public byte[] encrypt(final byte[] plaintext) {
		final byte[] ciphertext = new byte[plaintext.length];
		int i = 0, j = 0, k, t;
		for (int counter = 0; counter < plaintext.length; counter++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			S[i] ^= S[j];
			S[j] ^= S[i];
			S[i] ^= S[j];
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			ciphertext[counter] = (byte) (plaintext[counter] ^ k);
		}
		return ciphertext;
	}

	public byte[] decrypt(final byte[] ciphertext) {
		return encrypt(ciphertext);
	}

	private String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		String HEX = "0123456789ABCDEF";
		for (int i = 0; i < buf.length; i++) {

			result.append(HEX.charAt((buf[i] >> 4) & 0x0f)).append(HEX.charAt(buf[i] & 0x0f));
		}
		return result.toString();
	}

	private byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		return result;
	}

}