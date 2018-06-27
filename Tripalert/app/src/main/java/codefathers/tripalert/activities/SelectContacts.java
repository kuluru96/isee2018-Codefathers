package codefathers.tripalert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import codefathers.tripalert.R;
import codefathers.tripalert.interfaces.NextStepActivity;

public class SelectContacts extends AppCompatActivity implements NextStepActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
    }
    @Override
    public void onNext(View v) {
        startActivity(new Intent(SelectContacts.this,SpecifyDetails.class));

    }

    @Override
    public void onCancel(View v) {
        startActivity(new Intent(SelectContacts.this,HomeScreen.class));

    }

    @Override
    public void getData() {

    }

    @Override
    public void saveData() {

    }
}
