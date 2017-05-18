package com.example.SecurityTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity {

	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final RC4 rc = new RC4(new byte[] { 1, 2, 3 });
		final EditText editText = (EditText) findViewById(R.id.editText);
		final EditText editText1 = (EditText) findViewById(R.id.editText1);
		final EditText editText2 = (EditText) findViewById(R.id.editText2);
		final EditText number = (EditText) findViewById(R.id.editText3);
		final Button dec = (Button) findViewById(R.id.button1);
		final Button rc4 = (Button) findViewById(R.id.button3);

		dec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent i = new Intent(MyActivity.this, Decrypt.class);
				startActivity(i);
			}
		});

		rc4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					String str = rc.encryptRC4(editText.getText().toString());
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(number.getText().toString(), null, str, null, null);
					editText1.setText(str);
				} catch (Exception e) {
				}
			}
		});

	}
}
