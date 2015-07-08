package com.enology.eip.e_nology.cave.scanner;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerFragment extends Fragment implements ZBarScannerView.ResultHandler
{
    private final static String DEBUG_TAG = "scanner";

    public static ScannerFragment newInstance() {
        return new ScannerFragment();
    }

    public ScannerFragment() {
        // Required empty public constructor
    }

    private ZBarScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZBarScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(), "Contents = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
        Log.v(DEBUG_TAG, rawResult.getContents()); // Prints scan results
        Log.v(DEBUG_TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        getActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
