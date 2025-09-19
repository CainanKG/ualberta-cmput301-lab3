package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * I used to ChatGPT and used the prompt - "How do make dialog fragment in android studio?"
 * 2025-09-18
 */

public class EditCityFragment extends DialogFragment {

    public interface Listener {
        void onCityEdited(int position, String newName, String newProvince);
    }

    private static final String ARG_POS  = "pos";
    private static final String ARG_NAME = "name";
    private static final String ARG_PROV = "prov";

    public static EditCityFragment newInstance(int position, String name, String prov) {
        Bundle b = new Bundle();
        b.putInt(ARG_POS, position);
        b.putString(ARG_NAME, name);
        b.putString(ARG_PROV, prov);
        EditCityFragment f = new EditCityFragment();
        f.setArguments(b);
        return f;
    }

    @NonNull @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View content = LayoutInflater.from(requireContext())
                .inflate(R.layout.city_edit, null);
        EditText etName = content.findViewById(R.id.etCityName);
        EditText etProv = content.findViewById(R.id.etProvince);

        Bundle args = requireArguments();
        final int position = args.getInt(ARG_POS);
        etName.setText(args.getString(ARG_NAME));
        etProv.setText(args.getString(ARG_PROV));

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Edit City")
                .setView(content)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", null)
                .create();

        dialog.setOnShowListener(d -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                String name = etName.getText().toString().trim();
                String prov = etProv.getText().toString().trim();
                if (name.isEmpty()) { etName.setError("Required"); return; }
                if (prov.isEmpty()) { etProv.setError("Required"); return; }

                ((Listener) requireActivity()).onCityEdited(position, name, prov);
                dialog.dismiss();
            });
        });

        return dialog;
    }
}

