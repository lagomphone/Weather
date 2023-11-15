package cz.martykan.forecastie.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.DialogInterface;

import cz.martykan.forecastie.R;

public class AboutDialogFragment extends DialogFragment {
    private String readFullLicense() {
        try {
            InputStream is = getContext().getResources().openRawResource(R.raw.full_license);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getContext();

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(getText(R.string.app_name))
                .setMessage(TextUtils.concat(
                        getText(R.string.about_description), "\n\n",
                        getText(R.string.about_developers), "\n\n",
                        getText(R.string.about_src), "\n\n",
                        getText(R.string.about_issues), "\n\n",
                        getText(R.string.about_data), "\n\n",
                        getText(R.string.about_icons)))
                .setPositiveButton(R.string.dialog_ok, null)
                .setNeutralButton("Full GPLv3 License", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Show another dialog with the full license
                        new AlertDialog.Builder(context)
                                .setTitle("Full GPLv3 License")
                                .setMessage(readFullLicense())
                                .setPositiveButton(R.string.dialog_ok, null)
                                .show();
                    }
                })

                .create();

        alertDialog.show();

        TextView message = alertDialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setMovementMethod(LinkMovementMethod.getInstance());
        }
        return alertDialog;
    }


}
