package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText contentView;
    Button btn;
    // 퍼미션 부여 여부
    boolean fileReadPermission; boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        String content = contentView.getText().toString(); // 퍼미션이 부여되어 있다면
        if (fileReadPermission && fileWritePermission) {
            String filename = "myfile.txt";
            FileWriter writer;
            try {
                File file = new File(getFilesDir(), filename); // 파일이 없다면 새로 만들어 준다.
                if (!file.exists()) {
                    file.createNewFile();
                }// file write
                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush();
                writer.close();
                // 결과 확인을 위한 FileReadActivity 실행 클래스
                Intent intent = new Intent(this, ReadFileActivity.class); // FileReadActivity로 화면 전환
                startActivity(intent);
            } catch (Exception e) { e.printStackTrace();
            }
        } else {
            showToast("permission 이 부여 안되어 기능 실행이 안됩니다.");
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT); toast.show();
    }
}
