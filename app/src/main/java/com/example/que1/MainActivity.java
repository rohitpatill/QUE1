package com.example.que1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button addButton1;
    private Button addButton2;
    private Button moveButton;
    private Button moveAllButton;
    private Button moveBackButton;
    private Button moveAllBackButton;
    private ListView listOne;
    private ListView listTwo;

    private ArrayList<String> listOneItems;
    private ArrayList<String> listTwoItems;

    private ArrayAdapter<String> listOneAdapter;
    private ArrayAdapter<String> listTwoAdapter;

    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        addButton1 = findViewById(R.id.button);
        addButton2 = findViewById(R.id.button2);
        moveButton = findViewById(R.id.button3);
        moveAllButton = findViewById(R.id.button4);
        moveBackButton = findViewById(R.id.button5);
        moveAllBackButton = findViewById(R.id.button6);
        listOne = findViewById(R.id.listone);
        listTwo = findViewById(R.id.listtwo);

        listOneItems = new ArrayList<>();
        listTwoItems = new ArrayList<>();

        listOneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOneItems);
        listTwoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTwoItems);

        listOne.setAdapter(listOneAdapter);
        listTwo.setAdapter(listTwoAdapter);

        listOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = listOneItems.get(position);
                Toast.makeText(MainActivity.this, selectedItem + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        listTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = listTwoItems.get(position);
                Toast.makeText(MainActivity.this, selectedItem + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                if (!newItem.isEmpty()) {
                    if (!listOneItems.contains(newItem)) {
                        listOneItems.add(newItem);
                        listOneAdapter.notifyDataSetChanged();
                    }
                    editText.getText().clear();
                }
            }
        });

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                if (!newItem.isEmpty()) {
                    if (!listTwoItems.contains(newItem)) {
                        listTwoItems.add(newItem);
                        listTwoAdapter.notifyDataSetChanged();
                    }
                    editText.getText().clear();
                }
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null && !listTwoItems.contains(selectedItem)) {
                    listTwoItems.add(selectedItem);
                    listTwoAdapter.notifyDataSetChanged();
                    listOneItems.remove(selectedItem);
                    listOneAdapter.notifyDataSetChanged();
                    selectedItem = null;
                }
            }
        });

        moveBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null && !listOneItems.contains(selectedItem)) {
                    listOneItems.add(selectedItem);
                    listOneAdapter.notifyDataSetChanged();
                    listTwoItems.remove(selectedItem);
                    listTwoAdapter.notifyDataSetChanged();
                    selectedItem = null;
                }
            }
        });

        moveAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String item : listOneItems) {
                    if (!listTwoItems.contains(item)) {
                        listTwoItems.add(item);
                    }
                }
                listOneItems.clear();
                listOneAdapter.notifyDataSetChanged();
                listTwoAdapter.notifyDataSetChanged();
            }
        });

        moveAllBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String item : listTwoItems) {
                    if (!listOneItems.contains(item)) {
                        listOneItems.add(item);
                    }
                }
                listTwoItems.clear();
                listOneAdapter.notifyDataSetChanged();
                listTwoAdapter.notifyDataSetChanged();
            }
        });
    }
}
