package com.example.onlineretailers.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.onlineretailers.MainActivity;
import com.example.onlineretailers.R;
import com.example.onlineretailers.RegularCheck.RegularCheck;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.register.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements View.OnClickListener,
        LoginContract.LoginView{

    private TextInputLayout textInputUsername, textInputPassword;
    private EditText usernameEdit, passwordEdit;
    private LoginPresenter loginPresenter;
    private Boolean aBoolean;//比较结果

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_login;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_OnlineRetailers);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        textInputPassword = findViewById(R.id.login_password);
        textInputUsername = findViewById(R.id.login_username);
        usernameEdit = findViewById(R.id.edit_username);
        passwordEdit = findViewById(R.id.edit_password);
        Button loginBtn = findViewById(R.id.login_btn);
        Button registerBtn = findViewById(R.id.register);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:

                queryDatabase();//查询数据库是否有该用户

                //输入验证或者数据库对比
                if (validateLogon() && aBoolean ) {

                    updateLoginState();//更新登录特定用户的登录状态

                    //验证成功后跳转到主页面
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "密码或者用户名错误，请检查输入是否有误",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    /**
     *
     */
    private void updateLoginState() {
        //将要更新登录状态的id传进去
        loginPresenter.setDataBaseLoginState(usernameEdit.getText().toString());
    }

    /**
     * 查询数据库内是否有该用户
     */
    private void queryDatabase() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.getLoginReturnResult(usernameEdit.getText().toString(),
                passwordEdit.getText().toString());

    }

    /**
     * 输入验证
     */
    private boolean validateLogon() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if(!validateUsername(username)) {
            textInputUsername.setErrorEnabled(true);
            textInputUsername.setError("用户名只包含字母或数字，且在6-18位之间");
        } else if(!validatePassword(password)) {
            textInputPassword.setErrorEnabled(true);
            textInputPassword.setError("密码必须包含字母或数字，且在6-18位之间");
        } else {
            textInputPassword.setErrorEnabled(false);
            textInputUsername.setErrorEnabled(false);

            return true;
        }

        return false;
    }

    /**
     * 密码校验
     * @param password 密码
     * @return 校验结果
     */
    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(RegularCheck.PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 用户名校验
     * @param username 用户名
     * @return 校验结果
     */
    private boolean validateUsername(String username) {
        Pattern pattern = Pattern.compile(RegularCheck.USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }


    /**
     * 注册页面返回结果
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 返回数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String[] str = data.getStringArrayExtra("result_return");
                usernameEdit.setText(str[0]);
                passwordEdit.setText(str[1]);
            }
        }
    }


    @Override
    public void getLoginResult(Boolean boo) {
        this.aBoolean = boo;
    }
}
