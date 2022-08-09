package com.example.onlineretailers.ui.register;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlineretailers.R;
import com.example.onlineretailers.RegularCheck.RegularCheck;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView{

    private TextInputLayout textInputUsername,textInputPassword,
            textInputPersonName,textInputPhoneNumber;
    private Boolean aBoolean;//检查数据库是否有相同的id的验证结果
    private RegisterPresenter registerPresenter;


    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        textInputUsername = findViewById(R.id.register_username);
        textInputPassword = findViewById(R.id.register_password);
        textInputPersonName = findViewById(R.id.register_name);
        textInputPhoneNumber = findViewById(R.id.register_phoneNumber);
        Button registerCompleteBtn = findViewById(R.id.register_complete);

        registerPresenter = new RegisterPresenter(this);

        //注册完成的按钮点击
        registerCompleteBtn.setOnClickListener(v -> {

                //检查数据库是否有相同id
                checkDifferenceID(Objects.requireNonNull(textInputUsername
                        .getEditText()).getText().toString());

                //验证并且获取数据库id是否相同的结果
                if (validateLogon() && aBoolean) {
                    insertDatabase();//把数据加入数据库

                    Intent intent = new Intent();
                    String[] str= {Objects.requireNonNull(textInputUsername.getEditText())
                            .getText().toString(),
                            Objects.requireNonNull(textInputPassword.getEditText())
                                    .getText().toString()};
                    intent.putExtra("result_return", str);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "存在相同的id，请重新输入不同的id",
                            Toast.LENGTH_SHORT).show();
                }
        });
    }

    /**
     * 检查是否有相同id，排除相同id
     * @param username 注册id
     */
    private void checkDifferenceID(String username) {
        registerPresenter.getRegisterCheckResult(username);
    }

    /**
     * 把注册信息插进数据库
     */
    private void insertDatabase() {
        //将信息存入数据库
        registerPresenter.insertDataBasePresenter(
                Objects.requireNonNull(textInputUsername.getEditText()).getText().toString(),
                Objects.requireNonNull(textInputPersonName.getEditText()).getText().toString(),
                Objects.requireNonNull(textInputPassword.getEditText()).getText().toString(),
                Objects.requireNonNull(textInputPhoneNumber.getEditText()).getText().toString());
    }

    /**
     * 验证
     */
    private boolean validateLogon() {
        String username = Objects.requireNonNull(textInputUsername.getEditText())
                .getText().toString();
        String password = Objects.requireNonNull(textInputPassword.getEditText())
                .getText().toString();
        String phoneNumbers = Objects.requireNonNull(textInputPhoneNumber.getEditText())
                .getText().toString();
        if(!validateUsername(username)) {
            textInputUsername.setErrorEnabled(true);
            textInputUsername.setError("用户名只包含字母或数字，且在6-18位之间");
        } else if(!validatePassword(password)) {
            textInputPassword.setErrorEnabled(true);
            textInputPassword.setError("密码必须包含字母或数字，且在6-18位之间");
        } else if (!validatePhoneNumber(phoneNumbers)){
            textInputPhoneNumber.setErrorEnabled(true);
            textInputPhoneNumber.setError("手机号码必须在0-9之间,且位数为11位");
            } else{
            textInputPassword.setErrorEnabled(false);
            textInputUsername.setErrorEnabled(false);
            return true;
        }
        return false;
    }

    /**
     * 电话号码验证
     * @param phoneNumbers 电话号码
     * @return 验证结果
     */
    private boolean validatePhoneNumber(String phoneNumbers) {
        return phoneNumbers.length() == 11;
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
     * 检测id是否唯一
     * @param boo 返回结果
     */
    @Override
    public void getRegisterCheckResult(boolean boo) {
        this.aBoolean = boo;
    }
}
