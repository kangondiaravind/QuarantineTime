package com.aravind.coronatracker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuarantineResultActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;
    TextView result;
    TextView quarantinePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarantine_result);
        result = findViewById(R.id.count);
        quarantinePlace = findViewById(R.id.tv_quarantine_place);
        String pin = getIntent().getStringExtra("PINCODE");
        quarantinePlace.setText("Quarantined count at " + pin);
        showProgress();

        ApiService requestInterface = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<DieaseDetail> call = requestInterface.getDieaseDetails(pin);
        call.enqueue(new Callback<DieaseDetail>() {
            @Override
            public void onResponse(Call<DieaseDetail> call, Response<DieaseDetail> response) {
                progressDoalog.dismiss();
                if (response.code() == 200) {
                    generateDataList(response.body().getResult());
                } else {
                    Toast.makeText(QuarantineResultActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DieaseDetail> call, Throwable t) {
                Toast.makeText(QuarantineResultActivity.this, "Failed to fetch the data", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateDataList(String resultValue) {
        result.setText(resultValue);
    }

    private void showProgress() {
        progressDoalog = new ProgressDialog(QuarantineResultActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setCancelable(false);
        progressDoalog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}