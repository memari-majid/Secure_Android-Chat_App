package com.example.SecurityTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Decrypt extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Intent intent = getIntent();
		final EditText editText = (EditText) findViewById(R.id.e1);
		final EditText editText1 = (EditText) findViewById(R.id.e2);
		final TextView textView = (TextView) findViewById(R.id.e3);
		final Button bRc4 = (Button) findViewById(R.id.brc);
		final RC4 rc = new RC4(new byte[] { 1, 2, 3 });

		bRc4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					String str = rc.decryptRC4(editText.getText().toString());
					textView.setText(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
