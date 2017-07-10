package company.co.kr.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class CustomDialogActivity extends Activity implements
        View.OnClickListener {

    /**
     * 시크바를 담고 있는 레이아웃 객체
     */
    private View panel;

    /**
     * 시크바 객체
     */
    private SeekBar seekbar;

    /**
     * 텍스트뷰
     */
    private TextView text01;

    /**
     * 화면밝기 값
     */
    private int brightness = 50;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onClick(View v) {
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.activity_main);
        loginDialog.setTitle("Steeting Intensity");

        panel = loginDialog.findViewById(R.id.panel01);
        text01 = (TextView) loginDialog.findViewById(R.id.text01);
        seekbar = (SeekBar) loginDialog.findViewById(R.id.seekbar01);
        seekbar.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());

        // 버튼 이벤트 처리
        Button showBtn = (Button) loginDialog.findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPanel();
            }
        });


        loginDialog.show();

    }



    private void showPanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        seekbar.setProgress(this.brightness);
        panel.setVisibility(View.VISIBLE);
        panel.startAnimation(animation);
    }


    private void hidePanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        panel.startAnimation(animation);
        panel.setVisibility(View.GONE);
    }




    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (i < 30) {
                text01.setText("밝기 수준 : 하");
            }else if (i < 70) {
                text01.setText("밝기 수준 : 중");
            }else {
                text01.setText("밝기 수준 : 상");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int i = seekBar.getProgress();

            if (i < 30) {
                seekBar.setProgress(0);
            } else if (i < 70) {
                seekBar.setProgress(50);
            } else {
                seekBar.setProgress(100);
            }
        }
    }






}
