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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.travel.voyage.room.User;
import com.travel.voyage.room.UserDao;
import com.travel.voyage.room.UserDataBase;

/**
 *
 * @author Beti
 */
public class RegisterActivity extends AppCompatActivity {

    // user data management
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userPasswordVerification;

    private TextInputLayout userEmailLayout;
    private TextInputLayout userPasswordLayout;
    private TextInputLayout userPasswordVerificationLayout;

    private UserDao userDao; // user dao for data access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        UserDataBase userDataBase = Room.databaseBuilder(this, UserDataBase.class, DataManager.USERS_DB_NAME).allowMainThreadQueries().build();
        userDao = userDataBase.getUserDao();

        userName = findViewById(R.id.text_field_name_value);
        userEmail = findViewById(R.id.text_field_email_value);
        userPassword = findViewById(R.id.text_field_password_value);
        userPasswordVerification = findViewById(R.id.text_field_password_verification_value);

        userEmailLayout = findViewById(R.id.text_field_email);
        userPasswordLayout = findViewById(R.id.text_field_password);
        userPasswordVerificationLayout = findViewById(R.id.text_field_password_verification);

        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!userEmail.getText().toString().isEmpty()) userEmailLayout.setError(null);
                else userEmailLayout.setError(getString(R.string.input_required));

                if (!TextUtils.isEmpty(s) && !Patterns.EMAIL_ADDRESS.matcher(s).matches())
                    userEmailLayout.setError(getString(R.string.invalid_email));
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
                if (!userPassword.getText().toString().isEmpty()) {
                    if (!userPasswordVerification.getText().toString().isEmpty() && !userPasswordVerification.getText().toString().equals(userPassword.getText().toString()))
                        userPasswordVerificationLayout.setError(getString(R.string.error_password_verification));
                    else userPasswordVerificationLayout.setError(null);

                    userPasswordLayout.setError(null);
                } else userPasswordLayout.setError(getString(R.string.input_required));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userPasswordVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!userPasswordVerification.getText().toString().isEmpty())
                    userPasswordVerificationLayout.setError(null);

                if (!userPasswordVerification.getText().toString().equals(userPassword.getText().toString()))
                    userPasswordVerificationLayout.setError(getString(R.string.error_password_verification));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void openLoginActivity(View view) {
        startActivity(new Intent(view.getContext(), LoginActivity.class));
    }

    public void createAccount(View view) {
        String name = userName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String passwordVerification = userPasswordVerification.toString().trim();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !passwordVerification.isEmpty()) {
            User existingUser = userDao.getUserByEmail(email);

            if (existingUser != null) {
                Snackbar.make(view, getString(R.string.email_taken), BaseTransientBottomBar.LENGTH_SHORT).show();
            } else {
                if (!userPasswordVerification.getText().toString().equals(userPassword.getText().toString())) {
                    Snackbar.make(view, R.string.error_form_generic, BaseTransientBottomBar.LENGTH_SHORT).show();
                } else {
                    User newUser = new User(name, email, password);
                    long newUserId = userDao.insert(newUser);
                    newUser.setId(newUserId);

                    DataManager.setLoggedInUser(newUser, view.getContext());

                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        } else {
            Snackbar.make(view, R.string.error_unfilled, BaseTransientBottomBar.LENGTH_SHORT).show();
        }
    }
}