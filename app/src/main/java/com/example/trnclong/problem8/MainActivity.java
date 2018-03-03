package com.example.trnclong.problem8;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.trnclong.problem8.model.JobInWeekModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtNgayHT,txtGioHT;
    EditText edtCongViec,edtNoiDung;
    Button btnDate,btnTime,btnAdd, btnUpdate, btnReset;
    Date gioHT,ngayHT;
    ListView listView;
    ArrayList<JobInWeekModel> arrayJob = new ArrayList<>();
    ArrayAdapter arrayAdapter = null;
    Calendar calendar;
    SimpleDateFormat dinhDangNgay, dinhDangGio;
    int index=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();
        addControls();
        initialSetup();
        addEvents();
        arrayAdapter = new ArrayAdapter<JobInWeekModel>(MainActivity.this,android.R.layout.simple_list_item_1, arrayJob);
        listView.setAdapter(arrayAdapter);
    }

    private void initialSetup() {
         dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
         dinhDangGio = new SimpleDateFormat("hh:mm a");
        txtNgayHT.setText(dinhDangNgay.format(calendar.getTime()));
        txtGioHT.setText(dinhDangGio.format(calendar.getTime()));
        ngayHT=calendar.getTime();
        gioHT=calendar.getTime();
    }

    private void addControls() {
        edtCongViec = findViewById(R.id.edtCongViec);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        txtNgayHT = findViewById(R.id.txtHienThiDate);
        txtGioHT = findViewById(R.id.txtHienThiGio);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnAdd = findViewById(R.id.btnThem);
        btnUpdate = findViewById(R.id.btnCapNhat);
        btnReset = findViewById(R.id.btnReset);
        listView = findViewById(R.id.lvDanhSach);
    }

    private void addEvents() {
        btnDate.setOnClickListener(new ButtonEvents());
        btnTime.setOnClickListener(new ButtonEvents());
        btnAdd.setOnClickListener(new ButtonEvents());
        btnUpdate.setOnClickListener(new ButtonEvents());
        btnReset.setOnClickListener(new ButtonEvents());
        listView.setOnItemClickListener(new ListViewEvents());
        listView.setOnItemLongClickListener(new ListViewEvents());
    }

    private class ButtonEvents implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnDate: {
                    dialogDataPicker();
                    break;
                }
                case R.id.btnTime: {
                    dialogTimePicker();
                    break;
                }
                case R.id.btnThem: {
                    if(edtCongViec.getText().toString().isEmpty() ) {
                        Toast.makeText(MainActivity.this,"Vui Lòng Nhập Công Việc",Toast.LENGTH_SHORT).show();
                    }
                     else {
                        addJob();
                    }
                    break;
                }
                case R.id.btnCapNhat: {
                    editWork();
                    break;
                }
                case R.id.btnReset: {
                    edtCongViec.setText("");
                    edtNoiDung.setText("");
                    calendar=Calendar.getInstance();
                    txtNgayHT.setText(dinhDangNgay.format(calendar.getTime()));
                    txtGioHT.setText(dinhDangGio.format(calendar.getTime()));
                    break;
                }

            }
        }
    }

    public void dialogDataPicker() {
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                txtNgayHT.setText(dinhDangNgay.format(calendar.getTime()));
                ngayHT=calendar.getTime();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,onDateSetListener,nam,thang,ngay);
        datePickerDialog.show();
    }

    public void dialogTimePicker() {
        calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);
                txtGioHT.setText(dinhDangGio.format(calendar.getTime()));
                gioHT =calendar.getTime();
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,gio,phut,false);
        timePickerDialog.show();
    }

    public void addJob() {
        String congViec = edtCongViec.getText()+"";
        String noiDung = edtNoiDung.getText()+"";
        JobInWeekModel mJobInWeek = new JobInWeekModel(congViec,noiDung,ngayHT,gioHT);
        arrayJob.add(mJobInWeek);
        arrayAdapter.notifyDataSetChanged();
        //reset and set focus
        edtCongViec.setText("");
        edtNoiDung.setText("");
        edtCongViec.requestFocus();
    }

    public void editWork() {
        String congViec = edtCongViec.getText()+"";
        String noiDung = edtNoiDung.getText()+"";
        JobInWeekModel mJobInWeek = new JobInWeekModel(congViec,noiDung,ngayHT,gioHT);
        arrayJob.set(index,mJobInWeek );
        arrayAdapter.notifyDataSetChanged();
        //reset and set focus
        edtCongViec.setText("");
        edtNoiDung.setText("");
        edtCongViec.requestFocus();
    }

    private class ListViewEvents implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            index = position;
            edtCongViec.setText(arrayJob.get(position).getTenCongViec());
            edtNoiDung.setText(arrayJob.get(position).getNoiDungCongViec());
            txtNgayHT.setText(dinhDangNgay.format(arrayJob.get(position).getNgayHT()));
            txtGioHT.setText(dinhDangGio.format(arrayJob.get(position).getGioHT()));
            Toast.makeText(MainActivity.this,arrayJob.get(position).getNoiDungCongViec(),Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            arrayJob.remove(position);
            arrayAdapter.notifyDataSetChanged();
            return false;
        }
    }
}
