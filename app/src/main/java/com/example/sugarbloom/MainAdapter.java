package com.example.sugarbloom;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter <MainModel,MainAdapter.myViewHolder>

{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull MainModel model) {
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.name.setText(model.getName());

        Glide.with(holder.img.getContext())
                .load(model.getTurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .centerCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);




        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

               // dialogPlus.show();

                View view1 = dialogPlus.getHolderView();

                EditText address=view.findViewById(R.id.txtAddress);
                EditText email=view.findViewById(R.id.txtEmail);
                EditText name=view.findViewById(R.id.txtName);
                EditText turl=view.findViewById(R.id.txtImageUrl);

                Button btnUpdate=view.findViewById(R.id.btnUpdate);

                address.setText(model.getAddress());
                email.setText(model.getEmail());
                name.setText(model.getName());
                turl.setText(model.getTurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("address",address.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("turl",turl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("delivery")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.address.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.address.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });


            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.address.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("delivery")
                                .child(getRef(position).getKey()).removeValue();




                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView address,email,name;

        Button  btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView)itemView.findViewById(R.id.img1);
            address=(TextView) itemView.findViewById(R.id.addresstext);
            email=(TextView) itemView.findViewById(R.id.emailtext);
            name=(TextView) itemView.findViewById(R.id.nametext);

            btnEdit=(Button) itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button) itemView.findViewById(R.id.btnDelete);

        }

    }
}
