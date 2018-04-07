package ly.mysummery02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ly.customscaleview.ScrollScaleView;

public class MainActivity extends AppCompatActivity implements ScrollScaleView.OnDataChangedListener,
        View.OnClickListener{

    private ScrollScaleView scrollScaleView;

    private EditText edtNum;

    private Button btnReset;

    private TextView tvInfo;

    private int scaleViewWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
//        scrollScaleView.setScaleViewLenth(800);
//        scrollScaleView.setPartNum(10);
//        scrollScaleView.setInitPos(200);
        scrollScaleView.setListener(this);
        btnReset.setOnClickListener(this);
    }

    private void initView() {
        scrollScaleView = findViewById(R.id.scrollScaleView);
        edtNum = findViewById(R.id.edtNum);
        btnReset = findViewById(R.id.btnReset);
        tvInfo = findViewById(R.id.tvInfo);
    }

    @Override
    public void onDataChanged(int data) {
        tvInfo.setText(data+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnReset:
                String numStr = edtNum.getText().toString();
                int num = 0;
                if (!TextUtils.isEmpty(numStr)){
                    num = Integer.parseInt(numStr);
                }
                scrollScaleView.setDataFromInput(num);
                break;
        }
    }
}
