package com.travel.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.travel.voyage.room.User;
import com.travel.voyage.room.UserDao;
import com.travel.voyage.room.UserDataBase;
/**
 * @author Beti
 */
public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private TextInputLayout userEmailLayout, userPasswordLayout;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        UserDataBase userDataBase;

        userDataBase = Room.databaseBuilder(this, UserDataBase.class,
                DataManager.USERS_DB_NAME).allowMainThreadQueries().build();

        userDao = userDataBase.getUserDao();

        userEmail = findViewById(R.id.text_field_email_value);
        userPassword = findViewById(R.id.text_field_password_value);
        userEmailLayout = findViewById(R.id.text_field_email);
        userPasswordLayout = findViewById(R.id.text_field_password);

        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                userEmailLayout.setError(!userEmail.getText().toString().isEmpty() ?
                        null : getString(R.string.input_required));

                if (!TextUtils.isEmpty(s) && !Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    userEmailLayout.setError(getString(R.string.invalid_email));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userPasswordLayout.setError(!userPassword.getText().toString().isEmpty() ?
                        null : getString(R.string.input_required));
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void openRegisterActivity(View view) {
        startActivity(new Intent(view.getContext(), RegisterActivity.class));
    }

    public void beginLogin(View view) {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            User user = userDao.getUser(email, password);

            if (user != null) {
                DataManager.setLoggedInUser(user, view.getContext());
                startActivity(new Intent(view.getContext(), HomeActivity.class));
            } else {
                userEmailLayout.setError(getString(R.string.wrong_email_or_pass));
            }
        } else {
            if (email.isEmpty()) {
                userEmailLayout.setError(getString(R.string.input_required));
            } else {
                userEmailLayout.setError(null);
            }

            if (password.isEmpty()) {
                userPasswordLayout.setError(getString(R.string.input_required));
            } else {
                userPasswordLayout.setError(null);
            }
        }
    }
}