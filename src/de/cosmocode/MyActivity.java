package de.cosmocode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import android.widget.TextView;
import de.cosmocode.autocompletion.MyTextWatcher;
import de.cosmocode.autocompletion.MyAutoCompleteAdapter;

public class MyActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.simple_rest_autocompletion);

        final MyAutoCompleteAdapter autocompleteAdapter = new MyAutoCompleteAdapter(this, R.layout.autocomplete_item);
        autocompleteAdapter.setNotifyOnChange(true);

        // The items will be fetched from the API in the textChangeListener
        autoCompleteTextView.addTextChangedListener(new MyTextWatcher(autoCompleteTextView, autocompleteAdapter));
        autoCompleteTextView.setOnItemClickListener(new MyAutoCompleteItemClickListener());
        autoCompleteTextView.setAdapter(autocompleteAdapter);
    }

    public class MyAutoCompleteItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String clicked = ((MyAutoCompleteAdapter) adapterView.getAdapter()).getItem(position);
            ((TextView) findViewById(R.id.last_clicked)).setText(clicked);
        }
    }
}
