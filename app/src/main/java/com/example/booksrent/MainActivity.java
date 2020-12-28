package com.example.booksrent;

import androidx.appcompat.app.AppCompatActivity;
import com.example.booksrent.model.Book;
import com.example.booksrent.model.User;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText username;
    private RadioGroup sex;
    private EditText rent_time;
    private TextView back_time;
    private CheckBox history,suspense,art;
    private boolean isHistory,isSuspense,isArt;
    private SeekBar seekBar;
    private int age;
    private TextView selected_age;
    private ImageView book_image;
    private Button serach_button,next_button;
    private TextView book_name,book_type,book_required_age;
    private List<Book> mbookList;
    private List<Book> resBookList;
    private User user;
    private Book book;
    private int current_index = -1;
    private String type_des;
    private Book current_book;
    private TextView res_message;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"***************start***************");
        user = new User();

        findViews();
        defaultSetting();
        initData();
        setListeners();
    }

    private void findViews() {
        username = findViewById(R.id.user_name);
        sex = findViewById(R.id.sex);
        rent_time = findViewById(R.id.rent_time);
        back_time = findViewById(R.id.back_time);
        history = findViewById(R.id.hobby_history);
        suspense = findViewById(R.id.hobby_suspense);
        art = findViewById(R.id.hobby_art);
        seekBar = findViewById(R.id.seekBar);
        book_image = findViewById(R.id.book_image);
        serach_button = findViewById(R.id.search);
        next_button = findViewById(R.id.search_next);
        //TextView book_name,book_type,book_required_age;
        book_name = findViewById(R.id.book_name);
        book_type = findViewById(R.id.type);
        book_required_age = findViewById(R.id.required_age);
        selected_age =findViewById(R.id.selected_age);
        res_message = findViewById(R.id.res_message);

    }

    private void defaultSetting(){
        seekBar.setProgress(18);
        selected_age.setText("18");
    }

    private void initData(){
        mbookList = new ArrayList<>();

        mbookList.add(new Book("边城", 15, R.drawable.bb, true, false, true));
        mbookList.add(new Book("splr", 18, R.drawable.cc, true, true, false));
        mbookList.add(new Book("光辉岁月", 15, R.drawable.dd, true, false, false));
        mbookList.add(new Book("宋词三百首", 12, R.drawable.ee, true, false, true));
        mbookList.add(new Book("玫瑰花", 18, R.drawable.f, false, true, false));
        mbookList.add(new Book("中国古代文学", 15, R.drawable.ff, true, false, true));
        mbookList.add(new Book("无花果", 15, R.drawable.gg, true, false, true));
        mbookList.add(new Book("古镇", 15, R.drawable.hh, true, false, true));
        mbookList.add(new Book("人生感悟", 15, R.drawable.aa, false, false, true));

    }

    private void  setListeners(){

        //读者姓名
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setUser_name(username.getText().toString());
                Log.d(TAG,"username is"+user.getUser_name());
            }
        });

        //性别
        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        user.setSex("男");
                        break;
                    case R.id.female:
                        user.setSex("女");
                        break;
                }
                Log.d(TAG,"sex is"+user.getSex());


            }
        });

        //借出时间
        rent_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //比较时间，如果时间大于归还时间，则返回错误信息
                checkDate();

            }
        });


        //爱好
        history.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHistory = isChecked;
                Log.d(TAG,"history is"+isHistory);
            }
        });

        suspense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSuspense = isChecked;
                Log.d(TAG,"suspense is"+isSuspense);
            }
        });

        art.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isArt = isChecked;
                Log.d(TAG,"art is"+isArt);
            }
        });

        //age

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age= seekBar.getProgress();
                selected_age.setText(String.valueOf(age));
                user.setAge(age);
                Log.d(TAG,"age is"+age);

            }
        });

        //searchButton
        serach_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkDate()){
                    search();
                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查找第一个图片或者是下一个
                if(checkDate()){
                    next();
                }
            }
        });

    }

    private void search(){
        if(resBookList == null){
            resBookList = new ArrayList<>();
        }
        current_index = 0;

        resBookList.clear();
        for(Book book:mbookList){
            if(null != book){
                Log.d(TAG,"user is"+user);
                Log.d(TAG,"book age  is"+book.getRequired_age());

                if(user.getAge()>=book.getRequired_age()&&
                        (book.isArt() == isArt||book.isHistory() == isHistory||book.isSuspense()==isSuspense)) {
                    resBookList.add(book);
                }
            }
        }
        Log.d(TAG,"list is"+resBookList.size());

        if(current_index<resBookList.size()){
            current_book = resBookList.get(current_index);

            Log.d(TAG,"book is"+current_book);
            book_image.setImageResource(current_book.getPic());
            book_name.setText(current_book.getBook_name());
            book_type.setText(current_book.getType());
            book_required_age.setText(String.valueOf(current_book.getRequired_age()));
            res_message.setText("符合条件的书籍有"+resBookList.size()+"本");

            Toast.makeText(MainActivity.this,user.toString(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"没有书籍了",Toast.LENGTH_SHORT).show();
        }





    }

    private void next(){
        if(-1==current_index&&checkDate()){
            search();
        }else{
            current_index++;
            if(current_index<resBookList.size()){
                current_book = resBookList.get(current_index);
                Log.d(TAG,"book is"+current_book);
                book_image.setImageResource(current_book.getPic());
                book_name.setText(current_book.getBook_name());
                book_type.setText(current_book.getType());
                book_required_age.setText(String.valueOf(current_book.getRequired_age()));
            }else{
                //显示没有要寻找的书籍
                Toast.makeText(MainActivity.this,"没有书籍了",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkDate(){
        DateMatcher dateMatcher = new FormattedDateMatcher("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        if(dateMatcher.matches(rent_time.getText().toString())){
            try {
                Date r_time=sdf.parse(rent_time.getText().toString());
                Date b_time=sdf.parse(back_time.getText().toString());
                if(b_time.before(r_time)){
                    Toast.makeText(MainActivity.this,"借出日期晚于归还日期，请重新输入",Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }else{
            Toast.makeText(MainActivity.this,"日期格式不对，请重新输入",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }



}