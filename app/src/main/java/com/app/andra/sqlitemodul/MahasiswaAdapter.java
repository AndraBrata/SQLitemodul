package com.app.andra.sqlitemodul;


/**
 * Created by Komang Candra Brata on 10/2/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.andra.sqlitemodul.dbHelper.MahasiswaHelper;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.CustomViewHolder> {
    //private ArrayList<String> values;


    private LayoutInflater mInflater;
    private ArrayList<MahasiswaModel> mahasiswa;
    private Context context;
    private MahasiswaHelper mahasiswaHelper;


    public MahasiswaAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mahasiswaHelper = new MahasiswaHelper(context);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String nama = mahasiswa.get(position).getName();
        final String nim = mahasiswa.get(position).getNim();
        holder.editNama.setText(nama);
        holder.editNim.setText(nim);


        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mahasiswa.get(position).setName(holder.editNama.getText().toString());
                mahasiswa.get(position).setNim(holder.editNim.getText().toString());

                mahasiswaHelper.open();
                mahasiswaHelper.update(mahasiswa.get(position));
                mahasiswaHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(mahasiswa.get(position).getId());
                mahasiswa.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        mahasiswaHelper.open();
        mahasiswaHelper.delete(id);
        mahasiswaHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

    public void addItem(ArrayList<MahasiswaModel> mData) {
        this.mahasiswa = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editNama, editNim;
        private Button btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editNama = (EditText) itemView.findViewById(R.id.edit_nama);
            editNim = (EditText) itemView.findViewById(R.id.edit_nim);
            btnupdate = (Button) itemView.findViewById(R.id.btn_update);
            btndelete = (Button) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);


        }

    }


}