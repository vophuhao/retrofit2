package com.example.retrofix2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AsyncTaskActivity extends AppCompatActivity {

    Button btnStart;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Khởi tạo tiến trình của bạn
//Truyền Activity chính là MainActivity sang bên tiến trình của mình
                myAsyncTask = new MyAsyncTask(AsyncTaskActivity.this );
//Gọi hàm execute để kích hoạt tiến trình
                myAsyncTask.execute();
            }
        });
    }

}