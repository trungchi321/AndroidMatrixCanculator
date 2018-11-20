package com.whitedeveloper.matrix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.whitedeveloper.matrix.HidenKeyboard;
import com.whitedeveloper.matrix.ManagerMatrix;
import com.whitedeveloper.matrix.R;
import com.whitedeveloper.matrix.operationModules.DeterminantMatrix;

import static com.whitedeveloper.matrix.fragments.Tags.TAG_ID_MATRIX_A;

public class FragmentDeterminantMatrix extends Fragment implements AdapterView.OnItemSelectedListener, TextWatcher {
    private View view;

    private Spinner spSizeMatrix;

    private GridLayout glMatrix;

    private RelativeLayout rlResult;
    private TextView tvDet;

    private ManagerMatrix managerMatrix;

    private int dimensionMatrix;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_determinant_matrix, container, false);
        init();
        return view;
    }

    private void init() {
        spSizeMatrix = view.findViewById(R.id.sp_dimension_matrix);
        spSizeMatrix.setOnItemSelectedListener(this);
        glMatrix = view.findViewById(R.id.gl_matrix_determinant);

        spSizeMatrix.setOnItemSelectedListener(this);

        rlResult = view.findViewById(R.id.rl_result);
        tvDet = view.findViewById(R.id.tv_determinant);

        managerMatrix = new ManagerMatrix(getContext());

        final Button btnRun = view.findViewById(R.id.btn_run);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }
    private void calculate() {
            if (managerMatrix.allIsFill(glMatrix, TAG_ID_MATRIX_A, dimensionMatrix, dimensionMatrix)) {
                DeterminantMatrix determinantMatrix =
                        new DeterminantMatrix(managerMatrix.readMatrix(glMatrix,TAG_ID_MATRIX_A,dimensionMatrix,dimensionMatrix));

                showResult(determinantMatrix.countDeterminant());

            } else
                Toast.makeText(getContext(), getResources().getString(R.string.text_warming_fill_up_matrix), Toast.LENGTH_SHORT).show();
        }

    private void showResult(double det) {
        HidenKeyboard.hideKeyboardFrom(getContext(),view);
        tvDet.setVisibility(View.VISIBLE);
        tvDet.setText(getResources().getString(R.string.text_determinant).concat(String.valueOf(det)));
        rlResult.setVisibility(View.VISIBLE);
    }

    private void removeResult() {
        rlResult.setVisibility(View.INVISIBLE);
        tvDet.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        removeResult();
        dimensionMatrix = i + 2;
        HidenKeyboard.hideKeyboardFrom(getContext(),view);

        managerMatrix.generateMatrix(glMatrix, Tags.TAG_ID_MATRIX_A, dimensionMatrix, dimensionMatrix, this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (rlResult.getVisibility() == View.VISIBLE)
            removeResult();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}